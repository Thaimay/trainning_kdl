package jp.co.world.storedevelopment.batch.pushnotification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.batch.constant.DeviceType;
import jp.co.world.storedevelopment.batch.constant.SendStatus;
import jp.co.world.storedevelopment.model.InvalidDeviceToken;
import jp.co.world.storedevelopment.model.PushNotificationResult;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.mapper.repository.SendReserveRepository;
import jp.co.world.storedevelopment.utils.AndroidPushParam;
import jp.co.world.storedevelopment.utils.AndroidReturn;
import jp.co.world.storedevelopment.utils.AndroidReturnResults;
import jp.co.world.storedevelopment.utils.IosReturnError;
import jp.co.world.storedevelopment.utils.IosReturnResults;
import jp.co.world.storedevelopment.utils.PushNotificationHelper;
import jp.co.world.storedevelopment.utils.PushParam;

public class SendExecution {

	private static final String DATE_PATTERN = "yyyyMMddHHmmss";
    private static final String ENCODE_TYPE         = "UTF-8";
    private static final String backupDirectoryPath = "/home/wsdbatch/push_notification/backup";
    private static final String outputDirectoryPath = "/home/wsdbatch/push_notification/output";
    private static final String workDirectoryPath   = "/home/wsdbatch/push_notification/work";
    private static final String iosCertificateFileDirectory = "/home/wsdbatch/wsd.p12";
    private static final String pushSandbox = "";
    
    public static void main(String[] args) {
    	
        List<SendReserve> sendReserveList = new SendReserveRepository().getDeliveryReserved();
        
        for(SendReserve sendReserve : sendReserveList) {        
	        try {
	            //データ更新(SendDataUpdate)
	    		if (sendReserve != null) {
	    			updateSendReserve(sendReserve, SendStatus.DURING_DELIVERY);
	    		}
	            File workDirPath = new File(workDirectoryPath);
	            FileFilter fileFilter = new RegexFileFilter(sendReserve.getId() + "_03_.*");
	            File[] files = workDirPath.listFiles(fileFilter);
	            
	            for(File fileName : files) {
	            	// ファイル名よりデバイスタイプを取得
		            DeviceType deviceType = null;
		            {
		                String deviceTypeString = fileName.getName().split("_")[2];
		                if(DeviceType.IOS.toString().equals(deviceTypeString)) {
		                    deviceType = DeviceType.IOS;
		                } else if (DeviceType.ANDROID.toString().equals(deviceTypeString)) {
		                    deviceType = DeviceType.ANDROID;
		                }
		            }
		            if(sendReserve != null) {
		                backupSendEmployeeCdListFile(workDirectoryPath, backupDirectoryPath, fileName.getName(), sendReserve, deviceType);
		                moveSendEmployeeCdListFile(workDirectoryPath, outputDirectoryPath, fileName.getName(), sendReserve, deviceType);
		                List<String> employeeCdList = readSendEmployeeCdListFile(outputDirectoryPath, fileName.getName(), sendReserve, deviceType);

		                if (employeeCdList.size() == 0) {
		                    deleteSendEmployeeCdListFile(outputDirectoryPath, fileName.getName(), sendReserve, deviceType);
		                    return;
		                }
		                try {
		                    PushNotificationHelper pushNotificationHelper;
		                    {
		                        String gcpPushUrl = "https://fcm.googleapis.com/fcm/send";
		                        String androidKey = "AAAAQBanjX0:APA91bHv1SMpwDcNxWlY7qn5zJ398x39nV1BnZDH-LrgDH7KrzRlW43Hx7YUkwBxIrixTeg1JUlwVjc_a1WXF4vHse0gyR6qsIQhRGfyvnpg6pK2Riic_3b_Ar6V4NbVW3SsGyByW1SR";
		                        String iOsCertificateFilePath = iosCertificateFileDirectory;
		                        String iOsCertificateFilePass = "worldstoredevelopment";
		                        
		                        pushNotificationHelper = new PushNotificationHelper(gcpPushUrl, androidKey, iOsCertificateFilePath, iOsCertificateFilePass);
		                    }

		                    // プッシュ通知
		                    if (deviceType == DeviceType.IOS) {

		                        PushParam pushParam = new PushParam();
		                        // プッシュ通知基本設定
		                        basicSetting(pushParam, sendReserve.getMessage(), employeeCdList);
		                        pushParam.setTitle(sendReserve.getTitle());
		                        pushParam.setPushSandbox(pushSandbox);
		                        IosReturnResults iosReturnResults = pushNotificationHelper.iOsPush(pushParam);

		                        // プッシュ通知結果集計 登録
		                        insetNotificationResult(fileName.getName(), DeviceType.IOS.toString(), sendReserve.getId(), (long) employeeCdList.size(), (long) iosReturnResults.getFailure());
		                        
		                        // 無効デバイストークンを保存
		                        for (IosReturnError iosReturnError : iosReturnResults.getIosReturnError()) {
		                            insetInvalidDeviceToken(iosReturnError.getDeviceToken(), iosReturnError.getErrMessege(), DeviceType.IOS.toString(), sendReserve.getId());
		                        }
		
		                    } else if (deviceType == DeviceType.ANDROID) {

		                        AndroidPushParam pushParam = new AndroidPushParam();
		                        // プッシュ通知基本設定
		                        basicSetting(pushParam, sendReserve.getMessage(), employeeCdList);
		                        // タイトル
		                        pushParam.setTitle(sendReserve.getTitle());
		                        // メッセージの優先順位
		                        pushParam.setPriority("high");
		
		                        AndroidReturnResults androidReturnResults = pushNotificationHelper.androidPush(pushParam);// 通知不可の登録

		                        // 無効デバイストークンを保存
		                        for (AndroidReturn androidReturn : androidReturnResults.getResults()) {
		                            if (StringUtils.isEmpty(androidReturn.getMessage_id())) {
		                                insetInvalidDeviceToken(androidReturn.getRegistration_id().toString(), androidReturn.getError(), DeviceType.ANDROID.toString(), sendReserve.getId());
		                            }
		                        }
		
		                    }
		                    // 配信完了ステータスに更新
		                    updateSendReserve(sendReserve, SendStatus.DELIVERY_COMPLETION);
		                    /** 作業ディレクトリのファイルを削除します */
		                    deleteSendAppIdListFile(outputDirectoryPath, fileName.getName(), sendReserve, deviceType);
		                } catch (Exception e) {
		                    // iOS,Androidどちらかでもエラーが出れば配信エラーとして該当配信予約の処理を中断する
		    	            e.printStackTrace();
		    	            System.err.println(String.format("プッシュ通知の実行中にエラーが発生しました"));
		                    // カレントのファイルは削除する
		                    deleteSendAppIdListFile(outputDirectoryPath, fileName.getName(), sendReserve, deviceType);
		                    updateSendReserve(sendReserve, SendStatus.SEND_ERROR);
		                }
		            }
	            }
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	            System.err.println(String.format("プッシュ通知の実行中にエラーが発生しました"));
	        }
        }
    }


    private static void backupSendEmployeeCdListFile(String workDirectoryPath, String backupDirectoryPath, String fileName, SendReserve sendReserve, DeviceType deviceType) {
        try (InputStream inputStream = new FileInputStream(workDirectoryPath + File.separator + fileName)) {
            String backupFileName = String.format("%s_%s", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)), fileName);
            FileUtils.copyInputStreamToFile(inputStream, new File(backupDirectoryPath + File.separator + backupFileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(String.format("プッシュ通知の実行中にエラーが発生しました"));
        }
    }
    
    private static void moveSendEmployeeCdListFile(String workDirectoryPath, String outputDirectoryPath, String fileName, SendReserve sendReserve, DeviceType deviceType) {
        File workFile = null;
        File outputFile = null;
        
        try {
            workFile   = new File(workDirectoryPath,   fileName);
            outputFile = new File(outputDirectoryPath, fileName);
            FileUtils.moveFile(workFile, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(String.format("プッシュ通知の実行中にエラーが発生しました"));
        }
    }
    
    private static List<String> readSendEmployeeCdListFile(String outputDirectoryPath, String fileName, SendReserve sendReserve, DeviceType deviceType) {
        List<String> employeeCdList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(outputDirectoryPath + File.separator + fileName), ENCODE_TYPE))) {
        	for (String value; (value = reader.readLine()) != null;) {
            	employeeCdList.add(value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(String.format("プッシュ通知の実行中にエラーが発生しました"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(String.format("プッシュ通知の実行中にエラーが発生しました"));
        }
        return employeeCdList;
    }

    private static void deleteSendEmployeeCdListFile(String outputDirectoryPath, String fileName, SendReserve sendReserve, DeviceType deviceType) {
        try {
            FileUtils.forceDelete(new File(outputDirectoryPath, fileName));
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println(String.format("プッシュ通知の実行中にエラーが発生しました"));
        }
    }
    
    /** push通知内容を保存 */
    private static void basicSetting(PushParam pushParam, String message, List<String> employeeCdList) {
        pushParam.setMessage(message);
        pushParam.setTokenIdList(employeeCdList);
    }
    
    /** 通知結果を保存 */
    private static void insetNotificationResult(String targetFile, String deviceType, Long reserveId, Long sendPlanCount, Long sendFailCount) {
        String resultJson = "";
        insetNotificationResult(targetFile, deviceType, reserveId, sendPlanCount, sendFailCount, resultJson);

    }
    
    /** 通知結果を保存 */
    private static void insetNotificationResult(String targetFile, String deviceType, Long reserveId, Long sendPlanCount, Long sendFailCount, String resultJson) {
        PushNotificationResult pushNotificationResult = new PushNotificationResult();

        pushNotificationResult.setTargetFile(targetFile);
        pushNotificationResult.setDeviceType(deviceType);
        pushNotificationResult.setReserveId(reserveId);
        pushNotificationResult.setSendPlanCount(sendPlanCount);
        pushNotificationResult.setSendSuccessCount(sendPlanCount - sendFailCount);
        pushNotificationResult.setSendFailCount(sendFailCount);

        pushNotificationResult.setIsDeleted(false);
        pushNotificationResult.setCreatedAccountCode("batch");
        pushNotificationResult.setUpdateAccountCode("batch");
        pushNotificationResult.setCreatedDatetime(LocalDateTime.now());
        pushNotificationResult.setUpdateDatetime(LocalDateTime.now());

        pushNotificationResult.create();

    }
    
    /** 無効デバイストークンを保存 */
    private static void insetInvalidDeviceToken(String deviceToken, String reason, String deviceType, Long reserveId) {
        InvalidDeviceToken invalidDeviceToken = new InvalidDeviceToken();

        invalidDeviceToken.setDeviceToken(deviceToken);
        invalidDeviceToken.setReserveId(reserveId);
        invalidDeviceToken.setReason(reason);
        invalidDeviceToken.setDeviceType(deviceType);
        
        invalidDeviceToken.setScreeningFlag("0");
        invalidDeviceToken.setIsDeleted(false);                    
        invalidDeviceToken.setCreatedAccountCode("batch");         
        invalidDeviceToken.setUpdateAccountCode("batch");          
        invalidDeviceToken.setCreatedDatetime(LocalDateTime.now());
        invalidDeviceToken.setUpdateDatetime(LocalDateTime.now()); 

        invalidDeviceToken.create();
    }
    
    /** 作業ディレクトリのファイルを削除します */
    private static void deleteSendAppIdListFile(String direcotryPath, String fileName, SendReserve sendReserve, DeviceType deviceType) {
        try {
            FileUtils.forceDelete(new File(direcotryPath, fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(String.format("プッシュ通知の実行中にエラーが発生しました"));
        }
    }
    
    /** 配信開始情報を保存します */
    private static void updateSendReserve(SendReserve sendReserve, SendStatus sendStatus) {
        sendReserve.setSendStartTime(LocalDateTime.now());
        sendReserve.setSendStatus(sendStatus.toString());
        sendReserve.setUpdateAccountCode("batch");
        sendReserve.setUpdateDatetime(LocalDateTime.now());
        sendReserve.update();
    }
}

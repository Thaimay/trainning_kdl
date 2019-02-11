package jp.co.world.storedevelopment.batch.pushnotification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.batch.constant.DeviceType;
import jp.co.world.storedevelopment.batch.constant.LockFlag;
import jp.co.world.storedevelopment.batch.constant.SendStatus;
import jp.co.world.storedevelopment.model.PushNotificationPermit;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.mapper.repository.PushNotificationPermitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.SendReserveRepository;

/**
 * 配信対象のリストを抽出します
 * 
 * @author togashi
 *
 */
public class SendListExtraction {

    private static final String ENCODE_TYPE = "UTF-8";
    private static final String DEFAULT_FILE_COUNT = "00000";
    private static final String workDirectoryPath   = "/home/wsdbatch/push_notification/work";
    private static final String backupDirectoryPath = "/home/wsdbatch/push_notification/backup";    
    private static final String pushTargetFileMaxSize = "1000";
    
    public enum WriteType{
        IOS,
        ANDROID;
    }
    
    public static void main(String[] args) throws Exception {
        List<SendReserve> sendReserveList = new SendReserveRepository().getExtracted();
        for (SendReserve sendReserve : sendReserveList) {
            try {
                if (StringUtils.equals(sendReserve.getLockFlag(), LockFlag.LOCK.toString())) {
                    continue;
                }
                
                List<List<PushNotificationPermit>> employeeCdsIosFileUnit     = new ArrayList<>();
                List<List<PushNotificationPermit>> employeeCdsAndroidFileUnit = new ArrayList<>();
                {
                    List<String> employeeCdsIos     =readSendEmployeeCdsListFile(workDirectoryPath, sendReserve, DeviceType.IOS);
                    List<String> employeeCdsAndroid =readSendEmployeeCdsListFile(workDirectoryPath, sendReserve, DeviceType.ANDROID);
                    if (employeeCdsIos != null && employeeCdsIos.size() > 0) {
                        List<PushNotificationPermit> permitIosList = getPermit(employeeCdsIos);
                        employeeCdsIosFileUnit = listDevide(permitIosList, Integer.valueOf(pushTargetFileMaxSize));
                    }
                    if (employeeCdsAndroid != null && employeeCdsAndroid.size() > 0) {
                        List<PushNotificationPermit> permitAndroidList = getPermit(employeeCdsAndroid);
                        employeeCdsAndroidFileUnit = listDevide(permitAndroidList, Integer.valueOf(pushTargetFileMaxSize));
                    }
                }
                
                // 抽出結果が0件の場合、配信完了で終わらせる
                if ((employeeCdsIosFileUnit == null || employeeCdsIosFileUnit.size() == 0) & (employeeCdsAndroidFileUnit == null || employeeCdsAndroidFileUnit.size() == 0)) {
                    saveComplete(sendReserve);
                } else {
                    Integer outputCount = 0;
                    int fileCount = 0;
                    for (List<PushNotificationPermit> permitIosList : employeeCdsIosFileUnit) {
                        outputCount += writeSendDeviceTokenListFile(WriteType.IOS, workDirectoryPath, sendReserve, permitIosList, fileCount);
                        fileCount++;
                    }
                    fileCount = 0;
                    for (List<PushNotificationPermit> permitAndroidList : employeeCdsAndroidFileUnit) {
                        outputCount += writeSendDeviceTokenListFile(WriteType.ANDROID, workDirectoryPath, sendReserve, permitAndroidList, fileCount);
                        fileCount++;
                    }
                    saveExtractionResult(sendReserve, outputCount.longValue());
                }
                // 事前抽出ファイルを削除する
                deleteAdvanceExtractionFile(workDirectoryPath, sendReserve);
            } catch(Throwable e) {
                e.printStackTrace();
                System.err.println(String.format("配信リストファイルの抽出中にエラーが発生しました"));
            }
        }
    }

    /** 通知許可端末IDファイルからアプリIDを読み込みます */
    private static List<String> readSendEmployeeCdsListFile(String workDirectoryPath, SendReserve sendReserve, DeviceType deviceType) {
        String reserveId  = sendReserve.getId().toString();
        String sendStatus = SendStatus.EXTRACTED.toString();
        String fileName   = String.format("%s_%s_%s_%s.txt", reserveId, sendStatus, deviceType.toString(), DEFAULT_FILE_COUNT);
        String filePath   = workDirectoryPath + File.separator + fileName;
        
        List<String> employeeCds  = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), ENCODE_TYPE))) {
            for (String value; (value = reader.readLine()) != null;) {
            	employeeCds.add(value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(String.format("配信リストファイルの抽出中にエラーが発生しました"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(String.format("配信リストファイルの抽出中にエラーが発生しました"));
        }
        return employeeCds;
    }
    
    private static List<PushNotificationPermit> getPermit(List<String> employeeCds) {
        List<PushNotificationPermit> permitList = new ArrayList<>();
        List<PushNotificationPermit> permitListAll = new PushNotificationPermitRepository().getPushNotificationPermitList();
        Map<String, PushNotificationPermit> permitMap = new HashMap<>();
        for (PushNotificationPermit pushNotificationPermit : permitListAll) {
            permitMap.put(pushNotificationPermit.getEmployeeCd(), pushNotificationPermit);
        }
        
        for(String employeeCd : employeeCds) {
            PushNotificationPermit permit = permitMap.get(employeeCd);
            if (permit != null) {
                permitList.add(permit);
            }
        }
        return permitList;
    }

    private static List<List<PushNotificationPermit>> listDevide(List<PushNotificationPermit> permitList, int size) {
    	
        if (permitList == null || permitList.isEmpty() || size <= 0) {
            return Collections.emptyList();
        }

        int arrayLength = permitList.size() / size + (permitList.size() % size > 0 ? 1 :0);
        
        List<List<PushNotificationPermit>> devidedList = new ArrayList<>(arrayLength);
        for (int i=0; i < arrayLength; i++) {
            int start = i * size;
            int end   = Math.min(start + size, permitList.size());
            devidedList.add(new ArrayList<>(permitList.subList(start, end)));
        }
        return devidedList;
    }
    
    private static Integer writeSendDeviceTokenListFile(WriteType writeType, String workDirectoryPath, SendReserve sendReserve, List<PushNotificationPermit> pushNotificationPermitList, int fileCount) {
        Long reserveId = sendReserve.getId();
        
        String sendStatus = SendStatus.DELIVERY_RESERVED.toString();
        
        String fileName = "";
        
        if (writeType == WriteType.IOS) {
            fileName = String.format("%s_%s_%s_%s.txt", reserveId, sendStatus, DeviceType.IOS.toString(), String.format("%05d", fileCount));
        } else if (writeType == WriteType.ANDROID) {
            fileName = String.format("%s_%s_%s_%s.txt", reserveId, sendStatus, DeviceType.ANDROID.toString(), String.format("%05d", fileCount));
        }
        
        Integer outputCount = 0;
        
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(workDirectoryPath + File.separator + fileName), ENCODE_TYPE))) {
            for (PushNotificationPermit pushNotificationPermit : pushNotificationPermitList) {
                writer.write(pushNotificationPermit.getDeviceToken());
                writer.newLine();
                outputCount++;
            }
        } catch (FileNotFoundException e) {
            saveExtractionError(sendReserve);
            e.printStackTrace();
            System.err.println(String.format("配信リストファイルの抽出中にエラーが発生しました"));
        } catch (IOException e) {
            saveExtractionError(sendReserve);
            e.printStackTrace();
            System.err.println(String.format("配信リストファイルの抽出中にエラーが発生しました"));
        }
        
        return outputCount;
    }

    private static void deleteAdvanceExtractionFile(String workDirectoryPath, SendReserve sendReserve) {
        Long   reserveId       = sendReserve.getId();
        String sendStatus      = SendStatus.EXTRACTED.toString();
        String iosFileName     = String.format("%s_%s_%s_%s.txt", reserveId, sendStatus, DeviceType.IOS.toString(),     DEFAULT_FILE_COUNT);
        String androidFileName = String.format("%s_%s_%s_%s.txt", reserveId, sendStatus, DeviceType.ANDROID.toString(), DEFAULT_FILE_COUNT);
        try {
            FileUtils.forceDelete(new File(workDirectoryPath, iosFileName));
            FileUtils.forceDelete(new File(workDirectoryPath, androidFileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println(String.format("配信リストファイルの抽出中にエラーが発生しました"));
        }
    }
    
    /** 抽出結果を保存します */
    private static void saveExtractionResult(SendReserve sendReserve, Long sendPlanCount) {
        sendReserve.setSelectEndTime(LocalDateTime.now());
        sendReserve.setSendPlanCount(sendPlanCount);
        sendReserve.setSendStatus(SendStatus.DELIVERY_RESERVED.toString());
        sendReserve.setUpdateAccountCode("batch");
        sendReserve.setUpdateDatetime(LocalDateTime.now());
        sendReserve.update();
    }
    
    /** 配信完了で更新します */
    private static void saveComplete(SendReserve sendReserve) {
        sendReserve.setSelectEndTime(LocalDateTime.now());
        sendReserve.setSendPlanCount(0L);
        sendReserve.setSendStatus(SendStatus.DELIVERY_COMPLETION.toString());
        sendReserve.setIsDeleted(false);
        sendReserve.setUpdateAccountCode("batch");
        sendReserve.setUpdateDatetime(LocalDateTime.now());
        sendReserve.update();
    }
    
    /** 抽出エラーの結果を保存します */
    private static void saveExtractionError(SendReserve sendReserve) {
        sendReserve.setSendStatus(SendStatus.SELECT_ERROR.toString());
        sendReserve.setIsDeleted(false);
        sendReserve.setUpdateAccountCode("batch");
        sendReserve.setUpdateDatetime(LocalDateTime.now());
        sendReserve.update();
    }
}

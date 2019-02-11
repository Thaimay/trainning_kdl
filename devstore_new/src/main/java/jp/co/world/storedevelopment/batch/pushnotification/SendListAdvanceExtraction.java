package jp.co.world.storedevelopment.batch.pushnotification;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.batch.constant.DeviceType;
import jp.co.world.storedevelopment.batch.constant.SendStatus;
import jp.co.world.storedevelopment.model.PushNotificationPermit;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.mapper.repository.PushNotificationPermitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.SendReserveRepository;

/**
 * 配信対象のリストを事前抽出します
 * 
 * @author togashi
 *
 */
public class SendListAdvanceExtraction {

    private static final String ENCODE_TYPE         = "UTF-8";
    private static final String DEFAULT_FILE_COUNT  = "00000";
    
    private static final String workDirectoryPath   = "/home/wsdbatch/push_notification/work";
    private static final String backupDirectoryPath = "/home/wsdbatch/push_notification/backup";
    public static void main(String[] args) {
        List<SendReserve> sendReserves = new SendReserveRepository().findBySendReserve();

        for (SendReserve sendReserve : sendReserves) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.JAPANESE);
                
                // 配信開始時間の更新
                saveSelectStartTime(sendReserve);
                
                List<String> employeeCds = new ArrayList<>();
                String[] employeeCdList = sendReserve.getEmployeeCdList().split(",");

                for (String employeeCd : employeeCdList) {
                	employeeCds.add(employeeCd);
                }

                // Permitにあてて、配信可能端末を絞る
                List<PushNotificationPermit> permits = getPermit(employeeCds);
                Integer outputCount = writeSendEmployeeCdListFile(sendReserve, permits);
                
                saveExtractionResult(sendReserve, outputCount.longValue());
            } catch (Throwable e) {
                saveExtractionError(sendReserve);
                System.err.println(String.format("事前抽出ファイルの抽出中にエラーが発生しました"));
            }
        }
    }
    
    public static void saveSelectStartTime(SendReserve sendReserve) {
        sendReserve.setSelectStartTime(LocalDateTime.now());
        sendReserve.setUpdateAccountCode("batch");
        sendReserve.setUpdateDatetime(LocalDateTime.now());
        sendReserve.setIsDeleted(false);
        sendReserve.update();
    }
    
    private static List<PushNotificationPermit> getPermit(List<String> employeeCds){
        List<PushNotificationPermit> permits = new ArrayList<>();
        // 全件取得のemployeeCdsを渡すとPostgresのin句上限を超えるため
        List<PushNotificationPermit> permitAll = new PushNotificationPermitRepository().getPushNotificationPermitList();
        Map<String, PushNotificationPermit> permitMap = new HashMap<>();
        for (PushNotificationPermit pushNotificationPermit : permitAll) {
            permitMap.put(pushNotificationPermit.getEmployeeCd(), pushNotificationPermit);
        }
        for (String employeeCd : employeeCds) {
            PushNotificationPermit permit = permitMap.get(employeeCd);
            if (permit != null) {
            	permits.add(permit);
            }
        }
        return permits;
    }
    
    private static Integer writeSendEmployeeCdListFile(SendReserve sendReserve, List<PushNotificationPermit> permits) {
        String reserveId = sendReserve.getId().toString();
        
        String sendStatus = SendStatus.EXTRACTED.toString();
        
        String iosFileName     = String.format("%s_%s_%s_%s.txt", reserveId, sendStatus, DeviceType.IOS.toString(),     DEFAULT_FILE_COUNT);
        String androidFileName = String.format("%s_%s_%s_%s.txt", reserveId, sendStatus, DeviceType.ANDROID.toString(), DEFAULT_FILE_COUNT);
        
        Integer outputCount = 0;
        
        try (BufferedWriter iosWriter     = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(workDirectoryPath + File.separator + iosFileName),     ENCODE_TYPE));
             BufferedWriter androidWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(workDirectoryPath + File.separator + androidFileName), ENCODE_TYPE))){
            
            for (PushNotificationPermit permit : permits) {
                if (StringUtils.equals(permit.getDeviceType(), DeviceType.IOS.toString())) {
                    iosWriter.write(permit.getEmployeeCd().toString());
                    iosWriter.newLine();
                    outputCount++;
                } else if (StringUtils.equals(permit.getDeviceType(), DeviceType.ANDROID.toString())) {
                	androidWriter.write(permit.getEmployeeCd().toString());
                    androidWriter.newLine();
                    outputCount++;
                }
            }
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        // ファイルのバックアップ
        backupSendEmployeeCdListFile(workDirectoryPath, backupDirectoryPath, sendReserve, DeviceType.IOS.toString());
        backupSendEmployeeCdListFile(workDirectoryPath, backupDirectoryPath, sendReserve, DeviceType.ANDROID.toString());
        
        return outputCount;
    }

    /** 抽出結果を保存します */
    public static void backupSendEmployeeCdListFile(String workDirectoryPath, String backupDirectoryPath, SendReserve sendReserve, String deviceType) {

        String fileName = String.format("%s_%s_%s_%s.txt", sendReserve.getId().toString(), SendStatus.EXTRACTED.toString(), deviceType, DEFAULT_FILE_COUNT);
        
        try (InputStream inputStream = new FileInputStream(workDirectoryPath + File.separator + fileName)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.JAPANESE);
            String backupFileName = String.format("%s_%s", sdf.format(new Date()),fileName);
            FileUtils.copyInputStreamToFile(inputStream, new File(backupDirectoryPath + File.separator + backupFileName));
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    /** 抽出結果を保存します */
    public static void saveExtractionResult(SendReserve sendReserve, Long sendPlanCount) {
        sendReserve.setSelectEndTime(LocalDateTime.now());
        sendReserve.setSendPlanCount(sendPlanCount);
        sendReserve.setSendStatus(SendStatus.EXTRACTED.toString());
        sendReserve.setUpdateAccountCode("batch");
        sendReserve.setUpdateDatetime(LocalDateTime.now());
        sendReserve.setIsDeleted(false);
        sendReserve.update();
    }

    /** 抽出エラーの結果を保存します */
    public static void saveExtractionError(SendReserve sendReserve) {
        sendReserve.setSendStatus(SendStatus.SELECT_ERROR.toString());
        sendReserve.setUpdateAccountCode("batch");
        sendReserve.setUpdateDatetime(LocalDateTime.now());
        sendReserve.setIsDeleted(false);
        sendReserve.update();
    }
    
}

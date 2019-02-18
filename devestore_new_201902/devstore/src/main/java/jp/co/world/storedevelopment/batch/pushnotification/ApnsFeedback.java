package jp.co.world.storedevelopment.batch.pushnotification;

import java.time.LocalDateTime;
import java.util.List;

import jp.co.world.storedevelopment.batch.constant.DeviceType;
import jp.co.world.storedevelopment.model.InvalidDeviceToken;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.mapper.repository.SendReserveRepository;
import jp.co.world.storedevelopment.utils.IosReturnError;
import jp.co.world.storedevelopment.utils.PushNotificationHelper;

public class ApnsFeedback {
    
    private static final String iosCertificateFileDirectory = "/home/wsdbatch/wsd.p12";
	
    public static void main(String[] args) {
        try {
            List<SendReserve> sendReserveList = new SendReserveRepository().getDeliveryReserved();

            for (SendReserve sendReserve : sendReserveList) {
	            try {	
	                PushNotificationHelper pushNotificationHelper;
	                {
	                    String gcpPushUrl = "https://fcm.googleapis.com/fcm/send";
	                    String androidKey = "AAAAQBanjX0:APA91bHv1SMpwDcNxWlY7qn5zJ398x39nV1BnZDH-LrgDH7KrzRlW43Hx7YUkwBxIrixTeg1JUlwVjc_a1WXF4vHse0gyR6qsIQhRGfyvnpg6pK2Riic_3b_Ar6V4NbVW3SsGyByW1SR";
	                    String iOsCertificateFilePath = iosCertificateFileDirectory;
	                    String iOsCertificateFilePass = "worldstoredevelopment";
	
	                    pushNotificationHelper = new PushNotificationHelper(gcpPushUrl, androidKey, iOsCertificateFilePath, iOsCertificateFilePass);
	                }
	
	                List<IosReturnError> iosReturnErrorList = pushNotificationHelper.iOsfeedback();
	
	                for (IosReturnError iosReturnError : iosReturnErrorList) {
	                    insetInvalidDeviceToken(iosReturnError.getDeviceToken(), iosReturnError.getErrMessege(), DeviceType.IOS.toString(), sendReserve.getId());
	                }
	
	            } catch (Exception e) {
		            e.printStackTrace();
		            System.err.println(String.format("プッシュ通知(iOS)のフィードバック中にエラーが発生しました"));
	            }
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println(String.format("プッシュ通知(iOS)のフィードバック中にエラーが発生しました"));
        }
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
}

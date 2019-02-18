package jp.co.world.storedevelopment.batch.pushnotification;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.el.parser.ParseException;

import jp.co.world.storedevelopment.batch.constant.DeviceType;
import jp.co.world.storedevelopment.batch.constant.SendStatus;
import jp.co.world.storedevelopment.model.InvalidDeviceToken;
import jp.co.world.storedevelopment.model.PushNotificationPermit;
import jp.co.world.storedevelopment.model.PushNotificationResult;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.mapper.repository.InvalidDeviceTokenRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PushNotificationPermitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PushNotificationResultRepository;
import jp.co.world.storedevelopment.model.mapper.repository.SendReserveRepository;

public class SendResult {

	public static void main(String[] args) {
		List<SendReserve> sendReserveList = new SendReserveRepository().getDeliveryReserved();
		for(SendReserve sendReserve : sendReserveList) {
			try {
				long androidSendSuccessCount = 0;
				long androidSendFailCount = 0;
				long iosSendSuccessCount = 0;
				long iosSendFailCount = 0;
				{
					// プッシュ通知結果集計の取得
					List<PushNotificationResult> pushNotificationResultList = new PushNotificationResultRepository()
							.findByReserveId(sendReserve.getId());
					for (PushNotificationResult pushNotificationResult : pushNotificationResultList) {
						if (DeviceType.ANDROID.toString().equals(pushNotificationResult.getDeviceType())) {
							androidSendSuccessCount += pushNotificationResult.getSendSuccessCount();
							androidSendFailCount += pushNotificationResult.getSendFailCount();
						} else if (DeviceType.IOS.toString().equals(pushNotificationResult.getDeviceType())) {
							iosSendSuccessCount += pushNotificationResult.getSendSuccessCount();
							iosSendFailCount += pushNotificationResult.getSendFailCount();
						}
	
					}
	
					// 無効デバイストークン APNSからのフィードバック
					/*
					 * List<TrnInvalidDeviceToken> trnInvalidDeviceToken =
					 * notificationRepository.searchInvalidDeviceTokenIosOnly(sendReserve.
					 * getReserveId()); if (null != trnInvalidDeviceToken &&
					 * trnInvalidDeviceToken.size() != 0) { iosSendSuccessCount -=
					 * trnInvalidDeviceToken.size(); iosSendFailCount +=
					 * trnInvalidDeviceToken.size(); }
					 */
				}
	
				// スクリーニング
				List<InvalidDeviceToken> invalidDeviceTokenScreeningTargetList = new InvalidDeviceTokenRepository()
						.getScreeningTargetList();
				for (InvalidDeviceToken invalidDeviceTokenScreening : invalidDeviceTokenScreeningTargetList) {
	
					String targetDeviceToken = invalidDeviceTokenScreening.getDeviceToken();
	
					// iOSの場合は小文字に変換する
					if (DeviceType.IOS.toString().equals(invalidDeviceTokenScreening.getDeviceType())) {
						targetDeviceToken = targetDeviceToken.toLowerCase();
					}
	
					List<PushNotificationPermit> pushNotificationPermitList = new PushNotificationPermitRepository()
							.findByDeviceToken(targetDeviceToken);
	
					for (PushNotificationPermit pushNotificationPermit : pushNotificationPermitList) {
						pushNotificationPermit.setIsDeleted(true);
						pushNotificationPermit.setUpdateAccountCode("batch");
						pushNotificationPermit.setUpdateDatetime(LocalDateTime.now());
						pushNotificationPermit.update();
					}
	
					invalidDeviceTokenScreening.setScreeningFlag("1");
					invalidDeviceTokenScreening.update();
	
				}
	
				Date SendStartTime = new Date();
				updateSendFinish(sendReserve, SendStartTime, androidSendSuccessCount, androidSendFailCount,
						iosSendSuccessCount, iosSendFailCount);
	
			} catch (Exception e) {
				// 配信エラー
				updateSendNotFinish(sendReserve);
			}
		}
	}

	/** 配信予約を配信完了に更新します */
	private static void updateSendFinish(SendReserve sendReserve, Date SendStartTime, long androidSendSuccessCount,
			long androidSendFailCount, long iosSendSuccessCount, long iosSendFailCount) throws ParseException {
		// ios/android個別の処理結果
		// sendReserve.setAndroidSendSuccessCount(androidSendSuccessCount);
		// sendReserve.setAndroidSendFailCount(androidSendFailCount);
		// sendReserve.setAndroidSendResultCount(androidSendSuccessCount +
		// androidSendFailCount);
		// sendReserve.setIosSendSuccessCount(iosSendSuccessCount);
		// sendReserve.setIosSendFailCount(iosSendFailCount);
		// sendReserve.setIosSendResultCount(iosSendSuccessCount + iosSendFailCount);
		//
		// // 合算データ
		// sendReserve.setSendSuccessCount(nullToZero(sendReserve.getIosSendSuccessCount())
		// + nullToZero(sendReserve.getAndroidSendSuccessCount()));
		// sendReserve.setSendFailCount(nullToZero(sendReserve.getIosSendFailCount()) +
		// nullToZero(sendReserve.getAndroidSendFailCount()));
		// // 成功数と同じにする
		// sendReserve.setSendResultCount(nullToZero(sendReserve.getIosSendSuccessCount())
		// + nullToZero(sendReserve.getAndroidSendSuccessCount()));
		//
		// // 開始日時は早いほう、終了日時は遅いほう
		// sendReserve.setSendStartTime(SendStartTime);
		// sendReserve.setSendEndTime(new Date());

		// 上書きでよいデータ
		sendReserve.setSendStatus(SendStatus.DELIVERY_COMPLETION.toString());
		sendReserve.setIsDeleted(false);
		sendReserve.setUpdateAccountCode("batch");
		sendReserve.setUpdateDatetime(LocalDateTime.now());

		sendReserve.update();
	}

	/** 配信予約を配信エラーに更新します */
	private static void updateSendNotFinish(SendReserve sendReserve) {

		sendReserve.setSendStatus(SendStatus.SEND_ERROR.toString());
		sendReserve.setIsDeleted(false);
		sendReserve.setUpdateAccountCode("batch");
		sendReserve.setUpdateDatetime(LocalDateTime.now());

		sendReserve.update();
	}

	private static Long nullToZero(Long src) {
		if (src == null) {
			return 0L;
		}
		return src;
	}
}

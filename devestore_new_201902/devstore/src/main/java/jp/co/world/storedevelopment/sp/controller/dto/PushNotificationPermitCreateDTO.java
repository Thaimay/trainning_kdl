package jp.co.world.storedevelopment.sp.controller.dto;

public class PushNotificationPermitCreateDTO {
	private String deviceToken;
	private String deviceType;

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}

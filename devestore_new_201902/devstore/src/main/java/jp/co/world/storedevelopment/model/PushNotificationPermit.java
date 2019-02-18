package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.PushNotificationPermitModelMapper;

public class PushNotificationPermit extends ActiveModel<PushNotificationPermit> {

	private String employeeCd = "";
	private String deviceToken = "";
	private String deviceType = "";

	public PushNotificationPermit(String employeeCd, String deviceToken, String deviceType) {
		this.employeeCd = employeeCd;
		this.deviceToken = deviceToken;
		this.deviceType = deviceType;
	}

	public PushNotificationPermit() {
	}

	@Override
	protected ModelMapper<PushNotificationPermit> modelMapper(SqlSession session) {
		return session.getMapper(PushNotificationPermitModelMapper.class);
	}

	/**
	 * 
	 * @return
	 */
	public String getEmployeeCd() {
		return employeeCd;
	}

	/**
	 * 
	 * @param employeeCd
	 */
	public void setEmployeeCd(String employeeCd) {
		this.employeeCd = employeeCd;
	}

	/**
	 * 
	 * @return
	 */
	public String getDeviceToken() {
		return deviceToken;
	}

	/**
	 * 
	 * @param deviceToken
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	/**
	 * 
	 * @return
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 
	 * @param deviceType
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}

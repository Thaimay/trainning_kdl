package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.InvalidDeviceTokenModelMapper;

public class InvalidDeviceToken extends ActiveModel<InvalidDeviceToken> {

	private String deviceToken = "";
	private Long reserveId = (long) 0;
	private String reason = "";
	private String deviceType = "";
	private String screeningFlag = "";

	@Override
	protected ModelMapper<InvalidDeviceToken> modelMapper(SqlSession session) {
		return session.getMapper(InvalidDeviceTokenModelMapper.class);
	}

	public InvalidDeviceToken() {

	}

	public InvalidDeviceToken(String deviceToken, Long reserveId, String reason, String deviceType,
			String screeningFlag) {
		this.deviceToken = deviceToken;
		this.reserveId = reserveId;
		this.reason = reason;
		this.deviceType = deviceType;
		this.screeningFlag = screeningFlag;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public Long getReserveId() {
		return reserveId;
	}

	public void setReserveId(Long reserveId) {
		this.reserveId = reserveId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getScreeningFlag() {
		return screeningFlag;
	}

	public void setScreeningFlag(String screeningFlag) {
		this.screeningFlag = screeningFlag;
	}

}

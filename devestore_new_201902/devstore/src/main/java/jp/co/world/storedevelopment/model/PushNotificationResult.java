package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.PushNotificationResultModelMapper;

public class PushNotificationResult extends ActiveModel<PushNotificationResult> {

	private String targetFile = "";
	private String deviceType = "";
	private Long reserveId = (long) 0;
	private Long sendPlanCount = (long) 0;
	private Long sendSuccessCount = (long) 0;
	private Long sendFailCount = (long) 0;

	@Override
	protected ModelMapper<PushNotificationResult> modelMapper(SqlSession session) {
		return session.getMapper(PushNotificationResultModelMapper.class);
	}

	public PushNotificationResult(String targetFile, String deviceType, Long reserveId, Long sendPlanCount,
			Long sendSuccessCount, Long sendFailCount) {
		this.targetFile = targetFile;
		this.deviceType = deviceType;
		this.reserveId = reserveId;
		this.sendPlanCount = sendPlanCount;
		this.sendSuccessCount = sendSuccessCount;
		this.sendFailCount = sendFailCount;
	}

	public PushNotificationResult() {

	}

	/**
	 * 
	 * @return
	 */
	public String getTargetFile() {
		return targetFile;
	}

	/**
	 * 
	 * @param targetFile
	 */
	public void setTargetFile(String targetFile) {
		this.targetFile = targetFile;
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

	/**
	 * 
	 * @return
	 */
	public Long getReserveId() {
		return reserveId;
	}

	/**
	 * 
	 * @param reserveId
	 */
	public void setReserveId(Long reserveId) {
		this.reserveId = reserveId;
	}

	/**
	 * 
	 * @return
	 */
	public Long getSendPlanCount() {
		return sendPlanCount;
	}

	/**
	 * 
	 * @param sendPlanCount
	 */
	public void setSendPlanCount(Long sendPlanCount) {
		this.sendPlanCount = sendPlanCount;
	}

	/**
	 * 
	 * @return
	 */
	public Long getSendSuccessCount() {
		return sendSuccessCount;
	}

	/**
	 * 
	 * @param sendSuccessCount
	 */
	public void setSendSuccessCount(Long sendSuccessCount) {
		this.sendSuccessCount = sendSuccessCount;
	}

	/**
	 * 
	 * @return
	 */
	public Long getSendFailCount() {
		return sendFailCount;
	}

	/**
	 * 
	 * @param sendFailCount
	 */
	public void setSendFailCount(Long sendFailCount) {
		this.sendFailCount = sendFailCount;
	}
}

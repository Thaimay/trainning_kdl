package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.SendReserveModelMapper;

public class SendReserve extends ActiveModel<SendReserve> {

	private String employeeCdList = "";
	private String title = "";
	private String message = "";
	private String jsonData = "";
	private Long badge = null;
	private String sendPlanDate = "";
	private String sendPlanTime = "";
	private String sendReserveDate = "";
	private String sendReserveTime = "";
	private LocalDateTime selectStartTime = null;
	private LocalDateTime selectEndTime = null;
	private Long sendPlanCount = null;
	private LocalDateTime sendStartTime = null;
	private LocalDateTime sendEndTime = null;
	private String sendResultCount = "";
	private String sendSuccessCount = "";
	private String sendFailCount = "";
	private String iosSendStartTime = "";
	private String iosSendEndTime = "";
	private String iosSendResultCount = "";
	private String iosSendSuccessCount = "";
	private String iosSendFailCount = "";
	private String androidSendStartTime = "";
	private String androidSendEndTime = "";
	private String androidSendResultCount = "";
	private String androidSendSuccessCount = "";
	private String androidSendFailCount = "";
	private String sendStatus = "";
	private String iosQueueKey = "";
	private String andoridQueueKey = "";
	private String lockFlag = "";
	private String uploadFileName = "";
	private String deliveryFile = "";
	private String deviceIdCount = "";

	private static DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH");
	private static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH");

	@Override
	protected ModelMapper<SendReserve> modelMapper(SqlSession session) {
		return session.getMapper(SendReserveModelMapper.class);
	}

	public SendReserve() {
		setSendStatus("01");
	}

	public void setEmployeeCodeListValue(List<Account> accounts) {
		List<String> list = accounts.stream().map(a -> {
			return a.getEmployeeCd();
		}).collect(Collectors.toList());
		setEmployeeCdList(String.join(",", list));
	}

	public SendReserve(String employeeCdList, String message) {
		this.setEmployeeCdList(employeeCdList);
		this.setMessage(message);
	}

	public void setInitializeStatus() {
		setSendStatus("01");
	}

	public String getEmployeeCdList() {
		return employeeCdList;
	}

	public void setEmployeeCdList(String employeeCdList) {
		this.employeeCdList = employeeCdList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public Long getBadge() {
		return badge;
	}

	public void setBadge(Long badge) {
		this.badge = badge;
	}

	public String getSendPlanDate() {
		return sendPlanDate;
	}

	public void setSendPlanDate(String sendPlanDate) {
		this.sendPlanDate = sendPlanDate;
	}

	public String getSendPlanTime() {
		return sendPlanTime;
	}

	public void setSendPlanTime(String sendPlanTime) {
		this.sendPlanTime = sendPlanTime;
	}

	public String getSendReserveDate() {
		return sendReserveDate;
	}

	public void setSendReserveDate(String sendReserveDate) {
		this.sendReserveDate = sendReserveDate;
	}

	public String getSendReserveTime() {
		return sendReserveTime;
	}

	public void setSendReserveTime(String sendReserveTime) {
		this.sendReserveTime = sendReserveTime;
	}

	public LocalDateTime getSelectStartTime() {
		return selectStartTime;
	}

	public void setSelectStartTime(LocalDateTime selectStartTime) {
		this.selectStartTime = selectStartTime;
	}

	public LocalDateTime getSelectEndTime() {
		return selectEndTime;
	}

	public void setSelectEndTime(LocalDateTime selectEndTime) {
		this.selectEndTime = selectEndTime;
	}

	public Long getSendPlanCount() {
		return sendPlanCount;
	}

	public void setSendPlanCount(Long sendPlanCount) {
		this.sendPlanCount = sendPlanCount;
	}

	public LocalDateTime getSendStartTime() {
		return sendStartTime;
	}

	public void setSendStartTime(LocalDateTime sendStartTime) {
		this.sendStartTime = sendStartTime;
	}

	public LocalDateTime getSendEndTime() {
		return sendEndTime;
	}

	public void setSendEndTime(LocalDateTime sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

	public String getSendResultCount() {
		return sendResultCount;
	}

	public void setSendResultCount(String sendResultCount) {
		this.sendResultCount = sendResultCount;
	}

	public String getSendSuccessCount() {
		return sendSuccessCount;
	}

	public void setSendSuccessCount(String sendSuccessCount) {
		this.sendSuccessCount = sendSuccessCount;
	}

	public String getSendFailCount() {
		return sendFailCount;
	}

	public void setSendFailCount(String sendFailCount) {
		this.sendFailCount = sendFailCount;
	}

	public String getIosSendStartTime() {
		return iosSendStartTime;
	}

	public void setIosSendStartTime(String iosSendStartTime) {
		this.iosSendStartTime = iosSendStartTime;
	}

	public String getIosSendEndTime() {
		return iosSendEndTime;
	}

	public void setIosSendEndTime(String iosSendEndTime) {
		this.iosSendEndTime = iosSendEndTime;
	}

	public String getIosSendResultCount() {
		return iosSendResultCount;
	}

	public void setIosSendResultCount(String iosSendResultCount) {
		this.iosSendResultCount = iosSendResultCount;
	}

	public String getIosSendSuccessCount() {
		return iosSendSuccessCount;
	}

	public void setIosSendSuccessCount(String iosSendSuccessCount) {
		this.iosSendSuccessCount = iosSendSuccessCount;
	}

	public String getIosSendFailCount() {
		return iosSendFailCount;
	}

	public void setIosSendFailCount(String iosSendFailCount) {
		this.iosSendFailCount = iosSendFailCount;
	}

	public String getAndroidSendStartTime() {
		return androidSendStartTime;
	}

	public void setAndroidSendStartTime(String androidSendStartTime) {
		this.androidSendStartTime = androidSendStartTime;
	}

	public String getAndroidSendEndTime() {
		return androidSendEndTime;
	}

	public void setAndroidSendEndTime(String androidSendEndTime) {
		this.androidSendEndTime = androidSendEndTime;
	}

	public String getAndroidSendResultCount() {
		return androidSendResultCount;
	}

	public void setAndroidSendResultCount(String androidSendResultCount) {
		this.androidSendResultCount = androidSendResultCount;
	}

	public String getAndroidSendSuccessCount() {
		return androidSendSuccessCount;
	}

	public void setAndroidSendSuccessCount(String androidSendSuccessCount) {
		this.androidSendSuccessCount = androidSendSuccessCount;
	}

	public String getAndroidSendFailCount() {
		return androidSendFailCount;
	}

	public void setAndroidSendFailCount(String androidSendFailCount) {
		this.androidSendFailCount = androidSendFailCount;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getIosQueueKey() {
		return iosQueueKey;
	}

	public void setIosQueueKey(String iosQueueKey) {
		this.iosQueueKey = iosQueueKey;
	}

	public String getAndoridQueueKey() {
		return andoridQueueKey;
	}

	public void setAndoridQueueKey(String andoridQueueKey) {
		this.andoridQueueKey = andoridQueueKey;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDeliveryFile() {
		return deliveryFile;
	}

	public void setDeliveryFile(String deliveryFile) {
		this.deliveryFile = deliveryFile;
	}

	public String getDeviceIdCount() {
		return deviceIdCount;
	}

	public void setDeviceIdCount(String deviceIdCount) {
		this.deviceIdCount = deviceIdCount;
	}

}

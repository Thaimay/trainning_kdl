package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationOpenAccount;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class NegotiationListDTO extends NegotiationDetailDTOSupport {

	private String status;

	private String statusLabel;

	private String title;

	private String division;

	private List<String> buildingNames = new ArrayList<String>();

	private String projectName;

	private String implemetentionDate;

	private String scheduleDate;

	private LocalDateTime scheduleStartDatetime = null;
	private LocalDateTime scheduleEndDatetime = null;
	private LocalDateTime implementationStartDatetime = null;
	private LocalDateTime implementationEndDatetime = null;

	private String place;

	private String content;
	
	private String purpose;

	private String businessCardFree;

	private String interviewAccountFree;

	private String releaseLevelValue;

	private Boolean hasFile;

	private Boolean isReadLater;

	private Boolean isOpened;
	private Boolean opened;

	private Boolean isStop;

	private Boolean isDraft;

	private int priority;

	private int numberOfComment;

	private CreateAccountDTO account = new CreateAccountDTO();

	private List<String> openAccounts = new ArrayList<>();

	private LocalDateTime updateDatetime;

	private static DateTimeFormatter START_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	private static DateTimeFormatter END_DATE_FORMAT = DateTimeFormatter.ofPattern("-HH:mm");
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

	public static List<NegotiationListDTO> toList(List<Negotiation> list, Account account) {
		return list.stream().filter(n -> (n.getIsDeleted() == false)).map(n -> {
			return new NegotiationListDTO(n, account);
		}).collect(Collectors.toList());
	}

	public NegotiationListDTO() {
		super();
	}

	public NegotiationListDTO(Negotiation n, Account a) {
		super(n);
		Account createAccount = new AccountRepository().findByCode(n.getUpdateAccountCode()).orElseGet(() -> {
			throw new IllegalStateException("存在しないアカウントです:" + n.getUpdateAccountCode());
		});
		setId(n.getId());
		setStatus(n.getStatusValue());
		setDivision(n.getDivisionValue());
		setTitle(n.getTitle());
		setPurpose(n.getPurpose());
		setBuildingNames(n.getBuildingNames());
		setPlace(n.getPlace());
		setProjectName("");
		setImplemetentionDate(n.getImplementationDateValue());
		setScheduleDate(n.getScheduleDateValue());
		setHasFile(n.hasFile());
		setIsReadLater(n.getIsLater(a));
		setIsOpened(n.getIsOpend(a));
		setNumberOfComment(n.getNumberOfComments());
		setIsStop(n.isStop());
		setIsDraft(n.isDraft());
		setAccount(new CreateAccountDTO(createAccount));
		setPriority(n.getPriority());
		setBusinessCardFree(n.getBusinessCardFree());
		setInterviewAccountFree(n.getInterviewAccountFree());
		setReleaseLevelValue(n.getReleaseLevelValue());
		setStatusLabel(n.getStatusLabel());
		setOpenAccounts(n.getOpenAccounts());
		setUpdateDatetime(n.getUpdateDatetime());
		if (n.getImplementationStartDatetime() == null) {
			setContent(n.getPurpose());
		} else {
			setContent(n.getContent());
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getBuildingNames() {
		return buildingNames;
	}

	public void setBuildingNames(List<String> buildingName) {
		buildingNames = buildingName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getImplemetentionDate() {
		return implemetentionDate;
	}

	public void setImplemetentionDate(String implemetentionDate) {
		this.implemetentionDate = implemetentionDate;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String price) {
		place = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getHasFile() {
		return hasFile;
	}

	public void setHasFile(Boolean hasFile) {
		this.hasFile = hasFile;
	}

	public Boolean getIsReadLater() {
		return isReadLater;
	}

	public void setIsReadLater(Boolean isReadLater) {
		this.isReadLater = isReadLater;
	}

	public int getNumberOfComment() {
		return numberOfComment;
	}

	public void setNumberOfComment(int numberOfCooment) {
		numberOfComment = numberOfCooment;
	}

	public Boolean getIsOpened() {
		return isOpened;
	}

	public void setIsOpened(Boolean isOpened) {
		this.isOpened = isOpened;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Boolean getIsStop() {
		return isStop;
	}

	public void setIsStop(Boolean isStop) {
		this.isStop = isStop;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	public LocalDateTime getScheduleStartDatetime() {
		return scheduleStartDatetime;
	}

	public void setScheduleStartDatetime(LocalDateTime scheduleStartDatetime) {
		this.scheduleStartDatetime = scheduleStartDatetime;
	}

	public LocalDateTime getScheduleEndDatetime() {
		return scheduleEndDatetime;
	}

	public void setScheduleEndDatetime(LocalDateTime scheduleEndDatetime) {
		this.scheduleEndDatetime = scheduleEndDatetime;
	}

	public LocalDateTime getImplementationStartDatetime() {
		return implementationStartDatetime;
	}

	public void setImplementationStartDatetime(LocalDateTime implementationStartDatetime) {
		this.implementationStartDatetime = implementationStartDatetime;
	}

	public LocalDateTime getImplementationEndDatetime() {
		return implementationEndDatetime;
	}

	public void setImplementationEndDatetime(LocalDateTime implementationEndDatetime) {
		this.implementationEndDatetime = implementationEndDatetime;
	}

	public CreateAccountDTO getAccount() {
		return account;
	}

	public void setAccount(CreateAccountDTO account) {
		this.account = account;
	}

	public Boolean getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Boolean isDraft) {
		this.isDraft = isDraft;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getBusinessCardFree() {
		return businessCardFree;
	}

	public void setBusinessCardFree(String businessCardFree) {
		this.businessCardFree = businessCardFree;
	}

	public String getInterviewAccountFree() {
		return interviewAccountFree;
	}

	public void setInterviewAccountFree(String interviewAccountFree) {
		this.interviewAccountFree = interviewAccountFree;
	}

	public String getReleaseLevelValue() {
		return releaseLevelValue;
	}

	public void setReleaseLevelValue(String releaseLevelValue) {
		this.releaseLevelValue = releaseLevelValue;
	}

	public String getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}

	public List<String> getOpenAccounts() {
		return openAccounts;
	}

	public void setOpenAccounts(List<String> openAccounts) {
		this.openAccounts = openAccounts;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
}

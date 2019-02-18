package jp.co.world.storedevelopment.pc.controller.view.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;
import jp.co.world.storedevelopment.model.NegotiationInterviewBrand;
import jp.co.world.storedevelopment.model.NegotiationInterviewBuilding;
import jp.co.world.storedevelopment.model.NegotiationInterviewBusinessCard;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationOpenAccountRepository;
import jp.co.world.storedevelopment.pc.controller.dto.InterviewCorporationDTO;

public class NegotiationViewModel {

	private static DateTimeFormatter START_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	private static DateTimeFormatter END_DATE_FORMAT = DateTimeFormatter.ofPattern("-HH:mm");
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
	private static int trimContentIndex = 15;
	private static int trimNextActionIndex = 15;

	private List<String> noticeAccountNames;
	private Account account;
	private List<InterviewCorporationDTO> negotiationInterviewCorporations = Arrays.asList();

	private Negotiation negotiation;

	private Long id;
	private String title;
	private String division;
	private LocalDateTime scheduleStartDatetime;
	private LocalDateTime scheduleEndDatetime;
	private LocalDateTime implementationStartDatetime;
	private LocalDateTime implementationEndDatetime;
	private Integer priority = 0;
	private Boolean release = false;
	private String releaseLevel = "";
	private String releaseLevelValue = "";
	private String nextActionContent = "";
	private String content = "";
	private String place = "";
	private String businessCardFree = "";
	private String interviewAccountFree = "";
	private Boolean opened;
	private Boolean draft;
	private String purpose;
	private int openedAccountSize;
	private List<String> openAccountList;
	private LocalDateTime updateDatetime = LocalDateTime.now();

	private List<NegotiationInterviewAccount> interviewAccounts = new ArrayList<>();

	private List<NegotiationInterviewBusinessCard> interviewBussinessCards = new ArrayList<>();

	private List<NegotiationInterviewBuilding> interviewBuildings = new ArrayList<>();

	private List<NegotiationInterviewBrand> interviewBrands = new ArrayList<>();

	private Boolean stop;

	public static List<NegotiationViewModel> toList(List<Negotiation> list, Account account) {
		return list.stream().filter(n -> (n.getIsDeleted() == false)).map(n -> {
			NegotiationViewModel model = new NegotiationViewModel(n, account);
			return model;
		}).collect(Collectors.toList());
	}

	public NegotiationViewModel(Negotiation negotiation, Account account) {
		List<String> list = negotiation.getNoticeAccounts().stream().map(n -> {
			return n.getAccountName();
		}).collect(Collectors.toList());
		this.account = account;
		setId(id);
		setNoticeAccountNames(list);
		setNegotiationInterviewCorporations(negotiation);
		this.negotiation = negotiation;
		setId(negotiation.getId());
		setTitle(negotiation.getTitle());
		setInterviewAccounts(negotiation.getInterviewAccounts());
		setInterviewBussinessCards(negotiation.getInterviewBussinessCards());
		setInterviewBuildings(negotiation.getInterviewBuildings());
		setInterviewBrands(negotiation.getInterviewBrands());
		setPriority(negotiation.getPriority());
		setPlace(negotiation.getPlace());
		setReleaseLevelValue(negotiation.getReleaseLevelValue());
		setNextActionContent(negotiation.getNextActionContent());
		setContent(negotiation.getContent());
		setBusinessCardFree(negotiation.getBusinessCardFree());
		setInterviewAccountFree(negotiation.getInterviewAccountFree());
		setOpened(negotiation.getIsOpend(account));
		setStop(negotiation.isStop());
		setDraft(negotiation.isDraft());
		setDivision(negotiation.getDivision());
		setScheduleStartDatetime(negotiation.getScheduleStartDatetime());
		setScheduleEndDatetime(negotiation.getScheduleEndDatetime());
		setImplementationStartDatetime(negotiation.getImplementationStartDatetime());
		setImplementationEndDatetime(negotiation.getImplementationEndDatetime());
		setPurpose(negotiation.getPurpose());
		setOpenedAccountSize(negotiation.getOpenAccounts().size());
		setOpenAccountList(negotiation.getOpenAccounts());
		setUpdateDatetime(negotiation.getUpdateDatetime());
	}

	private static NegotiationViewModel copyProperties(NegotiationViewModel model, Negotiation negotiation) {
		try {
			BeanUtils.copyProperties(model, negotiation);
		} catch (Exception e) {
			throw new IllegalStateException("オブジェクトのコピーに失敗しました" + e.getMessage());
		}
		return model;
	}

	public String getTrimContent() {
		String content = negotiation.getContent();
		if (content.length() >= trimContentIndex) {
			return content.substring(0, trimNextActionIndex) + "...";
		} else {
			return content;
		}
	}

	public String getTrimNextActionContent() {
		String content = negotiation.getNextActionContent();
		if (content.length() >= trimNextActionIndex) {
			return content.substring(0, trimNextActionIndex) + "...";
		} else {
			return content;
		}
	}

	public boolean IsReadLater() {
		return negotiation.getIsLater(account);
	}

	private String getTime(LocalDateTime start, LocalDateTime end) {
		if (start != null && end != null) {
			String startTime = TIME_FORMAT.format(start);
			String endTime = TIME_FORMAT.format(end);
			return startTime + " - " + endTime;
		} else {
			return "";
		}
	}

	private String formatDate(LocalDateTime date) {
		if (date != null) {
			return DATE_FORMAT.format(date);
		} else {
			return "";
		}
	}

	public String getScheduleDate() {
		return formatDate(negotiation.getScheduleStartDatetime());
	}

	public String getScheduleTime() {
		return getTime(negotiation.getScheduleStartDatetime(), negotiation.getScheduleEndDatetime());
	}

	public String getImplementationDate() {
		return formatDate(negotiation.getImplementationStartDatetime());
	}

	public String getImplementationTime() {
		return getTime(negotiation.getImplementationStartDatetime(), negotiation.getImplementationEndDatetime());
	}

	public String getNotification() {
		return String.join(" ", getNoticeAccountNames());
	}

	public List<String> getNoticeAccountNames() {
		return noticeAccountNames;
	}

	public void setNoticeAccountNames(List<String> noticeAccountNames) {
		this.noticeAccountNames = noticeAccountNames;
	}

	public String getDivisionName() {
		String division = getDivision();
		switch (division) {
		case "NEGOTIATION":
			return "商談";
		case "INSPECTION":
			return "現調";
		case "RECEPTION":
			return "会食";
		case "GENERALMEETING":
			return "総会";
		case "OTHER":
			return "その他";
		default:
			return "";
		}
	}

	public List<InterviewCorporationDTO> getNegotiationInterviewCorporations() {
		return negotiationInterviewCorporations;
	}

	public void setNegotiationInterviewCorporations(Negotiation negotiation) {
		negotiationInterviewCorporations = new NegotiationInterviewCorporationRepository()
				.findByNegotiation(negotiation).stream().map(n -> {
					return new InterviewCorporationDTO(n);
				}).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String divisionValue() {
		String division = this.getDivision();
		switch (division) {
		case "NEGOTIATION":
			return "商談";
		case "INSPECTION":
			return "現調";
		case "RECEPTION":
			return "会食";
		case "GENERALMEETING":
			return "総会";
		case "OTHER":
			return "その他";
		default:
			return "";
		}
	}

	private String dateValue(LocalDateTime start, LocalDateTime end) {
		if (start != null && end != null) {
			String startDate = START_DATE_FORMAT.format(start);
			String endDate = END_DATE_FORMAT.format(end);
			return startDate + endDate;
		} else {
			return "";
		}
	}

	private String dateFormatValue(LocalDateTime datetime, DateTimeFormatter format) {
		if (datetime != null) {
			return format.format(datetime);
		} else {
			return "";
		}
	}

	public String scheduleDateValue() {
		return dateFormatValue(getScheduleStartDatetime(), DATE_FORMAT);
	}

	public String scheduleStartTimeValue() {
		return dateFormatValue(getScheduleStartDatetime(), TIME_FORMAT);
	}

	public String scheduleEndTimeValue() {
		return dateFormatValue(getScheduleEndDatetime(), TIME_FORMAT);
	}

	public String implementationDateValue() {
		return dateFormatValue(getImplementationStartDatetime(), DATE_FORMAT);
	}

	public String implementationStartTimeValue() {
		return dateFormatValue(getImplementationStartDatetime(), TIME_FORMAT);
	}

	public String implementationEndTimeValue() {
		return dateFormatValue(getImplementationEndDatetime(), TIME_FORMAT);
	}

	private String getDateValue(LocalDateTime start, LocalDateTime end) {
		if (start != null && end != null) {
			String startDate = START_DATE_FORMAT.format(start);
			String endDate = END_DATE_FORMAT.format(end);
			return startDate + endDate;
		} else {
			return "";
		}
	}

	public String getImplementationDateValue() {
		return getDateValue(getImplementationStartDatetime(), getImplementationEndDatetime());
	}

	public String getScheduleDateValue() {
		return getDateValue(getScheduleStartDatetime(), getScheduleEndDatetime());
	}

	public String getUpdateDatetimeValue() {
		return START_DATE_FORMAT.format(getUpdateDatetime());
	}

	public String getScheduleStartTime() {
		if (getScheduleStartDatetime() == null) {
			return "";
		} else {
			return TIME_FORMAT.format(getScheduleStartDatetime());
		}
	}

	public String getScheduleEndTime() {
		if (getScheduleEndDatetime() == null) {
			return "";
		} else {
			return TIME_FORMAT.format(getScheduleEndDatetime());
		}
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

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public String getStopState() {
		if (isStop()) {
			return "中止";
		} else {
			return "";
		}
	}

	public String openRates() {

		return String.valueOf(new NegotiationOpenAccountRepository().calcOpenRates(negotiation)) + "%";
	}

	public List<NegotiationInterviewAccount> getInterviewAccounts() {
		return interviewAccounts;
	}

	public void setInterviewAccounts(List<NegotiationInterviewAccount> interviewAccount) {
		this.interviewAccounts = interviewAccount;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public List<NegotiationInterviewBusinessCard> getInterviewBussinessCards() {
		return interviewBussinessCards;
	}

	public void setInterviewBussinessCards(List<NegotiationInterviewBusinessCard> interviewBussinessCards) {
		this.interviewBussinessCards = interviewBussinessCards;
	}

	public List<NegotiationInterviewBuilding> getInterviewBuildings() {
		return interviewBuildings;
	}

	public void setInterviewBuildings(List<NegotiationInterviewBuilding> interviewBuildings) {
		this.interviewBuildings = interviewBuildings;
	}

	public List<NegotiationInterviewBrand> getInterviewBrands() {
		return interviewBrands;
	}

	public void setInterviewBrands(List<NegotiationInterviewBrand> interviewBrands) {
		this.interviewBrands = interviewBrands;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getRelease() {
		return release;
	}

	public void setRelease(Boolean release) {
		this.release = release;
	}

	public String getReleaseLevel() {
		return releaseLevel;
	}

	public void setReleaseLevel(String releaseLevel) {
		this.releaseLevel = releaseLevel;
	}

	public String getNextActionContent() {
		return nextActionContent;
	}

	public void setNextActionContent(String nextActionContent) {
		this.nextActionContent = nextActionContent;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
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

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	public String getReleaseLevelValue() {
		return releaseLevelValue;
	}

	public void setReleaseLevelValue(String releaseLevelValue) {
		this.releaseLevelValue = releaseLevelValue;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public int getOpenedAccountSize() {
		return openedAccountSize;
	}

	public void setOpenedAccountSize(int openedAccountSize) {
		this.openedAccountSize = openedAccountSize;
	}

	public List<String> getOpenAccountList() {
		return openAccountList;
	}

	public void setOpenAccountList(List<String> openAccountList) {
		this.openAccountList = openAccountList;
	}

}

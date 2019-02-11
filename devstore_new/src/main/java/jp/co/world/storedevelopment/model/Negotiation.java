package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationCommentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationCommentUpdateHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationNoticeAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationOpenAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationReadLaterAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationVideoRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectNegotiationRepository;

public class Negotiation extends ActiveModel<Negotiation> {
	private String division = "";
	private LocalDateTime scheduleStartDatetime = null;
	private LocalDateTime scheduleEndDatetime = null;
	private LocalDateTime implementationStartDatetime = null;
	private LocalDateTime implementationEndDatetime = null;
	private String place = "";
	private String title = "";
	private String purpose = "";
	private String content = "";
	private Integer priority = 0;
	private Boolean release = false;
	private String releaseLevel = "";
	private String nextActionContent = "";
	private boolean stop;
	private boolean draft;
	private String businessCardFree = "";
	private String interviewAccountFree = "";
	private String createdCompanyCd = "";

	public Negotiation() {
	}

	public Negotiation(String title) {
		setTitle(title);
	}

	private static DateTimeFormatter START_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	private static DateTimeFormatter END_DATE_FORMAT = DateTimeFormatter.ofPattern("-HH:mm");
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

	private static String[] ignoreFields = new String[] { "statusValue", "statusLabel", "isLater", "accessRecoreds",
			"interviewAccounts", "unmanagedInterviewName", "implementationDateValue", "numberOfComments",
			"buildingNames", "scheduleDateValue", "interviewBussinessCards", "interviewCorporations",
			"interviewBuildings", "updateDatetimeValue", "noticeAccounts", "interviewBrands", "images", "files",
			"openRates", "brandName", "comments", "numberOfComments", "relatedNegotiaions", "divisionValue",
			"releaseLevelValue", "importantInformations", "scheduleStartTime", "scheduleEndTime", "stopState",
			"interviewAccount", "interviewCorporationsNames", "interviewBuildingNames", "interviewBusinessCardNames",
			"noticeAccountName", "interviewCorporationsNames", "interviewBuildingNames", "interviewBusinessCardNames",
			"noticeAccountName", "interviewAccountNames", "interviewAccountValues", "noticeAccountValues",
			"noticeAccountValues", "videos", "openAccounts", "projectNegotiations" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<Negotiation> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationModelMapper.class);
	}
	
	public Boolean sameCreatedAccount(Account a) {
		return getCreatedAccountCode().equals(a.getEmployeCode());
	}
	
	public Optional<ICompany> company() {
		String code = getCreatedCompanyCd();

		if (code == null) {
			return Optional.ofNullable(null);
		} else {
			return new ICompanyRepository().findByCode(code);

		}
	}

	public String companyName() {
		Optional<ICompany> opt = company();
		
		if (opt.isPresent()) {
			return opt.get().getCompanyKanjiName();
		} else {
			return "";
		}
	}
	
	public String getDivision() {
		return division;
	}

	public void setDivision(String negotiationDivisionId) {
		division = negotiationDivisionId;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getNextActionContent() {
		return nextActionContent;
	}

	public void setNextActionContent(String next_actionContent) {
		nextActionContent = next_actionContent;
	}

	public void switchReadLater(Account account) {
		Optional<NegotiationReadLaterAccount> opt = new NegotiationReadLaterAccountRepository().findByAccount(this,
				account);
		if (opt.isPresent()) {
			opt.get().delete();
		} else {
			new NegotiationReadLaterAccount(this, account).create();
		}
	}

	public void open(Account account) {
		Optional<NegotiationOpenAccount> opt = new NegotiationOpenAccountRepository().findByAccount(this, account);
		if (!opt.isPresent()) {
			new NegotiationOpenAccount(this, account).create();
		}
	}

	public boolean getIsLater(Account account) {
		return new NegotiationReadLaterAccountRepository().isReadLater(this, account);
	}

	public boolean getIsOpend(Account account) {
		return new NegotiationOpenAccountRepository().isOpen(this, account);
	}

	public String getInterviewCorporationsNames() {
		return String.join("/", getInterviewCorporations().stream().map(c -> {
			return c.getCorporationName();
		}).collect(Collectors.toList()));
	}

	public String getInterviewBuildingNames() {
		return String.join("/", getInterviewBuildings().stream().map(b -> {
			return b.getBuilding().getName();
		}).collect(Collectors.toList()));
	}

	public String getInterviewBusinessCardNames() {
		return String.join("/", getInterviewBussinessCards().stream().map(bc -> {
			if (bc.isUnmanaged()) {
				return bc.getUnmanagedName();
			} else {
				IBusinessCard card = bc.findBusinessCard();
				return String.format("%s %s %s", card.getCompanyName(), card.getPositionName(), card.getName());
			}
		}).collect(Collectors.toList()));
	}

	public String getNoticeAccountName() {
		return String.join("/", getNoticeAccounts().stream().map(n -> {
			return n.getAccountName();
		}).collect(Collectors.toList()));
	}

	public String getInterviewAccountNames() {
		return String.join("/", getInterviewAccounts().stream().map(a -> {
			return a.getAccountName();
		}).collect(Collectors.toList()));
	}

	public List<NegotiationInterviewAccount> getInterviewAccounts() {
		return new NegotiationInterviewAccountRepository().findByNegotiation(this);
	}

	public List<Account> getInterviewAccountValues() {
		return getInterviewAccounts().stream().filter(a -> a.isUnmanaged() == false).map(a -> {
			return a.findAccount();
		}).collect(Collectors.toList());
	}

	public List<Account> getNoticeAccountValues() {
		return getNoticeAccounts().stream().map(a -> {
			return a.getAccount();
		}).collect(Collectors.toList());
	}

	public List<ProjectNegotiation> getProjectNegotiations() {
		List<ProjectNegotiation> projects = new ProjectNegotiationRepository().findByNegotiation(this);

		if (projects.isEmpty()) {
			return Collections.emptyList();
		} else {
			return projects;
		}
	}

	public List<NegotiationInterviewCorporation> getInterviewCorporations() {
		List<NegotiationInterviewCorporation> corporations = new NegotiationInterviewCorporationRepository()
				.findByNegotiation(this);

		if (corporations.isEmpty()) {
			return Collections.emptyList();
		} else {
			return corporations;
		}
	}

	public List<NegotiationInterviewBuilding> getInterviewBuildings() {
		return new NegotiationInterviewBuildingRepository().findByNegotiation(this);
	}

	public List<NegotiationInterviewBusinessCard> getInterviewBussinessCards() {
		return new NegotiationInterviewBusinessCardRepository().findByNegotiation(this);
	}

	public List<NegotiationNoticeAccount> getNoticeAccounts() {
		return new NegotiationNoticeAccountRepository().findByNegotiation(this);
	}

	public List<NegotiationInterviewBrand> getInterviewBrands() {
		return new NegotiationInterviewBrandRepository().findOfNegotiation(this);
	}

	public Boolean hasImplementationDatetime() {
		return getImplementationStartDatetime() != null || getImplementationEndDatetime() != null;
	}

	public String getStatusLabel() {
		if (hasImplementationDatetime()) {
			return NegotiationStatus.getImplementationLabel();
		} else {
			return NegotiationStatus.getScheduleLabel();
		}
	}

	public String getStatusValue() {
		if (hasImplementationDatetime()) {
			return NegotiationStatus.REPORT.toString();
		} else {
			return NegotiationStatus.SCHEDULE.toString();
		}
	}

	public List<String> getBuildingNames() {
		return getInterviewBuildings().stream().map(ib -> ib.getBuilding().getName()).collect(Collectors.toList());
	}

	public Boolean isLevel2() {
		return getReleaseLevel() == "NEGOTIATION_LV2";
	}

	public Boolean isLevel3() {
		return getReleaseLevel() == "NEGOTIATION_LV3";
	}

	public String getDivisionValue() {
		String division = this.getDivision();
		switch (division) {
		case "NEGOTIATION":
			return "商談";
		case "INSPECTION":
			return "現調";
		case "GENERALMEETING":
			return "総会";
		case "OTHER":
			return "その他";
		default:
			return "";
		}
	}

	public String getReleaseLevelValue() {
		switch (getReleaseLevel()) {
		case "NEGOTIATION_LV1":
			return "全員に公開";
		case "NEGOTIATION_LV2":
			return "自社のみ公開";
		case "NEGOTIATION_LV3":
			return "通知先のみに公開";
		default:
			return "";
		}
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

	public Boolean hasFile() {
		if (getImages().size() > 0 || getFiles().size() > 0 || getVideos().size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int getNumberOfComments() {
		if (getId() == null) {
			return 0;
		} else {
			return new NegotiationCommentRepository().countByNegotiation(this);
		}
	}

	public void addInterviewBusinessCard(IBusinessCard businessCard) {
		new NegotiationInterviewBusinessCard(this, businessCard).create();
	}

	public void addInterviewCorporation(ICorporation corporation) {
		new NegotiationInterviewCorporation(this, corporation).create();
	}

	public void addInterviewAccount(Account account) {
		new NegotiationInterviewAccount(this, account).create();
	}

	public void addInterviewBuilding(Building building) {
		new NegotiationInterviewBuilding(this, building).create();
	}

	public void addNoticeAccount(Account account) {
		new NegotiationNoticeAccount(this, account).create();
	}

	public List<NegotiationImage> getImages() {
		return new NegotiationImageRepository().findByNegotiation(this);
	}

	public List<NegotiationFile> getFiles() {
		return new NegotiationFileRepository().findByNegotiation(this);
	}

	public List<NegotiationVideo> getVideos() {
		return new NegotiationVideoRepository().findByNegotiation(this);
	}

	public void addComment(NegotiationComment comment, Account myself) {
		comment.setNegotiationId(getId());
		comment.create();
		
		//save update datetime when add comment
		updateCommentHistory();

		SendReserve reserve = new SendReserve();
		reserve.setEmployeeCdList(commentPushNotificationEmployeeCodes());
		reserve.setMessage(String.format("%sさんがコメント追加：%s %s", myself.getFullName(), getTitle(), comment.getContent()));
		reserve.setInitializeStatus();
		reserve.create();

		getInterviewAccounts().stream().filter(a -> a.isNotSame(myself)).forEach(a -> {
			Account account = a.findAccount();
			RelatedTask task = new RelatedTask(account);
			task.setNegotiationCommentId(comment.getId());
			task.setDivision("NEGOTIATION_COMMENT");
			task.create();
		});
	}
	
	private void updateCommentHistory() {
		//delete by negotiation
		new NegotiationCommentUpdateHistoryRepository().deleteByNegotiation(this);
		
		// add new record
		new NegotiationCommentUpdateHistory(id).create();
	}

	private String commentPushNotificationEmployeeCodes() {
		List<String> result = new ArrayList<String>();
		result.add(getCreatedAccountCode());
		result.addAll(getInterviewAccountValues().stream().map(a -> a.getEmployeCode()).collect(Collectors.toList()));
		result.addAll(getNoticeAccountValues().stream().map(a -> a.getEmployeCode()).collect(Collectors.toList()));

		return String.join(",", result.stream().distinct().collect(Collectors.toList()));
	}

	public String openRates() {
		return String.valueOf(new NegotiationOpenAccountRepository().calcOpenRates(this)) + "%";
	}

	public List<NegotiationComment> getComments() {
		return new NegotiationCommentRepository().findByNegotiation(this);
	}

	public List<Negotiation> findRelatedNegotiaions() {
		return new NegotiationRepository().findRelatedNegotiation(this);
	}

	public List<ImportantInformation> getImportantInformations() {
		return new ImportantInformationRepository().findByNegotiation(this);
	}

	public Boolean hasImportantInformation() {
		return getImportantInformations().size() > 0;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getReleaseLevel() {
		return releaseLevel;
	}

	public void setReleaseLevel(String releaseLevel) {
		this.releaseLevel = releaseLevel;
	}

	public String createAccountName() {
		return new AccountRepository().findByCode(getCreatedAccountCode()).orElseGet(() -> {
			throw new IllegalArgumentException("検索したアカウントは存在しません");
		}).getFullName();

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

	public List<String> getOpenAccounts() {
		List<NegotiationOpenAccount> list = new NegotiationOpenAccountRepository().findByNegotiation(this);
		List<String> resultList = new ArrayList<>();
		String employeeCd = getCreatedAccountCode();
		String updateAccountCd = getUpdateAccountCode();

		for(NegotiationOpenAccount op : list) {
			Account account = new AccountRepository().findById(op.getAccountId()).get();

			if (!employeeCd.equals(updateAccountCd) && !updateAccountCd.equals(account.getEmployeeCd())) {
				resultList.add(account.getFullName());
			} else if (employeeCd.equals(updateAccountCd) && !employeeCd.equals(account.getEmployeeCd())){
				resultList.add(account.getFullName());
			}
		}

		return resultList;
	}
	
	public String getCreatedCompanyCd() {
		return createdCompanyCd;
	}

	public void setCreatedCompanyCd(String createdCompanyCd) {
		this.createdCompanyCd = createdCompanyCd;
	}

}

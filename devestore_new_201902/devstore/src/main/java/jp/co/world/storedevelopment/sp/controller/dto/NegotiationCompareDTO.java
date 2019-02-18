package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.Negotiation;

public class NegotiationCompareDTO {
	protected static final ObjectMapper MAPPER = new ObjectMapper();

	private String title;
	private String division;
	private String purpose;
	private String content;
	private String place;
	private String nextActionContent;
	private String releaseLevel;
	private String businessCardFree;
	private String interviewAccountFree;
	private Boolean release;
	private Integer priority;
	private Boolean stop;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime scheduleStartDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime scheduleEndDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime implementationEndDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime implementationStartDatetime;

	private List<Long> corporationIds = new ArrayList<>();
	private List<Long> buildingIds = new ArrayList<>();
	private List<Long> accountIds = new ArrayList<>();
	private List<Long> brandIds = new ArrayList<>();
	private List<Long> bussinessCardIds = new ArrayList<>();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime importantShowEndDate;
	private String importantContent;
	private List<Long> importantBuildingIds = new ArrayList<>();
	private List<Long> importantCorporationIds = new ArrayList<>();

	public NegotiationCompareDTO() {

	}

	public NegotiationCompareDTO(Negotiation n) {
		setTitle(n.getTitle());
		setDivision(n.getDivision());
		setPurpose(n.getPurpose());
		setContent(n.getContent());
		setPlace(n.getPlace());
		setNextActionContent(n.getNextActionContent());
		setReleaseLevel(n.getReleaseLevel());
		setBusinessCardFree(n.getBusinessCardFree());
		setInterviewAccountFree(n.getInterviewAccountFree());
		setRelease(n.getRelease());
		setPriority(n.getPriority());
		setStop(n.isStop());
		setScheduleStartDatetime(n.getScheduleStartDatetime());
		setScheduleEndDatetime(n.getScheduleEndDatetime());
		setImplementationStartDatetime(n.getImplementationStartDatetime());
		setImplementationEndDatetime(n.getImplementationEndDatetime());

		setCorporationIds(n.getInterviewCorporations().stream().map(x -> x.getCorporationId()).sorted()
				.collect(Collectors.toList()));
		setBuildingIds(
				n.getInterviewBuildings().stream().map(x -> x.getBuildingId()).sorted().collect(Collectors.toList()));
		setAccountIds(
				n.getInterviewAccounts().stream().map(x -> x.getAccountId()).sorted().collect(Collectors.toList()));
		setBrandIds(n.getInterviewBrands().stream().map(x -> x.getBrandId()).sorted().collect(Collectors.toList()));
		setBussinessCardIds(n.getInterviewBussinessCards().stream().map(x -> x.getBusinessCardId()).sorted()
				.collect(Collectors.toList()));

		if (n.getImportantInformations().size() > 0) {
			ImportantInformation important = n.getImportantInformations().get(0);
			setImportantShowEndDate(important.getShowEndDatetime());
			setImportantContent(important.getContent());
			setImportantBuildingIds(important.importantInformationBuildings().stream().map(x -> x.getBuildingId())
					.sorted().collect(Collectors.toList()));
			setImportantCorporationIds(important.importantInformationCorporation().stream()
					.map(x -> x.getCorporationId()).sorted().collect(Collectors.toList()));
		}
	}

	public NegotiationCompareDTO(NegotiationUpdateFormDTO dto) {
		setTitle(dto.getTitle());
		setDivision(dto.getDivision());
		setPurpose(dto.getPurpose());
		setContent(dto.getContent());
		setPlace(dto.getPlace());
		setNextActionContent(dto.getNextActionContent());
		setReleaseLevel(dto.getReleaseLevel());
		setBusinessCardFree(dto.getBusinessCardFree());
		setInterviewAccountFree(dto.getInterviewAccountFree());
		setRelease(dto.getRelease());
		setPriority(dto.getPriority());
		setStop(dto.getStop());
		setScheduleStartDatetime(dto.getScheduleStartDatetime());
		setScheduleEndDatetime(dto.getScheduleEndDatetime());
		setImplementationStartDatetime(dto.getImplementationStartDatetime());
		setImplementationEndDatetime(dto.getImplementationEndDatetime());

		setCorporationIds(dto.getCorporationIds().stream().sorted().collect(Collectors.toList()));
		setBuildingIds(dto.getBuildingIds().stream().sorted().collect(Collectors.toList()));
		setAccountIds(dto.getAccountIds().stream().sorted().collect(Collectors.toList()));
		setBrandIds(dto.getBrandIds().stream().sorted().collect(Collectors.toList()));
		setBussinessCardIds(dto.getBussinessCardIds().stream().sorted().collect(Collectors.toList()));

		if (dto.getImportantInformation() != null) {
			NegotiationImportantInfomationCreateDTO important = dto.getImportantInformation();
			setImportantShowEndDate(important.getShowEndDate());
			setImportantContent(important.getContent());
			setImportantBuildingIds(important.getBuildingIds().stream().sorted().collect(Collectors.toList()));
			setImportantCorporationIds(important.getCorporationIds().stream().sorted().collect(Collectors.toList()));
		}
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getNextActionContent() {
		return nextActionContent;
	}

	public void setNextActionContent(String nextActionContent) {
		this.nextActionContent = nextActionContent;
	}

	public String getReleaseLevel() {
		return releaseLevel;
	}

	public void setReleaseLevel(String releaseLevel) {
		this.releaseLevel = releaseLevel;
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

	public Boolean getRelease() {
		return release;
	}

	public void setRelease(Boolean release) {
		this.release = release;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getStop() {
		return stop;
	}

	public void setStop(Boolean stop) {
		this.stop = stop;
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

	public LocalDateTime getImplementationEndDatetime() {
		return implementationEndDatetime;
	}

	public void setImplementationEndDatetime(LocalDateTime implementationEndDatetime) {
		this.implementationEndDatetime = implementationEndDatetime;
	}

	public LocalDateTime getImplementationStartDatetime() {
		return implementationStartDatetime;
	}

	public void setImplementationStartDatetime(LocalDateTime implementationStartDatetime) {
		this.implementationStartDatetime = implementationStartDatetime;
	}

	public List<Long> getCorporationIds() {
		return corporationIds;
	}

	public void setCorporationIds(List<Long> corporationIds) {
		this.corporationIds = corporationIds;
	}

	public List<Long> getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}

	public List<Long> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Long> brandIds) {
		this.brandIds = brandIds;
	}

	public List<Long> getBussinessCardIds() {
		return bussinessCardIds;
	}

	public void setBussinessCardIds(List<Long> bussinessCardIds) {
		this.bussinessCardIds = bussinessCardIds;
	}

	public LocalDateTime getImportantShowEndDate() {
		return importantShowEndDate;
	}

	public void setImportantShowEndDate(LocalDateTime importantShowEndDate) {
		this.importantShowEndDate = importantShowEndDate;
	}

	public String getImportantContent() {
		return importantContent;
	}

	public void setImportantContent(String importantContent) {
		this.importantContent = importantContent;
	}

	public List<Long> getImportantBuildingIds() {
		return importantBuildingIds;
	}

	public void setImportantBuildingIds(List<Long> importantBuildingIds) {
		this.importantBuildingIds = importantBuildingIds;
	}

	public List<Long> getImportantCorporationIds() {
		return importantCorporationIds;
	}

	public void setImportantCorporationIds(List<Long> importantCorporationIds) {
		this.importantCorporationIds = importantCorporationIds;
	}

	public String toJSON() {
		try {
			return MAPPER.writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	public boolean isSame(NegotiationCompareDTO other) {
		return this.toJSON().equals(other.toJSON());
	}
}

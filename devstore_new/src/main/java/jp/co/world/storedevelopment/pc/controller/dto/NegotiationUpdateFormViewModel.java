package jp.co.world.storedevelopment.pc.controller.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ICompany;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationUpdateFormViewDTO;
import jp.co.world.storedevelopment.sp.controller.dto.SelectOptionDTO;
import jp.co.world.storedevelopment.utils.JsonParseUtils;

public class NegotiationUpdateFormViewModel extends NegotiationUpdateFormViewDTO {
	private String scheduleDate;
	private String scheduleStartTime;
	private String scheduleEndTime;
	private String implementationDate;
	private String implementationStartTime;
	private String implementationEndTime;

	private String projectsJson;
	private String corporationsJson;
	private String buildingsJson;
	private String businessCardsJson;
	private String accountsJson;
	private String brandsJson;
	private String businessCardUnmanagedJson;
	private String accountsUnmanagedJson;
	private String noticeAccountsJson;
	private String importantBuildingsJson;
	private String importantBuildingUnmanagedJson;
	private String importantCorporationsJson;
	private String importantCorporationUnmanagedJson;
	private String companyName;
	private List<SelectOptionDTO> companySelect = new ArrayList<>();
	private Boolean canEditCompany;

	public static NegotiationUpdateFormViewModel toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, NegotiationUpdateFormViewModel.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	public NegotiationUpdateFormViewModel() {
	}

	public NegotiationUpdateFormViewModel(Negotiation n) {
		super(n);
		setCompanyName(n.companyName());
		setCompanyCd(n.getCreatedCompanyCd());
		setScheduleDate(n.scheduleDateValue());
		setScheduleStartTime(n.scheduleStartTimeValue());
		setScheduleEndTime(n.scheduleEndTimeValue());
		setImplementationDate(n.implementationDateValue());
		setImplementationStartTime(n.implementationStartTimeValue());
		setImplementationEndTime(n.implementationEndTimeValue());

		setProjectsJson(JsonParseUtils.parse(getProjectValues()));
		setCorporationsJson(JsonParseUtils.parse(getCorporationValues()));
		setBuildingsJson(JsonParseUtils.parse(getBuildingValues()));
		setBusinessCardsJson(JsonParseUtils.parse(getBussinesCardValues()));
		setAccountsJson(JsonParseUtils.parse(getAccountValues()));
		setNoticeAccountsJson(JsonParseUtils.parse(getNoticeValues()));
		setBrandsJson(JsonParseUtils.parse(getBrandValues()));
		setBusinessCardUnmanagedJson(JsonParseUtils.parse(getBussinessCardUnmanagedValeus()));
		setAccountsUnmanagedJson(JsonParseUtils.parse(getAccountUnmanagedValues()));

		setImportantBuildingsJson(JsonParseUtils.parse(getImportant().getBuildingValues()));
		setImportantBuildingUnmanagedJson(JsonParseUtils.parse(getImportant().getBuildingUnmanagedValues()));
		setImportantCorporationsJson(JsonParseUtils.parse(getImportant().getCorporationValues()));
		setImportantCorporationUnmanagedJson(JsonParseUtils.parse(getImportant().getCorporationUnmanagedValues()));
	}

	public NegotiationUpdateFormViewModel(Negotiation n, Account a) {
		this(n);
		setCompanySelect(companyListValue(n, a));
		setCanEditCompany(n.sameCreatedAccount(a));
	}

	private List<SelectOptionDTO> companyListValue(Negotiation n, Account a) {
		if (n.sameCreatedAccount(a)) {
			return a.companyList().stream().map(c -> {
				return new SelectOptionDTO(c.getCompanyKanjiName(), c.getCompanyCode());
			}).collect(Collectors.toList());
		} else {
			Optional<ICompany> c = n.company();
			
			if (c.isPresent()) {
				return Arrays.asList(
						new SelectOptionDTO(c.get().getCompanyKanjiName(), c.get().getCompanyCode()));
			} else {
				return new ArrayList<>();
			}
		}
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(String scheduleStartDate) {
		this.scheduleStartTime = scheduleStartDate;
	}

	public String getScheduleEndTime() {
		return scheduleEndTime;
	}

	public void setScheduleEndTime(String scheduleEndDate) {
		this.scheduleEndTime = scheduleEndDate;
	}

	public String getImplementationDate() {
		return implementationDate;
	}

	public void setImplementationDate(String implementationDate) {
		this.implementationDate = implementationDate;
	}

	public String getImplementationStartTime() {
		return implementationStartTime;
	}

	public void setImplementationStartTime(String implementationStartDate) {
		this.implementationStartTime = implementationStartDate;
	}

	public String getImplementationEndTime() {
		return implementationEndTime;
	}

	public void setImplementationEndTime(String implementationEndDate) {
		this.implementationEndTime = implementationEndDate;
	}

	public String getProjectsJson() {
		return projectsJson;
	}

	public void setProjectsJson(String projectsJson) {
		this.projectsJson = projectsJson;
	}

	public String getCorporationsJson() {
		return corporationsJson;
	}

	public void setCorporationsJson(String corporationsJson) {
		this.corporationsJson = corporationsJson;
	}

	public String getBuildingsJson() {
		return buildingsJson;
	}

	public void setBuildingsJson(String buildingsJson) {
		this.buildingsJson = buildingsJson;
	}

	public String getBusinessCardsJson() {
		return businessCardsJson;
	}

	public void setBusinessCardsJson(String businessCardsJson) {
		this.businessCardsJson = businessCardsJson;
	}

	public String getAccountsJson() {
		return accountsJson;
	}

	public void setAccountsJson(String accountsJson) {
		this.accountsJson = accountsJson;
	}

	public String getBrandsJson() {
		return brandsJson;
	}

	public void setBrandsJson(String brandsJson) {
		this.brandsJson = brandsJson;
	}

	public String getBusinessCardUnmanagedJson() {
		return businessCardUnmanagedJson;
	}

	public void setBusinessCardUnmanagedJson(String businessCardUnmanagedJson) {
		this.businessCardUnmanagedJson = businessCardUnmanagedJson;
	}

	public String getAccountsUnmanagedJson() {
		return accountsUnmanagedJson;
	}

	public void setAccountsUnmanagedJson(String accountsUnmanagedJson) {
		this.accountsUnmanagedJson = accountsUnmanagedJson;
	}

	public String getImportantBuildingsJson() {
		return importantBuildingsJson;
	}

	public void setImportantBuildingsJson(String importantBuildingsJson) {
		this.importantBuildingsJson = importantBuildingsJson;
	}

	public String getImportantBuildingUnmanagedJson() {
		return importantBuildingUnmanagedJson;
	}

	public void setImportantBuildingUnmanagedJson(String importantBuildingUnmanagedJson) {
		this.importantBuildingUnmanagedJson = importantBuildingUnmanagedJson;
	}

	public String getImportantCorporationsJson() {
		return importantCorporationsJson;
	}

	public void setImportantCorporationsJson(String importantCorporationsJson) {
		this.importantCorporationsJson = importantCorporationsJson;
	}

	public String getImportantCorporationUnmanagedJson() {
		return importantCorporationUnmanagedJson;
	}

	public void setImportantCorporationUnmanagedJson(String importantCorporationUnmanagedJson) {
		this.importantCorporationUnmanagedJson = importantCorporationUnmanagedJson;
	}

	public String getNoticeAccountsJson() {
		return noticeAccountsJson;
	}

	public void setNoticeAccountsJson(String noticeAccountsJson) {
		this.noticeAccountsJson = noticeAccountsJson;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

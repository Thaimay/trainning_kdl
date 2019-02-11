
package jp.co.world.storedevelopment.pc.controller.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ProjectClassification;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectClassificationRepository;
import jp.co.world.storedevelopment.pc.controller.dto.FindFormDTO;
import jp.co.world.storedevelopment.utils.JsonParseUtils;

/**
 * @author tienpv
 *
 */
@JsonIgnoreProperties({ "account", "keyWordHankaku", "keyWordZenkaku", "planPeriods", "implementationPeriods", "expirationPeriods",
		"updateDayFromShop", "updateDayToShop" })
public class ProjectFindForm extends FindFormDTO {
	public static final ProjectFindForm EMPTY = new ProjectFindForm();

	private List<Long> iShopCompanieIds = new ArrayList<>();
	private List<Long> iBrandIds = new ArrayList<>();
	private List<Long> iAreaIds = new ArrayList<>();
	private List<Long> iPrefectureIds = new ArrayList<>();
	private List<Long> iBlockIds = new ArrayList<>();
	private List<String> planPeriodUpDowns = new ArrayList<>();
	private List<Long> projectCategoryIds = new ArrayList<>();
	private List<Long> landingCategoryIds = new ArrayList<>();
	private List<Long> officeProjectProgressIds = new ArrayList<>();
	private List<Long> negotiationProjectProgressIds = new ArrayList<>();
	private List<String> implementationPeriodUpDowns = new ArrayList<>();
	private List<String> expirationPeriodUpDowns = new ArrayList<>();
	private List<String> operationForms = new ArrayList<>();
	private List<Long> salesChannelIds = new ArrayList<>();
	private List<String> storeDevelopTeamCds = new ArrayList<>();
	private List<Long> otherAccountStoreIds = new ArrayList<>();
	private String updateDatetimeFrom;
	private String updateDatetimeTo;
	private String updateDayFrom;
	private String updateDayTo;

	private String salesAchievementFrom;
	private String salesAchievementTo;
	private String salesAchievementYearToYearFrom;
	private String salesAchievementYearToYearTo;
	private String operatingProfitAchievementFrom;
	private String operatingProfitAchievementTo;
	private String operatingProfitAchievementCompositionFrom;
	private String operatingProfitAchievementCompositionTo;
	private String buildingExpectedValueFrom;
	private String buildingExpectedValueTo;

	private String orderByOption = "ORDER_BY_1";
	private Boolean orderByDesc = false;

	private List<Long> negotiationIds;
	private List<Long> buildingIds;

	private Boolean isOwn;
	private Boolean isReadLater;
	private Boolean isMonBefore;
	private Boolean isRunOnly;
	private boolean isAdvancedSearch = false;
	private Boolean isLandingNotPrint = true;
	private Boolean isCurrentContractNotPrint = true;
	private Boolean isProgressContractNotPrint = true;
	private Boolean isPlanNotPrint = true;
	private Boolean isCurrentSectionNotPrint = true;
	private Boolean isProgressSectionNotPrint = true;
	private Boolean isStatusNotPrint = true;
	private Boolean isBasicNotPrint = true;
	private Boolean isCorporationNotPrint = true;

	private Boolean isNewShop = true;
	private Boolean isExistsShop = true;
	private Boolean isOtherShop = true;
	private Boolean isLandingNothing = true;
	private List<Long> landingIds;

	private String jsonCurrentOtherAccountStore = "[]";
	private Account account;

	private Boolean isDefaultSearch = false;

	public ProjectFindForm() {
		landingIds = new ArrayList<>();
		List<ProjectClassification> pc = new ProjectClassificationRepository().getLandingClassification();
		if (pc.size() > 0) {
			landingIds = pc.stream().map(l -> l.getId()).collect(Collectors.toList());
		}
	}

	public List<Long> getiAreaIds() {
		return iAreaIds;
	}

	public void setiAreaIds(List<Long> iAreaIds) {
		this.iAreaIds = iAreaIds;
	}

	public List<Long> getiBlockIds() {
		return iBlockIds;
	}

	public void setiBlockIds(List<Long> iBlockIds) {
		this.iBlockIds = iBlockIds;
	}

	public List<Long> getiBrandIds() {
		return iBrandIds;
	}

	public void setiBrandIds(List<Long> iBrandIds) {
		this.iBrandIds = iBrandIds;
	}

	public List<Long> getNegotiationIds() {
		return negotiationIds;
	}

	public void setNegotiationIds(List<Long> negotiationIds) {
		this.negotiationIds = negotiationIds;
	}

	public List<Long> getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}

	public Boolean getIsOwn() {
		return isOwn;
	}

	public void setIsOwn(Boolean isOwn) {
		this.isOwn = isOwn;
	}

	public Boolean getIsReadLater() {
		return isReadLater;
	}

	public void setIsReadLater(Boolean isReadLater) {
		this.isReadLater = isReadLater;
	}

	public Boolean getIsMonBefore() {
		return isMonBefore;
	}

	public void setIsMonBefore(Boolean isMonBefore) {
		this.isMonBefore = isMonBefore;
	}

	public Boolean getIsRunOnly() {
		return isRunOnly;
	}

	public void setIsRunOnly(Boolean isRunOnly) {
		this.isRunOnly = isRunOnly;
	}

	public boolean isAdvancedSearch() {
		return isAdvancedSearch;
	}

	public void setAdvancedSearch(boolean isAdvancedSearch) {
		this.isAdvancedSearch = isAdvancedSearch;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Boolean getIsLandingNotPrint() {
		return isLandingNotPrint;
	}

	public void setIsLandingNotPrint(Boolean isLandingNotPrint) {
		this.isLandingNotPrint = isLandingNotPrint;
	}

	public Boolean getIsCurrentContractNotPrint() {
		return isCurrentContractNotPrint;
	}

	public void setIsCurrentContractNotPrint(Boolean isCurrentContractNotPrint) {
		this.isCurrentContractNotPrint = isCurrentContractNotPrint;
	}

	public Boolean getIsProgressContractNotPrint() {
		return isProgressContractNotPrint;
	}

	public void setIsProgressContractNotPrint(Boolean isProgressContractNotPrint) {
		this.isProgressContractNotPrint = isProgressContractNotPrint;
	}

	public Boolean getIsPlanNotPrint() {
		return isPlanNotPrint;
	}

	public void setIsPlanNotPrint(Boolean isPlanNotPrint) {
		this.isPlanNotPrint = isPlanNotPrint;
	}

	public Boolean getIsCurrentSectionNotPrint() {
		return isCurrentSectionNotPrint;
	}

	public void setIsCurrentSectionNotPrint(Boolean isCurrentSectionNotPrint) {
		this.isCurrentSectionNotPrint = isCurrentSectionNotPrint;
	}

	public Boolean getIsProgressSectionNotPrint() {
		return isProgressSectionNotPrint;
	}

	public void setIsProgressSectionNotPrint(Boolean isProgressSectionNotPrint) {
		this.isProgressSectionNotPrint = isProgressSectionNotPrint;
	}

	public Boolean getIsStatusNotPrint() {
		return isStatusNotPrint;
	}

	public void setIsStatusNotPrint(Boolean isStatusNotPrint) {
		this.isStatusNotPrint = isStatusNotPrint;
	}

	public String getUpdateDatetimeFrom() {
		return updateDatetimeFrom;
	}

	public void setUpdateDatetimeFrom(String updateDatetimeFrom) {
		this.updateDatetimeFrom = updateDatetimeFrom;
	}

	public String getUpdateDatetimeTo() {
		return updateDatetimeTo;
	}

	public void setUpdateDatetimeTo(String updateDatetimeTo) {
		this.updateDatetimeTo = updateDatetimeTo;
	}

	public String getUpdateDayFrom() {
		return updateDayFrom;
	}

	public void setUpdateDayFrom(String updateDayFrom) {
		this.updateDayFrom = updateDayFrom;
	}

	public String getUpdateDayTo() {
		return updateDayTo;
	}

	public void setUpdateDayTo(String updateDayTo) {
		this.updateDayTo = updateDayTo;
	}

	public Boolean getIsDefaultSearch() {
		return isDefaultSearch;
	}

	public void setIsDefaultSearch(Boolean isDefaultSearch) {
		this.isDefaultSearch = isDefaultSearch;
	}

	public static ProjectFindForm toDTO(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			return mapper.readValue(json, ProjectFindForm.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	public String defaultCondition() {
		ProjectFindForm dto = EMPTY;
		dto.setiShopCompanieIds(iShopCompanieIds);
		dto.setiBrandIds(iBrandIds);
		dto.setiAreaIds(iAreaIds);
		dto.setiPrefectureIds(iPrefectureIds);
		dto.setiBlockIds(iBlockIds);
		dto.setPlanPeriodUpDowns(planPeriodUpDowns);
		dto.setProjectCategoryIds(projectCategoryIds);
		dto.setLandingCategoryIds(landingCategoryIds);
		dto.setOfficeProjectProgressIds(officeProjectProgressIds);
		dto.setNegotiationProjectProgressIds(negotiationProjectProgressIds);
		dto.setImplementationPeriodUpDowns(implementationPeriodUpDowns);
		dto.setExpirationPeriodUpDowns(expirationPeriodUpDowns);
		dto.setOperationForms(operationForms);
		dto.setSalesChannelIds(salesChannelIds);
		dto.setStoreDevelopTeamCds(storeDevelopTeamCds);
		dto.setOtherAccountStoreIds(otherAccountStoreIds);
		dto.setUpdateDatetimeFrom(updateDatetimeFrom);
		dto.setUpdateDatetimeTo(updateDatetimeTo);
		dto.setUpdateDayFrom(updateDayFrom);
		dto.setUpdateDayTo(updateDayTo);
		dto.setSalesAchievementFrom(salesAchievementFrom);
		dto.setSalesAchievementTo(salesAchievementTo);
		dto.setSalesAchievementYearToYearFrom(salesAchievementYearToYearFrom);
		dto.setSalesAchievementYearToYearTo(salesAchievementYearToYearTo);
		dto.setOperatingProfitAchievementFrom(operatingProfitAchievementFrom);
		dto.setOperatingProfitAchievementTo(operatingProfitAchievementTo);
		dto.setOperatingProfitAchievementCompositionFrom(operatingProfitAchievementCompositionFrom);
		dto.setOperatingProfitAchievementCompositionTo(operatingProfitAchievementCompositionTo);
		dto.setBuildingExpectedValueFrom(buildingExpectedValueFrom);
		dto.setBuildingExpectedValueTo(buildingExpectedValueTo);
		dto.setOrderByOption(orderByOption);
		dto.setOrderByDesc(orderByDesc);
		dto.setJsonCurrentOtherAccountStore(jsonCurrentOtherAccountStore);
		return JsonParseUtils.parse(dto);
	}

	public Boolean getIsNewShop() {
		return isNewShop;
	}

	public void setIsNewShop(Boolean isNewShop) {
		this.isNewShop = isNewShop;
	}

	public Boolean getIsExistsShop() {
		return isExistsShop;
	}

	public void setIsExistsShop(Boolean isExistsShop) {
		this.isExistsShop = isExistsShop;
	}

	public Boolean getIsOtherShop() {
		return isOtherShop;
	}

	public void setIsOtherShop(Boolean isOtherShop) {
		this.isOtherShop = isOtherShop;
	}

	public List<Long> getLandingIds() {
		return landingIds;
	}

	public void setLandingIds(List<Long> landingIds) {
		this.landingIds = landingIds;
	}

	public List<Long> getiShopCompanieIds() {
		return iShopCompanieIds;
	}

	public void setiShopCompanieIds(List<Long> iShopCompanieIds) {
		this.iShopCompanieIds = iShopCompanieIds;
	}

	public List<Long> getiPrefectureIds() {
		return iPrefectureIds;
	}

	public void setiPrefectureIds(List<Long> iPrefectureIds) {
		this.iPrefectureIds = iPrefectureIds;
	}

	public List<String> getPlanPeriodUpDowns() {
		return planPeriodUpDowns;
	}

	public void setPlanPeriodUpDowns(List<String> planPeriodUpDowns) {
		this.planPeriodUpDowns = planPeriodUpDowns;
	}

	public List<Long> getProjectCategoryIds() {
		return projectCategoryIds;
	}

	public void setProjectCategoryIds(List<Long> projectCategoryIds) {
		this.projectCategoryIds = projectCategoryIds;
	}

	public List<Long> getLandingCategoryIds() {
		return landingCategoryIds;
	}

	public void setLandingCategoryIds(List<Long> landingCategoryIds) {
		this.landingCategoryIds = landingCategoryIds;
	}

	public List<Long> getOfficeProjectProgressIds() {
		return officeProjectProgressIds;
	}

	public void setOfficeProjectProgressIds(List<Long> officeProjectProgressIds) {
		this.officeProjectProgressIds = officeProjectProgressIds;
	}

	public List<Long> getNegotiationProjectProgressIds() {
		return negotiationProjectProgressIds;
	}

	public void setNegotiationProjectProgressIds(List<Long> negotiationProjectProgressIds) {
		this.negotiationProjectProgressIds = negotiationProjectProgressIds;
	}

	public List<String> getImplementationPeriodUpDowns() {
		return implementationPeriodUpDowns;
	}

	public void setImplementationPeriodUpDowns(List<String> implementationPeriodUpDowns) {
		this.implementationPeriodUpDowns = implementationPeriodUpDowns;
	}

	public List<String> getExpirationPeriodUpDowns() {
		return expirationPeriodUpDowns;
	}

	public void setExpirationPeriodUpDowns(List<String> expirationPeriodUpDowns) {
		this.expirationPeriodUpDowns = expirationPeriodUpDowns;
	}

	public List<String> getOperationForms() {
		return operationForms;
	}

	public void setOperationForms(List<String> operationForms) {
		this.operationForms = operationForms;
	}

	public List<Long> getSalesChannelIds() {
		return salesChannelIds;
	}

	public void setSalesChannelIds(List<Long> salesChannelIds) {
		this.salesChannelIds = salesChannelIds;
	}

	public List<String> getStoreDevelopTeamCds() {
		return storeDevelopTeamCds;
	}

	public void setStoreDevelopTeamCds(List<String> storeDevelopTeamCds) {
		this.storeDevelopTeamCds = storeDevelopTeamCds;
	}

	public List<Long> getOtherAccountStoreIds() {
		return otherAccountStoreIds;
	}

	public void setOtherAccountStoreIds(List<Long> otherAccountStoreIds) {
		this.otherAccountStoreIds = otherAccountStoreIds;
	}

	public String getJsonCurrentOtherAccountStore() {
		return jsonCurrentOtherAccountStore;
	}

	public void setJsonCurrentOtherAccountStore(String jsonCurrentOtherAccountStore) {
		this.jsonCurrentOtherAccountStore = jsonCurrentOtherAccountStore;
	}

	public String getSalesAchievementFrom() {
		return salesAchievementFrom;
	}

	public void setSalesAchievementFrom(String salesAchievementFrom) {
		this.salesAchievementFrom = salesAchievementFrom;
	}

	public String getSalesAchievementTo() {
		return salesAchievementTo;
	}

	public void setSalesAchievementTo(String salesAchievementTo) {
		this.salesAchievementTo = salesAchievementTo;
	}

	public String getSalesAchievementYearToYearFrom() {
		return salesAchievementYearToYearFrom;
	}

	public void setSalesAchievementYearToYearFrom(String salesAchievementYearToYearFrom) {
		this.salesAchievementYearToYearFrom = salesAchievementYearToYearFrom;
	}

	public String getSalesAchievementYearToYearTo() {
		return salesAchievementYearToYearTo;
	}

	public void setSalesAchievementYearToYearTo(String salesAchievementYearToYearTo) {
		this.salesAchievementYearToYearTo = salesAchievementYearToYearTo;
	}

	public String getOperatingProfitAchievementFrom() {
		return operatingProfitAchievementFrom;
	}

	public void setOperatingProfitAchievementFrom(String operatingProfitAchievementFrom) {
		this.operatingProfitAchievementFrom = operatingProfitAchievementFrom;
	}

	public String getOperatingProfitAchievementTo() {
		return operatingProfitAchievementTo;
	}

	public void setOperatingProfitAchievementTo(String operatingProfitAchievementTo) {
		this.operatingProfitAchievementTo = operatingProfitAchievementTo;
	}

	public String getOperatingProfitAchievementCompositionFrom() {
		return operatingProfitAchievementCompositionFrom;
	}

	public void setOperatingProfitAchievementCompositionFrom(String operatingProfitAchievementCompositionFrom) {
		this.operatingProfitAchievementCompositionFrom = operatingProfitAchievementCompositionFrom;
	}

	public String getOperatingProfitAchievementCompositionTo() {
		return operatingProfitAchievementCompositionTo;
	}

	public void setOperatingProfitAchievementCompositionTo(String operatingProfitAchievementCompositionTo) {
		this.operatingProfitAchievementCompositionTo = operatingProfitAchievementCompositionTo;
	}

	public String getBuildingExpectedValueFrom() {
		return buildingExpectedValueFrom;
	}

	public void setBuildingExpectedValueFrom(String buildingExpectedValueFrom) {
		this.buildingExpectedValueFrom = buildingExpectedValueFrom;
	}

	public String getBuildingExpectedValueTo() {
		return buildingExpectedValueTo;
	}

	public void setBuildingExpectedValueTo(String buildingExpectedValueTo) {
		this.buildingExpectedValueTo = buildingExpectedValueTo;
	}

	public String getOrderByOption() {
		return orderByOption;
	}

	public void setOrderByOption(String orderByOption) {
		this.orderByOption = orderByOption;
	}

	public Boolean getOrderByDesc() {
		return orderByDesc;
	}

	public void setOrderByDesc(Boolean orderByDesc) {
		this.orderByDesc = orderByDesc;
	}

	public Boolean getIsBasicNotPrint() {
		return isBasicNotPrint;
	}

	public void setIsBasicNotPrint(Boolean isBasicNotPrint) {
		this.isBasicNotPrint = isBasicNotPrint;
	}

	public Boolean getIsCorporationNotPrint() {
		return isCorporationNotPrint;
	}

	public void setIsCorporationNotPrint(Boolean isCorporationNotPrint) {
		this.isCorporationNotPrint = isCorporationNotPrint;
	}

	public Boolean getIsLandingNothing() {
		return isLandingNothing;
	}

	public void setIsLandingNothing(Boolean isLandingNothing) {
		this.isLandingNothing = isLandingNothing;
	}
}

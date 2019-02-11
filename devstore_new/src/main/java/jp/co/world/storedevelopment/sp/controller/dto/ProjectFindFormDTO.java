
package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.utils.JsonParseUtils;

/**
 * @author tienpv
 *
 */
@JsonIgnoreProperties({ "account", "keyWordHankaku", "keyWordZenkaku", "planPeriods", "implementationPeriods", "expirationPeriods" })
public class ProjectFindFormDTO extends FindFormDTO {

	public static final ProjectFindFormDTO EMPTY = new ProjectFindFormDTO();

	private List<NegotiationRelationDTO> iShopCompanies = new ArrayList<>();
	private List<NegotiationRelationDTO> iBrands = new ArrayList<>();
	private List<NegotiationRelationDTO> iAreas = new ArrayList<>();
	private List<NegotiationRelationDTO> iPrefectures = new ArrayList<>();
	private List<NegotiationRelationDTO> iBlocks = new ArrayList<>();
	private List<NegotiationRelationDTO> projectCategories = new ArrayList<>();
	private List<NegotiationRelationDTO> landingCategories = new ArrayList<>();
	private List<NegotiationRelationDTO> officeProjectProgresses = new ArrayList<>();
	private List<NegotiationRelationDTO> negotiationProjectProgresses = new ArrayList<>();
	private List<String> operationForms = new ArrayList<>();
	private List<NegotiationRelationDTO> salesChannels = new ArrayList<>();
	private List<NegotiationRelationDTO> storeDevelopTeams = new ArrayList<>();
	private List<NegotiationRelationDTO> otherAccountStores = new ArrayList<>();
	private List<KeyStringDTO> planPeriodUpDowns = new ArrayList<>();
	private List<KeyStringDTO> implementationPeriodUpDowns = new ArrayList<>();
	private List<KeyStringDTO> expirationPeriodUpDowns = new ArrayList<>();

	private List<Long> negotiationIds;
	private List<Long> buildingIds;

	private Boolean isOwn;
	private Boolean isReadLater;
	private Boolean isMonBefore;
	private Boolean isRunOnly;
	private Account account;

	private Boolean isNewShop;
	private Boolean isExistsShop;
	private Boolean isOtherShop;
	private Boolean isLandingNothing = true;
	private List<Long> landingIds;

	private String searchText = "";

	private Boolean isDefaultSearch = false;

	public ProjectFindFormDTO() {
	}

	public List<Long> accountIds() {
		return getAccount().commonAccount().stream().map(a -> {
			return a.getId();
		}).collect(Collectors.toList());
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchTextHankaku() {
		return HankakuUtils.toHankaku(searchText);
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

	public List<NegotiationRelationDTO> getiShopCompanies() {
		return iShopCompanies;
	}

	public void setiShopCompanies(List<NegotiationRelationDTO> iShopCompanies) {
		this.iShopCompanies = iShopCompanies;
	}

	public List<NegotiationRelationDTO> getiBrands() {
		return iBrands;
	}

	public void setiBrands(List<NegotiationRelationDTO> iBrands) {
		this.iBrands = iBrands;
	}

	public List<NegotiationRelationDTO> getiAreas() {
		return iAreas;
	}

	public void setiAreas(List<NegotiationRelationDTO> iAreas) {
		this.iAreas = iAreas;
	}

	public List<NegotiationRelationDTO> getiPrefectures() {
		return iPrefectures;
	}

	public void setiPrefectures(List<NegotiationRelationDTO> iPrefectures) {
		this.iPrefectures = iPrefectures;
	}

	public List<NegotiationRelationDTO> getiBlocks() {
		return iBlocks;
	}

	public void setiBlocks(List<NegotiationRelationDTO> iBlocks) {
		this.iBlocks = iBlocks;
	}

	public List<NegotiationRelationDTO> getProjectCategories() {
		return projectCategories;
	}

	public void setProjectCategories(List<NegotiationRelationDTO> projectCategories) {
		this.projectCategories = projectCategories;
	}

	public List<NegotiationRelationDTO> getLandingCategories() {
		return landingCategories;
	}

	public void setLandingCategories(List<NegotiationRelationDTO> landingCategories) {
		this.landingCategories = landingCategories;
	}

	public List<NegotiationRelationDTO> getOfficeProjectProgresses() {
		return officeProjectProgresses;
	}

	public void setOfficeProjectProgresses(List<NegotiationRelationDTO> officeProjectProgresses) {
		this.officeProjectProgresses = officeProjectProgresses;
	}

	public List<NegotiationRelationDTO> getNegotiationProjectProgresses() {
		return negotiationProjectProgresses;
	}

	public void setNegotiationProjectProgresses(List<NegotiationRelationDTO> negotiationProjectProgresses) {
		this.negotiationProjectProgresses = negotiationProjectProgresses;
	}

	public List<String> getOperationForms() {
		return operationForms;
	}

	public void setOperationForms(List<String> operationForms) {
		this.operationForms = operationForms;
	}

	public List<NegotiationRelationDTO> getSalesChannels() {
		return salesChannels;
	}

	public void setSalesChannels(List<NegotiationRelationDTO> salesChannels) {
		this.salesChannels = salesChannels;
	}

	public List<NegotiationRelationDTO> getStoreDevelopTeams() {
		return storeDevelopTeams;
	}

	public void setStoreDevelopTeams(List<NegotiationRelationDTO> storeDevelopTeams) {
		this.storeDevelopTeams = storeDevelopTeams;
	}

	public List<NegotiationRelationDTO> getOtherAccountStores() {
		return otherAccountStores;
	}

	public void setOtherAccountStores(List<NegotiationRelationDTO> otherAccountStores) {
		this.otherAccountStores = otherAccountStores;
	}

	public Boolean getIsDefaultSearch() {
		return isDefaultSearch;
	}

	public void setIsDefaultSearch(Boolean isDefaultSearch) {
		this.isDefaultSearch = isDefaultSearch;
	}

	public String defaultCondition() {
		ProjectFindFormDTO dto = EMPTY;

		dto.setiShopCompanies(iShopCompanies);
		dto.setiBrands(iBrands);
		dto.setiAreas(iAreas);
		dto.setiPrefectures(iPrefectures);
		dto.setiBlocks(iBlocks);
		dto.setPlanPeriodUpDowns(planPeriodUpDowns);
		dto.setProjectCategories(projectCategories);
		dto.setLandingCategories(landingCategories);
		dto.setOfficeProjectProgresses(officeProjectProgresses);
		dto.setNegotiationProjectProgresses(negotiationProjectProgresses);
		dto.setImplementationPeriodUpDowns(implementationPeriodUpDowns);
		dto.setExpirationPeriodUpDowns(expirationPeriodUpDowns);
		dto.setOperationForms(operationForms);
		dto.setSalesChannels(salesChannels);
		dto.setStoreDevelopTeams(storeDevelopTeams);
		dto.setOtherAccountStores(otherAccountStores);

		return JsonParseUtils.parse(dto);
	}

	public List<KeyStringDTO> getPlanPeriodUpDowns() {
		return planPeriodUpDowns;
	}

	public void setPlanPeriodUpDowns(List<KeyStringDTO> planPeriodUpDowns) {
		this.planPeriodUpDowns = planPeriodUpDowns;
	}

	public List<KeyStringDTO> getImplementationPeriodUpDowns() {
		return implementationPeriodUpDowns;
	}

	public void setImplementationPeriodUpDowns(List<KeyStringDTO> implementationPeriodUpDowns) {
		this.implementationPeriodUpDowns = implementationPeriodUpDowns;
	}

	public List<KeyStringDTO> getExpirationPeriodUpDowns() {
		return expirationPeriodUpDowns;
	}

	public void setExpirationPeriodUpDowns(List<KeyStringDTO> expirationPeriodUpDowns) {
		this.expirationPeriodUpDowns = expirationPeriodUpDowns;
	}

	public Boolean getIsLandingNothing() {
		return isLandingNothing;
	}

	public void setIsLandingNothing(Boolean isLandingNothing) {
		this.isLandingNothing = isLandingNothing;
	}

}

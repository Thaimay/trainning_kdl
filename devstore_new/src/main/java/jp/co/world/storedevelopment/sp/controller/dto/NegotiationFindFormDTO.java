package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.Account;

@JsonIgnoreProperties({ "accountId", "accountCode", "account", "keyWordHankaku" })
public class NegotiationFindFormDTO extends FindFormDTO {
	protected static final ObjectMapper MAPPER = new ObjectMapper();

	public static final NegotiationFindFormDTO EMPTY = new NegotiationFindFormDTO();

	private Boolean isOpened;

	private Boolean isScheduled = false;

	private Boolean isImplemented = true;

	private Boolean isOwn;

	private Boolean isReadLater;

	private String title;

	private String fillText;

	private List<Long> projectIds;

	private List<Long> buildingIds = Arrays.asList();

	private List<Long> shopIds = Arrays.asList();

	private List<Long> corporationIds = Arrays.asList();

	private List<Long> interviewIds = Arrays.asList();

	private List<Long> accountIds = Arrays.asList();

	private String interviewName;

	private String implementationStartDate;

	private String implementationEndDate;

	private List<String> division = Arrays.asList();

	private List<Long> updateAccountIds = Arrays.asList();

	private String orderByOption = "UPDATE_DATETIME_DESC";

	private Optional<Account> account = Optional.empty();

	private Boolean isDefaultSearch = false;

	private String corporationsJson;

	private String accountsJson;

	private String businessCardsJson;

	private String buildingJson;

	private String shopJson;

	private String updateAccountJson;

	public NegotiationFindFormDTO() {
	}

	public Boolean getIsScheduled() {
		return isScheduled;
	}

	public Boolean getIsImplemented() {
		return isImplemented;
	}

	public void setIsImplemented(Boolean isImplemented) {
		this.isImplemented = isImplemented;
	}

	public void setIsScheduled(Boolean isPlan) {
		isScheduled = isPlan;
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

	public String getFillText() {
		return fillText;
	}

	public void setFillText(String fillText) {
		this.fillText = fillText;
	}

	public List<Long> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<Long> projectIds) {
		this.projectIds = projectIds;
	}

	public List<Long> getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}

	public List<Long> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<Long> shopIds) {
		this.shopIds = shopIds;
	}

	public List<Long> getCorporationIds() {
		return corporationIds;
	}

	public void setCorporationIds(List<Long> companyIds) {
		corporationIds = companyIds;
	}

	public List<Long> getInterviewIds() {
		return interviewIds;
	}

	public void setInterviewIds(List<Long> interviewIds) {
		this.interviewIds = interviewIds;
	}

	public String getImplementationStartDate() {
		return implementationStartDate;
	}

	public void setImplementationStartDate(String implementationStartDate) {
		this.implementationStartDate = implementationStartDate;
	}

	public String getImplementationEndDate() {
		return implementationEndDate;
	}

	public void setImplementationEndDate(String implementationEndDate) {
		this.implementationEndDate = implementationEndDate;
	}

	public List<String> getDivision() {
		return division;
	}

	public void setDivision(List<String> division) {
		this.division = division;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String inteviewName) {
		interviewName = inteviewName;
	}

	public Account getAccount() {
		return account.isPresent() ? account.get() : null;
	}

	public void setAccount(Account account) {
		this.account = Optional.ofNullable(account);
	}

	public Long getAccountId() {
		return getAccount().getId();
	}

	public String getAccountCode() {
		return getAccount().getEmployeCode();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}

	public List<Long> getUpdateAccountIds() {
		return updateAccountIds;
	}

	public void setUpdateAccountIds(List<Long> updateAccountIds) {
		this.updateAccountIds = updateAccountIds;
	}

	public String getOrderByOption() {
		return orderByOption;
	}

	public void setOrderByOption(String orderByOption) {
		this.orderByOption = orderByOption;
	}

	public Boolean getIsDefaultSearch() {
		return isDefaultSearch;
	}

	public void setIsDefaultSearch(Boolean isDefaultSearch) {
		this.isDefaultSearch = isDefaultSearch;
	}

	public String toJSON() {
		try {
			return MAPPER.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
			return new JSONObject().toString();
		}
	}

	public static NegotiationFindFormDTO toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, NegotiationFindFormDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	public String getCorporationsJson() {
		return corporationsJson;
	}

	public void setCorporationsJson(String corporationsJson) {
		this.corporationsJson = corporationsJson;
	}

	public String getAccountsJson() {
		return accountsJson;
	}

	public void setAccountsJson(String accountsJson) {
		this.accountsJson = accountsJson;
	}

	public String getBusinessCardsJson() {
		return businessCardsJson;
	}

	public void setBusinessCardsJson(String businessCardsJson) {
		this.businessCardsJson = businessCardsJson;
	}

	public String getBuildingJson() {
		return buildingJson;
	}

	public void setBuildingJson(String buildingJson) {
		this.buildingJson = buildingJson;
	}

	public String getShopJson() {
		return shopJson;
	}

	public void setShopJson(String shopJson) {
		this.shopJson = shopJson;
	}

	public String getUpdateAccountJson() {
		return updateAccountJson;
	}

	public void setUpdateAccountJson(String updateAccountJson) {
		this.updateAccountJson = updateAccountJson;
	}

	public Boolean getIsOpened() {
		return isOpened;
	}

	public void setIsOpened(Boolean isOpened) {
		this.isOpened = isOpened;
	}
}

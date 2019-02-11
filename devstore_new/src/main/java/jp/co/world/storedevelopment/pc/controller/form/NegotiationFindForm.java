package jp.co.world.storedevelopment.pc.controller.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.pc.controller.dto.FindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ValueDTO;
import jp.co.world.storedevelopment.utils.JsonParseUtils;

@JsonIgnoreProperties({ "accountId", "accountCode", "account", "keyWordHankaku" })
public class NegotiationFindForm extends FindFormDTO {
	protected static final ObjectMapper MAPPER = new ObjectMapper();

	public static final NegotiationFindForm EMPTY = new NegotiationFindForm();

	private Boolean isOpened;

	private Boolean isScheduled = false;

	private Boolean isImplemented = true;

	private Boolean isOwn;

	private Boolean isReadLater;

	private String fillText;

	private String title;

	private List<Long> projectIds;

	private List<Long> buildingIds = new ArrayList<>();

	private List<Long> shopIds = new ArrayList<>();

	private List<Long> corporationIds = new ArrayList<>();

	private List<Long> interviewIds = new ArrayList<>();

	private List<Long> accountIds = new ArrayList<>();

	private String interviewName;

	private String implementationStartDate;

	private String implementationEndDate;

	private List<String> division = new ArrayList<>();

	private String corporationsJson;

	private String accountsJson;

	private String businessCardsJson;

	private String buildingJson;

	private String shopJson;

	private Optional<Account> account = Optional.empty();

	private List<Long> updateAccountIds = new ArrayList<>();

	private String updateAccountJson;

	private String orderByOption = "UPDATE_DATETIME_DESC";

	private Boolean isDefaultSearch = false;

	public NegotiationFindForm() {
	}

	public void loadJson() {
		setCorporationValuesJson(getCorporationIds());
		setAccountValuesJson(getAccountIds());
		setBusinessCardValuesJson(getInterviewIds());
		setBuildingValuesJson(getBuildingIds());
		setShopValuesJson(getShopIds());
		setUpdateAccountValuesJson(getUpdateAccountIds());
	}

	private void setCorporationValuesJson(List<Long> ids) {
		List<ValueDTO> dtos = ids.stream().map(id -> {
			ICorporation model = new ICorporationRepository().findById(id).orElseGet(() -> {
				throw new IllegalArgumentException("法人が存在しません");
			});
			return new ValueDTO(model.getId(), model.getCorporationName());
		}).collect(Collectors.toList());

		setCorporationsJson(JsonParseUtils.parse(dtos));
	}

	private void setAccountValuesJson(List<Long> ids) {
		List<ValueDTO> dtos = ids.stream().map(id -> {
			Account model = new AccountRepository().findById(id).orElseGet(() -> {
				throw new IllegalArgumentException("訪問者が存在しません");
			});
			return new ValueDTO(model.getId(), model.getFullName());
		}).collect(Collectors.toList());

		setAccountsJson(JsonParseUtils.parse(dtos));
	}

	private void setBusinessCardValuesJson(List<Long> ids) {
		List<ValueDTO> dtos = ids.stream().map(id -> {
			IBusinessCard model = new IBusinessCardRepository().findById(id).orElseGet(() -> {
				throw new IllegalArgumentException("面談者が存在しません");
			});
			return new ValueDTO(model.getId(), model.getFullValue());
		}).collect(Collectors.toList());

		setBusinessCardsJson(JsonParseUtils.parse(dtos));
	}

	private void setBuildingValuesJson(List<Long> ids) {
		List<ValueDTO> dtos = ids.stream().map(id -> {
			Building model = new BuildingRepository().findById(id).orElseGet(() -> {
				throw new IllegalArgumentException("館が存在しません");
			});
			return new ValueDTO(model.getId(), model.getName());
		}).collect(Collectors.toList());

		setBuildingJson(JsonParseUtils.parse(dtos));
	}

	private void setShopValuesJson(List<Long> ids) {
		List<ValueDTO> dtos = ids.stream().map(id -> {
			IShop model = new IShopRepository().findById(id).orElseGet(() -> {
				throw new IllegalArgumentException("館が存在しません");
			});
			return new ValueDTO(model.getId(), model.getShopNameZenkaku());
		}).collect(Collectors.toList());

		setShopJson(JsonParseUtils.parse(dtos));
	}

	private void setUpdateAccountValuesJson(List<Long> ids) {
		List<ValueDTO> dtos = ids.stream().map(id -> {
			Account model = new AccountRepository().findById(id).orElseGet(() -> {
				throw new IllegalArgumentException("");
			});
			return new ValueDTO(model.getId(), model.getFullName());
		}).collect(Collectors.toList());

		setUpdateAccountJson(JsonParseUtils.parse(dtos));
	}

	public Boolean getIsScheduled() {
		return isScheduled;
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
		return account.get();
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

	public List<Long> getUpdateAccountIds() {
		return updateAccountIds;
	}

	public void setUpdateAccountIds(List<Long> updateAccountIds) {
		this.updateAccountIds = updateAccountIds;
	}

	public String getUpdateAccountJson() {
		return updateAccountJson;
	}

	public void setUpdateAccountJson(String updateAccountJson) {
		this.updateAccountJson = updateAccountJson;
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

	public static NegotiationFindForm toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, NegotiationFindForm.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	public Boolean getIsOpened() {
		return isOpened;
	}

	public void setIsOpened(Boolean isOpened) {
		this.isOpened = isOpened;
	}

	public Boolean getIsImplemented() {
		return isImplemented;
	}

	public void setIsImplemented(Boolean isImplemented) {
		this.isImplemented = isImplemented;
	}
}

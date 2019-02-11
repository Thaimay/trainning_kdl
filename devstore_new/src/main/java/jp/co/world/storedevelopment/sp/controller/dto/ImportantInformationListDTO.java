package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.Role;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class ImportantInformationListDTO {
	private Long id;

	private String content;

	private String division;

	private Boolean isRead;

	private Boolean isNice;

	private int niceNumber;

	private String actionDate;

	private String createdDatetime;

	private List<String> corporationNames;

	private List<String> buildingNames;

	private CreateAccountDTO account = new CreateAccountDTO();

	private Boolean isDeleted = false;

	private Boolean canDelete;

	private Boolean canView;

	private String buildingName;

	private String corporationName;

	private String createdAccountCode;

	private final static String IMPORTANT_INFORMATION_EDITABLE = "IMPORTANT_INFORMATION_EDITABLE";
	private final static String IMPORTANT_INFORMATION = "IMPORTANT_INFORMATION";

	public ImportantInformationListDTO(ImportantInformation importantInformation, Account user) {
		Account account = new AccountRepository().findByCode(importantInformation.getUpdateAccountCode())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:"));
		setAccount(new CreateAccountDTO(account, importantInformation));
		setCreatedAccountCode(importantInformation.getCreatedAccountCode());

		setId(importantInformation.getId());
		setContent(importantInformation.getContent());
		setDivision(importantInformation.getDivision());

		setIsRead(importantInformation.isRead(user));
		setIsNice(importantInformation.isNice(user));
		setNiceNumber(importantInformation.niceNumber());
		setActionDate(importantInformation.getActionDate());
		setCreatedDatetime(importantInformation.getCreatedDate());

		List<String> corporationNameList = importantInformation.importantInformationCorporation().stream().map(i -> {
			return i.corporationName();
		}).collect(Collectors.toList());

		List<String> buildingNameList = importantInformation.importantInformationBuildings().stream().map(i -> {
			return i.getBuildingName();
		}).collect(Collectors.toList());

		String buildingNameConver = String.join("、", buildingNameList);
		String corporationNameConver = String.join("、", corporationNameList);
		setCorporationNames(corporationNameList);
		setCorporationName(corporationNameConver);

		setBuildingNames(buildingNameList);
		setBuildingName(buildingNameConver);

		setCanDelete(canDelete(importantInformation, user));
		setCanView(canView(importantInformation, user));
	}

	private Boolean canDelete(ImportantInformation important, Account accountCurrent) {

		if (accountCurrent == null || accountCurrent.getRole() == null) {
			return false;
		}
		Role role = accountCurrent.getRole();
		if (role.hasPermission(IMPORTANT_INFORMATION_EDITABLE) || role.hasPermission(IMPORTANT_INFORMATION)
				&& important.getCreatedAccountCode().equals(accountCurrent.getEmployeCode())) {
			return true;
		}

		return false;
	}

	private Boolean canView(ImportantInformation important, Account accountCurrent) {

		if (accountCurrent == null || accountCurrent.getRole() == null) {
			return false;
		}
		Role role = accountCurrent.getRole();
		if (role.hasPermission(IMPORTANT_INFORMATION_EDITABLE) || role.hasPermission(IMPORTANT_INFORMATION)
				|| createdAccountCode.equals(accountCurrent.getEmployeCode())) {
			return true;
		}

		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public CreateAccountDTO getAccount() {
		return account;
	}

	public void setAccount(CreateAccountDTO account) {
		this.account = account;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsNice() {
		return isNice;
	}

	public void setIsNice(Boolean isNice) {
		this.isNice = isNice;
	}

	public int getNiceNumber() {
		return niceNumber;
	}

	public void setNiceNumber(int niceNumber) {
		this.niceNumber = niceNumber;
	}

	public List<String> getCorporationNames() {
		return corporationNames;
	}

	public void setCorporationNames(List<String> corporationNames) {
		this.corporationNames = corporationNames;
	}

	public List<String> getBuildingNames() {
		return buildingNames;
	}

	public void setBuildingNames(List<String> buildingNames) {
		this.buildingNames = buildingNames;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public Boolean getCanView() {
		return canView;
	}

	public void setCanView(Boolean canView) {
		this.canView = canView;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

}

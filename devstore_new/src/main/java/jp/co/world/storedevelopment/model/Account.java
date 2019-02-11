package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.AccountModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountDataRepository;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.DepartmentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RoleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.SubAccountRepository;

public class Account extends IActiveModel<Account> implements UserDetails {

	private static final long serialVersionUID = 5462671968977865585L;

	public static final Account NULL = new Account(0, "__NULL__");

	public static final String SESSION = "LOGIN_ACCOUNT";
	public static final String USER_CODE = "USER_CODE";
	public static final String USER_NAME = "USER_NAME";
	public static final String USER_ROLE = "USER_ROLE";

	private String employeeCd = "";
	private String secondmentFormerEmployeeCd;
	private String fullName = "";
	private String kanaFullName = "";
	private String kanaFamilyName;
	private String kanaName;
	private String sexCd;
	private String birthdate;
	private String joiningDate;
	private String retirementDate;
	private BigDecimal retirementFlag;
	private String commonEmployeeCd;
	private String mailAddress = "";
	private String mobileAddress;
	private String mobilePhoneNumber;
	private String officeIssueDate;
	private String officeCd;
	private String officeAbbreviation;
	private String affiliationIssueDate;
	private String additionalPostDivision;
	private Integer deptRank = 0;
	private Long deptCd = 0L;
	private String affiliationName;
	private String affiliationAbbreviation;
	private String shopId;
	private String wspShopId;
	private String jobTitleIssueDate;
	private String jobTitleDivisionCd;
	private String jobTitleName;
	private BigDecimal jobTitleNumber;
	private String employeeDivisionIssueDate;
	private String employeeDivisionCd;
	private String employeeDivisionName;
	private String jobClassIssueDate;
	private String jobClassCd;
	private String jobClassName;
	private String responsibleJobCategoryIssueDate;
	private String responsibleJobCd;
	private String responsibleJobName;
	private String jobCategoryCd;
	private String jobCategoryName;
	private String jobCategoryRemarks;
	private String expensesDepartmentCd = "";
	private String adAccount;
	private String adCompany;
	private String adDepartment;
	private String stfConsignmentFormerTempingFormer;
	private String stfContractTargetDivision;
	private String stfContractType;
	private String position;
	private String dataType;
	private String flgSb;
	private String flgAd;
	private String flgCard;
	private String flgSbIntroduction;
	private String coordinationCreator;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreationTime;
	private String coordinationDeletingFlag;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationApplyingDate;
	private String coordinationUpdater;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateTime;

	private Role role = null;

	private Optional<SubAccount> subAccount = null;

	@Override
	public String getTableName() {
		return "I_" + super.getTableName();
	}

	private String[] ignoreFields = new String[] { "isRead", "username", "authorities", "systemAccount",
			"accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled", "role", "password",
			"department", "subAccount", "role", "employeCode", "dataUser", "accountData" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public Account() {
		//
	}

	public Account(long id, String code, String name) {
		this(id, code);
		fullName = name;
	}

	public Account(long id, String code) {
		this.id = id;
		employeeCd = code;
	}

	public Account(String name) {
		fullName = name;
	}

	public Account(String code, String mail, String password) {
		setEmployeeCd(code);
		setMailAddress(mail);
	}

	public Account(String code, String mail, String password, String name, String expensesDepartmentCode) {
		this(code, mail, password);
		setFullName(name);
		setExpensesDepartmentCd(expensesDepartmentCode);
	}

	public Account(String code, String name, String mail, String expensesDepartmentCode) {
		setEmployeeCd(code);
		setFullName(name);
		setMailAddress(mail);
		setExpensesDepartmentCd(expensesDepartmentCode);
	}
	
	public List<String> areaCodes() {
		return new AccountRepository().areaCodesByAccountId(getId());
	}

	@Override
	protected ModelMapper<Account> modelMapper(SqlSession session) {
		return session.getMapper(AccountModelMapper.class);
	}

	public String getEmployeCode() {
		return getEmployeeCd();
	}

	public String getEmployeeCd() {
		return employeeCd;
	}

	public void setEmployeeCd(String employeCode) {
		employeeCd = employeCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		fullName = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String email) {
		mailAddress = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getUsername() {
		return getEmployeeCd();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Boolean hasNegotiationCloseRole() {
		return getRole().hasPermission("NEGOTIATION_LV2_CLOSE")
				|| getRole().hasPermission("NEGOTIATION_LV2_CLOSE_LIMITED");
	}

	public Boolean hasRole() {
		return getRole() != null;
	}
	
	public Role getRole() {
		if (role == null) {
			Optional<Role> optRole = new RoleRepository().findByAccount(this);
			if (optRole.isPresent()) {
				role = optRole.get();

				System.out.println("==================== hit role =====================");
				System.out.println("role name: " + role.getName());
				System.out.println("==================== /hit role =====================");
			}
		}
		return role;
	}
	
	public List<Account> commonAccount() {
		return new AccountRepository().findCommon(this);
	}

	@Override
	public String getPassword() {
		return "";
	}

	public Integer getDeptRank() {
		return deptRank;
	}

	public void setDeptRank(Integer deptRank) {
		this.deptRank = deptRank;
	}

	public Long getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(Long deptCd) {
		this.deptCd = deptCd;
	}

	public String getExpensesDepartmentCd() {
		return expensesDepartmentCd;
	}

	public void setExpensesDepartmentCd(String expensesDepartmentCd) {
		this.expensesDepartmentCd = expensesDepartmentCd;
	}

	public Optional<Department> department() {
		return new DepartmentRepository().findByAccount(this);
	}

	public Optional<SubAccount> getSubAccount() {
		if (subAccount == null) {
			subAccount = new SubAccountRepository().findByEmployeeCode(getEmployeeCd());
		}
		return subAccount;
	}

	public RoleFinder createRoleFinder() {
		Optional<Department> opt = department();
		if (opt.isPresent()) {
			Department department = opt.get();
			return new RoleFinder(getEmployeeCd(), department.getIncomeUnitCd1(), department.getCompanyCd());
		}
		return new RoleFinder();
	}
	
	public List<String> companyCodeList() {
		return companyList().stream().map(c -> {
			return c.getCompanyCode();
		}).collect(Collectors.toList());
	}
	
	public List<ICompany> companyList() {
		List<ICompany> list = new ArrayList<>();
		Optional<ICompany> c = companys();
		List<ICompany> companys = subCompany();		
		if (c.isPresent()) {
			list.add(c.get());
		}

		for (Integer i = 0; i < companys.size(); i++) {
			list.add(companys.get(i));
		}
		return list;
	}
	
	public Optional<ICompany> companys() {
		Optional<Department> opt = department();
		
		if (opt.isPresent()) {
			String code = String.format("%02d", Integer.parseInt(opt.get().getCompanyCd().trim()));
			Optional<ICompany> model = new ICompanyRepository().findByCode(code);			
			
			if (model.isPresent()) {
				return model;
			} else {
				return Optional.ofNullable(null);
			}
		} else {
			return Optional.ofNullable(null);
		}
	}
	
	public List<ICompany> subCompany() {
		Optional<SubAccount> sub = getSubAccount();

		if (sub.isPresent()) {
			String code = sub.get().getCompanyCode();
			
			if (code.isEmpty()) {
				return new ArrayList<ICompany>();
			} else {
				code = Arrays.asList(code.split(",")).stream().map(c -> {
					return String.format("'%02d'", Integer.parseInt(c.trim()));
				}).collect(Collectors.joining(","));
				return new ICompanyRepository().findListByCode(code);
			}
		} else {
			return new ArrayList<ICompany>();			
		}
	}
	
	public String companyCd() {
		Optional<Department> opt = department();
		if (opt.isPresent()) {
			return opt.get().getCompanyCd().trim();
		} else {
			return null;
		}
	}

	public Boolean isMyCreate(String targetAccountCode) {
		if (targetAccountCode.equals(getEmployeCode())) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean usePhone() {
		Optional<SubAccount> sub = getSubAccount();
		
		if (sub.isPresent()) {
			return sub.get().getUsePhone();
		} else {
			return false;
		}
	}
	
	public String getKanaFullName() {
		return kanaFullName;
	}

	public void setKanaFullName(String kanaFullName) {
		this.kanaFullName = kanaFullName;
	}

	public String getSecondmentFormerEmployeeCd() {
		return secondmentFormerEmployeeCd;
	}

	public void setSecondmentFormerEmployeeCd(String secondmentFormerEmployeeCd) {
		this.secondmentFormerEmployeeCd = secondmentFormerEmployeeCd;
	}

	public String getKanaFamilyName() {
		return kanaFamilyName;
	}

	public void setKanaFamilyName(String kanaFamilyName) {
		this.kanaFamilyName = kanaFamilyName;
	}

	public String getKanaName() {
		return kanaName;
	}

	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}

	public BigDecimal getRetirementFlag() {
		return retirementFlag;
	}

	public void setRetirementFlag(BigDecimal retirementFlag) {
		this.retirementFlag = retirementFlag;
	}

	public String getCommonEmployeeCd() {
		return commonEmployeeCd;
	}

	public void setCommonEmployeeCd(String commonEmployeeCd) {
		this.commonEmployeeCd = commonEmployeeCd;
	}

	public String getMobileAddress() {
		return mobileAddress;
	}

	public void setMobileAddress(String mobileAddress) {
		this.mobileAddress = mobileAddress;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getOfficeIssueDate() {
		return officeIssueDate;
	}

	public void setOfficeIssueDate(String officeIssueDate) {
		this.officeIssueDate = officeIssueDate;
	}

	public String getOfficeCd() {
		return officeCd;
	}

	public void setOfficeCd(String officeCd) {
		this.officeCd = officeCd;
	}

	public String getOfficeAbbreviation() {
		return officeAbbreviation;
	}

	public void setOfficeAbbreviation(String officeAbbreviation) {
		this.officeAbbreviation = officeAbbreviation;
	}

	public String getAffiliationIssueDate() {
		return affiliationIssueDate;
	}

	public void setAffiliationIssueDate(String affiliationIssueDate) {
		this.affiliationIssueDate = affiliationIssueDate;
	}

	public String getAdditionalPostDivision() {
		return additionalPostDivision;
	}

	public void setAdditionalPostDivision(String additionalPostDivision) {
		this.additionalPostDivision = additionalPostDivision;
	}

	public String getAffiliationName() {
		return affiliationName;
	}

	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}

	public String getAffiliationAbbreviation() {
		return affiliationAbbreviation;
	}

	public void setAffiliationAbbreviation(String affiliationAbbreviation) {
		this.affiliationAbbreviation = affiliationAbbreviation;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getWspShopId() {
		return wspShopId;
	}

	public void setWspShopId(String wspShopId) {
		this.wspShopId = wspShopId;
	}

	public String getJobTitleIssueDate() {
		return jobTitleIssueDate;
	}

	public void setJobTitleIssueDate(String jobTitleIssueDate) {
		this.jobTitleIssueDate = jobTitleIssueDate;
	}

	public String getJobTitleDivisionCd() {
		return jobTitleDivisionCd;
	}

	public void setJobTitleDivisionCd(String jobTitleDivisionCd) {
		this.jobTitleDivisionCd = jobTitleDivisionCd;
	}

	public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

	public BigDecimal getJobTitleNumber() {
		return jobTitleNumber;
	}

	public void setJobTitleNumber(BigDecimal jobTitleNumber) {
		this.jobTitleNumber = jobTitleNumber;
	}

	public String getEmployeeDivisionIssueDate() {
		return employeeDivisionIssueDate;
	}

	public void setEmployeeDivisionIssueDate(String employeeDivisionIssueDate) {
		this.employeeDivisionIssueDate = employeeDivisionIssueDate;
	}

	public String getEmployeeDivisionCd() {
		return employeeDivisionCd;
	}

	public void setEmployeeDivisionCd(String employeeDivisionCd) {
		this.employeeDivisionCd = employeeDivisionCd;
	}

	public String getEmployeeDivisionName() {
		return employeeDivisionName;
	}

	public void setEmployeeDivisionName(String employeeDivisionName) {
		this.employeeDivisionName = employeeDivisionName;
	}

	public String getJobClassIssueDate() {
		return jobClassIssueDate;
	}

	public void setJobClassIssueDate(String jobClassIssueDate) {
		this.jobClassIssueDate = jobClassIssueDate;
	}

	public String getJobClassCd() {
		return jobClassCd;
	}

	public void setJobClassCd(String jobClassCd) {
		this.jobClassCd = jobClassCd;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getResponsibleJobCategoryIssueDate() {
		return responsibleJobCategoryIssueDate;
	}

	public void setResponsibleJobCategoryIssueDate(String responsibleJobCategoryIssueDate) {
		this.responsibleJobCategoryIssueDate = responsibleJobCategoryIssueDate;
	}

	public String getResponsibleJobCd() {
		return responsibleJobCd;
	}

	public void setResponsibleJobCd(String responsibleJobCd) {
		this.responsibleJobCd = responsibleJobCd;
	}

	public String getResponsibleJobName() {
		return responsibleJobName;
	}

	public void setResponsibleJobName(String responsibleJobName) {
		this.responsibleJobName = responsibleJobName;
	}

	public String getJobCategoryCd() {
		return jobCategoryCd;
	}

	public void setJobCategoryCd(String jobCategoryCd) {
		this.jobCategoryCd = jobCategoryCd;
	}

	public String getJobCategoryName() {
		return jobCategoryName;
	}

	public void setJobCategoryName(String jobCategoryName) {
		this.jobCategoryName = jobCategoryName;
	}

	public String getJobCategoryRemarks() {
		return jobCategoryRemarks;
	}

	public void setJobCategoryRemarks(String jobCategoryRemarks) {
		this.jobCategoryRemarks = jobCategoryRemarks;
	}

	public String getAdAccount() {
		return adAccount;
	}

	public void setAdAccount(String adAccount) {
		this.adAccount = adAccount;
	}

	public String getAdCompany() {
		return adCompany;
	}

	public void setAdCompany(String adCompany) {
		this.adCompany = adCompany;
	}

	public String getAdDepartment() {
		return adDepartment;
	}

	public void setAdDepartment(String adDepartment) {
		this.adDepartment = adDepartment;
	}

	public String getStfConsignmentFormerTempingFormer() {
		return stfConsignmentFormerTempingFormer;
	}

	public void setStfConsignmentFormerTempingFormer(String stfConsignmentFormerTempingFormer) {
		this.stfConsignmentFormerTempingFormer = stfConsignmentFormerTempingFormer;
	}

	public String getStfContractTargetDivision() {
		return stfContractTargetDivision;
	}

	public void setStfContractTargetDivision(String stfContractTargetDivision) {
		this.stfContractTargetDivision = stfContractTargetDivision;
	}

	public String getStfContractType() {
		return stfContractType;
	}

	public void setStfContractType(String stfContractType) {
		this.stfContractType = stfContractType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFlgSb() {
		return flgSb;
	}

	public void setFlgSb(String flgSb) {
		this.flgSb = flgSb;
	}

	public String getFlgAd() {
		return flgAd;
	}

	public void setFlgAd(String flgAd) {
		this.flgAd = flgAd;
	}

	public String getFlgCard() {
		return flgCard;
	}

	public void setFlgCard(String flgCard) {
		this.flgCard = flgCard;
	}

	public String getFlgSbIntroduction() {
		return flgSbIntroduction;
	}

	public void setFlgSbIntroduction(String flgSbIntroduction) {
		this.flgSbIntroduction = flgSbIntroduction;
	}

	public String getCoordinationCreator() {
		return coordinationCreator;
	}

	public void setCoordinationCreator(String coordinationCreator) {
		this.coordinationCreator = coordinationCreator;
	}

	public LocalDateTime getCoordinationCreationTime() {
		return coordinationCreationTime;
	}

	public void setCoordinationCreationTime(LocalDateTime coordinationCreationTime) {
		this.coordinationCreationTime = coordinationCreationTime;
	}

	public String getCoordinationDeletingFlag() {
		return coordinationDeletingFlag;
	}

	public void setCoordinationDeletingFlag(String coordinationDeletingFlag) {
		this.coordinationDeletingFlag = coordinationDeletingFlag;
	}

	public LocalDateTime getCoordinationApplyingDate() {
		return coordinationApplyingDate;
	}

	public void setCoordinationApplyingDate(LocalDateTime coordinationApplyingDate) {
		this.coordinationApplyingDate = coordinationApplyingDate;
	}

	public String getCoordinationUpdater() {
		return coordinationUpdater;
	}

	public void setCoordinationUpdater(String coordinationUpdater) {
		this.coordinationUpdater = coordinationUpdater;
	}

	public LocalDateTime getCoordinationUpdateTime() {
		return coordinationUpdateTime;
	}

	public void setCoordinationUpdateTime(LocalDateTime coordinationUpdateTime) {
		this.coordinationUpdateTime = coordinationUpdateTime;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setSubAccount(Optional<SubAccount> subAccount) {
		this.subAccount = subAccount;
	}

	public boolean canCreateImportant() {
		return getRole().hasPermission("IMPORTANT_INFORMATION_EDITABLE")
				|| getRole().hasPermission("IMPORTANT_INFORMATION");
	}

	public boolean canEditImportant(Negotiation n) {
		if (getRole().hasPermission("IMPORTANT_INFORMATION_EDITABLE")) {
			return true;
		} else if (getRole().hasPermission("IMPORTANT_INFORMATION")) {
			if (n.hasImportantInformation()) {
				return n.getImportantInformations().get(0).getCreatedAccountCode().equals(getEmployeCode());
			}
			return n.getCreatedAccountCode().equals(getEmployeCode());
		} else {
			return false;
		}
	}

	public Optional<AccountData> fetchAccountData() {
		if (id != null) {
			return new AccountDataRepository().findByAccountId(id);
		} else {
			return Optional.ofNullable(null);
		}
		
	}

	public boolean hasDataUser() {
		return fetchAccountData().isPresent();
	}

	public String fetchDataUser() {
		if (hasDataUser()) {
			return fetchAccountData().get().getDataUser();
		}
		return StringUtils.EMPTY;
	}

}
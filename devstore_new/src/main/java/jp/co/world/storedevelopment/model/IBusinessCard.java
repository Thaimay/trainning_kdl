package jp.co.world.storedevelopment.model;

import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IBusinessCardModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IBusinessCard extends ActiveModel<IBusinessCard> {

	private Long cardId;
	private String companyName;
	private String companyNameKana;
	private String departmentName;
	private String positionName;
	private String name;
	private String lastName;
	private String firstName;
	private String nameKana;
	private String firstNameKana;
	private String lastNameKana;
	private String email;
	private String zipCode;
	private String addressFull;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String telNumberCompany;
	private String telNumberDepartment;
	private String faxNumber;
	private String mobileNumber;
	private String companyUrl;
	private Integer cardOwnerId;
	private String cardOwnerName;
	private String cardOwnerFirstName;
	private String cardOwnerLastName;
	private String cardOwnerCompanyName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime contactDate;
	private String cardIndexNo;
	private Integer isPrivate;
	private Integer complianceFlg;

	private static String[] ignoreFields = new String[] {"fullValue"};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public IBusinessCard() {

	}

	public IBusinessCard(String name) {
		this.setCorporationGroup("001");
		this.setCardId(1L);
		this.setCompanyName("会社名");
		this.setDepartmentName("部署");
		this.setPositionName("役職名");
		this.setName(name);
		this.setEmail("メールアドレス");
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}


	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNameKana() {
		return companyNameKana;
	}

	public void setCompanyNameKana(String companyNameKana) {
		this.companyNameKana = companyNameKana;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNameKana() {
		return nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getLastNameKana() {
		return lastNameKana;
	}

	public void setLastNameKana(String lastNameKana) {
		this.lastNameKana = lastNameKana;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddressFull() {
		return addressFull;
	}

	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getTelNumberCompany() {
		return telNumberCompany;
	}

	public void setTelNumberCompany(String telNumberCompany) {
		this.telNumberCompany = telNumberCompany;
	}

	public String getTelNumberDepartment() {
		return telNumberDepartment;
	}

	public void setTelNumberDepartment(String telNumberDepartment) {
		this.telNumberDepartment = telNumberDepartment;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public Integer getCardOwnerId() {
		return cardOwnerId;
	}

	public void setCardOwnerId(Integer cardOwnerId) {
		this.cardOwnerId = cardOwnerId;
	}

	public String getCardOwnerName() {
		return cardOwnerName;
	}

	public void setCardOwnerName(String cardOwnerName) {
		this.cardOwnerName = cardOwnerName;
	}

	public String getCardOwnerFirstName() {
		return cardOwnerFirstName;
	}

	public void setCardOwnerFirstName(String cardOwnerFirstName) {
		this.cardOwnerFirstName = cardOwnerFirstName;
	}

	public String getCardOwnerLastName() {
		return cardOwnerLastName;
	}

	public void setCardOwnerLastName(String cardOwnerLastName) {
		this.cardOwnerLastName = cardOwnerLastName;
	}

	public String getCardOwnerCompanyName() {
		return cardOwnerCompanyName;
	}

	public void setCardOwnerCompanyName(String cardOwnerCompanyName) {
		this.cardOwnerCompanyName = cardOwnerCompanyName;
	}

	public LocalDateTime getContactDate() {
		return contactDate;
	}

	public void setContactDate(LocalDateTime contactDate) {
		this.contactDate = contactDate;
	}

	public String getCardIndexNo() {
		return cardIndexNo;
	}

	public void setCardIndexNo(String cardIndexNo) {
		this.cardIndexNo = cardIndexNo;
	}

	public Integer getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Integer isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Integer getComplianceFlg() {
		return complianceFlg;
	}

	public void setComplianceFlg(Integer complianceFlg) {
		this.complianceFlg = complianceFlg;
	}

	@Override
	protected ModelMapper<IBusinessCard> modelMapper(SqlSession session) {
		return session.getMapper(IBusinessCardModelMapper.class);
	}

	public String getFullValue() {
		return String.format("%s %s %s %s", getCompanyName(), getDepartmentName(), getName(), getPositionName());
	}

}

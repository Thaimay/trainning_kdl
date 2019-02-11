package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ICompanyModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ICompany extends IActiveModel<ICompany> {

	private String companyCode;
	private String companyKanaName;
	private String companyKanjiName;
	private String addressKanji1;
	private String addressKanji2;
	private String addressKanji3;
	private String addressKana1;
	private String addressKana2;
	private String addressKana3;
	private String postalCode;
	private String phoneNumber;
	private String companyDivision;
	private String flagNonTargetAppropriating;
	private BigDecimal lockNumber;
	private String registrantCode;
	private String registrationTime;
	private String registrationPgid;
	private String registrationTransactionId;
	private String coordinationCreator;
	private String coordinationCreationTime;
	private String coordinationDeletingFlag;
	private String coordinationApplyingDate;
	private String coordinationUpdater;
	private String coordinationUpdateTime;

	public ICompany() {

	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyKanaName() {
		return companyKanaName;
	}

	public void setCompanyKanaName(String companyKanaName) {
		this.companyKanaName = companyKanaName;
	}

	public String getCompanyKanjiName() {
		return companyKanjiName;
	}

	public void setCompanyKanjiName(String companyKanjiName) {
		this.companyKanjiName = companyKanjiName;
	}

	public String getAddressKanji1() {
		return addressKanji1;
	}

	public void setAddressKanji1(String addressKanji1) {
		this.addressKanji1 = addressKanji1;
	}

	public String getAddressKanji2() {
		return addressKanji2;
	}

	public void setAddressKanji2(String addressKanji2) {
		this.addressKanji2 = addressKanji2;
	}

	public String getAddressKanji3() {
		return addressKanji3;
	}

	public void setAddressKanji3(String addressKanji3) {
		this.addressKanji3 = addressKanji3;
	}

	public String getAddressKana1() {
		return addressKana1;
	}

	public void setAddressKana1(String addressKana1) {
		this.addressKana1 = addressKana1;
	}

	public String getAddressKana2() {
		return addressKana2;
	}

	public void setAddressKana2(String addressKana2) {
		this.addressKana2 = addressKana2;
	}

	public String getAddressKana3() {
		return addressKana3;
	}

	public void setAddressKana3(String addressKana3) {
		this.addressKana3 = addressKana3;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyDivision() {
		return companyDivision;
	}

	public void setCompanyDivision(String companyDivision) {
		this.companyDivision = companyDivision;
	}

	public String getFlagNonTargetAppropriating() {
		return flagNonTargetAppropriating;
	}

	public void setFlagNonTargetAppropriating(String flagNonTargetAppropriating) {
		this.flagNonTargetAppropriating = flagNonTargetAppropriating;
	}

	public BigDecimal getLockNumber() {
		return lockNumber;
	}

	public void setLockNumber(BigDecimal lockNumber) {
		this.lockNumber = lockNumber;
	}

	public String getRegistrantCode() {
		return registrantCode;
	}

	public void setRegistrantCode(String registrantCode) {
		this.registrantCode = registrantCode;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getRegistrationPgid() {
		return registrationPgid;
	}

	public void setRegistrationPgid(String registrationPgid) {
		this.registrationPgid = registrationPgid;
	}

	public String getRegistrationTransactionId() {
		return registrationTransactionId;
	}

	public void setRegistrationTransactionId(String registrationTransactionId) {
		this.registrationTransactionId = registrationTransactionId;
	}

	public String getCoordinationCreator() {
		return coordinationCreator;
	}

	public void setCoordinationCreator(String coordinationCreator) {
		this.coordinationCreator = coordinationCreator;
	}

	public String getCoordinationCreationTime() {
		return coordinationCreationTime;
	}

	public void setCoordinationCreationTime(String coordinationCreationTime) {
		this.coordinationCreationTime = coordinationCreationTime;
	}

	public String getCoordinationDeletingFlag() {
		return coordinationDeletingFlag;
	}

	public void setCoordinationDeletingFlag(String coordinationDeletingFlag) {
		this.coordinationDeletingFlag = coordinationDeletingFlag;
	}

	public String getCoordinationApplyingDate() {
		return coordinationApplyingDate;
	}

	public void setCoordinationApplyingDate(String coordinationApplyingDate) {
		this.coordinationApplyingDate = coordinationApplyingDate;
	}

	public String getCoordinationUpdater() {
		return coordinationUpdater;
	}

	public void setCoordinationUpdater(String coordinationUpdater) {
		this.coordinationUpdater = coordinationUpdater;
	}

	public String getCoordinationUpdateTime() {
		return coordinationUpdateTime;
	}

	public void setCoordinationUpdateTime(String coordinationUpdateTime) {
		this.coordinationUpdateTime = coordinationUpdateTime;
	}

	@Override
	protected ModelMapper<ICompany> modelMapper(SqlSession session) {
		return session.getMapper(ICompanyModelMapper.class);
	}

}

package jp.co.world.storedevelopment.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IRentContractModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IContractTypeRepository;
import jp.co.world.storedevelopment.utils.DateParseUtil;

/**
 * @author hungdh
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IRentContract extends IActiveModel<IRentContract> {

	private Long rentContractId;
	private Long shopId;
	private Long contractKindId;
	private Long contractTypeId;
	private String postCd;
	private String prefecturesName;
	private String cityName;
	private String townNameAddress;
	private String buildingName;
	private String floorNum;
	private String address1;
	private String address2;
	private String address;
	private String addressOld;
	private String startDate;
	private String endDate;
	private String cancelDate;
	private Long holderCompanyId;
	private String contractTargetName;
	private Long paymentMethodId;
	private String autoUpdatingFlag;
	private Integer notificationDeadline;
	private String cancelPropriety;
	private Integer cancelOfferDeadline;
	private String cancelWritingFlag;
	private String cancelPenaltyFlag;
	private String cancelCondition;
	private String closingDate1;
	private String closingDate2;
	private String closingDate3;
	private String closingDate4;
	private String closingDate5;
	private String closingDate6;
	private String collectionMonth1;
	private String collectionMonth2;
	private String collectionMonth3;
	private String collectionMonth4;
	private String collectionMonth5;
	private String collectionMonth6;
	private String collectionDay1;
	private String collectionDay2;
	private String collectionDay3;
	private String collectionDay4;
	private String collectionDay5;
	private String collectionDay6;
	private Integer contractPeriod;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

	@Override
	protected ModelMapper<IRentContract> modelMapper(SqlSession session) {
		return session.getMapper(IRentContractModelMapper.class);
	}

	public IRentContract() {
	}
	
	public String contractType() {
		Optional<IContractType> type =  new IContractTypeRepository().findById(getContractTypeId());
		if (type.isPresent()) {
			return type.get().getContractTypeName();
		} else {
			return "";
		}
	}

	public LocalDate startDateValue() {
		String value = getStartDate();
		
		if (value.isEmpty()) {
			return null;
		} else {
			return DateParseUtil.parse(formatter, value);
		}
	}
	
	public LocalDate endDateValue() {
		String value = getEndDate();
		
		if (value.isEmpty() || value.equals("99999999")) {
			return null;
		} else {
			return DateParseUtil.parse(formatter, value);
		}		
	}
	
	public IRentContract(String name) {
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeeCd());
	}

	public Long getRentContractId() {
		return rentContractId;
	}

	public void setRentContractId(Long rentContractId) {
		this.rentContractId = rentContractId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getContractKindId() {
		return contractKindId;
	}

	public void setContractKindId(Long contractKindId) {
		this.contractKindId = contractKindId;
	}

	public Long getContractTypeId() {
		return contractTypeId;
	}

	public void setContractTypeId(Long contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	public String getPostCd() {
		return postCd;
	}

	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}

	public String getPrefecturesName() {
		return prefecturesName;
	}

	public void setPrefecturesName(String prefecturesName) {
		this.prefecturesName = prefecturesName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTownNameAddress() {
		return townNameAddress;
	}

	public void setTownNameAddress(String townNameAddress) {
		this.townNameAddress = townNameAddress;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressOld() {
		return addressOld;
	}

	public void setAddressOld(String addressOld) {
		this.addressOld = addressOld;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Long getHolderCompanyId() {
		return holderCompanyId;
	}

	public void setHolderCompanyId(Long holderCompanyId) {
		this.holderCompanyId = holderCompanyId;
	}

	public String getContractTargetName() {
		return contractTargetName;
	}

	public void setContractTargetName(String contractTargetName) {
		this.contractTargetName = contractTargetName;
	}

	public Long getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getAutoUpdatingFlag() {
		return autoUpdatingFlag;
	}

	public void setAutoUpdatingFlag(String autoUpdatingFlag) {
		this.autoUpdatingFlag = autoUpdatingFlag;
	}

	public Integer getNotificationDeadline() {
		return notificationDeadline;
	}

	public void setNotificationDeadline(Integer notificationDeadline) {
		this.notificationDeadline = notificationDeadline;
	}

	public String getCancelPropriety() {
		return cancelPropriety;
	}

	public void setCancelPropriety(String cancelPropriety) {
		this.cancelPropriety = cancelPropriety;
	}

	public Integer getCancelOfferDeadline() {
		return cancelOfferDeadline;
	}

	public void setCancelOfferDeadline(Integer cancelOfferDeadline) {
		this.cancelOfferDeadline = cancelOfferDeadline;
	}

	public String getCancelWritingFlag() {
		return cancelWritingFlag;
	}

	public void setCancelWritingFlag(String cancelWritingFlag) {
		this.cancelWritingFlag = cancelWritingFlag;
	}

	public String getCancelPenaltyFlag() {
		return cancelPenaltyFlag;
	}

	public void setCancelPenaltyFlag(String cancelPenaltyFlag) {
		this.cancelPenaltyFlag = cancelPenaltyFlag;
	}

	public String getCancelCondition() {
		return cancelCondition;
	}

	public void setCancelCondition(String cancelCondition) {
		this.cancelCondition = cancelCondition;
	}

	public String getClosingDate1() {
		return closingDate1;
	}

	public void setClosingDate1(String closingDate1) {
		this.closingDate1 = closingDate1;
	}

	public String getClosingDate2() {
		return closingDate2;
	}

	public void setClosingDate2(String closingDate2) {
		this.closingDate2 = closingDate2;
	}

	public String getClosingDate3() {
		return closingDate3;
	}

	public void setClosingDate3(String closingDate3) {
		this.closingDate3 = closingDate3;
	}

	public String getClosingDate4() {
		return closingDate4;
	}

	public void setClosingDate4(String closingDate4) {
		this.closingDate4 = closingDate4;
	}

	public String getClosingDate5() {
		return closingDate5;
	}

	public void setClosingDate5(String closingDate5) {
		this.closingDate5 = closingDate5;
	}

	public String getClosingDate6() {
		return closingDate6;
	}

	public void setClosingDate6(String closingDate6) {
		this.closingDate6 = closingDate6;
	}

	public String getCollectionMonth1() {
		return collectionMonth1;
	}

	public void setCollectionMonth1(String collectionMonth1) {
		this.collectionMonth1 = collectionMonth1;
	}

	public String getCollectionMonth2() {
		return collectionMonth2;
	}

	public void setCollectionMonth2(String collectionMonth2) {
		this.collectionMonth2 = collectionMonth2;
	}

	public String getCollectionMonth3() {
		return collectionMonth3;
	}

	public void setCollectionMonth3(String collectionMonth3) {
		this.collectionMonth3 = collectionMonth3;
	}

	public String getCollectionMonth4() {
		return collectionMonth4;
	}

	public void setCollectionMonth4(String collectionMonth4) {
		this.collectionMonth4 = collectionMonth4;
	}

	public String getCollectionMonth5() {
		return collectionMonth5;
	}

	public void setCollectionMonth5(String collectionMonth5) {
		this.collectionMonth5 = collectionMonth5;
	}

	public String getCollectionMonth6() {
		return collectionMonth6;
	}

	public void setCollectionMonth6(String collectionMonth6) {
		this.collectionMonth6 = collectionMonth6;
	}

	public String getCollectionDay1() {
		return collectionDay1;
	}

	public void setCollectionDay1(String collectionDay1) {
		this.collectionDay1 = collectionDay1;
	}

	public String getCollectionDay2() {
		return collectionDay2;
	}

	public void setCollectionDay2(String collectionDay2) {
		this.collectionDay2 = collectionDay2;
	}

	public String getCollectionDay3() {
		return collectionDay3;
	}

	public void setCollectionDay3(String collectionDay3) {
		this.collectionDay3 = collectionDay3;
	}

	public String getCollectionDay4() {
		return collectionDay4;
	}

	public void setCollectionDay4(String collectionDay4) {
		this.collectionDay4 = collectionDay4;
	}

	public String getCollectionDay5() {
		return collectionDay5;
	}

	public void setCollectionDay5(String collectionDay5) {
		this.collectionDay5 = collectionDay5;
	}

	public String getCollectionDay6() {
		return collectionDay6;
	}

	public void setCollectionDay6(String collectionDay6) {
		this.collectionDay6 = collectionDay6;
	}

	public Integer getContractPeriod() {
		return contractPeriod;
	}

	public void setContractPeriod(Integer contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public LocalDateTime getCoordinationCreatedDatetime() {
		return coordinationCreatedDatetime;
	}

	public void setCoordinationCreatedDatetime(LocalDateTime coordinationCreatedDatetime) {
		this.coordinationCreatedDatetime = coordinationCreatedDatetime;
	}

	public String getCoordinationCreatedAccountCode() {
		return coordinationCreatedAccountCode;
	}

	public void setCoordinationCreatedAccountCode(String coordinationCreatedAccountCode) {
		this.coordinationCreatedAccountCode = coordinationCreatedAccountCode;
	}

	public LocalDateTime getCoordinationUpdateDatetime() {
		return coordinationUpdateDatetime;
	}

	public void setCoordinationUpdateDatetime(LocalDateTime coordinationUpdateDatetime) {
		this.coordinationUpdateDatetime = coordinationUpdateDatetime;
	}

	public String getCoordinationUpdateAccountCode() {
		return coordinationUpdateAccountCode;
	}

	public void setCoordinationUpdateAccountCode(String coordinationUpdateAccountCode) {
		this.coordinationUpdateAccountCode = coordinationUpdateAccountCode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.IYakataModelMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IYakata extends IActiveModel<IYakata> {

	private Long buildingId;
	private String buildingCd;
	private String name;
	private String postalCode;
	private String jisPrefecturesCd;
	private String jisAreaCd;
	private String prefecturesName;
	private String cityName;
	private String townNameAddress;
	private String buildingName;
	private String address;
	private String oldAddress;
	private String buildingRepresentativePhoneNumber;
	private String transportationFacilitiesName;
	private String nearestStation;
	private String businessHours;
	private String regularHoliday;
	private String salesChannelId;
	private Long corporationId;
	private Long buildingRank;
	private Long blockId;
	private Long scTypeId;
	private String openDate;
	private String closeDate;
	private String aseismaticConstructionYn;
	private String coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	private String coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingCd() {
		return buildingCd;
	}

	public void setBuildingCd(String buildingCd) {
		this.buildingCd = buildingCd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getJisPrefecturesCd() {
		return jisPrefecturesCd;
	}

	public void setJisPrefecturesCd(String jisPrefecturesCd) {
		this.jisPrefecturesCd = jisPrefecturesCd;
	}

	public String getJisAreaCd() {
		return jisAreaCd;
	}

	public void setJisAreaCd(String jisAreaCd) {
		this.jisAreaCd = jisAreaCd;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOldAddress() {
		return oldAddress;
	}

	public void setOldAddress(String oldAddress) {
		this.oldAddress = oldAddress;
	}

	public String getBuildingRepresentativePhoneNumber() {
		return buildingRepresentativePhoneNumber;
	}

	public void setBuildingRepresentativePhoneNumber(String buildingRepresentativePhoneNumber) {
		this.buildingRepresentativePhoneNumber = buildingRepresentativePhoneNumber;
	}

	public String getTransportationFacilitiesName() {
		return transportationFacilitiesName;
	}

	public void setTransportationFacilitiesName(String transportationFacilitiesName) {
		this.transportationFacilitiesName = transportationFacilitiesName;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public String getRegularHoliday() {
		return regularHoliday;
	}

	public void setRegularHoliday(String regularHoliday) {
		this.regularHoliday = regularHoliday;
	}

	public String getSalesChannelId() {
		return salesChannelId;
	}

	public void setSalesChannelId(String salesChannelId) {
		this.salesChannelId = salesChannelId;
	}

	public Long getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}

	public Long getBuildingRank() {
		return buildingRank;
	}

	public void setBuildingRank(Long buildingRank) {
		this.buildingRank = buildingRank;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Long getScTypeId() {
		return scTypeId;
	}

	public void setScTypeId(Long scTypeId) {
		this.scTypeId = scTypeId;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getAseismaticConstructionYn() {
		return aseismaticConstructionYn;
	}

	public void setAseismaticConstructionYn(String aseismaticConstructionYn) {
		this.aseismaticConstructionYn = aseismaticConstructionYn;
	}

	public String getCoordinationCreatedDatetime() {
		return coordinationCreatedDatetime;
	}

	public void setCoordinationCreatedDatetime(String coordinationCreatedDatetime) {
		this.coordinationCreatedDatetime = coordinationCreatedDatetime;
	}

	public String getCoordinationCreatedAccountCode() {
		return coordinationCreatedAccountCode;
	}

	public void setCoordinationCreatedAccountCode(String coordinationCreatedAccountCode) {
		this.coordinationCreatedAccountCode = coordinationCreatedAccountCode;
	}

	public String getCoordinationUpdateDatetime() {
		return coordinationUpdateDatetime;
	}

	public void setCoordinationUpdateDatetime(String coordinationUpdateDatetime) {
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

	@Override
	protected ModelMapper<IYakata> modelMapper(SqlSession session) {
		return session.getMapper(IYakataModelMapper.class);
	}

}

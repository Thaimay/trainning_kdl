package jp.co.world.storedevelopment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.dev.RandomTestData;
import jp.co.world.storedevelopment.model.mapper.IPlaceModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class IPlace extends IActiveModel<IPlace> {

	private Long placeId;
	private String placeName;
	private Long originBuildingId;
	private String postalCd;
	private String jisPrefecturesCd;
	private String cityName;
	private String townNameAddress;
	private String buildingName;
	private String floorNum;
	private String salesFloorAddress;
	private BigDecimal tsuboNum;
	private String startDate;
	private String endDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationCreatedDatetime;
	private String coordinationCreatedAccountCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime coordinationUpdateDatetime;
	private String coordinationUpdateAccountCode;
	private String action;

	public IPlace() {

	}

	public IPlace(String name) {
		this.setPlaceId(Long.valueOf(RandomTestData.getInteger(1, 9)));
		this.setPlaceName(name);
		this.setOriginBuildingId(1L);
		this.setCreatedAccountCode(new AccountRepository().getHead().get().getEmployeCode());
		this.setUpdateAccountCode(new AccountRepository().getHead().get().getEmployeCode());
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Long getOriginBuildingId() {
		return originBuildingId;
	}

	public void setOriginBuildingId(Long originBuildingId) {
		this.originBuildingId = originBuildingId;
	}

	public String getPostalCd() {
		return postalCd;
	}

	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}

	public String getJisPrefecturesCd() {
		return jisPrefecturesCd;
	}

	public void setJisPrefecturesCd(String jisPrefecturesCd) {
		this.jisPrefecturesCd = jisPrefecturesCd;
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

	public String getSalesFloorAddress() {
		return salesFloorAddress;
	}

	public void setSalesFloorAddress(String salesFloorAddress) {
		this.salesFloorAddress = salesFloorAddress;
	}

	public BigDecimal getTsuboNum() {
		return tsuboNum;
	}

	public void setTsuboNum(BigDecimal tsuboNum) {
		this.tsuboNum = tsuboNum;
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

	@Override
	protected ModelMapper<IPlace> modelMapper(SqlSession session) {
		return session.getMapper(IPlaceModelMapper.class);
	}

}

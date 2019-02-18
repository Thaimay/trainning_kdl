package jp.co.world.storedevelopment.model;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ShopModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class Shop extends ActiveModel<Shop> {

	private Long iShopId;
	private String section;
	private String frontage;
	private Long iSalesAgencyTargetId;
	private Long participatingStoreCorporationId;
	private Integer buildingExpectedValue;
	private String remarks;
	private String imagePath;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentEndDate;
	private Float rentYear;
	private String businessHours;

	private String[] ignoreFields = new String[] {};

	public IShop iShop() {
		return new IShopRepository().findById(getiShopId()).orElseGet(() -> {
			throw new IllegalStateException("店舗が存在しません");
		});
	}

	public Shop() {
	}

	public Shop(IShop shop) {
		setiShopId(shop.getId());
	}

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public Optional<ParticipatingStoreCorporation> corporation() {
		return new ParticipatingStoreCorporationRepository().findById(getParticipatingStoreCorporationId());
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public Long getiShopId() {
		return iShopId;
	}

	public void setiShopId(Long iShopId) {
		this.iShopId = iShopId;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getFrontage() {
		return frontage;
	}

	public void setFrontage(String frontage) {
		this.frontage = frontage;
	}

	public Long getiSalesAgencyTargetId() {
		return iSalesAgencyTargetId;
	}

	public void setiSalesAgencyTargetId(Long iSalesAgencyTargetId) {
		this.iSalesAgencyTargetId = iSalesAgencyTargetId;
	}

	public Long getParticipatingStoreCorporationId() {
		return participatingStoreCorporationId;
	}

	public void setParticipatingStoreCorporationId(Long participatingStoreCorporationId) {
		this.participatingStoreCorporationId = participatingStoreCorporationId;
	}

	public Integer getBuildingExpectedValue() {
		return buildingExpectedValue;
	}

	public void setBuildingExpectedValue(Integer buildingExpectedValue) {
		this.buildingExpectedValue = buildingExpectedValue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public LocalDate getRentStartDate() {
		return rentStartDate;
	}

	public void setRentStartDate(LocalDate rentStartDate) {
		this.rentStartDate = rentStartDate;
	}

	public LocalDate getRentEndDate() {
		return rentEndDate;
	}

	public void setRentEndDate(LocalDate rentEndDate) {
		this.rentEndDate = rentEndDate;
	}

	public Float getRentYear() {
		return rentYear;
	}

	public void setRentYear(Float rentYear) {
		this.rentYear = rentYear;
	}

	@Override
	protected ModelMapper<Shop> modelMapper(SqlSession session) {
		return session.getMapper(ShopModelMapper.class);
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	
}
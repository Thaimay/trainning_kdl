package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopFile;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.mapper.repository.ShopFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopImageRepository;

public class ShopUpdateDTO implements DTO<Shop> {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private Long id;
	private Long iShopId;
	private String section;
	private String frontage;
	private Long iSalesAgencyTargetId;
	private Long participatingStoreCorporationId;
	private Integer buildingExpectedValue;
	private String remarks;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentEndDate;
	private Float rentYear;
	private String businessHours;

	private List<ShopImage> shopImageDto = new ArrayList<>();
	private List<ShopFile> shopFileDto = new ArrayList<>();

	public ShopUpdateDTO() {

	}

	public ShopUpdateDTO(Shop shop) {
		this.copyProperties(this, shop);
		this.setShopImageDto(new ShopImageRepository().findByShopId(this.getId()));
		this.setShopFileDto(new ShopFileRepository().findByShopId(this.getId()));
	}

	@Override
	public Shop createModel() {
		return new Shop();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ShopImage> getShopImageDto() {
		return shopImageDto;
	}

	public void setShopImageDto(List<ShopImage> shopImageDto) {
		this.shopImageDto = shopImageDto;
	}

	public List<ShopFile> getShopFileDto() {
		return shopFileDto;
	}

	public void setShopFileDto(List<ShopFile> shopFileDto) {
		this.shopFileDto = shopFileDto;
	}

	public static ShopUpdateDTO toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, ShopUpdateDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
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

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	
}

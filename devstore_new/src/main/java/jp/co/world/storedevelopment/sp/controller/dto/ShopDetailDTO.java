package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.ParticipatingStoreCorporation;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopFile;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopImageRepository;

public class ShopDetailDTO implements DTO<Shop> {

	private Long id;
	private Long iShopId;
	private String section;
	private String frontage;
	private Long iSalesAgencyTargetId;
	private Long participatingStoreCorporationId;
	private Integer buildingExpectedValue;
	private String remarks;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private String updateAccountCode;
	private String imagePath;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentStartDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate rentEndDate;
	private Float rentYear;
	private String businessHours;

	public ShopDetailDTO() {

	}

	public ShopDetailDTO(Shop shop) {
		copyProperties(this, shop);

		File file = new File(Application.resourcePath() + shop.getImagePath());
		if (file.exists()) {
			this.setImagePath(shop.getImagePath());
		} else {
			this.setImagePath("img/no_img.jpg");
		}
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

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateAccountCode() {
		return updateAccountCode;
	}

	public void setUpdateAccountCode(String updateAccountCode) {
		this.updateAccountCode = updateAccountCode;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public ISalesAgencyTargetRelationShopDetailDTO getiSalesAgencyTarget() {
		if (getiSalesAgencyTargetId() == null) {
			return null;
		}

		Optional<ISalesAgencyTarget> iSalesAgencyTarget = new ISalesAgencyTargetRepository()
				.findById(getiSalesAgencyTargetId());
		return iSalesAgencyTarget.isPresent() ? new ISalesAgencyTargetRelationShopDetailDTO(iSalesAgencyTarget.get())
				: null;
	}

	public ParticipatingStoreCorporationRelationShopDetailDTO getParticipatingStoreCorporation() {
		if (getParticipatingStoreCorporationId() == null) {
			return null;
		}

		Optional<ParticipatingStoreCorporation> participatingStoreCorporation = new ParticipatingStoreCorporationRepository()
				.findById(getParticipatingStoreCorporationId());
		return participatingStoreCorporation.isPresent()
				? new ParticipatingStoreCorporationRelationShopDetailDTO(participatingStoreCorporation.get())
				: null;
	}

	public List<ShopImageRelationShopDetailDTO> getShopImages() {
		List<ShopImageRelationShopDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<ShopImage> shopImages = new ShopImageRepository().findByShopId(getId());
		return shopImages != null && shopImages.size() > 0
				? shopImages.stream().map(x -> new ShopImageRelationShopDetailDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	public List<ShopFileRelationShopDetailDTO> getShopFiles() {
		List<ShopFileRelationShopDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<ShopFile> shopfiles = new ShopFileRepository().findByShopId(getId());
		return shopfiles != null && shopfiles.size() > 0
				? shopfiles.stream().map(x -> new ShopFileRelationShopDetailDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	public String getUpdateAccountName() {
		if (getUpdateAccountCode() != null && !getUpdateAccountCode().isEmpty()) {
			Optional<Account> account = new AccountRepository().findByCode(getUpdateAccountCode());
			return account.isPresent() ? account.get().getFullName() : "";
		}
		return "";
	}

	@Override
	public Shop createModel() {
		return new Shop();
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

	public String getRentStartDateString() {
		if (rentStartDate != null) {
			return rentStartDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		}
		return StringUtils.EMPTY;
	}

	public String getRentEndDateString() {
		if (rentEndDate != null) {
			return rentEndDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		}
		return StringUtils.EMPTY;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	
	
}

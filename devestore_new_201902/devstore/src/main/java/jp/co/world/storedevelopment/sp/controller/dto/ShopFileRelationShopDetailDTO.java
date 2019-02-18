package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.ShopFile;

public class ShopFileRelationShopDetailDTO implements DTO<ShopFile> {

	private Long id;
	private Long shopId;
	private String name;
	private String displayName;
	private String type;
	private String comment;
	private Long size;
	private String path;
	private String createdAccountCode;
	private String createdAccountName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private Boolean isDeleted = false;

	public ShopFileRelationShopDetailDTO(ShopFile shopFile) {
		this.copyProperties(this, shopFile);
		this.setCreatedAccountName(shopFile.getAccountName());

		File file = new File(jp.co.world.storedevelopment.model.File.DOCUMENT_FILE_PATH + shopFile.getName());
		if (file.exists()) {
			this.setPath(shopFile.urlPath() + shopFile.getName());
		} else {
			this.setPath("img/no_img.jpg");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	public String getCreatedAccountName() {
		return createdAccountName;
	}

	public void setCreatedAccountName(String createdAccountName) {
		this.createdAccountName = createdAccountName;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDiffirentFromNow() {
		Date startDate = Date.from(this.getUpdateDatetime().atZone(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		long different = endDate.getTime() - startDate.getTime();

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		if (elapsedDays > 0) {
			return elapsedDays + "日前";
		} else if (elapsedHours > 0) {
			return elapsedHours + "時間前";
		} else if (elapsedMinutes > 0) {
			return elapsedMinutes + "分前";
		} else {
			return elapsedSeconds + "秒前";
		}
	}

	@Override
	public ShopFile createModel() {
		return new ShopFile();
	}

}

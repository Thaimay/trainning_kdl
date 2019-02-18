package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.BuildingFile;

public class BuildingFileRelationBuildingDetailDTO implements DTO<BuildingFile> {

	private Long id;
	private Long buildingId;
	private String name;
	private String displayName;
	private String type;
	private String comment;
	private Long size;
	private String division;
	private String path;
	private String createdAccountCode;
	private String createdAccountName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updateDatetime;
	private Boolean isDeleted;

	public BuildingFileRelationBuildingDetailDTO() {
	}

	public BuildingFileRelationBuildingDetailDTO(BuildingFile buildingFile) {
		this.copyProperties(this, buildingFile);
		this.setPath(buildingFile.getUrlPath());
		this.setCreatedAccountName(buildingFile.getAccountName());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
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
	public BuildingFile createModel() {
		return new BuildingFile();
	}

}

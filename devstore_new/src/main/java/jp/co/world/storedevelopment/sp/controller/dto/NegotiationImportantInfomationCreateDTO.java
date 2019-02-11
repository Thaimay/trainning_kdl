package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NegotiationImportantInfomationCreateDTO {
	private List<Long> buildingIds = new ArrayList<>();

	private List<Long> corporationIds = new ArrayList<>();

	private List<String> buildingUnmanagedNames = new ArrayList<>();

	private List<String> corporationUnmanagedNames = new ArrayList<>();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime showEndDate;

	@Size(min = 0, max = 10000)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getShowEndDate() {
		return showEndDate;
	}

	public void setShowEndDate(LocalDateTime showEndDate) {
		this.showEndDate = showEndDate;
	}

	public List<Long> getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}

	public List<Long> getCorporationIds() {
		return corporationIds;
	}

	public void setCorporationIds(List<Long> corporationIds) {
		this.corporationIds = corporationIds;
	}

	public List<String> getBuildingUnmanagedNames() {
		return buildingUnmanagedNames;
	}

	public void setBuildingUnmanagedNames(List<String> buildingUnmanagedNames) {
		this.buildingUnmanagedNames = buildingUnmanagedNames;
	}

	public List<String> getCorporationUnmanagedNames() {
		return corporationUnmanagedNames;
	}

	public void setCorporationUnmanagedNames(List<String> corporationUnmanagedNames) {
		this.corporationUnmanagedNames = corporationUnmanagedNames;
	}

}

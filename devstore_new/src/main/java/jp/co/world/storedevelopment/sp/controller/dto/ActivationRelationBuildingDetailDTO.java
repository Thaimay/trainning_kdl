package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Activation;

public class ActivationRelationBuildingDetailDTO implements DTO<Activation> {

	private Long id;
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expectedDay;
	private String floor;
	private Boolean isDeleted;

	public ActivationRelationBuildingDetailDTO() {

	}

	public ActivationRelationBuildingDetailDTO(Activation activation) {
		this.copyProperties(this, activation);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getExpectedDay() {
		return expectedDay;
	}

	public void setExpectedDay(LocalDate expectedDay) {
		this.expectedDay = expectedDay;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public Activation createModel() {
		return new Activation();
	}

}

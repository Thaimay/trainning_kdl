package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.IBusinessCard;

public class BusinessCardRelationBuildingListDTO implements DTO<IBusinessCard> {

	private Long id;
	private String departmentName;
	private String positionName;
	private String name;

	public BusinessCardRelationBuildingListDTO() {

	}

	public BusinessCardRelationBuildingListDTO(IBusinessCard businessCard) {
		this.copyProperties(this, businessCard);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String department) {
		this.departmentName = department;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public IBusinessCard createModel() {
		return new IBusinessCard();
	}

}

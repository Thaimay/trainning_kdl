package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.FileDivisionModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class FileDivision extends ActiveModel<FileDivision> {

	private String fileDivisionCode;
	private String displayName;
	private Boolean negotiationFlag;
	private Boolean buildingFlag;
	private Boolean shopFlag;
	private Boolean projectFlag;
	private Integer listOrder;
	private Integer selectOrder;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<FileDivision> modelMapper(SqlSession session) {
		return session.getMapper(FileDivisionModelMapper.class);
	}

	public String getFileDivisionCode() {
		return fileDivisionCode;
	}

	public void setFileDivisionCode(String fileDivisionCode) {
		this.fileDivisionCode = fileDivisionCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Boolean getNegotiationFlag() {
		return negotiationFlag;
	}

	public void setNegotiationFlag(Boolean negotiationFlag) {
		this.negotiationFlag = negotiationFlag;
	}

	public Boolean getBuildingFlag() {
		return buildingFlag;
	}

	public void setBuildingFlag(Boolean buildingFlag) {
		this.buildingFlag = buildingFlag;
	}

	public Boolean getShopFlag() {
		return shopFlag;
	}

	public void setShopFlag(Boolean shopFlag) {
		this.shopFlag = shopFlag;
	}

	public Boolean getProjectFlag() {
		return projectFlag;
	}

	public void setProjectFlag(Boolean projectFlag) {
		this.projectFlag = projectFlag;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public Integer getSelectOrder() {
		return selectOrder;
	}

	public void setSelectOrder(Integer selectOrder) {
		this.selectOrder = selectOrder;
	}

}

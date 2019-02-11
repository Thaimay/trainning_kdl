package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.KeymanModelMapper;

/**
 * @author tainm
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class Keyman extends ActiveModel<Keyman> {

	private Long businessCardId;
	private Long corporationId;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public Keyman() {

	}

	public Keyman(IBusinessCard card) {
		businessCardId = card.getId();
		corporationId = null;
	}

	public Long getBusinessCardId() {
		return businessCardId;
	}

	public void setBusinessCardId(Long businessCardId) {
		this.businessCardId = businessCardId;
	}

	public Long getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}

	@Override
	protected ModelMapper<Keyman> modelMapper(SqlSession session) {
		return session.getMapper(KeymanModelMapper.class);
	}

}

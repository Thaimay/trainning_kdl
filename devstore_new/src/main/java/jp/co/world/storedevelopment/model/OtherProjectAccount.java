package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.OtherProjectAccountModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class OtherProjectAccount extends ActiveModel<OtherProjectAccount> {

	private Long projectId;
	private String category;
	private Long iAccountId;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<OtherProjectAccount> modelMapper(SqlSession session) {
		return session.getMapper(OtherProjectAccountModelMapper.class);
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getiAccountId() {
		return iAccountId;
	}

	public void setiAccountId(Long iAccountId) {
		this.iAccountId = iAccountId;
	}

}

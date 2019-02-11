package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationCommentUpdateHistoryModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class NegotiationCommentUpdateHistory extends ActiveModel<NegotiationCommentUpdateHistory> {

	private Long negotiationId;

	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}
	
	public NegotiationCommentUpdateHistory() {
		
	}
	
	public NegotiationCommentUpdateHistory(Long negotiationId) {
		this.negotiationId = negotiationId;
	}


	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	@Override
	protected ModelMapper<NegotiationCommentUpdateHistory> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationCommentUpdateHistoryModelMapper.class);
	}

}

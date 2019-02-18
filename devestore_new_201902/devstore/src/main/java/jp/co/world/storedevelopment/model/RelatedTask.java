package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.RelatedTaskModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationCommentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class RelatedTask extends ActiveModel<RelatedTask> {
	private Long accountId;
	private String division = "";
	private String content = "";
	private Long negotiationId;
	private Long buildingId;
	private Long negotiationCommentId;

	private static String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public RelatedTask() {
	}

	public RelatedTask(Account account) {
		setAccountId(account.getId());
	}

	@Override
	protected ModelMapper<RelatedTask> modelMapper(SqlSession session) {
		return session.getMapper(RelatedTaskModelMapper.class);
	}

	public Negotiation negotiation() {
		return new NegotiationRepository().findById(getNegotiationId()).orElseGet(() -> {
			throw new IllegalArgumentException("検索した商談が存在しません");
		});
	}

	public NegotiationComment negotiationComment() {
		return new NegotiationCommentRepository().findById(getNegotiationCommentId()).orElseGet(() -> {
			throw new IllegalArgumentException("検索した商談コメントが存在しません");
		});
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Long getNegotiationCommentId() {
		return negotiationCommentId;
	}

	public void setNegotiationCommentId(Long negotiationCommentId) {
		this.negotiationCommentId = negotiationCommentId;
	}

}

package jp.co.world.storedevelopment.model;

import java.time.format.DateTimeFormatter;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationCommentModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RelatedTaskRepository;

@JsonIgnoreProperties(value = { "negotiation" })
public class NegotiationComment extends ActiveModel<NegotiationComment> {

	private Long negotiationId = Long.MIN_VALUE;
	private String accountCode = "";
	private String content = "";
	private static DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	private static String[] ignoreFields = new String[] { "negotiation", "createDate" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public NegotiationComment() {
		//
	}

	public NegotiationComment(Negotiation negotiation) {
		setNegotiationId(negotiation.getId());
	}

	public NegotiationComment(Negotiation negotiation, Account account) {
		this(negotiation);
		setAccountCode(account.getEmployeeCd());
	}

	public NegotiationComment(Negotiation negotiation, Account account, String content) {
		this(negotiation);
		setAccountCode(account.getEmployeeCd());
		setContent(content);
	}

	@Override
	public Boolean delete() {
		new RelatedTaskRepository().findByNegotiationCommentId(getId()).stream().forEach(r -> {
			r.delete();
		});
		return super.delete();
	}

	@Override
	protected ModelMapper<NegotiationComment> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationCommentModelMapper.class);
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getCreateDate() {
		return DATETIME_FORMAT.format(getCreatedDatetime());
	}

	public Negotiation getNegotiation() {
		return new NegotiationRepository().findById(getNegotiationId())
				.orElseThrow(() -> new IllegalStateException("商談が見つかりません:" + getNegotiationId()));
	}

	public String createAccountName() {
		return new AccountRepository().findByCode(getCreatedAccountCode()).orElseGet(() -> {
			throw new IllegalStateException("存在しないアカウントです");
		}).getFullName();
	}

}

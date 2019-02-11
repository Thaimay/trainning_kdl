package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationNoticeAccountModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class NegotiationNoticeAccount extends ActiveModel<NegotiationNoticeAccount> {

	private Long negotiationId = Long.MIN_VALUE;
	private Long accountId = Long.MIN_VALUE;
	private String sendType = "TO"; 

	private static String[] ignoreFields = new String[] { "account", "accountName", "isRead", "accessRecord", "account",
			"negotiation", "employeCode" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public NegotiationNoticeAccount() {
		//
	}

	public NegotiationNoticeAccount(Negotiation negotiation) {
		negotiationId = negotiation.getId();
	}

	public NegotiationNoticeAccount(Negotiation negotiation, Account account) {
		this(negotiation);
		accountId = account.getId();
	}
	
	public NegotiationNoticeAccount(Negotiation negotiation, Account account, String sendType) {
		this(negotiation, account);
		this.sendType = sendType;
	}

	@Override
	protected ModelMapper<NegotiationNoticeAccount> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationNoticeAccountModelMapper.class);
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public Account getAccount() {
		return new AccountRepository().findById(accountId)
				.orElseThrow(() -> new IllegalStateException("存在しないアカウント:" + getAccountId()));
	}

	public Negotiation getNegotiation() {
		return new NegotiationRepository().findById(negotiationId)
				.orElseThrow(() -> new IllegalStateException("存在しない商談:" + getAccountId()));
	}

	public String getAccountName() {
		return getAccount().getFullName();
	}

	public String getEmployeCode() {
		return getAccount().getEmployeCode();
	}

}

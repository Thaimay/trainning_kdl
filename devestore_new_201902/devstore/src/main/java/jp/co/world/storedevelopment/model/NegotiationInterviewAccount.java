package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewAccountModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class NegotiationInterviewAccount extends ActiveModel<NegotiationInterviewAccount> {

	private Long negotiationId = Long.MIN_VALUE;
	private Long accountId = null;
	private String unmanagedName = "";

	private static String[] ignoreFields = new String[] { "account", "accountName" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public NegotiationInterviewAccount() {
		//
	}

	public NegotiationInterviewAccount(Negotiation negotiation, Account account) {
		negotiationId = negotiation.getId();
		accountId = account.getId();
	}

	public NegotiationInterviewAccount(Negotiation negotiation, String name) {
		negotiationId = negotiation.getId();
		unmanagedName = name;
	}

	public Boolean isUnmanaged() {
		return accountId == null;
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

	public String getUnmanagedName() {
		return unmanagedName;
	}

	public void setUnmanagedName(String acctounName) {
		unmanagedName = acctounName;
	}

	@Override
	protected ModelMapper<NegotiationInterviewAccount> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewAccountModelMapper.class);
	}

	public String getAccountName() {
		if (isUnmanaged()) {
			return getUnmanagedName();
		} else {
			return findAccount().getFullName();
		}
	}

	public Account findAccount() {
		return new AccountRepository().findById(accountId).orElseGet(() -> {
			throw new IllegalStateException("存在しないアカウントです:" + accountId);
		});
	}

	public Boolean hasAccount() {
		return accountId != null;
	}

	public Boolean isNotSame(Account a) {
		return hasAccount() && !getAccountId().equals(a.getId());
	}
}

package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationOpenAccountModelMapper;

public class NegotiationOpenAccount extends ActiveModel<NegotiationOpenAccount> {

	private Long negotiationId;

	private Long accountId;

	@Override
	protected ModelMapper<NegotiationOpenAccount> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationOpenAccountModelMapper.class);
	}

	public NegotiationOpenAccount() {
		//
	}

	public NegotiationOpenAccount(Negotiation negotiation, Account account) {
		negotiationId = negotiation.id;
		accountId = account.id;
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
}

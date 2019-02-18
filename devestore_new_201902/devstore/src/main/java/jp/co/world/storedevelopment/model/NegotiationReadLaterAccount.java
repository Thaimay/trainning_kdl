package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationReadLaterAccountModelMapper;

public class NegotiationReadLaterAccount extends ActiveModel<NegotiationReadLaterAccount> {

	private Long negotiationId;

	private Long accountId;

	public NegotiationReadLaterAccount() {
		//
	}

	public NegotiationReadLaterAccount(Negotiation negotiation, Account account) {
		negotiationId = negotiation.id;
		accountId = account.id;
	}

	@Override
	protected ModelMapper<NegotiationReadLaterAccount> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationReadLaterAccountModelMapper.class);
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

	public void setAccountId(Long accoutnId) {
		accountId = accoutnId;
	}

}

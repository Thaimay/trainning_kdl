package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationReadLaterAccount;
import jp.co.world.storedevelopment.model.mapper.NegotiationReadLaterAccountRepositoryMapper;

public class NegotiationReadLaterAccountRepository
		extends Repository<NegotiationReadLaterAccount, NegotiationReadLaterAccountRepositoryMapper> {

	@Override
	protected NegotiationReadLaterAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationReadLaterAccountRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationReadLaterAccount.class);
	}

	public boolean isReadLater(Negotiation negotiation, Account account) {
		return execute((mapper) -> {
			return findByAccount(negotiation, account).isPresent();
		});
	}

	public Optional<NegotiationReadLaterAccount> findByAccount(Negotiation negotiation, Account account) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByAccount(negotiation, account));
		});
	}
}

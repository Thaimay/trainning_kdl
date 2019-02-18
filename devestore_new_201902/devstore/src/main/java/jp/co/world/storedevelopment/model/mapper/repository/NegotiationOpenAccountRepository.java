package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;
import jp.co.world.storedevelopment.model.NegotiationOpenAccount;
import jp.co.world.storedevelopment.model.mapper.NegotiationOpenAccountRepositoryMapper;

public class NegotiationOpenAccountRepository
		extends NegotiationRelatedRepository<NegotiationOpenAccount, NegotiationOpenAccountRepositoryMapper> {

	@Override
	protected NegotiationOpenAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationOpenAccountRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationOpenAccount.class);
	}

	public int calcOpenRates(Negotiation negotiation) {
		return execute((mapper) -> {
			List<NegotiationNoticeAccount> noticeAccounts = negotiation.getNoticeAccounts();
			if (noticeAccounts.size() == 0) {
				return 0;
			}
			List<Long> accountIds = noticeAccounts.stream().map(a -> a.getAccountId()).collect(Collectors.toList());
			Integer open = mapper.countIsOpened(negotiation, accountIds);
			Integer all = noticeAccounts.size();
			return (int) ((open.doubleValue() / all.doubleValue()) * 100);
		});
	}

	public Optional<NegotiationOpenAccount> findByAccount(Negotiation negotiation, Account account) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByAccount(negotiation, account));
		});
	}

	public boolean isOpen(Negotiation negotiation, Account account) {
		return findByAccount(negotiation, account).isPresent();
	}
}

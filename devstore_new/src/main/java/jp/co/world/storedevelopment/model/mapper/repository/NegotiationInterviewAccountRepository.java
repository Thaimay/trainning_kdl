package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;
import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewAccountRepositoryMapper;

public class NegotiationInterviewAccountRepository
		extends NegotiationRelatedRepository<NegotiationInterviewAccount, NegotiationInterviewAccountRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationInterviewAccount.class);
	}

	@Override
	protected NegotiationInterviewAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewAccountRepositoryMapper.class);
	}

	public List<NegotiationInterviewAccount> findByNegotiationId(Long id) {
		return execute((mapper) -> {
			return mapper.findByNegotiationId(id);
		});
	}

	public List<NegotiationInterviewAccount> findByAccountId(Long id) {
		return execute((mapper) -> {
			return mapper.findByAccountId(id);
		});
	}
}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;
import jp.co.world.storedevelopment.model.mapper.NegotiationNoticeAccountRepositoryMapper;

public class NegotiationNoticeAccountRepository
		extends NegotiationRelatedRepository<NegotiationNoticeAccount, NegotiationNoticeAccountRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationNoticeAccount.class);
	}

	@Override
	protected NegotiationNoticeAccountRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationNoticeAccountRepositoryMapper.class);
	}

    public List<NegotiationNoticeAccount> findByNegotiationId(Long id) {
        return execute((mapper) -> {
            return mapper.findByNegotiationId(id);
        });
    }

}

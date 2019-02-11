package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.NegotiationCommentUpdateHistory;
import jp.co.world.storedevelopment.model.mapper.NegotiationCommentUpdateHistoryRepositoryMapper;

public class NegotiationCommentUpdateHistoryRepository extends
		NegotiationRelatedRepository<NegotiationCommentUpdateHistory, NegotiationCommentUpdateHistoryRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationCommentUpdateHistory.class);
	}

	@Override
	protected NegotiationCommentUpdateHistoryRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationCommentUpdateHistoryRepositoryMapper.class);
	}

}

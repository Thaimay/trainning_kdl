package jp.co.world.storedevelopment.model.mapper.repository;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationComment;
import jp.co.world.storedevelopment.model.mapper.NegotiationCommentRepositoryMapper;

public class NegotiationCommentRepository
		extends NegotiationRelatedRepository<NegotiationComment, NegotiationCommentRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationComment.class);
	}
	
	public int countByNegotiation(Negotiation n) {
	    return execute((mapper) -> {
	        return mapper.countByNegotiation(n);
	    });
	}

	@Override
	protected NegotiationCommentRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationCommentRepositoryMapper.class);
	}

}

package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.NegotiationInterviewBusinessCard;
import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewBusinessCardRepositoryMapper;

public class NegotiationInterviewBusinessCardRepository extends
		NegotiationRelatedRepository<NegotiationInterviewBusinessCard, NegotiationInterviewBusinessCardRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationInterviewBusinessCard.class);
	}

	@Override
	protected NegotiationInterviewBusinessCardRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewBusinessCardRepositoryMapper.class);
	}

	public List<NegotiationInterviewBusinessCard> findByBusinessCardId(Long id) {
		return execute((mapper) -> {
			return mapper.findByBusinessCardId(id);
		});
	}
}

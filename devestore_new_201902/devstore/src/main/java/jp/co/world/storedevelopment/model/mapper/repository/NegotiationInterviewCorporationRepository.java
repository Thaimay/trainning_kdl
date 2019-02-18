package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.NegotiationInterviewCorporation;
import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewCorporationRepositoryMapper;

public class NegotiationInterviewCorporationRepository extends
		NegotiationRelatedRepository<NegotiationInterviewCorporation, NegotiationInterviewCorporationRepositoryMapper> {

	@Override
	protected NegotiationInterviewCorporationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewCorporationRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationInterviewCorporation.class);
	}

	public List<NegotiationInterviewCorporation> findByCorporationId(Long id) {
		return execute((mapper) -> {
			return mapper.findByCorporationId(id);
		});
	}
}

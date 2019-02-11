package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.NegotiationInterviewBuilding;
import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewBuildingRepositoryMapper;

public class NegotiationInterviewBuildingRepository extends
		NegotiationRelatedRepository<NegotiationInterviewBuilding, NegotiationInterviewBuildingRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(NegotiationInterviewBuilding.class);
	}

	@Override
	protected NegotiationInterviewBuildingRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewBuildingRepositoryMapper.class);
	}

    public NegotiationInterviewBuilding findByNegotiationId(Long id) {
        return execute((mapper) -> {
            return mapper.findByNegotiationId(id);
        });
    }

	public List<NegotiationInterviewBuilding> findByBuildingId(Long buildingId) {
		return execute((mapper) -> {
			return mapper.findByBuildingId(buildingId);
		});
	}

}

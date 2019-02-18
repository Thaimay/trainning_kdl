package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.ProjectNegotiation;
import jp.co.world.storedevelopment.model.mapper.ProjectNegotiationRepositoryMapper;

public class ProjectNegotiationRepository
		extends ProjectRelatedRepository<ProjectNegotiation, ProjectNegotiationRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectNegotiation.class);
	}

	@Override
	protected ProjectNegotiationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectNegotiationRepositoryMapper.class);
	}
	
	public List<ProjectNegotiation> findByNegotiation(Negotiation negotiation) {
		return execute(mapper -> {
			return mapper.findOfNegotiation(negotiation);
		});
	}
	
	public List<ProjectNegotiation> findNegotiationByProjectId(Long projectId) {
		return execute(mapper -> {
			return mapper.findNegotiationByProjectId(projectId);
		});
	}
	
	public int deleteByNegotiation(Negotiation negotiation) {
		return execute(mapper -> {
			return mapper.deleteByNegotiation(negotiation);
		});
	}

}

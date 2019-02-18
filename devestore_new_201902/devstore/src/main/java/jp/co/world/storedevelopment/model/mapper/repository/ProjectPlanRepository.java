package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ProjectPlan;
import jp.co.world.storedevelopment.model.mapper.ProjectPlanRepositoryMapper;

public class ProjectPlanRepository extends Repository<ProjectPlan, ProjectPlanRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectPlan.class);
	}

	@Override
	protected ProjectPlanRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectPlanRepositoryMapper.class);
	}

	public List<ProjectPlan> findByProjectId(Long projectId) {
		return execute((mapper) -> {
			return mapper.findByProjectid(projectId);
		});
	}

	public Optional<ProjectPlan> findCurrentByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findCurrentByProjectId(id));
		});
	}
	
	public Optional<ProjectPlan> findProgressByProjectId(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findProgressByProjectId(id));
		});
	}
	
	public int deleteByProjectId(Long projectId) {
		return execute((mapper) -> {
			return mapper.deleteByProjectId(projectId);
		});
	}

	public List<ProjectPlan> findHistoryByProjectId(Long projectId) {
		return execute((mapper) -> {
			return mapper.findHistoryByProjectid(projectId);
		});
	}
}

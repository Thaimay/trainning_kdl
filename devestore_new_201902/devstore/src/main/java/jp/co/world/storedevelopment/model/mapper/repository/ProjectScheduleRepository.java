package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ProjectSchedule;
import jp.co.world.storedevelopment.model.mapper.ProjectScheduleRepositoryMapper;

public class ProjectScheduleRepository extends Repository<ProjectSchedule, ProjectScheduleRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectSchedule.class);
	}

	@Override
	protected ProjectScheduleRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectScheduleRepositoryMapper.class);
	}

	public Optional<ProjectSchedule> getProjectSchedule(Long projectId, Long mProjectActionStatusId) {
		return execute((mapper) -> {
			if (projectId == null || mProjectActionStatusId == null) {
				return Optional.ofNullable(null);
			}
			ProjectSchedule projectSchedule = mapper.getProjectSchedule(projectId, mProjectActionStatusId);
			return Optional.ofNullable(projectSchedule);
		});
	}

	public Optional<ProjectSchedule> findConsensusSchedule(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findConsensusSchedule(id));
		});
	}

	public Optional<ProjectSchedule> findReviewSchedule(Long id) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findReviewSchedule(id));
		});
	}
	
	public List<ProjectSchedule> findByProjectId(Long projectId) {
		return execute((mapper) -> {
			return mapper.findByProjectid(projectId);
		});
	}
	
	public int deleteByProjectId(Long id) {
		return execute((mapper) -> {
			return mapper.deleteByProjectId(id);
		});
	}

	public List<ProjectSchedule> findHistoryByProjectId(Long projectId) {
		return execute((mapper) -> {
			return mapper.findHistoryByProjectid(projectId);
		});
	}
	
	public Optional<ProjectSchedule> getProjectScheduleHistory(Long projectId, Long mProjectActionStatusId) {
		return execute((mapper) -> {
			if (projectId == null || mProjectActionStatusId == null) {
				return Optional.ofNullable(null);
			}
			ProjectSchedule projectSchedule = mapper.getProjectScheduleHistory(projectId, mProjectActionStatusId);
			return Optional.ofNullable(projectSchedule);
		});
	}
	
}

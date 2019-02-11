package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectHistory;
import jp.co.world.storedevelopment.model.mapper.ProjectHistoryRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectHistoryListDTO;

public class ProjectHistoryRepository extends Repository<ProjectHistory, ProjectHistoryRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectHistory.class);
	}

	@Override
	protected ProjectHistoryRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectHistoryRepositoryMapper.class);
	}

	public List<ProjectHistoryListDTO> getProjectHistoryList(Long project_id) {
		return execute((mapper) -> {
			return mapper.getProjectHistoryList(project_id).stream().map(x -> new ProjectHistoryListDTO(x))
					.sorted((b2, b1) -> b1.getId().compareTo(b2.getId())).collect(Collectors.toList());
		});
	}

	public void updateProjectHistory(Project project) {
		execute((mapper) -> {
			mapper.updateProjectHistory(project);
		});
	}
}

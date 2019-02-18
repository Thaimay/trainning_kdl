package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.mapper.ProjectFileRepositoryMapper;

public class ProjectFileRepository extends ProjectRelatedRepository<ProjectFile, ProjectFileRepositoryMapper> {
	@Override
	protected ProjectFileRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectFileRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<ProjectFile> findByProjectId(Long projectId) {
		return execute(mapper -> {
			return mapper.findByProjectId(tableName(), projectId).stream().filter(x -> x.isDocument())
					.collect(Collectors.toList());
		});
	}
}

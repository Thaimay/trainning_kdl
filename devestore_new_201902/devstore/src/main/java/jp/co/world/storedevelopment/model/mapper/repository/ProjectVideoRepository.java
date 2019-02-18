package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.ProjectVideo;
import jp.co.world.storedevelopment.model.mapper.ProjectVideoRepositoryMapper;

public class ProjectVideoRepository extends ProjectRelatedRepository<ProjectVideo, ProjectVideoRepositoryMapper> {
	@Override
	protected ProjectVideoRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectVideoRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<ProjectVideo> findByProjectId(Long projectId) {
		return execute(mapper -> {
			return mapper.findByProjectId(tableName(), projectId).stream().filter(x -> x.isVideo())
					.collect(Collectors.toList());
		});
	}
}

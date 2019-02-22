package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.model.File;
import jp.co.world.storedevelopment.model.ProjectDocImage;
import jp.co.world.storedevelopment.model.mapper.ProjectDocImageRepositoryMapper;



public class ProjectDocImageRepository extends ProjectDocRelatedRepository<ProjectDocImage, ProjectDocImageRepositoryMapper>{
	@Override
	protected ProjectDocImageRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectDocImageRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(File.class);
	}

	@Override
	public List<ProjectDocImage> findByProjectId(Long projectId) {
		return execute(mapper -> {
			return mapper.findByProjectDocId(tableName(), projectId).stream().filter(x -> x.isImage())
					.collect(Collectors.toList());
		});
	}

	public List<ProjectDocImage> findProjectDocImageRelatedProject(Long projectId) {
		return execute(mapper -> {
			return mapper.findProjectDocImageRelatedProject(projectId).stream().filter(x -> x.isImage())
					.collect(Collectors.toList());
		});
	}

//	public List<BuildingImage> findByProject(ProjectDocRelationFindByTextFormDTO dto) {
//		return execute((mapper) -> {
//			return mapper.findByProject(dto);
//		});
//	}
	

}

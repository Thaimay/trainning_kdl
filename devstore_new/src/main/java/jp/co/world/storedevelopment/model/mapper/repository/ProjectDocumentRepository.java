package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ProjectDocument;
import jp.co.world.storedevelopment.model.mapper.ProjectDocumentRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingDetailDTO;

public class ProjectDocumentRepository extends Repository<ProjectDocument, ProjectDocumentRepositoryMapper> {

	@Override
	protected ProjectDocumentRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ProjectDocumentRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ProjectDocument.class);
	}
	
	public List<ProjectDocument> getProjectList() {
		return execute((mapper) -> {
			return mapper.getProjectList();
		});
	}
	
	public ProjectDocument findProjectById(Long id) {
		return execute((mapper) -> {
			return mapper.findProjectById(id);
		});
	}
	
	public List<ProjectDocument> getProjectListSearch(String name) {
		return execute((mapper) -> {
			return mapper.getProjectListSearch(name);
		});
	}
}

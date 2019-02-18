package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ProjectDocument;

public interface ProjectDocumentRepositoryMapper extends RepositoryMapper<ProjectDocument>{
	
	@Select("SELECT * FROM project_document WHERE is_deleted = false ORDER BY name")
	List<ProjectDocument> getProjectList();
	
	@Select("select * from project_document where id = '${id}'")
	ProjectDocument findProjectById(@Param("id") Long id);
	
	@Select("SELECT * FROM project_document WHERE name like '%${name}%' OR output_status like '%${name}%' or meeting_point like N'%${name}%'")
	List<ProjectDocument> getProjectListSearch(@Param("name") String name);

}
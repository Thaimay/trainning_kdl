package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectFileModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class ProjectFile extends File<ProjectFile> {

	public ProjectFile() {
	}

	@Override
	public String filePrefix() {
		return "project_";
	}

	public ProjectFile(MultipartFile file, Project project, Account account) {
		super(file, account);
		setProjectId(project.getId());
	}

	public ProjectFile(MultipartFile file, Long projectId, Account account) {
		super(file, account);
		setProjectId(projectId);
	}

	@Override
	protected ModelMapper<ProjectFile> modelMapper(SqlSession session) {
		return session.getMapper(ProjectFileModelMapper.class);
	}

	@Override
	public String basePath() {
		return DOCUMENT_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "document/";
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

}

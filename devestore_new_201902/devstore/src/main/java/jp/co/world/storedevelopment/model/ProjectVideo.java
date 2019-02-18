package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectVideoModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class ProjectVideo extends File<ProjectVideo> {
	private Long projectId;

	public ProjectVideo() {
	}

	@Override
	public String filePrefix() {
		return "project_";
	}

	public ProjectVideo(MultipartFile file, Project project, Account account) {
		super(file, account);
		setProjectId(project.getId());
	}

	public ProjectVideo(MultipartFile file, Long projectId, Account account) {
		super(file, account);
		setProjectId(projectId);
	}

	@Override
	protected ModelMapper<ProjectVideo> modelMapper(SqlSession session) {
		return session.getMapper(ProjectVideoModelMapper.class);
	}

	@Override
	public String basePath() {
		return VIDEO_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "video/";
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

	@Override
	public Boolean isVideo() {
		return FileExtention.isVideo(getName());
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}

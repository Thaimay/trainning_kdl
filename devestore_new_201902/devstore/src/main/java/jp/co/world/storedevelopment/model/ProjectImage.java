package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectImageModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class ProjectImage extends File<ProjectImage> {

	private Boolean isDefaultImage;

	public ProjectImage() {
	}

	@Override
	public String filePrefix() {
		return "project_";
	}

	public ProjectImage(MultipartFile file, Project project, Account account) {
		super(file, account);
		setProjectId(project.getId());
	}

	public ProjectImage(MultipartFile file, Long projectId, Account account) {
		super(file, account);
		setProjectId(projectId);
	}

	@Override
	protected ModelMapper<ProjectImage> modelMapper(SqlSession session) {
		return session.getMapper(ProjectImageModelMapper.class);
	}

	@Override
	public String basePath() {
		return IMAGE_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "image/";
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

	public Boolean getIsDefaultImage() {
		return isDefaultImage;
	}

	public void setIsDefaultImage(Boolean isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}

}

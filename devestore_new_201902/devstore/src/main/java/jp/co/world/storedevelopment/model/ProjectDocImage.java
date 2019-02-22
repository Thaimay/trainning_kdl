package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectDocImageModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

@JsonIgnoreProperties({ "tableName" })
public class ProjectDocImage extends File<ProjectDocImage>{
	
	private Boolean isDefaultImage;
	
	public ProjectDocImage() {
	}

	public ProjectDocImage(MultipartFile file, ProjectDocument projectDoc) {
		super(file);
		setProjectId(projectDoc.getId());
	}

	public ProjectDocImage(MultipartFile file, Long projectId) {
		super(file);
		setProjectId(projectId);
	}
	
	@Override
	public String filePrefix() {
		return "projectdoc_";
	}

	@Override
	public String basePath() {
		return File.IMAGE_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "image/";
	}

	@Override
	protected ModelMapper<ProjectDocImage> modelMapper(SqlSession session) {
		return session.getMapper(ProjectDocImageModelMapper.class);
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isVideo() {
		return FileExtention.isVideo(getName());
	}
	
	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

	public Boolean getIsDefaultImage() {
		return isDefaultImage;
	}

	public void setIsDefaultImage(Boolean isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}

}

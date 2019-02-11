package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.ProjectImage;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.ProjectVideo;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectVideoRepository;

public class ProjectOpinionRelationProjectDTO implements DTO<ProjectOpinion> {

	private Long id;
	private Long projectId;
	private Long fileId;
	private String category;
	private String title;
	private String comment;

	public ProjectOpinionRelationProjectDTO() {

	}

	public ProjectOpinionRelationProjectDTO(ProjectOpinion projectOpinion) {
		this.copyProperties(this, projectOpinion);
	}

	@Override
	public ProjectOpinion createModel() {
		return new ProjectOpinion();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ProjectFileUploadDTO getFile() {
		if (fileId != null) {
			Optional<ProjectFile> file = new ProjectFileRepository().findById(fileId);

			if (file.isPresent()) {
				if (file.get().isDocument()) {
					return new ProjectFileUploadDTO(file.get());
				} else if (file.get().isImage()) {
					Optional<ProjectImage> image = new ProjectImageRepository().findById(fileId);
					return new ProjectFileUploadDTO(image.get());
				} else if (file.get().isVideo()) {
					Optional<ProjectVideo> video = new ProjectVideoRepository().findById(fileId);
					return new ProjectFileUploadDTO(video.get());
				}
			}
		}
		return null;
	}
}

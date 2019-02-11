package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectOpinionModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectOpinion extends ActiveModel<ProjectOpinion> {

	private Long projectId;
	private Long fileId;
	private String category;
	private String comment = "";
	@JsonDeserialize(as = MultipartFile.class)
	private MultipartFile formFile;
	private Boolean hasFile = false;

	private String[] ignoreFields = new String[] { "formFile", "hasFile" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectOpinion> modelMapper(SqlSession session) {
		return session.getMapper(ProjectOpinionModelMapper.class);
	}
	
	public Boolean notEmptyComment() {
		return getComment().isEmpty() == false;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public MultipartFile getFormFile() {
		return formFile;
	}

	public void setFormFile(MultipartFile formFile) {
		this.formFile = formFile;
	}

	public Boolean getHasFile() {
		return hasFile;
	}

	public void setHasFile(Boolean hasFile) {
		this.hasFile = hasFile;
	}

}

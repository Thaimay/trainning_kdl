package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectNegotiationModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectNegotiation extends ActiveModel<ProjectNegotiation> {

	private Long projectId;
	private Long negotiationId;

	private String[] ignoreFields = new String[] {"project", "projectName"};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectNegotiation> modelMapper(SqlSession session) {
		return session.getMapper(ProjectNegotiationModelMapper.class);
	}
	
	public ProjectNegotiation() {

	}

	public ProjectNegotiation(Negotiation negotiation) {
		setNegotiationId(negotiation.getId());
	}

	public ProjectNegotiation(Negotiation negotiation, Project project) {
		setNegotiationId(negotiation.getId());
		setProjectId(project.getId());
	}
	
	public String getProjectName() {
		return getProject().getTitle();
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}
	
	public Project getProject() {
		return new ProjectRepository().findById(getProjectId())
				.orElseThrow(() -> new IllegalStateException("ë∂ç›ÇµÇ»Ç¢àƒåèIDÇ≈Ç∑:" + getProjectId()));
	}

}

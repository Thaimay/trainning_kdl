package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.ProjectNegotiation;

public class ProjectNegotiationRelationProjectDTO implements DTO<ProjectNegotiation> {

	private Long id;
	private Long negotiationId;

	public ProjectNegotiationRelationProjectDTO() {

	}

	public ProjectNegotiationRelationProjectDTO(ProjectNegotiation projectNegotiation) {
		this.copyProperties(this, projectNegotiation);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	@Override
	public ProjectNegotiation createModel() {
		return new ProjectNegotiation();
	}
}

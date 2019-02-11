package jp.co.world.storedevelopment.pc.controller.dto;

import jp.co.world.storedevelopment.model.NegotiationInterviewCorporation;

public class InterviewCorporationDTO {
	private String name;

	public InterviewCorporationDTO(NegotiationInterviewCorporation n) {
		setName(n.getCorporationName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

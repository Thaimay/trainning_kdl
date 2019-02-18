package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class RelatedNegotiationFindFormDTO extends FindFormDTO {

	private Long id = 0L;

	private List<Long> projectIds = new ArrayList<Long>();

	private List<Long> corporationIds = new ArrayList<Long>();

	private List<Long> interviewBusinessCardIds = new ArrayList<Long>();

	private String interviewBusinessCardFree = "";

	private List<Long> buildingIds = new ArrayList<Long>();

	private List<Long> brandIds = new ArrayList<Long>();

	private Boolean latest2Year = true;

	private Boolean isInit = false;

	public RelatedNegotiationFindFormDTO() {
		//
	}

	public RelatedNegotiationFindFormDTO(Long id) {
		setId(id);
	}

	public RelatedNegotiationFindFormDTO(Negotiation negotiation) {
		this(negotiation.getId());
		setProjectIds(negotiation.getProjectNegotiations().stream().map(x -> x.getProjectId())
				.collect(Collectors.toList()));
		setCorporationIds(negotiation.getInterviewCorporations().stream().map(x -> x.getCorporationId())
				.collect(Collectors.toList()));
		setBuildingIds(
				negotiation.getInterviewBuildings().stream().map(x -> x.getBuildingId()).collect(Collectors.toList()));
	}

	public List<Long> getBuildingIds() {
		if (isInit && id > 0) {
			Optional<Negotiation> negotiation = new NegotiationRepository().findById(id);
			if (negotiation.isPresent() && negotiation.get().getInterviewBuildings().size() > 0) {
				buildingIds.addAll(negotiation.get().getInterviewBuildings().stream().map(x -> x.getBuildingId())
						.collect(Collectors.toList()));
			}
		}
		return buildingIds;
	}

	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getProjectIds() {
		if (isInit && id > 0) {
			Optional<Negotiation> negotiation = new NegotiationRepository().findById(id);
			if (negotiation.isPresent() && negotiation.get().getProjectNegotiations().size() > 0) {
				projectIds.addAll(negotiation.get().getProjectNegotiations().stream()
						.map(x -> x.getProjectId()).collect(Collectors.toList()));
			}
		}
		return projectIds;
	}

	public void setProjectIds(List<Long> projectIds) {
		this.projectIds = projectIds;
	}

	public List<Long> getCorporationIds() {
		if (isInit && id > 0) {
			Optional<Negotiation> negotiation = new NegotiationRepository().findById(id);
			if (negotiation.isPresent() && negotiation.get().getInterviewCorporations().size() > 0) {
				corporationIds.addAll(negotiation.get().getInterviewCorporations().stream()
						.map(x -> x.getCorporationId()).collect(Collectors.toList()));
			}
		}
		return corporationIds;
	}

	public void setCorporationIds(List<Long> corporationIds) {
		this.corporationIds = corporationIds;
	}

	public List<Long> getInterviewBusinessCardIds() {
		return interviewBusinessCardIds;
	}

	public void setInterviewBusinessCardIds(List<Long> interviewBusinessCardIds) {
		this.interviewBusinessCardIds = interviewBusinessCardIds;
	}

	public String getInterviewBusinessCardFree() {
		return interviewBusinessCardFree;
	}

	public void setInterviewBusinessCardFree(String interviewBusinessCardFree) {
		this.interviewBusinessCardFree = interviewBusinessCardFree;
	}

	public List<Long> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Long> brandIds) {
		this.brandIds = brandIds;
	}

	public Boolean getLatest2Year() {
		return latest2Year;
	}

	public void setLatest2Year(Boolean latest2Year) {
		this.latest2Year = latest2Year;
	}

	public Boolean getIsInit() {
		return isInit;
	}

	public void setIsInit(Boolean isInit) {
		this.isInit = isInit;
	}

}

package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewBuildingModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;

public class NegotiationInterviewBuilding extends ActiveModel<NegotiationInterviewBuilding> {

	private Long negotiationId = null;
	private Long buildingId = null;

	private static String[] ignoreFields = new String[] { "building", "buildingName" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public NegotiationInterviewBuilding(Negotiation negotiation) {
	    negotiationId = negotiation.getId();
	}

	public NegotiationInterviewBuilding() {
	}

	public NegotiationInterviewBuilding(Negotiation negotiation, Building building) {
		this(negotiation);
		buildingId = building.getId();
	}

	public String getBuildingName() {
		return getBuilding().getName();
	}

	@Override
	protected ModelMapper<NegotiationInterviewBuilding> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewBuildingModelMapper.class);
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long building) {
		buildingId = building;
	}

	public Building getBuilding() {
		return new BuildingRepository().findById(getBuildingId())
				.orElseThrow(() -> new IllegalStateException("存在しない館です:" + getBuildingId()));
	}

}

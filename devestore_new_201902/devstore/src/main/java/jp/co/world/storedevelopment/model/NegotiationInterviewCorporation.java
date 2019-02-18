package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewCorporationModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;

public class NegotiationInterviewCorporation extends ActiveModel<NegotiationInterviewCorporation> {

	private Long negotiationId = Long.MIN_VALUE;
	private Long corporationId = Long.MIN_VALUE;

	private static String[] ignoreFields = new String[] { "iCorporation", "corporationName" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public NegotiationInterviewCorporation() {

	}

	public NegotiationInterviewCorporation(Negotiation negotiation) {
		setNegotiationId(negotiation.getId());
	}

	public NegotiationInterviewCorporation(Negotiation negotiation, ICorporation corporation) {
		this(negotiation);
		setCorporationId(corporation.getId());
	}

	public String getCorporationName() {
		return getiCorporation().getCorporationName();
	}

	@Override
	protected ModelMapper<NegotiationInterviewCorporation> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewCorporationModelMapper.class);
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public Long getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}

	public ICorporation getiCorporation() {
		return new ICorporationRepository().findById(getCorporationId())
				.orElseThrow(() -> new IllegalStateException("存在しない法人IDです:" + getCorporationId()));
	}

}

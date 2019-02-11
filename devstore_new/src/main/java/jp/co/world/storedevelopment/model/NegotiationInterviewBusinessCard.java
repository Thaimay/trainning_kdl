package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewBusinessCardModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;

public class NegotiationInterviewBusinessCard extends ActiveModel<NegotiationInterviewBusinessCard> {

	private Long negotiationId = Long.MIN_VALUE;
	private Long businessCardId = null;
	private String unmanagedName = "";

	private static String[] ignoreFields = new String[] { "iBusinessCard", "businessCardName", "businessCardFullValue" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public NegotiationInterviewBusinessCard() {
		//
	}

	public NegotiationInterviewBusinessCard(Negotiation negotiation, IBusinessCard businessCard) {
		negotiationId = negotiation.getId();
		businessCardId = businessCard.getId();
	}

	public NegotiationInterviewBusinessCard(Negotiation negotiation, String name) {
		negotiationId = negotiation.getId();
		unmanagedName = name;
	}

	public NegotiationInterviewBusinessCard(Negotiation negotiation) {
		negotiationId = negotiation.getId();
	}

	public Boolean isUnmanaged() {
		return businessCardId == null;
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public Long getBusinessCardId() {
		return businessCardId;
	}

	public void setBusinessCardId(Long businessCardId) {
		this.businessCardId = businessCardId;
	}

	public String getBusinessCardName() {
		if (isUnmanaged()) {
			return getUnmanagedName();
		} else {
			return findBusinessCard().getName();
		}
	}

	public String getUnmanagedName() {
		return unmanagedName;
	}

	public void setUnmanagedName(String businessCardName) {
		unmanagedName = businessCardName;
	}

	@Override
	protected ModelMapper<NegotiationInterviewBusinessCard> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewBusinessCardModelMapper.class);
	}

	public IBusinessCard findBusinessCard() {
		return new IBusinessCardRepository().findById(businessCardId).orElseGet(() -> {
			throw new IllegalStateException("存在しないアカウントです:" + businessCardId);
		});
	}

	public String getBusinessCardFullValue() {
		if (getBusinessCardId() == null) {
			return getUnmanagedName();
		} else {
			IBusinessCard card = this.findBusinessCard();
			return card.getFullValue();
		}
	}

}

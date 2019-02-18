package jp.co.world.storedevelopment.model;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.NegotiationInterviewBrandModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;

public class NegotiationInterviewBrand extends ActiveModel<NegotiationInterviewBrand> {

	private Long negotiationId = null;
	private Long brandId = null;

	private static String[] ignoreFields = new String[] { "brand", "brandName", "IBrandIncomeUnit" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public NegotiationInterviewBrand(Negotiation negotiation) {
		negotiationId = negotiation.getId();
	}

	public NegotiationInterviewBrand() {
	}

	public NegotiationInterviewBrand(Negotiation negotiation, IBrandIncomeUnit iBrandIncomeUnit) {
		this(negotiation);
		brandId = iBrandIncomeUnit.getId();
	}

	@Override
	protected ModelMapper<NegotiationInterviewBrand> modelMapper(SqlSession session) {
		return session.getMapper(NegotiationInterviewBrandModelMapper.class);
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brand) {
		brandId = brand;
	}

	public IBrandIncomeUnit getIBrandIncomeUnit() {
		if(getBrandId() != null ){
			Optional<IBrandIncomeUnit> model = new IBrandIncomeUnitRepository().findById(getBrandId());
			if (model.isPresent()) {
				return model.get();
			}
		}
		return null;
	}

	public String getBrandName() {
		if(getIBrandIncomeUnit() != null) {
			return getIBrandIncomeUnit().getBrandIncomeUnitName();
		}
		return "";
	}

}

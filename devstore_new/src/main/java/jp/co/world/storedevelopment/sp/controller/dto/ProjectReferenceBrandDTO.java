package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MShopCompanyAbbreviationRepository;

public class ProjectReferenceBrandDTO {
	private NegotiationRelationDTO listIBusiness;

	public ProjectReferenceBrandDTO(Long BrandId) {
		if (BrandId != null) {
			Optional<IBrandIncomeUnit> ibrand = new IBrandIncomeUnitRepository().findById(BrandId);
			if (ibrand.isPresent()) {
				listIBusiness = new MShopCompanyAbbreviationRepository()
						.findByName(ibrand.get().getBrandIncomeUnitName());
			}
		}
	}

	public NegotiationRelationDTO getListIBusiness() {
		return listIBusiness;
	}

	public void setListIBusiness(NegotiationRelationDTO listIBusiness) {
		this.listIBusiness = listIBusiness;
	}

}

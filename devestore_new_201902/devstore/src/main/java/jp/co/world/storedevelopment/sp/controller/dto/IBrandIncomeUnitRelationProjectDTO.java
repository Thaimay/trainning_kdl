package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Optional;

import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.IIncomeUnit;
import jp.co.world.storedevelopment.model.IShopCompany;
import jp.co.world.storedevelopment.model.MShopCompanyAbbreviation;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MShopCompanyAbbreviationRepository;

public class IBrandIncomeUnitRelationProjectDTO implements DTO<IBrandIncomeUnit> {

	private Long id;
	private String name;
	private String brandIncomeUnitCd;
	private String brandIncomeUnitName;
	private Long incomeUnitId;

	public IBrandIncomeUnitRelationProjectDTO() {

	}

	public IBrandIncomeUnitRelationProjectDTO(IBrandIncomeUnit iBrand) {
		this.copyProperties(this, iBrand);
		name = iBrand.getBrandIncomeUnitName();
	}

	@Override
	public IBrandIncomeUnit createModel() {
		return new IBrandIncomeUnit();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrandIncomeUnitCd() {
		return brandIncomeUnitCd;
	}

	public void setBrandIncomeUnitCd(String brandIncomeUnitCd) {
		this.brandIncomeUnitCd = brandIncomeUnitCd;
	}

	public String getBrandIncomeUnitName() {
		return brandIncomeUnitName;
	}

	public void setBrandIncomeUnitName(String brandIncomeUnitName) {
		this.brandIncomeUnitName = brandIncomeUnitName;
	}

	public Long getIncomeUnitId() {
		return incomeUnitId;
	}

	public void setIncomeUnitId(Long incomeUnitId) {
		this.incomeUnitId = incomeUnitId;
	}

	public IShopCompany getIShopCompany() {
		if (getIncomeUnitId() == null) {
			return null;
		}

		Optional<IIncomeUnit> ii = new IIncomeUnitRepository().findById(getIncomeUnitId());
		if (ii.isPresent()) {
			if (ii.get().getCompanyId() != null) {
				IShopCompany iscc = new IShopCompany();
				iscc.setCompanyId(ii.get().getCompanyId().longValue());

				Optional<IShopCompany> isc = new IShopCompanyRepository().findByImportCode(iscc);
				if (isc.isPresent()) {
					return isc.get();
				}
			}

		}

		return null;
	}

	public String getIBussinessName() {
		if (getId() != null) {
			Optional<IBrandIncomeUnit> ibrand = new IBrandIncomeUnitRepository().findById(getId());
			if (ibrand.isPresent()) {
				Optional<MShopCompanyAbbreviation> optShopCompany = new MShopCompanyAbbreviationRepository()
						.findByName(ibrand.get().getBrandIncomeUnitName());
				if (optShopCompany.isPresent()) {
					return ZenkakuUtils.toZenkaku(optShopCompany.get().getAbbreviatedCompanyName());
				}
			}
		}

		return "";
	}
}

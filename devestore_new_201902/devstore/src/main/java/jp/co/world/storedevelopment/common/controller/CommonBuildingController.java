package jp.co.world.storedevelopment.common.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MBuildingSalesClassificationsRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MBuildingSalesTypesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MCreditRankRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TenantRepository;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingListParentDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IBusinessCardListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ICorporationListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TenantListDTO;

public abstract class CommonBuildingController extends ResourceController {

	@RequestMapping("/find/account")
	@ResponseBody
	public List<NegotiationRelationDTO> findAccount(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findAccount", dto);
			List<NegotiationRelationDTO> listDto = new AccountRepository().findByText(dto);
			logEndMethod("findAccount");
			return listDto;
		} catch (Exception ex) {
			logException("findAccount", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_area")
	@ResponseBody
	public List<BuildingRelationDTO> findArea(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findArea", dto);
			List<BuildingRelationDTO> listDto = new IAreaRepository().findByText(dto);
			logEndMethod("findArea");
			return listDto;
		} catch (Exception ex) {
			logException("findArea", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_block")
	@ResponseBody
	public List<BuildingRelationDTO> findBlock(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findBlock", dto);
			List<BuildingRelationDTO> listDto = new IBlockRepository().findByText(dto);
			logEndMethod("findBlock");
			return listDto;
		} catch (Exception ex) {
			logException("findBlock", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_business_card")
	@ResponseBody
	public List<IBusinessCardListDTO> findBusinessCard(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {

		try {
			logStartMethod("findBusinessCard", dto);
			List<IBusinessCardListDTO> listDto = new IBusinessCardRepository().findByString(dto).stream().map(b -> {
				b.setDisplayName(b.getName());
				b.setName(String.format("%s_%s_%s", b.getName(), b.getDepartmentName(), b.getPositionName()));
				return b;
			}).collect(Collectors.toList());
			logEndMethod("findBusinessCard");
			return listDto;
		} catch (Exception ex) {
			logException("findBusinessCard", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/corporation")
	@ResponseBody
	public List<BuildingRelationDTO> findCorporation(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findCorporation", dto);
			List<BuildingRelationDTO> listDto = new ICorporationRepository().findByString(dto);
			logEndMethod("findCorporation");
			return listDto;
		} catch (Exception ex) {
			logException("findCorporation", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/list_by_group/{iCorporationGpId}")
	@ResponseBody
	public List<ICorporationListDTO> findByCorporationGroupId(@PathVariable Long iCorporationGpId) {
		try {
			logStartMethod("findByCorporationGroupId");
			List<ICorporationListDTO> listDto = new ICorporationRepository().findByCorporationGroupId(iCorporationGpId);
			logEndMethod("findByCorporationGroupId");
			return listDto;
		} catch (Exception ex) {
			logException("findByCorporationGroupId", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_corporation_group")
	@ResponseBody
	public List<BuildingRelationDTO> findCorporationGroup(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findCorporationGroup", dto);
			List<BuildingRelationDTO> listDto = new ICorporationGroupRepository().findByText(dto);
			logEndMethod("findCorporationGroup");
			return listDto;
		} catch (Exception ex) {
			logException("findCorporationGroup", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_prefectures")
	@ResponseBody
	public List<BuildingRelationDTO> findIPrefectures(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findIPrefectures", dto);
			List<BuildingRelationDTO> listDto = new IPrefecturesRepository().findByText(dto);
			logEndMethod("findIPrefectures");
			return listDto;
		} catch (Exception ex) {
			logException("findIPrefectures", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_sales_agency_target")
	@ResponseBody
	public List<BuildingRelationDTO> findISalesAgencyTarget(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findISalesAgencyTarget", dto);
			List<BuildingRelationDTO> listDto = new ISalesAgencyTargetRepository().findByText(dto);
			logEndMethod("findISalesAgencyTarget");
			return listDto;
		} catch (Exception ex) {
			logException("findISalesAgencyTarget", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_sales_channel")
	@ResponseBody
	public List<BuildingRelationDTO> findISalesChannel(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findISalesChannel", dto);
			List<BuildingRelationDTO> listDto = new ISalesChannelRepository().findByText(dto);
			logEndMethod("findISalesChannel");
			return listDto;
		} catch (Exception ex) {
			logException("findISalesChannel", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/m_credit_rank")
	@ResponseBody
	public List<BuildingRelationDTO> findMCreditRank(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findMCreditRank", dto);
			List<BuildingRelationDTO> listDto = new MCreditRankRepository().findByText(dto);
			logEndMethod("findMCreditRank");
			return listDto;
		} catch (Exception ex) {
			logException("findMCreditRank", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/participating_store_corporation")
	@ResponseBody
	public List<BuildingRelationDTO> findParticipatingStoreCorporation(
			@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findParticipatingStoreCorporation", dto);
			List<BuildingRelationDTO> listDto = new ParticipatingStoreCorporationRepository().findByText(dto);
			logEndMethod("findParticipatingStoreCorporation");
			return listDto;
		} catch (Exception ex) {
			logException("findParticipatingStoreCorporation", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/tenant")
	@ResponseBody
	public List<TenantListDTO> findTenant(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findTenant", dto);
			List<TenantListDTO> listDto = new TenantRepository().findByText(dto);
			logEndMethod("findTenant");
			return listDto;
		} catch (Exception ex) {
			logException("findTenant", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/building_parent")
	@ResponseBody
	public List<BuildingListParentDTO> findBuilding(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findBuilding", dto);
			List<BuildingListParentDTO> listDto = new BuildingRepository().findByParentText(dto);
			logEndMethod("findBuilding");
			return listDto;
		} catch (Exception ex) {
			logException("findBuilding", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/shop")
	@ResponseBody
	public List<BuildingRelationDTO> findShop(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findShop", dto);
			List<BuildingRelationDTO> listDto = new ShopRepository().findByText(dto);
			logEndMethod("findShop");
			return listDto;
		} catch (Exception ex) {
			logException("findShop", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/m_building_sales_types")
	@ResponseBody
	public List<BuildingRelationDTO> findMBuildingSalesTypes(
			@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findMBuildingSalesTypes", dto);
			List<BuildingRelationDTO> listDto = new MBuildingSalesTypesRepository().findByText(dto);
			logEndMethod("findMBuildingSalesTypes");
			return listDto;
		} catch (Exception ex) {
			logException("findMBuildingSalesTypes", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/m_building_sales_classifications")
	@ResponseBody
	public List<BuildingRelationDTO> findMBuildingSalesClassifications(
			@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findMBuildingSalesClassifications", dto);
			List<BuildingRelationDTO> listDto = new MBuildingSalesClassificationsRepository().findByText(dto);
			logEndMethod("findMBuildingSalesClassifications");
			return listDto;
		} catch (Exception ex) {
			logException("findMBuildingSalesClassifications", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_shop_company")
	@ResponseBody
	public List<BuildingRelationDTO> findShopCompany(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findShopCompany", dto);
			List<BuildingRelationDTO> listDto = new IShopCompanyRepository().findByText(dto);
			logEndMethod("findShopCompany");
			return listDto;
		} catch (Exception ex) {
			logException("findShopCompany", ex.getMessage());
			return null;
		}
	}
}

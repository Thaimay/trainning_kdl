package jp.co.world.storedevelopment.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.FileDivision;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.FileDivisionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByParamFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationProjectDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedFindFromProjectDTO;

public abstract class CommonNegotiationController extends ResourceController {

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

	@RequestMapping("/find/partner")
	@ResponseBody
	public List<NegotiationRelationDTO> findPartner(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findPartner", dto);
			List<NegotiationRelationDTO> listDto = new IBusinessCardRepository().findByText(dto);
			logEndMethod("findPartner");
			return listDto;
		} catch (Exception ex) {
			logException("findPartner", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/building")
	@ResponseBody
	public List<NegotiationRelationDTO> findBuilding(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findBuilding", dto);
			List<NegotiationRelationDTO> listDto = new BuildingRepository().findByText(dto);
			logEndMethod("findBuilding");
			return listDto;
		} catch (Exception ex) {
			logException("findBuilding", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/shop")
	@ResponseBody
	public List<NegotiationRelationDTO> findShop(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findShop", dto);
			List<NegotiationRelationDTO> listDto = new IShopRepository().findByString(dto.getText()).stream().map(s -> {
				return new NegotiationRelationDTO(s.getId(), ZenkakuUtils.toZenkaku(s.getShopNameZenkaku()));
			}).collect(Collectors.toList());
			logEndMethod("findShop");
			return listDto;
		} catch (Exception ex) {
			logException("findShop", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/corporation")
	@ResponseBody
	public List<NegotiationRelationDTO> findCorporation(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findCorporation", dto);
			List<NegotiationRelationDTO> listDto = new ICorporationRepository().findByText(dto);
			logEndMethod("findCorporation");
			return listDto;
		} catch (Exception ex) {
			logException("findCorporation", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/brand")
	@ResponseBody
	public List<NegotiationRelationDTO> findBrand(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findCorporation", dto);
			List<NegotiationRelationDTO> listDto = new IBrandIncomeUnitRepository().findByText(dto);
			logEndMethod("findCorporation");
			return listDto;
		} catch (Exception ex) {
			logException("findCorporation", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project")
	@ResponseBody
	public List<NegotiationRelationProjectDTO> findProject(@RequestBody @Valid RelatedFindFromProjectDTO dto) {
		try {
			logStartMethod("findProject", dto);
			dto.setAccount(getAccount());
			dto.setPagingSize(Application.SUGGEST_LIMIT_SIZE);
			if (dto.getText().isEmpty()) {
				return new ArrayList<>();
			}
			List<Project> listProjectDto = new ProjectRepository().findForSuggest(dto);
			List<NegotiationRelationProjectDTO> dtos = new ArrayList<>();
			listProjectDto.forEach(x -> {
				dtos.add(new NegotiationRelationProjectDTO(x.getId(), ZenkakuUtils.toZenkaku(x.getTitle())));
			});
			logEndMethod("findProject");
			return dtos;
		} catch (Exception ex) {
			logException("findProject", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project/detail")
	@ResponseBody
	public NegotiationRelationProjectDTO findProjectDetail(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findProjectDetail", dto);
			NegotiationRelationProjectDTO projectDto = new ProjectRepository().findForNegotiationById(dto, getAccount());
			logEndMethod("findProjectDetail");
			return projectDto;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("findProjectDetail", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/buildingfile")
	@ResponseBody
	public List<NegotiationRelationDTO> findBuildingFile(@RequestBody @Valid NegotiationRelationFindByParamFormDTO dto) {
		try {
			logStartMethod("findBuildingFile", dto);
			List<NegotiationRelationDTO> listDto = new BuildingRepository().findByNameForNegotiationFile(dto);
			logEndMethod("findBuildingFile");
			return listDto;
		} catch (Exception ex) {
			logException("findBuildingFile", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/shopfile")
	@ResponseBody
	public List<NegotiationRelationDTO> findShopFile(@RequestBody @Valid NegotiationRelationFindByParamFormDTO dto) {
		try {
			logStartMethod("findShopFile", dto);
			List<NegotiationRelationDTO> listDto = new ArrayList<>();
			if(dto.getParams() != null && dto.getParams().size() > 0) {
				listDto = new ShopRepository().findByNameForNegotiationFile(dto);
			}
			return listDto;
		} catch (Exception ex) {
			logException("findShopFile", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/projectfile")
	@ResponseBody
	public List<NegotiationRelationDTO> findProjectFile(@RequestBody @Valid NegotiationRelationFindByParamFormDTO dto) {
		try {
			logStartMethod("findProjectFile", dto);
			List<NegotiationRelationDTO> listDto = new ProjectRepository().findByNameForNegotiationFile(dto);
			logEndMethod("findProjectFile");
			return listDto;
		} catch (Exception ex) {
			logException("findProjectFile", ex.getMessage());
			return null;
		}
	}
	
	@RequestMapping("/find/file_division")
	@ResponseBody
	public List<FileDivision> findFileDivision() {
		try {
			logStartMethod("findFileDivision");
			List<FileDivision> listDto = new FileDivisionRepository().findAll();
			logEndMethod("findFileDivision");
			return listDto;
		} catch (Exception ex) {
			logException("findFileDivision", ex.getMessage());
			return null;
		}
	}

}

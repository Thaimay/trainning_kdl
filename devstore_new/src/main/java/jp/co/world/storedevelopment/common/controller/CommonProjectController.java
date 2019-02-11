package jp.co.world.storedevelopment.common.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.FileDivision;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.MLocality;
import jp.co.world.storedevelopment.model.MPeriod;
import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.MProjectMeetingResult;
import jp.co.world.storedevelopment.model.MShopType;
import jp.co.world.storedevelopment.model.MStoreDevelopTeam;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectCategory;
import jp.co.world.storedevelopment.model.ProjectClassification;
import jp.co.world.storedevelopment.model.ProjectProgress;
import jp.co.world.storedevelopment.model.ProjectSchedule;
import jp.co.world.storedevelopment.model.ShopFile;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.FileDivisionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MLocalityRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MPeriodRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectMeetingResultRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MShopTypeRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MStoreDevelopTeamRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectClassificationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopImageRepository;
import jp.co.world.storedevelopment.model.service.ProjectService;
import jp.co.world.storedevelopment.model.value.ProjectFileDivision;
import jp.co.world.storedevelopment.sp.controller.dto.ActionStatusFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.AreaLocalityDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BlockLocalityDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.FileUploadDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IBrandIncomeUnitBusinessListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IBusinessCardListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IShopCompanySearchListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IShopRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.LocalityDTO;
import jp.co.world.storedevelopment.sp.controller.dto.MProjectActionStatusScheduleDTO;
import jp.co.world.storedevelopment.sp.controller.dto.MStoreDevelopTeamListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.PeriodSearchListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.PrefecturesLocalityDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectReferenceBrandDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectReferenceBuildingDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectReferenceShopDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectScheduleUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopRelationDTO;

public class CommonProjectController extends ResourceController {
	@RequestMapping("/find/iarea")
	@ResponseBody
	public List<BuildingRelationDTO> findIArea(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findIArea", dto);
			List<BuildingRelationDTO> listDto = new IAreaRepository().findByText(dto);
			logEndMethod("findIArea");
			return listDto;
		} catch (Exception ex) {
			logException("findIArea", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/action/status")
	public Project actionStatus(@RequestBody @Valid ActionStatusFormDTO dto) {
		try {
			logStartMethod("actionStatus");

			Project model = new ProjectService().reflectActionStatus(dto.toModel(), dto);
			logEndMethod("actionStatus");
			return model;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("actionStatus", ex.getMessage());
		}
		return null;
	}

	@RequestMapping("/find/iblock")
	@ResponseBody
	public List<BuildingRelationDTO> findIBlock(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findIBlock", dto);
			List<BuildingRelationDTO> listDto = new IBlockRepository().findByText(dto);
			logEndMethod("findIBlock");
			return listDto;
		} catch (Exception ex) {
			logException("findIBlock", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/iprefectures")
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

	@RequestMapping("/find/ibrandincomeunit")
	@ResponseBody
	public List<NegotiationRelationDTO> findIBrandIncomeUnit(
			@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findIBrandIncomeUnit", dto);
			List<NegotiationRelationDTO> listDto = new IBrandIncomeUnitRepository().findByText(dto);
			logEndMethod("findIBrandIncomeUnit");
			return listDto;
		} catch (Exception ex) {
			logException("findIBrandIncomeUnit", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/icorporation")
	@ResponseBody
	public List<NegotiationRelationDTO> findICorporation(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findICorporation", dto);
			List<NegotiationRelationDTO> listDto = new ICorporationRepository().findByText(dto);
			logEndMethod("findICorporation");
			return listDto;
		} catch (Exception ex) {
			logException("findICorporation", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/isaleschannel")
	@ResponseBody
	public List<ISalesChannel> findISaleschannel() {
		try {
			logStartMethod("findISaleschannel");
			List<ISalesChannel> listDto = new ISalesChannelRepository().findAll();
			logEndMethod("findISaleschannel");
			return listDto;
		} catch (Exception ex) {
			logException("findISaleschannel", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/projectprogress")
	@ResponseBody
	public List<NegotiationRelationDTO> findNegotiationProjectProgress(
			@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findProjectprogress", dto);
			List<NegotiationRelationDTO> listDto = new ProjectProgressRepository().findByText(dto);
			logEndMethod("findProjectprogress");
			return listDto;
		} catch (Exception ex) {
			logException("findProjectprogress", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/projectcategory")
	public List<ProjectCategory> findProjectCategory() {
		try {
			logStartMethod("findProjectCategory");
			List<ProjectCategory> listDto = new ProjectCategoryRepository().getAllBySort();
			logEndMethod("findProjectCategory");
			return listDto;
		} catch (Exception ex) {
			logException("findProjectCategory", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/projectlandingclassification")
	public List<ProjectClassification> findProjectLandingClassification() {
		try {
			logStartMethod("findProjectLandingClassification");
			List<ProjectClassification> listDto = new ProjectClassificationRepository().getLandingClassification();
			logEndMethod("findProjectLandingClassification");
			return listDto;
		} catch (Exception ex) {
			logException("findProjectLandingClassification", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_classification/suspension/{categoryId}")
	public List<ProjectClassification> findSuspensionClassification(@PathVariable Long categoryId) {
		try {
			logStartMethod("findSuspensionClassification");
			List<ProjectClassification> listDto = new ArrayList<ProjectClassification>();
			if (categoryId != null) {
				listDto = new ProjectClassificationRepository().getSuspensionClassification(categoryId);
			}
			logEndMethod("findSuspensionClassification");
			return listDto;
		} catch (Exception ex) {
			logException("findSuspensionClassification", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_classification/landing/{categoryId}")
	public List<ProjectClassification> findLandingClassification(@PathVariable Long categoryId) {
		try {
			logStartMethod("findLandingClassification");
			List<ProjectClassification> listDto = new ArrayList<ProjectClassification>();
			if (categoryId != null) {
				listDto = new ProjectClassificationRepository().getLandingClassification(categoryId);
			}
			logEndMethod("findLandingClassification");
			return listDto;
		} catch (Exception ex) {
			logException("findLandingClassification", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_classification/landing_possibility/{categoryId}")
	public List<ProjectClassification> findLandingPossibilityClassification(@PathVariable Long categoryId) {
		try {
			logStartMethod("findLandingPossibilityClassification");
			List<ProjectClassification> listDto = new ArrayList<ProjectClassification>();
			if (categoryId != null) {
				listDto = new ProjectClassificationRepository().getLandingPossibilityClassification(categoryId);
			}
			logEndMethod("findLandingPossibilityClassification");
			return listDto;
		} catch (Exception ex) {
			logException("findLandingPossibilityClassification", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_classification/project_plan")
	public List<ProjectClassification> findProjectPlanClassification() {
		try {
			logStartMethod("findProjectPlanClassification");
			List<ProjectClassification> listDto = new ProjectClassificationRepository().getProjectPlanClassification();
			logEndMethod("findProjectPlanClassification");
			return listDto;
		} catch (Exception ex) {
			logException("findProjectPlanClassification", ex.getMessage());
			return null;
		}
	}

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

	@RequestMapping("/find/shopCd")
	@ResponseBody
	public List<ShopRelationDTO> findShopCd(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findShopCd", dto);
			List<ShopRelationDTO> listDto = new IShopRepository().findShopCdByString(dto.getText()).stream().map(s -> {
				return new ShopRelationDTO(s.getId(), s.getShopCd(), ZenkakuUtils.toZenkaku(s.getShopNameZenkaku()));
			}).collect(Collectors.toList());
			logEndMethod("findShopCd");
			return listDto;
		} catch (Exception ex) {
			logException("findShopCd", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/ibusinesscard")
	@ResponseBody
	public List<IBusinessCardListDTO> findBusinessCard(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {

		try {
			logStartMethod("findIBusinessCard", dto);
			List<IBusinessCardListDTO> listDto = new IBusinessCardRepository().findByString(dto).stream().map(b -> {
				b.setDisplayName(b.getName());
				b.setName(String.format("%s_%s_%s", b.getName(), b.getDepartmentName(), b.getPositionName()));
				return b;
			}).collect(Collectors.toList());
			logEndMethod("findIBusinessCard");
			return listDto;
		} catch (Exception ex) {
			logException("findIBusinessCard", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/projectfiledivision")
	@ResponseBody
	public List<String> findProjectFileDivision() {
		try {
			logStartMethod("findProjectDivision");
			List<String> listDto = ProjectFileDivision.all();
			logEndMethod("findProjectDivision");
			return listDto;
		} catch (Exception ex) {
			logException("findProjectDivision", ex.getMessage());
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
	public List<IShopRelationDTO> findShop(@RequestBody @Valid NegotiationRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findShop", dto);
			List<IShopRelationDTO> listDto = new IShopRepository().findByString(dto.getText()).stream().map(s -> {
				return new IShopRelationDTO(s.getId(), ZenkakuUtils.toZenkaku(s.getShopNameZenkaku()), s.getShopCd());
			}).collect(Collectors.toList());
			logEndMethod("findShop");
			return listDto;
		} catch (Exception ex) {
			logException("findShop", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/ishopcompany")
	@ResponseBody
	public List<BuildingRelationDTO> findIShopCompany(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("ishopcompany", dto);
			List<BuildingRelationDTO> listDto = new IShopCompanyRepository().findByText(dto);
			logEndMethod("ishopcompany");
			return listDto;
		} catch (Exception ex) {
			logException("ishopcompany", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/mperiod/{period}")
	@ResponseBody
	public MPeriod findMPeriod(@PathVariable Long period) {
		try {
			logStartMethod("findMPeriod", period);
			MPeriod mp = new MPeriodRepository().findByPeriod(period);
			logEndMethod("findMPeriod");
			return mp;
		} catch (Exception ex) {
			logException("findMPeriod", ex.getMessage());
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

	@RequestMapping("/find/buildingfile/{buildingId}")
	public List<FileUploadDTO> findBuildingFile(@PathVariable Long buildingId) {
		try {
			logStartMethod("findBuildingFile");
			List<FileUploadDTO> listDto = new ArrayList<>();

			if (buildingId != null && buildingId > 0) {
				List<BuildingFile> listFile = new BuildingFileRepository().findBuildingFileRelatedProject(buildingId);
				listFile.forEach(x -> listDto.add(new FileUploadDTO(x)));

				List<BuildingImage> listImage = new BuildingImageRepository()
						.findBuildingImageRelatedProject(buildingId);
				listImage.forEach(x -> listDto.add(new FileUploadDTO(x)));
			}

			logEndMethod("findBuildingFile");
			return listDto.stream().sorted((x2, x1) -> x1.getUpdateDatetime().compareTo(x2.getUpdateDatetime()))
					.collect(Collectors.toList());
		} catch (Exception ex) {
			logException("findBuildingFile", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_progress/company/{categoryId}")
	public List<ProjectProgress> findCompanyProjectProgress(@PathVariable Long categoryId) {
		try {
			logStartMethod("findCompanyProjectProgress");
			List<ProjectProgress> listDto = new ProjectProgressRepository().getCompanyCategory(categoryId);
			logEndMethod("findCompanyProjectProgress");
			return listDto;
		} catch (Exception ex) {
			logException("findCompanyProjectProgress", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_progress/negotiation/{categoryId}")
	public List<ProjectProgress> findNegotiationProjectProgress(@PathVariable Long categoryId) {
		try {
			logStartMethod("findNegotiationProjectProgress");
			List<ProjectProgress> listDto = new ProjectProgressRepository().getNegotiationCategory(categoryId);
			logEndMethod("findNegotiationProjectProgress");
			return listDto;
		} catch (Exception ex) {
			logException("findNegotiationProjectProgress", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/refBuildingData/{buildingId}")
	public ProjectReferenceBuildingDTO refBuildingData(@PathVariable Long buildingId) {
		try {
			logStartMethod("refBuildingData");
			ProjectReferenceBuildingDTO dto = new ProjectReferenceBuildingDTO(buildingId);

			logEndMethod("refBuildingData");
			return dto;
		} catch (Exception ex) {
			logException("refBuildingData", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/refShopData/{shopId}")
	public ProjectReferenceShopDTO refShopData(@PathVariable Long shopId) {
		try {
			logStartMethod("refShopData");
			ProjectReferenceShopDTO dto = new ProjectReferenceShopDTO(shopId, getAccount());

			logEndMethod("refShopData");
			return dto;
		} catch (Exception ex) {
			logException("refShopData", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/refBrandData/{brandId}")
	public ProjectReferenceBrandDTO refBrandData(@PathVariable Long brandId) {
		try {
			logStartMethod("refBrandData");
			ProjectReferenceBrandDTO dto = new ProjectReferenceBrandDTO(brandId);

			logEndMethod("refBrandData");
			return dto;
		} catch (Exception ex) {
			logException("refBrandData", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/shopfile/{shopId}")
	public List<FileUploadDTO> findShopFile(@PathVariable Long shopId) {
		try {
			logStartMethod("findShopFile");
			List<FileUploadDTO> listDto = new ArrayList<>();

			if (shopId != null && shopId > 0) {
				List<ShopFile> listFile = new ShopFileRepository().findShopFileRelatedProject(shopId);
				listFile.forEach(x -> listDto.add(new FileUploadDTO(x)));

				List<ShopImage> listImage = new ShopImageRepository().findShopImageRelatedProject(shopId);
				listImage.forEach(x -> listDto.add(new FileUploadDTO(x)));
			}

			logEndMethod("findShopFile");
			return listDto.stream().sorted((x2, x1) -> x1.getUpdateDatetime().compareTo(x2.getUpdateDatetime()))
					.collect(Collectors.toList());
		} catch (Exception ex) {
			logException("findShopFile", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/action/status/{projectCategoryId}/{salesChannelId}")
	public List<MProjectActionStatus> findActionStatus(@PathVariable Long projectCategoryId,
			@PathVariable Long salesChannelId) {
		try {
			logStartMethod("findActionStatus");
			List<MProjectActionStatus> list = new MProjectActionStatusRepository().findBy(projectCategoryId,
					salesChannelId);
			logEndMethod("findActionStatus");
			return list;
		} catch (Exception ex) {
			logException("findActionStatus", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/m_project_meeting_result")
	public List<MProjectMeetingResult> findMProjectMeetingResult() {
		try {
			logStartMethod("findMProjectMeetingResult");
			List<MProjectMeetingResult> listDto = new MProjectMeetingResultRepository().getMProjectMeetingResultList();
			logEndMethod("findMProjectMeetingResult");
			return listDto;
		} catch (Exception ex) {
			logException("findMProjectMeetingResult", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_schedule/{projectId}")
	public List<ProjectScheduleUpdateDTO> findProjectSchedule(@PathVariable Long projectId) {
		try {
			logStartMethod("findSchedule");
			List<ProjectScheduleUpdateDTO> listDto = new ArrayList<ProjectScheduleUpdateDTO>();
			List<ProjectSchedule> projectScheduleList = new ProjectScheduleRepository().findByProjectId(projectId);
			projectScheduleList.forEach(schedule -> {
				MProjectActionStatus mProjectActionStatus = new MProjectActionStatusRepository()
						.findById(schedule.getMProjectActionStatusId()).orElse(new MProjectActionStatus());
				listDto.add(new ProjectScheduleUpdateDTO(schedule, mProjectActionStatus));
			});
			logEndMethod("finSchedule");
			return listDto;
		} catch (Exception ex) {
			logException("findSchedule", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_schedule_list/{projectCategoryId}/{salesChannelId}")
	public List<MProjectActionStatusScheduleDTO> findInitSchedule(@PathVariable Long projectCategoryId,
			@PathVariable Long salesChannelId) {
		try {
			logStartMethod("findInitSchedule");

			List<MProjectActionStatusScheduleDTO> listDto = new ArrayList<MProjectActionStatusScheduleDTO>();
			List<MProjectActionStatus> mProjectActionStatusList = new MProjectActionStatusRepository()
					.getScheduleUseList(projectCategoryId, salesChannelId);
			mProjectActionStatusList.forEach(m -> {
				listDto.add(new MProjectActionStatusScheduleDTO(m, null));
			});
			logEndMethod("findInitSchedule");
			return listDto;
		} catch (Exception ex) {
			logException("findInitSchedule", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_schedule_list/{projectCategoryId}/{salesChannelId}/{year}/{month}/{day}")
	public List<MProjectActionStatusScheduleDTO> findInitProjectSchedule(@PathVariable Long projectCategoryId,
			@PathVariable Long salesChannelId, @PathVariable Integer year, @PathVariable Integer month,
			@PathVariable Integer day) {
		try {
			LocalDate targetDate = LocalDate.of(year, month, day);

			logStartMethod("findInitProjectSchedule");
			List<MProjectActionStatusScheduleDTO> listDto = new ArrayList<MProjectActionStatusScheduleDTO>();
			List<MProjectActionStatus> mProjectActionStatusList = new MProjectActionStatusRepository()
					.getScheduleUseList(projectCategoryId, salesChannelId);

			mProjectActionStatusList.forEach(m -> {
				listDto.add(new MProjectActionStatusScheduleDTO(m, targetDate));
			});
			logEndMethod("findInitProjectSChedule");
			return listDto;
		} catch (Exception ex) {
			logException("findInitProjectSchedule", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/salesagencytarget")
	@ResponseBody
	public List<BuildingRelationDTO> findSalesAgencyTarget(@RequestBody @Valid BuildingRelationFindByTextFormDTO dto) {
		try {
			logStartMethod("findSalesAgencyTarget", dto);
			List<BuildingRelationDTO> listDto = new ISalesAgencyTargetRepository().findByText(dto);
			logEndMethod("findSalesAgencyTarget");
			return listDto;
		} catch (Exception ex) {
			logException("findSalesAgencyTarget", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/participatingstorecorporation")
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

	@RequestMapping("/find/locality")
	@ResponseBody
	public LocalityDTO findLocality() {
		try {
			logStartMethod("findLocality");
			LocalityDTO listDto = new LocalityDTO();

			TreeMap<Long, String> areaMap = new TreeMap<Long, String>();
			new IAreaRepository().findAll().forEach(x -> areaMap.put(x.getId(), x.getAreaName()));

			TreeMap<Long, String> prefecturesMap = new TreeMap<Long, String>();
			new IPrefecturesRepository().findAll().forEach(x -> prefecturesMap.put(x.getId(), x.getPrefecturesName()));

			TreeMap<Long, String> blockMap = new TreeMap<Long, String>();
			new IBlockRepository().findAll().forEach(x -> blockMap.put(x.getId(), x.getBlockName()));

			List<MLocality> listModel = new MLocalityRepository().findAllSort();

			List<AreaLocalityDTO> listArea = new ArrayList<>();
			List<Long> areaIds = listModel.stream().map(x -> x.getiAreaId()).distinct().collect(Collectors.toList());
			areaIds.forEach(areaId -> {
				if (areaId != null && areaMap.containsKey(areaId)) {
					AreaLocalityDTO areaDTO = new AreaLocalityDTO();
					areaDTO.setId(areaId);
					areaDTO.setName(areaMap.get(areaId));

					List<PrefecturesLocalityDTO> listPrefectures = new ArrayList<>();
					List<Long> prefecturesIds = listModel.stream().filter(x -> x.getiAreaId() == areaId)
							.map(x -> x.getiPrefectureId()).distinct().collect(Collectors.toList());

					prefecturesIds.forEach(prefecturesId -> {
						if (prefecturesId != null && prefecturesMap.containsKey(prefecturesId)) {
							PrefecturesLocalityDTO prefecturesDTO = new PrefecturesLocalityDTO();
							prefecturesDTO.setId(prefecturesId);
							prefecturesDTO.setName(prefecturesMap.get(prefecturesId));

							List<BlockLocalityDTO> listBlock = new ArrayList<>();
							List<Long> blockIds = listModel.stream().filter(x -> x.getiPrefectureId() == prefecturesId)
									.map(x -> x.getiBlockId()).distinct().collect(Collectors.toList());

							blockIds.forEach(blockId -> {
								if (blockId != null && blockMap.containsKey(blockId)) {
									BlockLocalityDTO blockDTO = new BlockLocalityDTO();
									blockDTO.setId(blockId);
									blockDTO.setName(blockMap.get(blockId));

									listBlock.add(blockDTO);
								}
							});

							prefecturesDTO.setListBlock(listBlock);

							listPrefectures.add(prefecturesDTO);
						}
					});

					areaDTO.setListPrefectures(listPrefectures);

					listArea.add(areaDTO);
				}
			});

			listDto.setListArea(listArea);

			logEndMethod("findLocality");
			return listDto;
		} catch (Exception ex) {
			logException("findLocality", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/store/require/{id}")
	public Boolean requireStore(@PathVariable Long id) {
		try {
			logStartMethod("requireStore");

			ProjectCategory model = new ProjectCategoryRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException("存在しない案件種別です");
			});
			logEndMethod("requireStore");
			return model.requireStore();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			logException("requireStore", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/period/{date}")
	public String getPeriod(@PathVariable String date) {
		try {
			logStartMethod("getPeriod", date);
			String term = StringUtils.EMPTY;
			if (!date.isEmpty()) {
				Optional<MPeriod> opt = new MPeriodRepository().getPeriod(date);
				if (opt.isPresent()) {
					term = opt.get().getPeriod() + "期";
				}
			}
			logEndMethod("getPeriod");
			return new JSONObject().put("term", term).toString();
		} catch (Exception ex) {
			logException("getPeriod", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/period/present")
	public Integer getPresentPeriod() {
		try {
			logStartMethod("getPresentPeriod");
			Integer period = null;
			String present = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			Optional<MPeriod> opt = new MPeriodRepository().getPeriod(present);
			if (opt.isPresent()) {
				period = opt.get().getPeriod();
			}
			logEndMethod("getPresentPeriod");
			return period;
		} catch (Exception ex) {
			logException("getPresentPeriod", ex.getMessage());
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/period/current_implementation_period_list")
	public List<PeriodSearchListDTO> getCurrentImplementationPeriod() {
		try {
			logStartMethod("getCurrentImplementationPeriod");
			List<PeriodSearchListDTO> currentImplementationPeriod = new ArrayList<>();
			Integer presentPeriod = getPresentPeriod();
			List<PeriodSearchListDTO> period = new MPeriodRepository().getCurrentImplementationPeriod();
			period.forEach(x -> {
				if (x.getId() >= presentPeriod) {
					currentImplementationPeriod.add(x);
				}
			});
			logEndMethod("getCurrentImplementationPeriod");
			return currentImplementationPeriod;
		} catch (Exception ex) {
			logException("getCurrentImplementationPeriod", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/m_store_develop_team")
	@ResponseBody
	public List<MStoreDevelopTeamListDTO> findMStoreDevelopTeam() {
		try {
			logStartMethod("findMStoreDevelopTeam");
			List<MStoreDevelopTeamListDTO> listDto = new ArrayList<>();
			List<MStoreDevelopTeam> listAll = new MStoreDevelopTeamRepository().findByALL();
			listAll.forEach(x -> {
				listDto.add(new MStoreDevelopTeamListDTO(x));
			});
			logEndMethod("findMStoreDevelopTeam");
			return listDto;
		} catch (Exception ex) {
			logException("findMStoreDevelopTeam", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/projectclassificationprogresscompany")
	public List<ProjectClassification> findProjectClassificationProgressCompany() {
		try {
			logStartMethod("findProjectClassificationProgressCompany");
			List<ProjectClassification> listDto = new ProjectClassificationRepository()
					.getProgressCompanyClassification();
			logEndMethod("findProjectClassificationProgressCompany");
			return listDto;
		} catch (Exception ex) {
			logException("findProjectClassificationProgressCompany", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/projectclassificationprogressnegotiation")
	public List<ProjectClassification> findProjectClassificationProgressNegotiation() {
		try {
			logStartMethod("projectclassificationprogressnegotiation");
			List<ProjectClassification> listDto = new ProjectClassificationRepository()
					.getProgressNegotiationClassification();
			logEndMethod("projectclassificationprogressnegotiation");
			return listDto;
		} catch (Exception ex) {
			logException("projectclassificationprogressnegotiation", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/i_shop_company_search_list")
	@ResponseBody
	public List<IShopCompanySearchListDTO> findIShopCompanySearchList() {
		try {
			logStartMethod("findIShopCompanySearchList");
			List<IShopCompanySearchListDTO> listDTO = new ArrayList<>();

			TreeMap<Long, String> companyMap = new TreeMap<Long, String>();
			new IShopCompanyRepository().findAll().forEach(x -> companyMap.put(x.getId(), x.getCompanyName()));

			List<IBrandIncomeUnitBusinessListDTO> listBrandBusiness = new IBrandIncomeUnitRepository()
					.getBrandBusinessList();

			List<Long> companyIds = listBrandBusiness.stream().map(x -> x.getCompanyId()).distinct()
					.collect(Collectors.toList());

			companyIds.forEach(companyId -> {
				if (companyId != null && companyMap.containsKey(companyId)) {
					IShopCompanySearchListDTO companyDTO = new IShopCompanySearchListDTO();
					companyDTO.setId(companyId);
					companyDTO.setName(companyMap.get(companyId));

					List<NegotiationRelationDTO> listBrand = new ArrayList<>();
					List<IBrandIncomeUnitBusinessListDTO> listBrandFilter = listBrandBusiness.stream()
							.filter(x -> x.getCompanyId() == companyId).collect(Collectors.toList());

					listBrandFilter
							.forEach(x -> listBrand.add(new NegotiationRelationDTO(x.getBrandId(), x.getBrandName())));

					companyDTO.setListBrand(listBrand);
					listDTO.add(companyDTO);
				}
			});

			logEndMethod("findIShopCompanySearchList");
			return listDTO;
		} catch (Exception ex) {
			logException("findIShopCompanySearchList", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/project_classification/project_requestor_reason")
	public List<ProjectClassification> findRequestorReasonClassification() {
		try {
			logStartMethod("findRequestorReasonClassification");
			List<ProjectClassification> listDto = new ProjectClassificationRepository()
					.getRequestorReasonClassification();
			logEndMethod("findRequestorReasonClassification");
			return listDto;
		} catch (Exception ex) {
			logException("findRequestorReasonClassification", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/find/m_shop_type")
	@ResponseBody
	public List<MShopType> findMShopType() {
		try {
			logStartMethod("findMShopType");
			List<MShopType> listAll = new MShopTypeRepository().getAllShopType();
			logEndMethod("findMShopType");
			return listAll;
		} catch (Exception ex) {
			logException("findMShopType", ex.getMessage());
			return null;
		}
	}
}

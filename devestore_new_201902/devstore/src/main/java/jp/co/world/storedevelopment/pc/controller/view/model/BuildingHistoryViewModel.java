package jp.co.world.storedevelopment.pc.controller.view.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Activation;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.BuildingKeyman;
import jp.co.world.storedevelopment.model.BuildingMeetingHistory;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.BuildingTenant;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationBuilding;
import jp.co.world.storedevelopment.model.PmCorporation;
import jp.co.world.storedevelopment.model.mapper.repository.ActivationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingKeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingMeetingHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingTenantRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ActivationRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingFileRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingImageRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingKeymanRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingMeetingHistoryRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingPersonalDevelopRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingSalesRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingTenantRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.PmCorporationRelationBuildingDetailDTO;

public class BuildingHistoryViewModel extends BuildingDetailViewModel {

	private Long buildingOriginId;
	
	private List<NegotiationHistoryViewModel> relatedNegotiationHistoryViewModelList = new ArrayList<NegotiationHistoryViewModel>();

	public BuildingHistoryViewModel(Building b, Account a) {
		super(b);		
		Long buildingId = new BuildingRepository().getBuildingByBuildingCd(b.getBuildingCd()).getId();
		setBuildingOriginId(buildingId);

		List<NegotiationHistoryViewModel> list = NegotiationHistoryViewModel
				.toList(new NegotiationRepository().findByBuilding(buildingId), a);
		setRelatedNegotiationHistoryViewModelList(list);
	}

	public List<NegotiationHistoryViewModel> getRelatedNegotiationHistoryViewModelList() {
		return relatedNegotiationHistoryViewModelList;
	}

	public void setRelatedNegotiationHistoryViewModelList(
			List<NegotiationHistoryViewModel> relatedNegotiationHistoryViewModelList) {
		this.relatedNegotiationHistoryViewModelList = relatedNegotiationHistoryViewModelList;
	}

	public List<BuildingPersonalDevelopRelationBuildingDetailDTO> getBuildingPersonalDevelops() {
		List<BuildingPersonalDevelopRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<BuildingPersonalDevelop> buildingPersonalDevelops = new BuildingPersonalDevelopRepository()
				.findHistoryByBuildingHistoryCd(getId().toString());
		return buildingPersonalDevelops != null && buildingPersonalDevelops.size() > 0 ? buildingPersonalDevelops
				.stream().map(x -> new BuildingPersonalDevelopRelationBuildingDetailDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	@Override
	public List<BuildingFileRelationBuildingDetailDTO> getBuildingFiles() {
		List<BuildingFileRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingOriginId() == null) {
			return dtos;
		}

		List<BuildingFile> buildingFiles = new BuildingFileRepository().findByBuildingId(getBuildingOriginId());
		return buildingFiles != null && buildingFiles.size() > 0 ? buildingFiles.stream()
				.map(x -> new BuildingFileRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	@Override
	public List<BuildingImageRelationBuildingDetailDTO> getBuildingImages() {
		List<BuildingImageRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingOriginId() == null) {
			return dtos;
		}

		List<BuildingImage> buildingImages = new BuildingImageRepository().findByBuildingId(getBuildingOriginId());
		return buildingImages != null && buildingImages.size() > 0 ? buildingImages.stream()
				.map(x -> new BuildingImageRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<BuildingTenantRelationBuildingDetailDTO> getBuildingTenants() {
		List<BuildingTenantRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<BuildingTenant> buildingTenants = new BuildingTenantRepository()
				.findHistoryByBuildingHistoryCd(getId().toString());
		return buildingTenants != null && buildingTenants.size() > 0 ? buildingTenants.stream()
				.map(x -> new BuildingTenantRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<PmCorporationRelationBuildingDetailDTO> getPmCorporations() {
		List<PmCorporationRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<PmCorporation> pmCorporations = new PmCorporationRepository()
				.findHistoryByBuildingHistoryCd(getId().toString());
		return pmCorporations != null && pmCorporations.size() > 0 ? pmCorporations.stream()
				.map(x -> new PmCorporationRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<BuildingKeymanRelationBuildingDetailDTO> getBuildingKeymans() {
		List<BuildingKeymanRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<BuildingKeyman> buildingKeymans = new BuildingKeymanRepository()
				.findHistoryByBuildingHistoryCd(getId().toString());
		return buildingKeymans != null && buildingKeymans.size() > 0 ? buildingKeymans.stream()
				.map(x -> new BuildingKeymanRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<ActivationRelationBuildingDetailDTO> getActivations() {
		List<ActivationRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<Activation> activations = new ActivationRepository().findHistoryByBuildingHistoryCd(getId().toString());
		return activations != null && activations.size() > 0
				? activations.stream().map(x -> new ActivationRelationBuildingDetailDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	public List<BuildingMeetingHistoryRelationBuildingDetailDTO> getBuildingMeetingHistorys() {
		List<BuildingMeetingHistoryRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<BuildingMeetingHistory> buildingMeetingHistorys = new BuildingMeetingHistoryRepository()
				.findHistoryByBuildingHistoryCd(getId().toString());
		return buildingMeetingHistorys != null && buildingMeetingHistorys.size() > 0 ? buildingMeetingHistorys.stream()
				.map(x -> new BuildingMeetingHistoryRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public List<BuildingSalesRelationBuildingDetailDTO> getBuildingSaless() {
		List<BuildingSalesRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<BuildingSales> buildingSaless = new BuildingSalesRepository()
				.findHistoryByBuildingHistoryCd(getId().toString());
		return buildingSaless != null && buildingSaless.size() > 0 ? buildingSaless.stream()
				.map(x -> new BuildingSalesRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}
	
	public List<ImportantInformationDetailDTO> getImportantInformations() {
		List<ImportantInformationDetailDTO> dtos = new ArrayList<>();

		if (getBuildingOriginId() == null) {
			return dtos;
		}

		List<ImportantInformation> lstImportantInformation = null;
		List<ImportantInformationBuilding> lstImportantInformationBuilding = new ImportantInformationBuildingRepository()
				.findByBuildingId(getBuildingOriginId());

		if (lstImportantInformationBuilding != null && lstImportantInformationBuilding.size() > 0) {
			lstImportantInformation = lstImportantInformationBuilding.stream()
					.map(x -> new ImportantInformationRepository().findById(x.getImportantInformationId()).get())
					.collect(Collectors.toList());
		}
		return lstImportantInformation != null && lstImportantInformation.size() > 0 ? lstImportantInformation.stream()
				.map(x -> new ImportantInformationDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public String getHistoryName() {
		if (getUpdateDatetime() != null && getUpdateAccountName() != null) {
			return getUpdateDatetime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")) + " "
					+ getUpdateAccountName();
		} else {
			return "";
		}
	}

	public Long getBuildingOriginId() {
		return buildingOriginId;
	}

	public void setBuildingOriginId(Long buildingOriginId) {
		this.buildingOriginId = buildingOriginId;
	}
}

package jp.co.world.storedevelopment.pc.controller.view.model;

import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Activation;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ActivationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ActivationRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingFileRelationBuildingDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingSalesRelationBuildingListDTO;

public class BuildingViewModel extends Building {

	private static final String EMPTY = "";
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public static List<BuildingViewModel> toList(List<Building> list) {
		return list.stream().map(b -> {
			BuildingViewModel model = new BuildingViewModel();
			copyProperties(model, b);
			return model;
		}).collect(Collectors.toList());
	}

	private static BuildingViewModel copyProperties(BuildingViewModel model, Building building) {
		try {
			PropertyUtils.copyProperties(model, building);
			File file = new File(Application.resourcePath() + building.getImagePath());
			if (file.exists()) {
				model.setImagePath(building.getImagePath());
			} else {
				model.setImagePath("img/no_img.jpg");
			}

		} catch (Exception e) {
			throw new IllegalStateException("オブジェクトのコピーに失敗しました");
		}
		return model;
	}

	public ICorporation getiCorporation() {
		if (getiCorporationId() == null) {
			return null;
		}

		Optional<ICorporation> iCorporation = new ICorporationRepository().findById(getiCorporationId());
		return iCorporation.isPresent() ? iCorporation.get() : null;
	}

	public ICorporationGroup getiCorporationGroup() {
		if (getiCorporation() != null && getiCorporation().getCorporationGpId() != null) {
			Optional<ICorporationGroup> iCorporationGroup = new ICorporationGroupRepository()
					.findById(getiCorporation().getCorporationGpId());
			return iCorporationGroup.isPresent() ? iCorporationGroup.get() : null;
		}
		return null;
	}

	public IArea getiArea() {
		if (getiAreaId() == null) {
			return null;
		}

		Optional<IArea> iArea = new IAreaRepository().findById(getiAreaId());
		return iArea.isPresent() ? iArea.get() : null;
	}

	public IBlock getiBlock() {
		if (getiBlockId() == null) {
			return null;
		}

		Optional<IBlock> iBlock = new IBlockRepository().findById(getiBlockId());
		return iBlock.isPresent() ? iBlock.get() : null;
	}

	public IPrefectures getiPrefectures() {
		if (getiPrefecturesId() == null) {
			return null;
		}

		Optional<IPrefectures> iPrefectures = new IPrefecturesRepository().findById(getiPrefecturesId());
		return iPrefectures.isPresent() ? iPrefectures.get() : null;
	}

	public ISalesChannel getiSalesChannel() {
		if (getiSalesChannelId() == null) {
			return null;
		}

		Optional<ISalesChannel> iSalesChannel = new ISalesChannelRepository().findById(getiSalesChannelId());
		return iSalesChannel.isPresent() ? iSalesChannel.get() : null;
	}

	public ISalesChannel getiSalesChannel2() {
		if (getSalesChannelCd2() == null) {
			return null;
		}

		Optional<ISalesChannel> iSalesChannel2 = new ISalesChannelRepository().findById(getSalesChannelCd2());
		return iSalesChannel2.isPresent() ? iSalesChannel2.get() : null;
	}

	public String getOpenDateString() {
		if (getOpenDate() != null) {
			return getOpenDate().format(DATE_FORMAT);
		} else {
			return EMPTY;
		}
	}

	public String getCloseDateString() {
		if (getCloseDate() != null) {
			return getCloseDate().format(DATE_FORMAT);
		} else {
			return EMPTY;
		}
	}

	public List<BuildingPersonalDevelop> getBuildingPersonalDevelops() {
		if (getBuildingCd() == null) {
			return new ArrayList<>();
		}

		List<BuildingPersonalDevelop> dtos = new BuildingPersonalDevelopRepository().findByBuildingCd(getBuildingCd());

		return dtos != null ? dtos : new ArrayList<>();
	}

	public String getStoreDeveloperNames() {
		List<String> storeDeveloperNames = new ArrayList<String>();

		for (BuildingPersonalDevelop bdp : getBuildingPersonalDevelops()) {
			if (bdp.getCategory().toLowerCase().equals("storedeveloper")) {
				Optional<Account> account = new AccountRepository().findById(bdp.getAccountId());
				if (account.isPresent()) {
					storeDeveloperNames.add(account.get().getFullName());
				}
			}
		}

		return storeDeveloperNames.stream().collect(Collectors.joining(","));
	}

	public String getBranchsSalesNames() {
		List<String> branchsSalesNames = new ArrayList<String>();

		for (BuildingPersonalDevelop bdp : getBuildingPersonalDevelops()) {
			if (bdp.getCategory().toLowerCase().equals("branchssales")) {
				Optional<Account> account = new AccountRepository().findById(bdp.getAccountId());
				if (account.isPresent()) {
					branchsSalesNames.add(account.get().getFullName());
				}
			}
		}

		return branchsSalesNames.stream().collect(Collectors.joining(","));
	}

	public String getHumanResourceLeaderNames() {
		List<String> humanResourceLeaderNames = new ArrayList<String>();

		for (BuildingPersonalDevelop bdp : getBuildingPersonalDevelops()) {
			if (bdp.getCategory().toLowerCase().equals("humanresourceleader")) {
				Optional<Account> account = new AccountRepository().findById(bdp.getAccountId());
				if (account.isPresent()) {
					humanResourceLeaderNames.add(account.get().getFullName());
				}
			}
		}

		return humanResourceLeaderNames.stream().collect(Collectors.joining(","));
	}

	public List<BuildingSalesRelationBuildingListDTO> getBuildingSaless() {
		List<BuildingSalesRelationBuildingListDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<BuildingSales> buildingSaless = new BuildingSalesRepository().findByBuildingCd(getBuildingCd());
		return buildingSaless != null && buildingSaless.size() > 0 ? buildingSaless.stream()
				.map(x -> new BuildingSalesRelationBuildingListDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public String getFormatBuildingSales() {
		String result = "";
		List<BuildingSalesRelationBuildingListDTO> buildingSales = getBuildingSaless().stream()
				.filter(x -> x.getSales().doubleValue() > 0 && x.getDivision() == 1).collect(Collectors.toList());

		if (buildingSales.size() > 0) {
			int maxYear = buildingSales.stream().map(x -> x.getFinancialMonth().getYear())
					.max(Comparator.comparing(Integer::valueOf)).get();

			double totalAmt = buildingSales.stream().filter(x -> x.getFinancialMonth().getYear() == maxYear)
					.mapToDouble(x -> x.getSales().doubleValue()).sum() / 100;

			result = String.format("%s年 %s億", Integer.toString(maxYear).substring(2),
					new DecimalFormat("###,##0.0").format(totalAmt));
		}

		return result;
	}

	private List<BuildingFileRelationBuildingDetailDTO> getBuildingFiles() {
		List<BuildingFileRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getId() == null) {
			return dtos;
		}

		List<BuildingFile> buildingFiles = new BuildingFileRepository().findByBuildingId(getId());
		return buildingFiles != null && buildingFiles.size() > 0 ? buildingFiles.stream()
				.map(x -> new BuildingFileRelationBuildingDetailDTO(x)).collect(Collectors.toList()) : dtos;
	}

	public String getExistRelatedDocument() {
		return getBuildingFiles().size() > 0 ? "有り" : "無し";
	}

	public List<ActivationRelationBuildingDetailDTO> getActivations() {
		List<ActivationRelationBuildingDetailDTO> dtos = new ArrayList<>();

		if (getBuildingCd() == null || getBuildingCd().isEmpty()) {
			return dtos;
		}

		List<Activation> activations = new ActivationRepository().findByBuildingCd(getBuildingCd());
		return activations != null && activations.size() > 0
				? activations.stream().map(x -> new ActivationRelationBuildingDetailDTO(x)).collect(Collectors.toList())
				: dtos;
	}

	public String getNearestExpectedDay() {
		Optional<LocalDate> futureExpectedDay = getActivations().stream().map(x -> x.getExpectedDay())
				.filter(x -> x.isAfter(LocalDate.now())).sorted((d1, d2) -> d1.compareTo(d2)).findFirst();

		if (futureExpectedDay.isPresent()) {
			return futureExpectedDay.get().format(DATE_FORMAT);
		}
		return "";
	}

}

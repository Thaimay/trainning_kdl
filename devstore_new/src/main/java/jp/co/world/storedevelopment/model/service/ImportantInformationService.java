package jp.co.world.storedevelopment.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationBuilding;
import jp.co.world.storedevelopment.model.ImportantInformationCorporation;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationCreateFormDTO;

public class ImportantInformationService {

	public ImportantInformation createAll(ImportantInformation in, ImportantInformationCreateFormDTO dto,
			Account account) {
		in.setContent(dto.getContent());
		in.setCreateAccount(account);
		in.setShowStartDatetime(LocalDateTime.now());
		in.setShowEndDatetime(dto.getShowEndDatetime());
		in.setDivision(dto.getDivision());
		ImportantInformation newImportantNotice = in.create();

		createPushNotificationPermit(in, dto);
		newCreateRelatedCorporation(in, dto.getCorporationIds());
		newCreateRelatedBuilding(in, dto.getBuildingIds());
		newCreateRelatedUnmanagedCorporation(in, dto.getCorporationUnmanagedNames());
		newCreateRelatedUnmanagedBuilding(in, dto.getBuildingUnmanagedNames());
		return newImportantNotice;
	}

	public ImportantInformation createAll(Account account) {
		return createAll(new ImportantInformationCreateFormDTO(), account);
	}

	public ImportantInformation createAll(ImportantInformationCreateFormDTO dto, Account account) {
		return this.createAll(new ImportantInformation(), dto, account);
	}

	public void createPushNotificationPermit(ImportantInformation in, ImportantInformationCreateFormDTO dto) {
		SendReserve model = new SendReserve();
		String corporationNames = pushNotificationCorporationNames(dto);
		String buildingNames = pushNotificationBuildingNames(dto);

		model.setEmployeeCodeListValue(new AccountRepository().findAll());
		model.setMessage("【重要情報】\n" + corporationNames + buildingNames + dto.getContent());
		model.setInitializeStatus();
		model.create();
	}

	private String pushNotificationCorporationNames(ImportantInformationCreateFormDTO dto) {
		String corporationNames = dto.getCorporationIds().stream().map(i -> {
			return new ICorporationRepository().findById(i).orElseGet(() -> {
				throw new IllegalStateException("存在しない法人です:" + i.toString());
			}).getCorporationName();
		}).collect(Collectors.joining(" "));
		String unmanagedNames = dto.getCorporationUnmanagedNames().stream().collect(Collectors.joining(" "));

		if (corporationNames.isEmpty() && unmanagedNames.isEmpty()) {
			return "";
		} else {
			return String.format("法人名:%s %s\n", corporationNames, unmanagedNames);
		}
	}

	private String pushNotificationBuildingNames(ImportantInformationCreateFormDTO dto) {
		String buildingNames = dto.getBuildingIds().stream().map(i -> {
			return new BuildingRepository().findById(i).orElseGet(() -> {
				throw new IllegalStateException("存在しない館です:" + i.toString());
			}).getName();
		}).collect(Collectors.joining(" "));
		String unmanagedNames = dto.getBuildingUnmanagedNames().stream().collect(Collectors.joining(" "));

		if (buildingNames.isEmpty()) {
			return "";
		} else {
			return String.format("館名:%s %s\n", buildingNames, unmanagedNames);
		}
	}

	public void newCreateRelatedCorporation(ImportantInformation in, List<Long> list) {
		list.stream().forEach(id -> {
			ICorporation c = new ICorporationRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException("存在しない法人です");
			});
			new ImportantInformationCorporation(in, c).create();
		});
	}

	public void newCreateRelatedBuilding(ImportantInformation in, List<Long> list) {
		list.stream().forEach(id -> {
			Building b = new BuildingRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException("存在しない館です");
			});
			new ImportantInformationBuilding(in, b).create();
		});
	}

	public void newCreateRelatedUnmanagedCorporation(ImportantInformation in, List<String> list) {
		list.stream().forEach(name -> {
			new ImportantInformationCorporation(in, name).create();
		});
	}

	public void newCreateRelatedUnmanagedBuilding(ImportantInformation in, List<String> list) {
		list.stream().forEach(name -> {
			new ImportantInformationBuilding(in, name).create();
		});
	}

	public static void newCreateRecord(ImportantInformation in) {
		List<Account> accounts = new AccountRepository().findAll();
		accounts.stream().forEach(a -> {
			// in.addAccessRecord(a);
		});
	}

}

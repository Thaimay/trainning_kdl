package jp.co.world.storedevelopment.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

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
import jp.co.world.storedevelopment.model.BuildingTradeArea;
import jp.co.world.storedevelopment.model.Keyman;
import jp.co.world.storedevelopment.model.PmCorporation;
import jp.co.world.storedevelopment.model.RelatedTask;
import jp.co.world.storedevelopment.model.Tenant;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingKeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingTenantRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;
import jp.co.world.storedevelopment.pc.controller.dto.BuildingKeymanRelationBuildingCreateDTO;
import jp.co.world.storedevelopment.pc.controller.dto.BuildingTenantRelationBuildingCreateDTO;
import jp.co.world.storedevelopment.pc.controller.form.BuildingCreateForm;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingKeymanRelationBuildingUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingTenantRelationBuildingUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingUpdateDTO;

public class BuildingService {

	private BuildingRepository buildingRepository;
	private PropertiesConfiguration config;

	public BuildingService() {
		buildingRepository = new BuildingRepository();
		config = readConfig();
	}

	public void updateAll(BuildingUpdateDTO dto, Account a) {
		Building b = dto.toModel();

		updateBuildingHistory(b);

		updateBuilding(b, a);

		updateRelatedPmCorporation(b, dto.getPmCorporationDto(), a);

		updateRelatedBuildingTenant(b, dto.getBuildingTenantDto(), a);

		updateRelatedBuildingPersonalDevelop(b, dto.getBuildingPersonalDevelopDto(), a);

		updateRelatedBuildingKeyman(b, dto.getBuildingKeymanDto(), a);

		updateRelatedActivation(b, dto.getActivationDto(), a);

		updateRelatedBuildingSales(b, dto.getBuildingSalesDto(), a);

		updateAllRelatedFile(b, dto.getBuildingFileDto(), a);

		updateAllRelatedImage(b, dto.getBuildingImageDto(), a);

		updateRelatedBuildingMeetingHistory(b, dto.getBuildingMeetingDto(), a);		
		
		b.relatedProjects().forEach(p -> {
			p.copyBuilding(b);
		});
	}

	public void createAllForPC(BuildingCreateForm dto, Account a) {
		Building b = createBuilding(dto.toModel(), a);

		createRelatedBuildingTradeArea(b, dto.getBuildingTradeAreaDto(), a);

		createRelatedPmCorporation(b, dto.getPmCorporationDto(), a);

		createRelatedBuildingTenant(b, dto.getBuildingTenantDto(), a);

		createRelatedBuildingPersonalDevelop(b, dto.getBuildingPersonalDevelopDto(), a);

		createRelatedBuildingKeyman(b, dto.getBuildingKeymanDto(), a);

		createRelatedActivation(b, dto.getActivationDto(), a);

		createRelatedBuildingSales(b, dto.getBuildingSalesDto(), a);

		createAllRelatedFile(b, dto.getBuildingFileDto(), a);

		createAllRelatedImage(b, dto.getBuildingImageDto(), a);

		createRelatedBuildingMeetingHistory(b, dto.getBuildingMeetingDto(), a);
		
		b.relatedProjects().forEach(p -> {
			p.copyBuilding(b);
		});
	}

	public void createRelatedBuildingTradeArea(Building b, BuildingTradeArea bta, Account a) {
		if (bta != null) {
			bta.setBuildingCd(b.getBuildingCd());
			bta.setCreateAccount(a);
			bta.create();
		}
	}

	public void createRelatedPmCorporation(Building b, List<PmCorporation> lstPmCorporation, Account a) {
		if (lstPmCorporation != null) {
			lstPmCorporation.forEach(pm -> createPmCorporation(pm, b, a));
		}
	}

	public void createRelatedBuildingTenant(Building b, List<BuildingTenantRelationBuildingCreateDTO> lstBuildingTenant,
			Account a) {
		if (lstBuildingTenant != null) {
			lstBuildingTenant.forEach(btDTO -> {
				Tenant t = btDTO.getTenant();
				if (t.getId() == 0) {
					createTenant(t, a);
				}

				createBuildingTenant(btDTO.toModel(), b, t, a);
			});
		}
	}

	public void createRelatedBuildingPersonalDevelop(Building b,
			List<BuildingPersonalDevelop> lstBuildingPersonalDevelop, Account a) {
		if (lstBuildingPersonalDevelop != null) {
			lstBuildingPersonalDevelop.forEach(bpd -> createBuildingPersonalDevelop(bpd, b, a));
		}
	}

	private void createRelatedBuildingKeyman(Building b,
			List<BuildingKeymanRelationBuildingCreateDTO> lstBuildingKeyman, Account a) {

		if (lstBuildingKeyman != null) {
			lstBuildingKeyman.forEach(bkmDTO -> {
				Keyman km = bkmDTO.getKeyman();
				if (km.getId() == 0) {
					createKeyman(km, a);
				} else {
					updateKeyman(km, a);
				}

				// create building keyman
				createBuildingKeyman(bkmDTO.toModel(), b, km, a);
			});
		}
	}

	public void createRelatedActivation(Building b, List<Activation> lstActivation, Account a) {
		if (lstActivation != null) {
			lstActivation.forEach(at -> createActivation(at, b, a));
		}
	}

	public void createRelatedBuildingSales(Building b, List<BuildingSales> lstBuildingSales, Account a) {
		if (lstBuildingSales != null) {
			lstBuildingSales.forEach(bs -> createBuildingSales(bs, b, a));
		}
	}

	public void createRelatedBuildingMeetingHistory(Building b, List<BuildingMeetingHistory> lstBuildingMeetingHistory,
			Account a) {
		if (lstBuildingMeetingHistory != null) {
			lstBuildingMeetingHistory.forEach(bmh -> createBuildingMeetingHistory(bmh, b, a));
		}
	}

	private Building createBuilding(Building b, Account a) {
		b.setCreateAccount(a);
		return b.create();
	}

	private void updateBuilding(Building b, Account a) {
		b.setIgnoreFields(new String[] { "originBuildingId", "buildingCd", "isBuildingGroup", "createdDatetime",
				"createdAccountCode" });
		b.setUpdateAccount(a);

		// if choose parent
		if (b.getBuildingGroupId() != null && !b.getBuildingGroupId().isEmpty()) {
			Building parent = new BuildingRepository().getBuildingByBuildingCd(b.getBuildingGroupId());

			// set building group id
			b.setBuildingGroupId(parent.getBuildingCd());
		}

		b.update();

		createRelatedTaskBuildings("output.relatedtask.building.update", b, a.getFullName());
	}

	private void updateRelatedPmCorporation(Building b, List<PmCorporation> lstPmCorporation, Account a) {

		// Delete PmCorporation by buildingCd
		new PmCorporationRepository().deleteByBuildingCd(b.getBuildingCd());

		// Create PmCorporation
		if (lstPmCorporation != null) {
			lstPmCorporation.forEach(pm -> createPmCorporation(pm, b, a));
		}
	}

	private void createPmCorporation(PmCorporation pm, Building b, Account a) {
		pm.setBuildingCd(b.getBuildingCd());
		pm.setCreateAccount(a);
		pm.create();
	}

	private void updateRelatedBuildingTenant(Building b,
			List<BuildingTenantRelationBuildingUpdateDTO> lstBuildingTenant, Account a) {

		// Delete BuildingTenant by buildingCd
		new BuildingTenantRepository().deleteByBuildingCd(b.getBuildingCd());

		if (lstBuildingTenant != null) {
			for (BuildingTenantRelationBuildingUpdateDTO btDTO : lstBuildingTenant) {
				Tenant t = btDTO.getTenant();
				if (t.getId() == 0) {
					// Create tenant
					createTenant(t, a);
				}

				// Create building tenant
				createBuildingTenant(btDTO.toModel(), b, t, a);
			}
		}
	}

	private void updateRelatedBuildingPersonalDevelop(Building b,
			List<BuildingPersonalDevelop> lstBuildingPersonalDevelop, Account a) {

		// Delete BuildingPersonalDevelop by building id
		new BuildingPersonalDevelopRepository().deleteByBuildingCd(b.getBuildingCd());

		// Create BuildingPersonalDevelop
		if (lstBuildingPersonalDevelop != null) {
			lstBuildingPersonalDevelop.forEach(bpd -> createBuildingPersonalDevelop(bpd, b, a));
		}
	}

	public void createBuildingPersonalDevelop(BuildingPersonalDevelop bpd, Building b, Account a) {
		bpd.setBuildingCd(b.getBuildingCd());
		bpd.setCreateAccount(a);
		bpd.create();
	}

	private void updateRelatedBuildingKeyman(Building b,
			List<BuildingKeymanRelationBuildingUpdateDTO> lstBuildingKeyman, Account a) {

		if (lstBuildingKeyman != null) {
			lstBuildingKeyman = lstBuildingKeyman.stream().filter(x -> !x.getIsDeleted()).collect(Collectors.toList());
		}

		// Delete BuildingKeyman by building id
		new BuildingKeymanRepository().deleteByBuildingCd(b.getBuildingCd());
		if (lstBuildingKeyman != null) {
			for (BuildingKeymanRelationBuildingUpdateDTO bkmDTO : lstBuildingKeyman) {
				Keyman km = bkmDTO.getKeyman();
				if (km.getId() == 0) {
					createKeyman(km, a);
				} else {
					updateKeyman(km, a);
				}

				// create building keyman
				createBuildingKeyman(bkmDTO.toModel(), b, km, a);
			}
		}
	}

	private void updateRelatedActivation(Building b, List<Activation> lstActivation, Account a) {
		if (lstActivation != null) {
			for (Activation at : lstActivation) {
				if (at.getId() == 0) {
					createActivation(at, b, a);
				} else if (at.getIsDeleted() == true) {
					at.delete();
				} else {
					updateActivation(at, a);
				}
			}
		}
	}

	private void updateRelatedBuildingMeetingHistory(Building b, List<BuildingMeetingHistory> lstBuildingMeetingHistory,
			Account a) {
		if (lstBuildingMeetingHistory != null) {
			for (BuildingMeetingHistory bmh : lstBuildingMeetingHistory) {
				if (bmh.getId() == 0) {
					createBuildingMeetingHistory(bmh, b, a);
				} else if (bmh.getIsDeleted() == true) {
					bmh.delete();
				} else {
					updateBuildingMeetingHistory(bmh, a);
				}
			}
		}
	}

	private void createBuildingMeetingHistory(BuildingMeetingHistory bmh, Building b, Account a) {
		bmh.setBuildingCd(b.getBuildingCd());
		bmh.setCreateAccount(a);
		bmh.create();
	}

	private void updateBuildingMeetingHistory(BuildingMeetingHistory bmh, Account a) {
		bmh.setIgnoreFields(
				new String[] { "buildingCd", "accountId", "createdDatetime", "createdAccountCode", "overview" });
		bmh.setUpdateAccount(a);
		bmh.update();
	}

	private void updateRelatedBuildingSales(Building b, List<BuildingSales> lstBuildingSales, Account a) {
		if (lstBuildingSales != null) {
			for (BuildingSales bs : lstBuildingSales) {
				if (bs.getId() == 0) {
					createBuildingSales(bs, b, a);
				} else if (bs.getIsDeleted() == true) {
					bs.delete();
				} else {
					updateBuildingSales(bs, a);
				}
			}
		}
	}

	private void createBuildingSales(BuildingSales bs, Building b, Account a) {
		bs.setBuildingCd(b.getBuildingCd());

		if (bs.getSales() != null) {
			bs.setSales(bs.getSales().multiply(new BigDecimal(1000000)));
		} else {
			bs.setSales(new BigDecimal(0));
		}

		if (bs.getMonthBasis() != null) {
			bs.setMonthBasis(bs.getMonthBasis().multiply(new BigDecimal(1000)));
		} else {
			bs.setMonthBasis(new BigDecimal(0));
		}

		if (bs.getArea() == null) {
			bs.setArea(new BigDecimal(0));
		}

		bs.setCreateAccount(a);
		bs.create();
	}

	private void updateBuildingSales(BuildingSales bs, Account a) {
		bs.setIgnoreFields(new String[] { "buildingCd", "createdDatetime", "createdAccountCode" });

		if (bs.getSales() != null) {
			bs.setSales(bs.getSales().multiply(new BigDecimal(1000000)));
		} else {
			bs.setSales(new BigDecimal(0));
		}

		if (bs.getMonthBasis() != null) {
			bs.setMonthBasis(bs.getMonthBasis().multiply(new BigDecimal(1000)));
		} else {
			bs.setMonthBasis(new BigDecimal(0));
		}

		if (bs.getArea() == null) {
			bs.setArea(new BigDecimal(0));
		}

		bs.setUpdateAccount(a);
		bs.update();
	}

	private void createActivation(Activation at, Building b, Account a) {
		at.setBuildingCd(b.getBuildingCd());
		at.setCreateAccount(a);
		at.create();
	}

	private void updateActivation(Activation at, Account a) {
		at.setIgnoreFields(new String[] { "buildingCd", "createdDatetime", "createdAccountCode", "isDeleted" });
		at.setUpdateAccount(a);
		at.update();
	}

	private void createBuildingKeyman(BuildingKeyman bkm, Building b, Keyman km, Account a) {
		bkm.setBuildingCd(b.getBuildingCd());
		bkm.setKeymanId(km.getId());
		bkm.setCreateAccount(a);
		bkm.create();
	}

	private void createKeyman(Keyman km, Account a) {
		km.setCreateAccount(a);
		km.create();
	}

	private void updateKeyman(Keyman km, Account a) {
		km.setIgnoreFields(new String[] { "businessCardId", "createdDatetime", "createdAccountCode", "isDeleted" });
		km.setUpdateAccount(a);
		km.update();
	}

	private void createBuildingTenant(BuildingTenant bt, Building b, Tenant t, Account a) {
		bt.setBuildingCd(b.getBuildingCd());
		bt.setTenantId(t.getId());
		bt.setCreateAccount(a);
		bt.create();
	}

	private void createTenant(Tenant t, Account a) {
		t.setCreateAccount(a);
		t.create();
	}

	private void updateAllRelatedImage(Building b, List<BuildingImage> images, Account a) {
		images.forEach(bi -> updateRelatedImage(b, bi, a));
	}

	private void updateRelatedImage(Building b, BuildingImage bi, Account a) {
		if (bi.getId().equals(Long.valueOf(0))) {
			createBuildingImage(b, bi, a);
		} else if (bi.getIsDeleted()) {
			bi.delete();
		} else {
			updateBuildingImage(b, bi, a);
		}
	}

	private void createBuildingImage(Building b, BuildingImage bi, Account a) {
		// create Building image
		BuildingImage biCreated = new BuildingImage(bi.getFile(), b, a);
		biCreated.setBuildingId(b.getId());
		biCreated.setComment(bi.getComment());
		biCreated.setDivision(bi.getDivision());
		biCreated.create();

		// update default image
		if (bi.getIsDefaultImage()) {
			updateImagePath(b, biCreated);
		}
	}

	private void updateBuildingImage(Building b, BuildingImage bi, Account a) {
		// update Building image
		BuildingImage biUpdate = new BuildingImageRepository().findById(bi.getId()).orElseGet(() -> {
			throw new IllegalArgumentException("存在しないファイルです");
		});

		// compare information
		if (!biUpdate.getComment().equals(bi.getComment()) || !biUpdate.getDivision().equals(bi.getDivision())) {
			biUpdate.setUpdateAccount(a);
			biUpdate.setComment(bi.getComment());
			biUpdate.setDivision(bi.getDivision());
			biUpdate.update();
		}

		// update default image
		if (bi.getIsDefaultImage()) {
			updateImagePath(b, biUpdate);
		}
	}

	private void updateImagePath(Building b, BuildingImage bi) {
		b.setImagePath(bi.urlPath() + bi.getName());
		b.update();
	}

	private void updateAllRelatedFile(Building b, List<BuildingFile> files, Account a) {
		files.forEach(bf -> updateRelatedFile(b, bf, a));
	}

	private void updateRelatedFile(Building b, BuildingFile bf, Account a) {
		if (bf.getId().equals(Long.valueOf(0))) {
			createBuildingFile(b, bf, a);

			// create related task
			createRelatedTaskFiles("output.relatedtask.file.create", b, bf.getDisplayName(), a.getFullName());
		} else if (bf.getIsDeleted()) {
			bf.delete();
		} else {
			updateBuildingFile(b, bf, a);

			// create related task
			createRelatedTaskFiles("output.relatedtask.file.update", b, bf.getDisplayName(), a.getFullName());
		}
	}

	private void createAllRelatedFile(Building b, List<BuildingFile> files, Account a) {
		files.forEach(bf -> createRelatedFile(b, bf, a));
	}

	private void createRelatedFile(Building b, BuildingFile bf, Account a) {
		createBuildingFile(b, bf, a);
	}

	private void createAllRelatedImage(Building b, List<BuildingImage> files, Account a) {
		files.forEach(bf -> createRelatedImage(b, bf, a));
	}

	private void createRelatedImage(Building b, BuildingImage bf, Account a) {
		createBuildingImage(b, bf, a);
	}

	private void createBuildingFile(Building b, BuildingFile bf, Account a) {
		BuildingFile bfCreated = new BuildingFile(bf.getFile(), b, a);
		bfCreated.setDivision(bf.getDivision());
		bfCreated.setComment(bf.getComment());
		bfCreated.setDisplayName(bf.getDisplayName());
		bfCreated.create();
	}

	private void updateBuildingFile(Building b, BuildingFile bf, Account a) {
		// get file by id
		BuildingFile bfUpdate = new BuildingFileRepository().findById(bf.getId()).orElseGet(() -> {
			throw new IllegalArgumentException("存在しないファイルです");
		});

		// compare information
		if (!bfUpdate.getDisplayName().equals(bf.getDisplayName()) || !bfUpdate.getComment().equals(bf.getComment())
				|| !bfUpdate.getDivision().equals(bf.getDivision())) {
			bfUpdate.setUpdateAccount(a);
			bfUpdate.setComment(bf.getComment());
			bfUpdate.setDivision(bf.getDivision());
			bfUpdate.setDisplayName(bf.getDisplayName());
			bfUpdate.update();
		}
	}

	private void updateBuildingHistory(Building b) {
		buildingRepository.updateBuildingHistory(b);
	}

	private void createRelatedTaskBuildings(String key, Building b, String updateAccountName) {
		new BuildingPersonalDevelopRepository().getListStoreDevAccountId(b.getBuildingCd())
				.forEach(personId -> createRelatedTaskBuilding(key, b, updateAccountName, personId));
		;
	}

	private void createRelatedTaskBuilding(String key, Building b, String updateAccountName, Long personId) {
		RelatedTask rt = new RelatedTask();
		rt.setBuildingId(b.getId());
		rt.setAccountId(personId);
		rt.setDivision("BUILDING");
		rt.setContent(getContent(key, b.getName(), updateAccountName));
		rt.create();
	}

	private void createRelatedTaskFiles(String key, Building b, String displayName, String updateAccountName) {
		buildingRepository.getListAccountIdForFile()
				.forEach(accountId -> createRelatedTaskFile(key, b, displayName, updateAccountName, accountId));
	}

	private void createRelatedTaskFile(String key, Building b, String displayName, String updateAccountName,
			Long accountId) {
		RelatedTask rt = new RelatedTask();
		rt.setAccountId(accountId);
		rt.setDivision("SALES");
		rt.setContent(getContent(key, displayName, updateAccountName));
		rt.create();
	}

	private String getContent(String key, Object... params) {
		return String.format(config.getString(key), params);
	}

	private PropertiesConfiguration readConfig() {
		try {
			String fileName = "application.properties";
			return new PropertiesConfiguration(fileName);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}
}
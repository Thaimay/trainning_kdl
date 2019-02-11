package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IYakata;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IYakataRepository;

public class IYakataBatch extends ImportBatch<IYakata> {

	private IYakataRepository repository = new IYakataRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"buildingId",
					"buildingCd",
					"name",
					"postalCode",
					"jisPrefecturesCd",
					"jisAreaCd",
					"prefecturesName",
					"cityName",
					"townNameAddress",
					"buildingName",
					"address",
					"oldAddress",
					"buildingRepresentativePhoneNumber",
					"transportationFacilitiesName",
					"nearestStation",
					"businessHours",
					"regularHoliday",
					"salesChannelId",
					"corporationId",
					"buildingRank",
					"blockId",
					"scTypeId",
					"openDate",
					"closeDate",
					"aseismaticConstructionYn",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IYakataBatch() {
		super("I_YAKATA.csv", fields);
	}

	public static void main(String[] args) {
		new IYakataBatch().execute(args);
	}

	@Override
	protected Optional<IYakata> findBy(IYakata model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IYakata createModel() {
		return new IYakata();
	}

	@Override
	protected void updateRelatedTable(IYakata model) {
		Building building = new BuildingRepository().getBuildingByBuildingCd(model.getBuildingCd());

		if (building != null) {
			building.setOriginBuildingId(model.getBuildingId());
			building.update();
		}
	}
}

package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IPlace;
import jp.co.world.storedevelopment.model.mapper.repository.IPlaceRepository;

public class IPlaceBatch extends ImportBatch<IPlace> {

	private IPlaceRepository repository = new IPlaceRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"placeId",
					"placeName",
					"originBuildingId",
					"postalCd",
					"jisPrefecturesCd",
					"cityName",
					"townNameAddress",
					"buildingName",
					"floorNum",
					"salesFloorAddress",
					"tsuboNum",
     				"startDate",
					"endDate",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IPlaceBatch() {
		super("I_PLACE.csv", fields);
	}

	public static void main(String[] args) {
		new IPlaceBatch().execute(args);
	}

	@Override
	protected Optional<IPlace> findBy(IPlace model) {
		return repository.findByImportPlaceId(model);
	}

	@Override
	protected IPlace createModel() {
		return new IPlace();
	}
}

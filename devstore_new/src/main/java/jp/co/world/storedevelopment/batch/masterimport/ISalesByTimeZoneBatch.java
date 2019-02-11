package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ISalesByTimeZone;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesByTimeZoneRepository;

public class ISalesByTimeZoneBatch extends ImportBatch<ISalesByTimeZone> {

	private ISalesByTimeZoneRepository repository = new ISalesByTimeZoneRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"shopCd",
					"dispTime",
					"mondaySalesAmount",
					"mondayPurchasingGuestsNumber",
					"mondayInRestaurantGuestsNumber",
					"tuesdaySalesAmount",
					"tuesdayPurchasingGuestsNumber",
					"tuesdayInRestaurantGuestsNumber",
					"wednesdaySalesAmount",
					"wednesdayPurchasingGuestsNumber",
					"wednesdayInRestaurantGuestsNumber",
					"thursdaySalesAmount",
					"thursdayPurchasingGuestsNumber",
					"thursdayInRestaurantGuestsNumber",
					"fridaySalesAmount",
					"fridayPurchasingGuestsNumber",
					"fridayInRestaurantGuestsNumber",
					"saturdaySalesAmount",
					"saturdayPurchasingGuestsNumber",
					"saturdayInRestaurantGuestsNumber",
					"sundaySalesAmount",
					"sundayPurchasingGuestsNumber",
					"sundayInRestaurantGuestsNumber",
					"dispOrder");

	//@formatter:on

	private ISalesByTimeZoneBatch() {
		super("I_SALES_BY_TIME_ZONE.csv", fields);
	}

	public static void main(String[] args) {
		new ISalesByTimeZoneBatch().execute(args);
	}

	@Override
	protected Optional<ISalesByTimeZone> findBy(ISalesByTimeZone model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ISalesByTimeZone createModel() {
		return new ISalesByTimeZone();
	}
}

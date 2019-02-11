package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IMdCalendar;
import jp.co.world.storedevelopment.model.mapper.repository.IMdCalendarRepository;

public class IMdCalendarBatch extends ImportBatch<IMdCalendar> {

	private IMdCalendarRepository repository = new IMdCalendarRepository();

	//@formatter:off

	private static List<String> fields =
			asList("status",
					"dataDivision1",
					"dataDivision2",
					"seqno",
					"calendarDate",
					"salesMdYear",
					"salesMdMonth",
					"salesMdWeekly",
					"backupK",
					"publicHolidayK",
					"appropriatingYearMonth",
					"closingFg",
					"registrationDate",
					"registrationTm",
					"updateDate",
					"updateTm",
					"lastCalendarDate",
					"lastSalesMdYear",
					"lastSalesMdMonth",
					"lastSalesMdWeekly",
					"lastBackupK");

	//@formatter:on

	private IMdCalendarBatch() {
		super("MDCALMST.csv", fields);
	}

	public static void main(String[] args) {
		new IMdCalendarBatch().execute(args);
	}

	@Override
	protected Optional<IMdCalendar> findBy(IMdCalendar model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IMdCalendar createModel() {
		return new IMdCalendar();
	}
}

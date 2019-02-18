package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ISalesAgency;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyRepository;

public class ISalesAgencyBatch extends ImportBatch<ISalesAgency> {

	private ISalesAgencyRepository repository = new ISalesAgencyRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"salesAgencyId",
					"salesAgencyCd",
					"salesAgencyName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private ISalesAgencyBatch() {
		super("I_SALES_AGENCY.csv", fields);
	}

	public static void main(String[] args) {
		new ISalesAgencyBatch().execute(args);
	}

	@Override
	protected Optional<ISalesAgency> findBy(ISalesAgency model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ISalesAgency createModel() {
		return new ISalesAgency();
	}
}

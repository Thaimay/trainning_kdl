package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;

public class ISalesAgencyTargetBatch extends ImportBatch<ISalesAgencyTarget> {

	private ISalesAgencyTargetRepository repository = new ISalesAgencyTargetRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"salesAgencyTargetId",
					"salesAgencyTargetName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private ISalesAgencyTargetBatch() {
		super("I_SALES_AGENCY_TARGET.csv", fields);
	}

	public static void main(String[] args) {
		new ISalesAgencyTargetBatch().execute(args);
	}

	@Override
	protected Optional<ISalesAgencyTarget> findBy(ISalesAgencyTarget model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ISalesAgencyTarget createModel() {
		return new ISalesAgencyTarget();
	}
}

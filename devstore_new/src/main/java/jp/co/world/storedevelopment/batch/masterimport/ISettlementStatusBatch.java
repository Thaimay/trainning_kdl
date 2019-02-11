package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ISettlementStatus;
import jp.co.world.storedevelopment.model.mapper.repository.ISettlementStatusRepository;

public class ISettlementStatusBatch extends ImportBatch<ISettlementStatus> {

	private ISettlementStatusRepository repository = new ISettlementStatusRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"settlementStatusId",
					"settlementStatusName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private ISettlementStatusBatch() {
		super("I_SETTLEMENT_STATUS.csv", fields);
	}

	public static void main(String[] args) {
		new ISettlementStatusBatch().execute(args);
	}

	@Override
	protected Optional<ISettlementStatus> findBy(ISettlementStatus model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ISettlementStatus createModel() {
		return new ISettlementStatus();
	}
}

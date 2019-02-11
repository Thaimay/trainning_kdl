package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IStatus;
import jp.co.world.storedevelopment.model.mapper.repository.IStatusRepository;

public class IStatusBatch extends ImportBatch<IStatus> {

	private IStatusRepository repository = new IStatusRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"statusId",
					"statusCd",
					"",  // TODO: statusName model 側の指定が Long になっているので要調整
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IStatusBatch() {
		super("I_STATUS.csv", fields);
	}

	public static void main(String[] args) {
		new IStatusBatch().execute(args);
	}

	@Override
	protected Optional<IStatus> findBy(IStatus model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IStatus createModel() {
		return new IStatus();
	}
}

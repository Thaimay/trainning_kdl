package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IRentKind;
import jp.co.world.storedevelopment.model.mapper.repository.IRentKindRepository;

public class IRentKindBatch extends ImportBatch<IRentKind> {

	private IRentKindRepository repository = new IRentKindRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"rentKindId",
					"rentKindCd",
					"rentKindDivision",
					"rentKindName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IRentKindBatch() {
		super("I_RENT_KIND.csv", fields);
	}

	public static void main(String[] args) {
		new IRentKindBatch().execute(args);
	}

	@Override
	protected Optional<IRentKind> findBy(IRentKind model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IRentKind createModel() {
		return new IRentKind();
	}
}

package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IContractKind;
import jp.co.world.storedevelopment.model.mapper.repository.IContractKindRepository;

public class IContractKindBatch extends ImportBatch<IContractKind> {

	private IContractKindRepository repository = new IContractKindRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"contractKindId",
					"contractKindCd",
					"contractKindName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IContractKindBatch() {
		super("I_CONTRACT_KIND.csv", fields);
	}

	public static void main(String[] args) {
		new IContractKindBatch().execute(args);
	}

	@Override
	protected Optional<IContractKind> findBy(IContractKind model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IContractKind createModel() {
		return new IContractKind();
	}
}

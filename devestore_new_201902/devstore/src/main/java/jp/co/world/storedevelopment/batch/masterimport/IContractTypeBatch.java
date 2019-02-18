package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IContractType;
import jp.co.world.storedevelopment.model.mapper.repository.IContractTypeRepository;

public class IContractTypeBatch extends ImportBatch<IContractType> {

	private IContractTypeRepository repository = new IContractTypeRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"contractTypeId",
					"contractTypeCd",
					"contractTypeName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IContractTypeBatch() {
		super("I_CONTRACT_TYPE.csv", fields);
	}

	public static void main(String[] args) {
		new IContractTypeBatch().execute(args);
	}

	@Override
	protected Optional<IContractType> findBy(IContractType model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IContractType createModel() {
		return new IContractType();
	}
}

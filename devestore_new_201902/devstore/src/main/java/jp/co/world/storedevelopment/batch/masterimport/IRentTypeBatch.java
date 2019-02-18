package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IRentType;
import jp.co.world.storedevelopment.model.mapper.repository.IRentTypeRepository;

public class IRentTypeBatch extends ImportBatch<IRentType> {

	private IRentTypeRepository repository = new IRentTypeRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"rentTypeId",
					"rentTypeCd",
					"rentTypeDivision",
					"rentTypeName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IRentTypeBatch() {
		super("I_RENT_TYPE.csv", fields);
	}

	public static void main(String[] args) {
		new IRentTypeBatch().execute(args);
	}

	@Override
	protected Optional<IRentType> findBy(IRentType model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IRentType createModel() {
		return new IRentType();
	}
}

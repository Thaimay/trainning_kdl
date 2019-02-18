package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IFeeTypeDivision;
import jp.co.world.storedevelopment.model.mapper.repository.IFeeTypeDivisionRepository;

public class IFeeTypeDivisionBatch extends ImportBatch<IFeeTypeDivision> {

	private IFeeTypeDivisionRepository repository = new IFeeTypeDivisionRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"feeTypeDivisionId",
					"feeTypeDivisionName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IFeeTypeDivisionBatch() {
		super("I_FEE_TYPE_DIVISION.csv", fields);
	}

	public static void main(String[] args) {
		new IFeeTypeDivisionBatch().execute(args);
	}

	@Override
	protected Optional<IFeeTypeDivision> findBy(IFeeTypeDivision model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IFeeTypeDivision createModel() {
		return new IFeeTypeDivision();
	}
}

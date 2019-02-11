package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.mapper.repository.IBlockRepository;

public class IBlockBatch extends ImportBatch<IBlock> {

	private IBlockRepository repository = new IBlockRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"blockId",
					"blockCd",
					"blockName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IBlockBatch() {
		super("I_BLOCK.csv", fields);
	}

	public static void main(String[] args) {
		new IBlockBatch().execute(args);
	}

	@Override
	protected Optional<IBlock> findBy(IBlock model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IBlock createModel() {
		return new IBlock();
	}
}

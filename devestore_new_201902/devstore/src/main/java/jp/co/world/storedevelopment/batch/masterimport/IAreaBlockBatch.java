package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IAreaBlock;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaBlockRepository;

public class IAreaBlockBatch extends ImportBatch<IAreaBlock> {

	private IAreaBlockRepository repository = new IAreaBlockRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"blockId",
					"mainBlockCd",
					"mainBlockName",
					"prefectureCd",
					"prefectureName",
					"areaId",
					"jisPrefetureCd",
					"unknown",
					"blockFlg",
					"mailBlockFlg",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IAreaBlockBatch() {
		super("I_AREABLOCKMST.csv", fields);
	}

	public static void main(String[] args) {
		new IAreaBlockBatch().execute(args);
	}

	@Override
	protected Optional<IAreaBlock> findBy(IAreaBlock model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IAreaBlock createModel() {
		return new IAreaBlock();
	}
}

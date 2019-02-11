package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IIncomeUnitLayer;
import jp.co.world.storedevelopment.model.mapper.repository.IIncomeUnitLayerRepository;

public class IIncomeUnitLayerBatch extends ImportBatch<IIncomeUnitLayer> {

	private IIncomeUnitLayerRepository repository = new IIncomeUnitLayerRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"companyId",
					"incomeUnitId",
					"levelLevel1",
					"levelLevel2",
					"levelLevel3",
					"levelLevel4",
					"levelLevel5",
					"levelLevel6",
					"levelLevelSeven",
					"levelLevelEight",
					"levelLevelNine",
					"levelLevelTen",
					"startDate",
					"endDate",
					"reportDisplayControlRank",
					"webMenuDivision",
					"backupCd1",
					"backupCd2",
					"backupCd3",
					"backupCd4",
					"backupCd5",
					"coordinationCreator",
					"coordinationCreationTime",
					"coordinationDeletingFlag",
					"coordinationApplyingDate",
					"coordinationUpdater",
					"coordinationUpdateTime");

	//@formatter:on

	private IIncomeUnitLayerBatch() {
		super("M_RIEKI_TNI_KAISO.csv", fields);
	}

	public static void main(String[] args) {
		new IIncomeUnitLayerBatch().execute(args);
	}

	@Override
	protected Optional<IIncomeUnitLayer> findBy(IIncomeUnitLayer model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IIncomeUnitLayer createModel() {
		return new IIncomeUnitLayer();
	}
}

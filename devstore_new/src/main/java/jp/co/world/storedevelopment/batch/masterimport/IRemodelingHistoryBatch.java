package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IRemodelingHistory;
import jp.co.world.storedevelopment.model.mapper.repository.IRemodelingHistoryRepository;

public class IRemodelingHistoryBatch extends ImportBatch<IRemodelingHistory> {

	private IRemodelingHistoryRepository repository = new IRemodelingHistoryRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"remodelingLogId",
					"shopId",
					"leaveStartDate",
					"remodelingDate",
					"endDate",
					"floor",
					"contractTsubo",
					"effectiveTsubo",
					"backYardTsubo",
					"contractArea",
					"effectiveArea",
					"backYardArea",
					"remodelingConvenienceId",
					"remodelingReason",
					"remodelingTarget",
					"remodelingDivisionId",
					"relocationDivisionId",
					"increaseAndDecreaseFloorDivisionId",
					"economyConditionChangeExistence",
					"lossOnDisposalOfPropertyBalanceTop",
					"lossOnDisposalOfPropertyAccountingTop",
					"cancellationMoney",
					"upholsteryExpenses",
					"buildingEquipmentExpenses",
					"burdenMoney",
					"managementManagementExpenses",
					"vmdCostEtc",
					"otherExpenses",
					"upholsteryCostCompanyBurden",
					"constructionEquipmentCostCompanyBurden",
					"burdenMoneyCompanyBurden",
					"managementManagementCostCompanyBurden",
					"vmdCostEtcCompanyBurden",
					"otherExpensesCompanyBurden",
					"costTotalCompanyBurden",
					"costSum",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IRemodelingHistoryBatch() {
		super("I_REMODELING_HISTORY.csv", fields);
	}

	public static void main(String[] args) {
		new IRemodelingHistoryBatch().execute(args);
	}

	@Override
	protected Optional<IRemodelingHistory> findBy(IRemodelingHistory model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IRemodelingHistory createModel() {
		return new IRemodelingHistory();
	}
}

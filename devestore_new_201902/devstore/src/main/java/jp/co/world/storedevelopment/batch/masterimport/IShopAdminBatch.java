package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IShopAdmin;
import jp.co.world.storedevelopment.model.mapper.repository.IShopAdminRepository;

public class IShopAdminBatch extends ImportBatch<IShopAdmin> {

	private IShopAdminRepository repository = new IShopAdminRepository();

	//@formatter:off

	private static List<String> fields =
			asList("shopAdminId",
					"shopId",
					"deposit",
					"rentBrandCd",
					"directlyOperatedHouseFlag",
					"openingSettlementNo",
					"interiorCost",
					"equipmentExpenses",
					"burdenMoney",
					"managementExpenses",
					"vmdCost",
					"openingCostSum",
					"ownInteriorCost",
					"ownEquipmentExpenses",
					"ownBurdenMoney",
					"ownManagementExpenses",
					"ownVmdCost",
					"ownOpeningCostSum",
					"closingSettlementNo",
					"closingReason",
					"closingReason1",
					"closingReason2",
					"closingReason3",
					"currentSectionStatus",
					"alternativeOpeningDivisionId",
					"realClosingFlag",
					"lossOnRetirementIncome",
					"lossOnRetirementAccounting",
					"cancellationMoney",
					"currentStatusRecoveryMoney",
					"penalty",
					"otherExpenses",
					"closingCostSum",
					"ownClosingCostSum",
					"contentsReplacement",
					"shopOtherFlag",
					"shopTypeId",
					"shopCount",
					"brandIncomeUnitId",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IShopAdminBatch() {
		super("TENPOKANRIMST.csv", fields);
	}

	public static void main(String[] args) {
		new IShopAdminBatch().execute(args);
	}

	@Override
	protected Optional<IShopAdmin> findBy(IShopAdmin model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IShopAdmin createModel() {
		return new IShopAdmin();
	}

}

package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IRentCondition;
import jp.co.world.storedevelopment.model.mapper.repository.IRentConditionRepository;

public class IRentConditionBatch extends ImportBatch<IRentCondition> {

	private IRentConditionRepository repository = new IRentConditionRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"rentConditionId",
					"shopId",
					"startDate",
					"endDate",
					"monthlyFixedRentYenMonth",
					"monthlyFixedRentYenTsubo",
					"monthlyAssuranceSalesYenMonth",
					"monthlyAssuranceSalesYenTsubo",
					"monthlyAssuranceRentYenMonth",
					"monthlyAssuranceRentYenTsubo",
					"monthlyPRentPercent",
					"monthlyBRentPercent",
					"monthlyPRate1",
					"monthlyPRate2",
					"monthlyPRate3",
					"monthlyPRate4",
					"monthlyPRate5",
					"monthlyBRate1",
					"monthlyBRate2",
					"monthlyBRate3",
					"monthlyBRate4",
					"monthlyBRate5",
					"monthlySalesMin1",
					"monthlySalesMin2",
					"monthlySalesMin3",
					"monthlySalesMin4",
					"monthlySalesMin5",
					"monthlySalesMax1",
					"monthlySalesMax2",
					"monthlySalesMax3",
					"monthlySalesMax4",
					"monthlySalesMax5",
					"annualFixedRentYenYears",
					"annualFixedRentYenTsubo",
					"annualAssuranceSalesYenYears",
					"annualAssuranceSalesYenTsubo",
					"annualAssuranceRentYenYears",
					"annualAssuranceRentYenTsubo",
					"annualPRentPercent",
					"annualBRentPercent",
					"annualPRate1",
					"annualPRate2",
					"annualPRate3",
					"annualPRate4",
					"annualPRate5",
					"annualBRate1",
					"annualBRate2",
					"annualBRate3",
					"annualBRate4",
					"annualBRate5",
					"annualSalesMin1",
					"annualSalesMin2",
					"annualSalesMin3",
					"annualSalesMin4",
					"annualSalesMin5",
					"annualSalesMax1",
					"annualSalesMax2",
					"annualSalesMax3",
					"annualSalesMax4",
					"annualSalesMax5",
					"rentTypeId",
					"rentStyleId",
					"rentAboutSpecialContractExistence",
					"specialContractConditionAboutRent",
					"commonServiceFeeExistence",
					"commonServiceFeeTsubo",
					"commonServiceFeeMonth",
					"commonServiceFeeRate",
					"salesPromotionCostExistence",
					"salesPromotionCostTsubo",
					"salesPromotionCostMonth",
					"salesPromotionCostRate",
					"parkingBurdenMoneyExistence",
					"parkingBurdenMoneyTsubo",
					"parkingBurdenMoneyMonth",
					"parkingBurdenMoneyRate",
					"otherBurdenExistence",
					"otherBurdenDetail",
					"depositSecurityDeposit",
					"reductionFromDepositExistence",
					"reductionFromDepositDetail",
					"remarks",
					"warehouseUsageFee",
					"decisionNo",
					"decisionStatusId",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IRentConditionBatch() {
		super("I_RENT_CONDITION.csv", fields);
	}

	public static void main(String[] args) {
		new IRentConditionBatch().execute(args);
	}

	@Override
	protected Optional<IRentCondition> findBy(IRentCondition model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IRentCondition createModel() {
		return new IRentCondition();
	}
}

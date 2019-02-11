package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ISalesAgencyCondition;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyConditionRepository;

public class ISalesAgencyConditionBatch extends ImportBatch<ISalesAgencyCondition> {

	private ISalesAgencyConditionRepository repository = new ISalesAgencyConditionRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"salesAgencyConditionId",
					"shopId",
					"startDate",
					"endDate",
					"monthlyFixedFeeYenMonth",
					"monthlyFixedFeeYenTsubo",
					"monthlyAssuranceSalesYenMonth",
					"monthlyAssuranceSalesYenTsubo",
					"monthlyAssuranceFeeYenMonth",
					"monthlyAssuranceFeeYenTsubo",
					"monthlyPFeePercent",
					"monthlyBFeePercent",
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
					"annualFixedFeeYenYears",
					"annualFixedFeeYenTsubo",
					"annualAssuranceSalesYenYears",
					"annualAssuranceSalesYenTsubo",
					"annualAssuranceFeeYenYears",
					"annualAssuranceFeeYenTsubo",
					"annualPFeePercent",
					"annualBFeePercent",
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
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private ISalesAgencyConditionBatch() {
		super("I_SALES_AGENCY_CONDITION.csv", fields);
	}

	public static void main(String[] args) {
		new ISalesAgencyConditionBatch().execute(args);
	}

	@Override
	protected Optional<ISalesAgencyCondition> findBy(ISalesAgencyCondition model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ISalesAgencyCondition createModel() {
		return new ISalesAgencyCondition();
	}
}

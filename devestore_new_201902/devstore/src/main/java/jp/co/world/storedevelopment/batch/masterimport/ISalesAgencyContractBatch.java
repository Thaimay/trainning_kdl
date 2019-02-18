package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ISalesAgencyContract;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyContractRepository;

public class ISalesAgencyContractBatch extends ImportBatch<ISalesAgencyContract> {

	private ISalesAgencyContractRepository repository = new ISalesAgencyContractRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"salesAgencyContractId",
					"shopId",
					"settlementNo",
					"startDate",
					"endDate",
					"salesAgencyTargetId",
					"feeTypeDivisionId",
					"remarks",
					"companySales",
					"companySalesConditions",
					"depletionPRate",
					"depletionBRate",
					"offSet",
					"advancesExpenses",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private ISalesAgencyContractBatch() {
		super("I_SALES_AGENCY_CONTRACT.csv", fields);
	}

	public static void main(String[] args) {
		new ISalesAgencyContractBatch().execute(args);
	}

	@Override
	protected Optional<ISalesAgencyContract> findBy(ISalesAgencyContract model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ISalesAgencyContract createModel() {
		return new ISalesAgencyContract();
	}
}

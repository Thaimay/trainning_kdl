package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;

public class IRentContractBatch extends ImportBatch<IRentContract> {

	private IRentContractRepository repository = new IRentContractRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"rentContractId",
					"shopId",
					"contractKindId",
					"contractTypeId",
					"postCd",
					"prefecturesName",
					"cityName",
					"townNameAddress",
					"buildingName",
					"floorNum",
					"address1",
					"address2",
					"address",
					"addressOld",
					"startDate",
					"endDate",
					"cancelDate",
					"holderCompanyId",
					"contractTargetName",
					"paymentMethodId",
					"autoUpdatingFlag",
					"notificationDeadline",
					"cancelPropriety",
					"cancelOfferDeadline",
					"cancelWritingFlag",
					"cancelPenaltyFlag",
					"cancelCondition",
					"closingDate1",
					"closingDate2",
					"closingDate3",
					"closingDate4",
					"closingDate5",
					"closingDate6",
					"collectionMonth1",
					"collectionMonth2",
					"collectionMonth3",
					"collectionMonth4",
					"collectionMonth5",
					"collectionMonth6",
					"collectionDay1",
					"collectionDay2",
					"collectionDay3",
					"collectionDay4",
					"collectionDay5",
					"collectionDay6",
					"contractPeriod",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IRentContractBatch() {
		super("I_RENT_CONTRACT.csv", fields);
	}

	public static void main(String[] args) {
		new IRentContractBatch().execute(args);
	}

	@Override
	protected Optional<IRentContract> findBy(IRentContract model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IRentContract createModel() {
		return new IRentContract();
	}
}

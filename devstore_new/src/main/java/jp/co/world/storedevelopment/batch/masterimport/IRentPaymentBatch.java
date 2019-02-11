package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IRentPayment;
import jp.co.world.storedevelopment.model.mapper.repository.IRentPaymentRepository;

public class IRentPaymentBatch extends ImportBatch<IRentPayment> {

	private IRentPaymentRepository repository = new IRentPaymentRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"rentPaymentId",
					"rentPaymentName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IRentPaymentBatch() {
		super("I_RENT_PAYMENT.csv", fields);
	}

	public static void main(String[] args) {
		new IRentPaymentBatch().execute(args);
	}

	@Override
	protected Optional<IRentPayment> findBy(IRentPayment model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IRentPayment createModel() {
		return new IRentPayment();
	}
}

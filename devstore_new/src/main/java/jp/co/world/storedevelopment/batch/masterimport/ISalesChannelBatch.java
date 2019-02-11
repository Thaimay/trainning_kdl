package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;

public class ISalesChannelBatch extends ImportBatch<ISalesChannel> {

	private ISalesChannelRepository repository = new ISalesChannelRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"salesChannelId",
					"salesChannelCd",
					"salesChannelName",
					"verificationSalesChannelCd",
					"verificationSalesChannelName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
				);

	//@formatter:on

	private ISalesChannelBatch() {
		super("I_SALES_CHANNEL.csv", fields);
	}

	public static void main(String[] args) {
		new ISalesChannelBatch().execute(args);
	}

	@Override
	protected Optional<ISalesChannel> findBy(ISalesChannel model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ISalesChannel createModel() {
		return new ISalesChannel();
	}
}

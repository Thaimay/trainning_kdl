package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationGroupRepository;

public class ICorporationGroupBatch extends ImportBatch<ICorporationGroup> {

	private ICorporationGroupRepository repository = new ICorporationGroupRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"corporationGpId",
					"corporationGpCd",
					"corporationGpName",
					"corporationGpNameKana",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private ICorporationGroupBatch() {
		super("I_COMPANY_GROUP.csv", fields);
	}

	public static void main(String[] args) {
		new ICorporationGroupBatch().execute(args);
	}

	@Override
	protected Optional<ICorporationGroup> findBy(ICorporationGroup model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ICorporationGroup createModel() {
		return new ICorporationGroup();
	}
}

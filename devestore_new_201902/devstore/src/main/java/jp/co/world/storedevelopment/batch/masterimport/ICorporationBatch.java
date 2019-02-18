package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;

public class ICorporationBatch extends ImportBatch<ICorporation> {

	private ICorporationRepository repository = new ICorporationRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"corporationId",
					"corporationCd",
					"corporationName",
					"corporationGpId",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private ICorporationBatch() {
		super("I_CORPORATIVE.csv", fields);
	}

	public static void main(String[] args) {
		new ICorporationBatch().execute(args);
	}

	@Override
	protected Optional<ICorporation> findBy(ICorporation model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ICorporation createModel() {
		return new ICorporation();
	}
}

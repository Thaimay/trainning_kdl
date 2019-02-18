package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ICompetitionShopCorporation;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionShopCorporationRepository;

public class ICompetitionShopCorporationBatch extends ImportBatch<ICompetitionShopCorporation> {

	private ICompetitionShopCorporationRepository repository = new ICompetitionShopCorporationRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"competitionShopCorporationId",
					"competitionShopCorporationCd",
					"competitionShopCorporationName",
					"registrationTime",
					"registrantCd",
					"updateTime",
					"updaterCd",
					"action",
					"pastId"
					);

	//@formatter:on

	private ICompetitionShopCorporationBatch() {
		super("I_COMPETITION_SHOP_CORPORATION.csv", fields);
	}

	public static void main(String[] args) {
		new ICompetitionShopCorporationBatch().execute(args);
	}

	@Override
	protected Optional<ICompetitionShopCorporation> findBy(ICompetitionShopCorporation model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ICompetitionShopCorporation createModel() {
		return new ICompetitionShopCorporation();
	}
}

package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ICompetition;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionRepository;

public class ICompetitionBatch extends ImportBatch<ICompetition> {

	private ICompetitionRepository repository = new ICompetitionRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"competitionShopId",
					"competitionShopCd",
					"competitionShopName",
					"positionId",
					"competitionBrId",
					"responsibleCd",
					"zoneId",
					"generationCd",
					"sellingSpaceAddress",
					"floor",
					"executingTsubo",
					"openDate",
					"status",
					"chargeShopId",
					"registrationTime",
					"registrantCd",
					"updateTime",
					"updaterCd",
					"action",
					"pastId"
					);

	//@formatter:on

	private ICompetitionBatch() {
		super("I_COMPETITION.csv", fields);
	}

	public static void main(String[] args) {
		new ICompetitionBatch().execute(args);
	}

	@Override
	protected Optional<ICompetition> findBy(ICompetition model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ICompetition createModel() {
		return new ICompetition();
	}
}

package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ICompetitionBr;
import jp.co.world.storedevelopment.model.mapper.repository.ICompetitionBrRepository;

public class ICompetitionBrBatch extends ImportBatch<ICompetitionBr> {

	private ICompetitionBrRepository repository = new ICompetitionBrRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"competitionBrId",
					"competitionBrCd",
					"competitionBrName",
					"competitionCorporateId",
					"registrationTime",
					"registrantCd",
					"updateTime",
					"updaterCd",
					"action",
					"pastId");

	//@formatter:on

	private ICompetitionBrBatch() {
		super("I_COMPETITION_BR.csv", fields);
	}

	public static void main(String[] args) {
		new ICompetitionBrBatch().execute(args);
	}

	@Override
	protected Optional<ICompetitionBr> findBy(ICompetitionBr model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ICompetitionBr createModel() {
		return new ICompetitionBr();
	}
}

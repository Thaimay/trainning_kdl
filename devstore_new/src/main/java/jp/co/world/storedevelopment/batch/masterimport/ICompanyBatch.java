package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ICompany;
import jp.co.world.storedevelopment.model.mapper.repository.ICompanyRepository;

public class ICompanyBatch extends ImportBatch<ICompany> {

	private ICompanyRepository repository = new ICompanyRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"companyCode",
					"companyKanaName",
					"companyKanjiName",
					"addressKanji1",
					"addressKanji2",
					"addressKanji3",
					"addressKana1",
					"addressKana2",
					"addressKana3",
					"postalCode",
					"phoneNumber",
					"companyDivision",
					"flagNonTargetAppropriating",
					"lockNumber",
					"registrantCode",
					"registrationTime",
					"registrationPgid",
					"registrationTransactionId",
					"coordinationCreator",
					"coordinationCreationTime",
					"coordinationDeletingFlag",
					"coordinationApplyingDate",
					"coordinationUpdater",
					"coordinationUpdateTime");

	//@formatter:on

	private ICompanyBatch() {
		super("I_COMPANY.csv", fields);
	}

	public static void main(String[] args) {
		new ICompanyBatch().execute(args);
	}

	@Override
	protected Optional<ICompany> findBy(ICompany model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ICompany createModel() {
		return new ICompany();
	}
}

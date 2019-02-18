package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class AccountBatch extends ImportBatch<Account> {

	private AccountRepository repository = new AccountRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"employeeCd",
					"secondmentFormerEmployeeCd",
					"fullName",
					"kanaFullName",
					"kanaFamilyName",
					"kanaName",
					"sexCd",
					"birthdate",
					"joiningDate",
					"retirementDate",
					"retirementFlag",
					"commonEmployeeCd",
					"mailAddress",
					"mobileAddress",
					"mobilePhoneNumber",
					"officeIssueDate",
					"officeCd",
					"officeAbbreviation",
					"affiliationIssueDate",
					"additionalPostDivision",
					"deptRank",
					"deptCd",
					"affiliationName",
					"affiliationAbbreviation",
					"shopId",
					"wspShopId",
					"jobTitleIssueDate",
					"jobTitleDivisionCd",
					"jobTitleName",
					"jobTitleNumber",
					"employeeDivisionIssueDate",
					"employeeDivisionCd",
					"employeeDivisionName",
					"jobClassIssueDate",
					"jobClassCd",
					"jobClassName",
					"responsibleJobCategoryIssueDate",
					"responsibleJobCd",
					"responsibleJobName",
					"jobCategoryCd",
					"jobCategoryName",
					"jobCategoryRemarks",
					"expensesDepartmentCd",
					"adAccount",
					"adCompany",
					"adDepartment",
					"stfConsignmentFormerTempingFormer",
					"stfContractTargetDivision",
					"stfContractType",
					"position",
					"dataType",
					"flgSb",
					"flgAd",
					"flgCard",
					"flgSbIntroduction",
					"coordinationCreator",
					"coordinationCreationTime",
					"coordinationDeletingFlag",
					"coordinationApplyingDate",
					"coordinationUpdater",
					"coordinationUpdateTime"
					);

	//@formatter:on

	private AccountBatch() {
		super("hitomst.csv", fields);
	}

	public static void main(String[] args) {
		new AccountBatch().execute(args);
	}

	@Override
	protected Optional<Account> findBy(Account model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected Account createModel() {
		return new Account();
	}
}

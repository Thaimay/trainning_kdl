package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.Department;
import jp.co.world.storedevelopment.model.mapper.repository.DepartmentRepository;

public class DepartmentBatch extends ImportBatch<Department> {

	private DepartmentRepository repository = new DepartmentRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"appropriatingYearMonth",
					"deptCd",
					"deptName",
					"deptKanaName",
					"companyCd",
					"incomeUnitCd1",
					"incomeUnitCd2",
					"incomeUnitCd3",
					"businessPlatformCd",
					"depreciationDivision",
					"financialIfDivision",
					"inputModel",
					"missionCriticalIfDivision",
					"backupCd1",
					"backupCd2",
					"backupCd3",
					"backupCd4",
					"backupCd5",
					"startDateForFinancialAccounting",
					"endDaysForFinancialAccounting",
					"startDateForManagementAccounting",
					"endDaysForManagementAccounting",
					"stepsRateBackBr",
					"storeStepsRateCostDivision",
					"marketDivision",
					"temporaryCdDivision",
					"registrationDate",
					"registrant",
					"registrationProcessingName",
					"changeDate",
					"changePerson",
					"changeProcessingName");

	//@formatter:on

	private DepartmentBatch() {
		super("TMABUMONMST.csv", fields);
	}

	public static void main(String[] args) {
		new DepartmentBatch().execute(args);
	}

	@Override
	protected Optional<Department> findBy(Department model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected Department createModel() {
		return new Department();
	}
}

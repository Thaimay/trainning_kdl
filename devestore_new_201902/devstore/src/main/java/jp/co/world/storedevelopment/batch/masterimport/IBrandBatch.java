package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IBrand;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandRepository;

public class IBrandBatch extends ImportBatch<IBrand> {

	private IBrandRepository repository = new IBrandRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"brandCd",
					"brandName",
					"incomeUnitCd",
					"gpCd",
					"brbu",
					"daiBr",
					"uriBu",
					"kanriBu",
					"baikanK",
					"systemK",
					"startDt",
					"endDt",
					"creDt",
					"creTm",
					"updDt",
					"updTm",
					"division1",
					"division2",
					"division3",
					"division4",
					"division5",
					"division6",
					"division7",
					"division8",
					"division9",
					"division10",
					"division11",
					"division12",
					"division13",
					"division14",
					"division15",
					"division16",
					"division17",
					"division18",
					"division19",
					"division20",
					"divisionUpdateDatetime1",
					"divisionUpdateDatetime2",
					"divisionUpdateDatetime3",
					"divisionUpdateDatetime4",
					"divisionUpdateDatetime5",
					"divisionUpdateDatetime6",
					"divisionUpdateDatetime7",
					"divisionUpdateDatetime8",
					"divisionUpdateDatetime9",
					"divisionUpdateDatetime10",
					"divisionUpdateDatetime11",
					"divisionUpdateDatetime12",
					"divisionUpdateDatetime13",
					"divisionUpdateDatetime14",
					"divisionUpdateDatetime15",
					"divisionUpdateDatetime16",
					"divisionUpdateDatetime17",
					"divisionUpdateDatetime18",
					"divisionUpdateDatetime19",
					"divisionUpdateDatetime20",
					"division21",
					"division22",
					"division23",
					"division24",
					"division25",
					"division26",
					"division27",
					"division28",
					"division29",
					"division30",
					"division31",
					"division32",
					"division33",
					"division34",
					"division35",
					"division36",
					"division37",
					"division38",
					"division39",
					"division40",
					"divisionUpdateDatetime21",
					"divisionUpdateDatetime22",
					"divisionUpdateDatetime23",
					"divisionUpdateDatetime24",
					"divisionUpdateDatetime25",
					"divisionUpdateDatetime26",
					"divisionUpdateDatetime27",
					"divisionUpdateDatetime28",
					"divisionUpdateDatetime29",
					"divisionUpdateDatetime30",
					"divisionUpdateDatetime31",
					"divisionUpdateDatetime32",
					"divisionUpdateDatetime33",
					"divisionUpdateDatetime34",
					"divisionUpdateDatetime35",
					"divisionUpdateDatetime36",
					"divisionUpdateDatetime37",
					"divisionUpdateDatetime38",
					"divisionUpdateDatetime39",
					"divisionUpdateDatetime40",
					"brandNameSub1",
					"brandNameSub2",
					"brandNameSub3",
					"brandNameSub4",
					"brandNameSub5",
					"divName1",
					"divName2",
					"divName3",
					"divName4",
					"divName5");

	//@formatter:on

	private IBrandBatch() {
		super("BRANDMST.csv", fields);
	}

	public static void main(String[] args) {
		new IBrandBatch().execute(args);
	}

	@Override
	protected Optional<IBrand> findBy(IBrand model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IBrand createModel() {
		return new IBrand();
	}
}

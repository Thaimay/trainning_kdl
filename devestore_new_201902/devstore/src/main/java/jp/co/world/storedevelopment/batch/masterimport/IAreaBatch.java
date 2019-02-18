package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;

public class IAreaBatch extends ImportBatch<IArea> {

	private IAreaRepository repository = new IAreaRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"areaId",
					"areaCd",
					"areaName",
					"wholesaleYn",
					"coordinationCreatedDatetime", 
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IAreaBatch() {
		super("I_AREA.csv", fields);
	}

	public static void main(String[] args) {
		new IAreaBatch().execute(args);
	}

	@Override
	protected Optional<IArea> findBy(IArea model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IArea createModel() {
		return new IArea();
	}
}

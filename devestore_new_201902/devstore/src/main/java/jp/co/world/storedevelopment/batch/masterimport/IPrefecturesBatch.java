package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.mapper.repository.IPrefecturesRepository;

public class IPrefecturesBatch extends ImportBatch<IPrefectures> {

	private IPrefecturesRepository repository = new IPrefecturesRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"jisPrefecturesId",
					"prefecturesName",
					"prefecturesNameEllipsisName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private IPrefecturesBatch() {
		super("I_TODOUFUKEN.csv", fields);
	}

	public static void main(String[] args) {
		new IPrefecturesBatch().execute(args);
	}

	@Override
	protected Optional<IPrefectures> findBy(IPrefectures model) {
		return repository.findByJisPrefecturesId(model);
	}

	@Override
	protected IPrefectures createModel() {
		return new IPrefectures();
	}
}

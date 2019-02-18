package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ISegmentDivision;
import jp.co.world.storedevelopment.model.mapper.repository.ISegmentDivisionRepository;

public class ISegmentDivisionBatch extends ImportBatch<ISegmentDivision> {

	private ISegmentDivisionRepository repository = new ISegmentDivisionRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"segmentDivisionId",
					"segmentDivisionCd",
					"segmentDivisionName",
					"generation",
					"trafficQuantity",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
				);

	//@formatter:on

	private ISegmentDivisionBatch() {
		super("I_SEGMENT_DIVISION.csv", fields);
	}

	public static void main(String[] args) {
		new ISegmentDivisionBatch().execute(args);
	}

	@Override
	protected Optional<ISegmentDivision> findBy(ISegmentDivision model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ISegmentDivision createModel() {
		return new ISegmentDivision();
	}
}

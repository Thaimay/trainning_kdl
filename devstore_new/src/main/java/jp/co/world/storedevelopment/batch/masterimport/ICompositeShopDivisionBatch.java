package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.ICompositeShopDivision;
import jp.co.world.storedevelopment.model.mapper.repository.ICompositeShopDivisionRepository;

public class ICompositeShopDivisionBatch extends ImportBatch<ICompositeShopDivision> {

	private ICompositeShopDivisionRepository repository = new ICompositeShopDivisionRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"compositeShopDivisionId",
					"compositeShopDivisionCd",
					"compositeShopDivisionName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action");

	//@formatter:on

	private ICompositeShopDivisionBatch() {
		super("I_COMPOSITE_SHOP_DIVISION.csv", fields);
	}

	public static void main(String[] args) {
		new ICompositeShopDivisionBatch().execute(args);
	}

	@Override
	protected Optional<ICompositeShopDivision> findBy(ICompositeShopDivision model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected ICompositeShopDivision createModel() {
		return new ICompositeShopDivision();
	}
}

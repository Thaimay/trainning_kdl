package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IShopType;
import jp.co.world.storedevelopment.model.mapper.repository.IShopTypeRepository;

public class IShopTypeBatch extends ImportBatch<IShopType> {

	private IShopTypeRepository repository = new IShopTypeRepository();

	//@formatter:off

	private static List<String> fields =
			asList("shopTypeId",
					"shopTypeCd",
					"shopTypeName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IShopTypeBatch() {
		super("TENPOKEITAIMST.csv", fields);
	}

	public static void main(String[] args) {
		new IShopTypeBatch().execute(args);
	}

	@Override
	protected Optional<IShopType> findBy(IShopType model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IShopType createModel() {
		return new IShopType();
	}

}

package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IShopCompany;
import jp.co.world.storedevelopment.model.mapper.repository.IShopCompanyRepository;

public class IShopCompanyBatch extends ImportBatch<IShopCompany> {

	private IShopCompanyRepository repository = new IShopCompanyRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"companyId",
					"companyCd",
					"companyName",
					"coordinationCreatedDatetime",
					"coordinationCreatedAccountCode",
					"coordinationUpdateDatetime",
					"coordinationUpdateAccountCode",
					"action"
					);

	//@formatter:on

	private IShopCompanyBatch() {
		super("I_SHOP_COMPANY.csv", fields);
	}

	public static void main(String[] args) {
		new IShopCompanyBatch().execute(args);
	}

	@Override
	protected Optional<IShopCompany> findBy(IShopCompany model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IShopCompany createModel() {
		return new IShopCompany();
	}
}

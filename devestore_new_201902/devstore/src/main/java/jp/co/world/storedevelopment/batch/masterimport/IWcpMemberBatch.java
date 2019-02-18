package jp.co.world.storedevelopment.batch.masterimport;

import java.util.List;
import java.util.Optional;

import jp.co.world.storedevelopment.model.IWcpMember;
import jp.co.world.storedevelopment.model.mapper.repository.IWcpMemberRepository;

public class IWcpMemberBatch extends ImportBatch<IWcpMember> {

	private IWcpMemberRepository repository = new IWcpMemberRepository();

	//@formatter:off

	private static List<String> fields =
			asList(
					"areaCd",
					"areaName",
					"blockCd",
					"blockName",
					"buildingCd",
					"buildingName",
					"shopCd",
					"shopName",
					"yearMonth",
					"wpcMember",
					"memberRate",
					"newsletterRegistrationNumber",
					"dispOrder");

	//@formatter:on

	private IWcpMemberBatch() {
		super("I_WCP_MEMBER.csv", fields);
	}

	public static void main(String[] args) {
		new IWcpMemberBatch().execute(args);
	}

	@Override
	protected Optional<IWcpMember> findBy(IWcpMember model) {
		return repository.findByImportCode(model);
	}

	@Override
	protected IWcpMember createModel() {
		return new IWcpMember();
	}
}

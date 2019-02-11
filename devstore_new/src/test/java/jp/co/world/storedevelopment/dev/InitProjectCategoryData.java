package jp.co.world.storedevelopment.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.ProjectCategory;

public class InitProjectCategoryData extends InitTestDataSupport {

	// 仮データの為マスターの内容が決定次第決定に合わせて内容を修正する
	private List<List<String>> masterData = new ArrayList<List<String>>() {
		{
			add(Arrays.asList("出店", "PROJECT"));
			add(Arrays.asList("退店", "PROEJCT"));
			add(Arrays.asList("改装(移)", "PROJECT"));
			add(Arrays.asList("改装(同)", "PROJECT"));
			add(Arrays.asList("継続", "PROJECT"));
			add(Arrays.asList("賃料低減(期中)", "PROJECT"));
		}
	};

	@Override
	public void init(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		masterData.forEach(m -> { createProjectCategories(m.get(0), m.get(1)); });
	}

	public Account getAccount() {
		return MAIN_ACCOUNT;
	}

	public void createProjectCategories(String name, String category) {

		createProjectCategory(name, category);
	}

	public ProjectCategory createProjectCategory(String name, String category) {
		ProjectCategory projectCategory = new ProjectCategory();
		projectCategory.setName(name);
		projectCategory.setCreateAccount(MAIN_ACCOUNT);
		return projectCategory.create();
	}

}

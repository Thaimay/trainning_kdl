package jp.co.world.storedevelopment.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;

public class InitMProjectProgressStatusData extends InitTestDataSupport {

	// 仮データの為マスターの内容が決定次第決定に合わせて内容を修正する
	private List<List<Object>> masterData = new ArrayList<List<Object>>() {
		{
			long projectCategoryId = new ProjectCategoryRepository().getHead().get().getId();
			add(Arrays.asList("COMPANY", projectCategoryId, 1, "BEFORE_CONSIDERATION", "部内検討前"));
			add(Arrays.asList("COMPANY", projectCategoryId, 2, "UNDER_CONSIDERATION", "部内検討中"));
			add(Arrays.asList("COMPANY", projectCategoryId, 3, "PROPERTY_CONSIDERATION_APPROVAL", "物件検討会承認"));
			add(Arrays.asList("COMPANY", projectCategoryId, 4, "MANAGEMENT_MEETING_APPROVAL", "経営会議、投資委員会承認"));
			add(Arrays.asList("NEGOTIATION", projectCategoryId, 1, "BEFORE_NEGOTIATION", "交渉前"));
			add(Arrays.asList("NEGOTIATION", projectCategoryId, 2, "IN_NEGOTIATION", "交渉中"));
			add(Arrays.asList("NEGOTIATION", projectCategoryId, 3, "AGREEMENT", "合意済(詳細交渉中)"));
			add(Arrays.asList("NEGOTIATION", projectCategoryId, 4, "UNSATISFIED", "不成立"));
			add(Arrays.asList("OTHER", projectCategoryId, 1, "NOT_INPUT", "未入力"));
			add(Arrays.asList("OTHER", projectCategoryId, 2, "AREA_ENTERED", "坪数入力済"));
			add(Arrays.asList("OTHER", projectCategoryId, 3, "SECTION_ENTERED", "区間入力済"));
			add(Arrays.asList("OTHER", projectCategoryId, 4, "OPENING_STORE", "出店日経過"));
		}
	};

	@Override
	public void init(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		masterData.forEach(m -> {
			createProjectProgress((String)m.get(0), (Long)m.get(1), (Integer)m.get(2), (String)m.get(3), (String)m.get(4));
		});
	}

	public Account getAccount() {
		return MAIN_ACCOUNT;
	}

	public MProjectProgressStatus createProjectProgress(String category, Long projectCategoryId, Integer priority, String code, String name) {
		MProjectProgressStatus mProjectProgressStatus = new MProjectProgressStatus();
		mProjectProgressStatus.setCategory(category);
		mProjectProgressStatus.setProjectCategoryId(projectCategoryId);
		mProjectProgressStatus.setPriority(priority);
		mProjectProgressStatus.setCode(code);
		mProjectProgressStatus.setName(name);
		return mProjectProgressStatus.create();
	}

}

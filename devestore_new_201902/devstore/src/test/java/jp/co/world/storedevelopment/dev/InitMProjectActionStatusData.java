package jp.co.world.storedevelopment.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesChannelRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectCategoryRepository;

public class InitMProjectActionStatusData extends InitTestDataSupport {

	// 仮データの為マスターの内容が決定次第決定に合わせて内容を修正する
	private List<List<Object>> masterData = new ArrayList<List<Object>>() {
		{
			long projectCategoryId = new ProjectCategoryRepository().getHead().get().getId();
			long salesChannelId = new ISalesChannelRepository().getHead().get().getId();
			add(Arrays.asList(projectCategoryId, salesChannelId, "訪問前", 1, "BEFORE_CONSIDERATION", "", "", false, ""));
			add(Arrays.asList(projectCategoryId, salesChannelId, "商談済", 2, "", "IN_NEGOTIATION", "", false, ""));
			add(Arrays.asList(projectCategoryId, salesChannelId, "区画提示", 3, "", "", "AREA_ENTERED", true, "8M"));
			add(Arrays.asList(projectCategoryId, salesChannelId, "物件上程", 4, "PROPERTY_CONSIDERATION_APPROVAL", "", "", true, "7M"));
			add(Arrays.asList(projectCategoryId, salesChannelId, "基本合意", 5, "", "AGREEMENT", "", true, "6M"));
			add(Arrays.asList(projectCategoryId, salesChannelId, "契約開始", 6, "DECIDED", "", "", true, "3M"));
			add(Arrays.asList(projectCategoryId, salesChannelId, "出店済み", 7, "", "", "OPENING_STORE", false, ""));
			add(Arrays.asList(projectCategoryId, salesChannelId, "中止", 8, "", "", "", false, ""));
		}
	};

	@Override
	public void init(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		masterData.forEach(m -> { createMProjectActionStatus(m); });
	}

	public Account getAccount() {
		return MAIN_ACCOUNT;
	}

	public MProjectActionStatus createMProjectActionStatus(List<Object> masterData) {
		MProjectActionStatus mProjectActionStatus = new MProjectActionStatus();
		mProjectActionStatus.setProjectCategoryId((Long)masterData.get(0));
		mProjectActionStatus.setSalesChannelId((Long)masterData.get(1));
		mProjectActionStatus.setName((String)masterData.get(2));
		mProjectActionStatus.setSort((Integer)masterData.get(3));
		mProjectActionStatus.setCompanyStatusCode((String)masterData.get(4));
		mProjectActionStatus.setNegotiationStatusCode((String)masterData.get(5));
		mProjectActionStatus.setOtherStatusCode((String)masterData.get(6));
		mProjectActionStatus.setScheduleUse((Boolean)masterData.get(7));
		mProjectActionStatus.setSchedule((String)masterData.get(8));
		mProjectActionStatus.setCreateAccount(MAIN_ACCOUNT);
		return mProjectActionStatus.create();
	}

}

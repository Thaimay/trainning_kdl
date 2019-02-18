package jp.co.world.storedevelopment.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.MProjectMeetingResult;

public class InitMProjectMeetingResultData extends InitTestDataSupport {

	// 仮データの為マスターの内容が決定次第決定に合わせて内容を修正する
	private List<List<Object>> masterData = new ArrayList<List<Object>>() {
		{
			add(Arrays.asList("承認", 1));
			add(Arrays.asList("条件付承認", 2));
			add(Arrays.asList("再審議", 3));
			add(Arrays.asList("その他", 4));
		}
	};

	@Override
	public void init(InitTestData main) {
		MAIN_ACCOUNT = main.MAIN_ACCOUNT;
		masterData.forEach(m -> {
			createMeetingResult((String)m.get(0), (Integer)m.get(1));
		});
	}

	public Account getAccount() {
		return MAIN_ACCOUNT;
	}

	public MProjectMeetingResult createMeetingResult(String name, Integer priority) {
		MProjectMeetingResult mProjectMeetingResult = new MProjectMeetingResult();
		mProjectMeetingResult.setName(name);
		mProjectMeetingResult.setPriority(priority);
		return mProjectMeetingResult.create();
	}

}

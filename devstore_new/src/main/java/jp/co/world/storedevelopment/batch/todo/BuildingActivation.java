package jp.co.world.storedevelopment.batch.todo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import jp.co.world.storedevelopment.batch.constant.TodoContent;
import jp.co.world.storedevelopment.batch.constant.TodoDivisionName;
import jp.co.world.storedevelopment.batch.constant.TodoList;
import jp.co.world.storedevelopment.model.Activation;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.ActivationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MTodoBatchSettingRepository;

public class BuildingActivation {

	public static void main(String[] args) {

		try {
			MTodoBatchSetting mTodoBatchSetting = new MTodoBatchSettingRepository().findByName(TodoDivisionName.BUILDING_ACTIVATION.toString());

			// 全ての館データ取得
			List<Building> buildingList = new BuildingRepository().getBuildingList();
			if(!buildingList.isEmpty()) {
				for (Building building : buildingList) {

					// 館の店舗開発者を取得
					List<BuildingPersonalDevelop> buildingPersonalDevelopList = new BuildingPersonalDevelopRepository().findByBuildingCd(building.getBuildingCd());

					//  カテゴリが店舗開発のアカウントIDを取り出す
					List<Long> accountIdList = selectStoreDeveloper(buildingPersonalDevelopList);

					// 館の活性化情報を取得
					List<Activation> activationList = new ActivationRepository().findByBuildingCd(building.getBuildingCd());
					int activationListSize = activationList.size();
					int count = 1;
					if (activationList.size() >= 0) {
						for (Activation activation : activationList) {
							if (count == activationListSize) {
								boolean createFlag = isCreateActivationInPeriod(activation.getCreatedDatetime(), mTodoBatchSetting);
								if (createFlag) {
									for (Long accountId : accountIdList) {
										createTodo(accountId, building.getId(), mTodoBatchSetting.getRemarks());
									}
								}
							}
							count++;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(String.format("TODO(BuildingActivation)登録バッチ実行中にエラーが発生しました"));
		}
	}

	private static List<Long> selectStoreDeveloper(List<BuildingPersonalDevelop> buildingPersonalDevelopList) {

		List<Long> accountIdList = new ArrayList<Long>();

		for (BuildingPersonalDevelop buildingPersonalDevelop : buildingPersonalDevelopList) {
			if (buildingPersonalDevelop.getCategory().equals("storeDeveloper")) {
				accountIdList.add(buildingPersonalDevelop.getAccountId());
			}
		}
		return accountIdList;

	}

	private static Boolean isCreateActivationInPeriod(LocalDateTime createdDateTime, MTodoBatchSetting mTodoBatchSetting) {

		long diffDays = ChronoUnit.DAYS.between(createdDateTime, LocalDateTime.now());
		System.out.println("diffDays=" + diffDays);
		if (diffDays == Long.parseLong(mTodoBatchSetting.getValue())) {
			return true;
		}

		return false;
	}

	private static void createTodo(Long accountId, Long buildingId, String remarks) {
		Todo todo = new Todo();
        todo.setAccountId(accountId);
        todo.setBuildingId(buildingId);
        todo.setDetailsDivisionId(TodoList.BUILDING_ACTIVATION.getTodoDetailsDivisionId());
        todo.setContent(TodoContent.BUILDING_ACTIVATION.toString());
        todo.setShowStartDatetime(LocalDateTime.now());
        todo.setShowEndDatetime(LocalDateTime.of(2099, 12, 31, 23, 59, 59, 999999999));
        todo.create();
	}
}

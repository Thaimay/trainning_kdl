package jp.co.world.storedevelopment.batch.todo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import jp.co.world.storedevelopment.batch.constant.TodoContent;
import jp.co.world.storedevelopment.batch.constant.TodoDivisionName;
import jp.co.world.storedevelopment.batch.constant.TodoList;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.BuildingSales;
import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MTodoBatchSettingRepository;

public class BuildingSalesNotInput {

	public static void main(String[] args) {
		try {
			MTodoBatchSetting mTodoBatchSetting = new MTodoBatchSettingRepository().findByName(TodoDivisionName.BUILDING_SALES_NOT_INPUT.toString());

			// 全ての館データ取得
			List<Building> buildingList = new BuildingRepository().getBuildingList();

			for (Building building : buildingList) {
				// 館の店舗開発者を取得
				List<BuildingPersonalDevelop> buildingPersonalDevelopList = new BuildingPersonalDevelopRepository().findByBuildingCd(building.getBuildingCd());
				//  カテゴリが店舗開発のアカウントIDを取り出す
				List<Long> accountIdList = selectStoreDeveloper(buildingPersonalDevelopList);

				// 館IDから館売上のデータを取得
				List<BuildingSales> buildingSalesList = new BuildingSalesRepository().findByBuildingCd(building.getBuildingCd());

				int buildingSalesListSize = buildingSalesList.size();
				int count = 1;
				for (BuildingSales buildingSales : buildingSalesList) {
					if(count == buildingSalesListSize) {
						boolean createFlag = isBuildingSalesNotInput(buildingSales.getCreatedDatetime(), mTodoBatchSetting);

						if(createFlag) {
							for (Long accountId : accountIdList) {
								createTodo(accountId, building.getId(), mTodoBatchSetting.getValue());
							}
						}
					}
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(String.format("TODO(BuildingSalesNotInput)登録バッチ実行中にエラーが発生しました"));
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

	/** 最後に館売上を入力した日からXX年経ったか判定 */
	private static Boolean isBuildingSalesNotInput(LocalDateTime createdDateTime, MTodoBatchSetting mTodoBatchSetting) {
		long diffDays = ChronoUnit.DAYS.between(createdDateTime, LocalDateTime.now());
		System.out.println("diffDays=" + diffDays);
		if (diffDays == Long.parseLong(mTodoBatchSetting.getValue())) {
			return true;
		}

		return false;
	}

	private static void createTodo(Long accountId, Long buildingId, String value) {
        Todo todo = new Todo();
        todo.setAccountId(accountId);
        todo.setBuildingId(buildingId);
        todo.setDetailsDivisionId(TodoList.BUILDING_SALES_NOT_INPUT.getTodoDetailsDivisionId());
        todo.setContent(TodoContent.BUILDING_SALES_NOT_INPUT.toString());
        todo.setShowStartDatetime(LocalDateTime.now());
        todo.setShowEndDatetime(LocalDateTime.of(2099, 12, 31, 23, 59, 59, 999999999));
        todo.create();
	}
}

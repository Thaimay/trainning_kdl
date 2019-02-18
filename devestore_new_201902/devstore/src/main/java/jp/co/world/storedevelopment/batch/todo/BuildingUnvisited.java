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
import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewBuilding;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MTodoBatchSettingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;

public class BuildingUnvisited {
	public static void main (String[] args) {
		try {
			MTodoBatchSetting mTodoBatchSetting = new MTodoBatchSettingRepository().findByName(TodoDivisionName.BUILDING_UNVISITED.toString());

			// 全ての館データ取得
			List<Building> buildingList = new BuildingRepository().getBuildingList();

			for (Building building : buildingList) {
				// 館の店舗開発者を取得
				List<BuildingPersonalDevelop> buildingPersonalDevelopList = new BuildingPersonalDevelopRepository().findByBuildingCd(building.getBuildingCd());
				//  カテゴリが店舗開発のアカウントIDを取り出す
				List<Long> accountIdList = selectStoreDeveloper(buildingPersonalDevelopList);

				// 商談した館データ取得
				List<NegotiationInterviewBuilding> negotiationInterviewBuildingList = new NegotiationInterviewBuildingRepository().findByBuildingId(building.getOriginBuildingId());

				if(negotiationInterviewBuildingList.isEmpty()) {
					continue;
				}

				for (NegotiationInterviewBuilding negotiationInterviewBuilding : negotiationInterviewBuildingList) {
					// 商談データ取得
					List<Negotiation> negotiationList = new NegotiationRepository().findByNegotiationId(negotiationInterviewBuilding.getNegotiationId());

					int negotiationListCount = negotiationList.size();
					int count = 1;
					// 最新の商談からXX日経っていたらアラート
					for (Negotiation negotiation : negotiationList) {
						if (negotiationListCount == count) {
							boolean createFlag = isCreatedNegotiationImplementation(negotiation.getImplementationStartDatetime(), negotiation.getImplementationEndDatetime(), mTodoBatchSetting);
							if (createFlag) {
								for (Long accountId : accountIdList) {
									createTodo(accountId, building.getId());
								}
							}
						}
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(String.format("TODO(BuildingUnvisited)登録バッチ実行中にエラーが発生しました"));
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

	private static Boolean isCreatedNegotiationImplementation(LocalDateTime implementationStartDatetime, LocalDateTime implementationEndDatetime, MTodoBatchSetting mTodoBatchSetting) {

		// 実施開始日と実施終了日が未入力の場合に出すお知らせは商談報告
		if (implementationStartDatetime == null || implementationEndDatetime == null) {
			return false;
		}

		long diffDays = ChronoUnit.DAYS.between(implementationEndDatetime, LocalDateTime.now());
		System.out.println("diffDays=" + diffDays);
		if (diffDays == Long.parseLong(mTodoBatchSetting.getValue())) {
			return true;
		}

		return false;
	}

	private static void createTodo(Long accountId, Long buildingId) {
		Todo todo = new Todo();
		todo.setAccountId(accountId);
		todo.setBuildingId(buildingId);
		todo.setDetailsDivisionId(TodoList.BUILDING_UNVISITED.getTodoDetailsDivisionId());
		todo.setContent(TodoContent.BUILDING_UNVISITED.toString());
		todo.setShowStartDatetime(LocalDateTime.now());
		todo.setShowEndDatetime(LocalDateTime.of(2099, 12, 31, 23, 59, 59, 999999999));
		todo.create();
	}
}

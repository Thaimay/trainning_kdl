package jp.co.world.storedevelopment.batch.todo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jp.co.world.storedevelopment.batch.constant.TodoContent;
import jp.co.world.storedevelopment.batch.constant.TodoDivisionName;
import jp.co.world.storedevelopment.batch.constant.TodoList;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingPersonalDevelop;
import jp.co.world.storedevelopment.model.IPlace;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.IShopSalesLatestOneYear;
import jp.co.world.storedevelopment.model.MTodoBatchSetting;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IPlaceRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IShopSalesLatestOneYearRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MTodoBatchSettingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;

public class BuildingExpectedValue {

	public static void main(String[] args) {
		try {
			MTodoBatchSetting mTodoBatchSetting = new MTodoBatchSettingRepository().findByName(TodoDivisionName.BUILDING_EXPECTED_VALUE.toString());

			// 全ての館のデータ取得
			List<Building> buildingList = new BuildingRepository().getBuildingList();

			for (Building building : buildingList) {
				// 館の店舗開発者を取得
				List<BuildingPersonalDevelop> buildingPersonalDevelopList = new BuildingPersonalDevelopRepository().findByBuildingCd(building.getBuildingCd());
				// カテゴリが店舗開発のアカウントIDを取り出す
				List<Long> accountIdList = selectStoreDeveloper(buildingPersonalDevelopList);

				// 館IDから 場所マスタを取得
				List<IPlace> iPlaceList = new IPlaceRepository().getIPlaceListByOriginBuildingId(building.getOriginBuildingId());
				if (iPlaceList == null) {
					continue;
				}

				for(IPlace iPlace : iPlaceList) {

					// 場所マスタの場所IDから店舗マスタを取得
					IShop iShop = new IShopRepository().findByPlaceId(iPlace.getPlaceId());
					if (iShop == null) {
						continue;
					}

					// 店舗マスタの店舗IDから店舗を取得
					Shop shop = new ShopRepository().findByIShopId(iShop.getShopId());

					if (shop == null || shop.getBuildingExpectedValue() == null) {
						continue;
					}

					BigDecimal buildingExpectedValue = BigDecimal.valueOf(shop.getBuildingExpectedValue());
					// 店舗IDから店舗売上直近一年のデータ取得
					IShopSalesLatestOneYear iShopSalesLatestOneYear = new IShopSalesLatestOneYearRepository().findByShopCd(iShop.getShopCd());
					if(iShopSalesLatestOneYear == null) {
						continue;
					}

					BigDecimal monthTsuboSales = iShopSalesLatestOneYear.getMonthAreaSalesAchievement();

					boolean createFlag = isBelowBuildingExpectedValue(buildingExpectedValue, monthTsuboSales, mTodoBatchSetting);

					if (createFlag) {
						for (Long accountId : accountIdList) {
							createTodo(accountId, building.getId());
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(String.format("TODO(BuildingExpectedValue)登録バッチ実行中にエラーが発生しました"));
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

	/** 館期待値売上のXX%未満か判定  */
	private static boolean isBelowBuildingExpectedValue(BigDecimal buildingExpectedValue, BigDecimal monthTsuboSales, MTodoBatchSetting mTodoBatchSetting) {

		BigDecimal conditionValue = BigDecimal.valueOf(Double.parseDouble(mTodoBatchSetting.getValue()));

		// 館期待値(月坪売上)を店舗の月坪売上で割る
		BigDecimal division = buildingExpectedValue.divide(monthTsuboSales, 2, BigDecimal.ROUND_HALF_UP);
		if (division.compareTo(conditionValue) < 0) {
			return true;
		}

		return false;
	}

	private static void createTodo(Long accountId, Long buildingId) {
		Todo todo = new Todo();
        todo.setAccountId(accountId);
        todo.setBuildingId(buildingId);
        todo.setDetailsDivisionId(TodoList.BUILDING_EXPECTED_VALUE.getTodoDetailsDivisionId());
        todo.setContent(TodoContent.BUILDING_EXPECTED_VALUE.toString());
        todo.setShowStartDatetime(LocalDateTime.now());
        todo.setShowEndDatetime(LocalDateTime.of(2099, 12, 31, 23, 59, 59, 999999999));
        todo.create();
	}
}

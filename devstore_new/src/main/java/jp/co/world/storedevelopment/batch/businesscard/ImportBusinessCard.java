package jp.co.world.storedevelopment.batch.businesscard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.IBusinessCardBatch;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardBatchRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.utils.HttpHelper;

public class ImportBusinessCard {

	private final static String URL = "https://www.advanced-diversity.jp/api/v1/cards";
	private final static String X_CLIENT_API_BY_KEY = "0da9f7b9";
	private final static String X_ACCESS_API_BY_KEY = "s9OIUmn4OI2451sprus0F3qL5";
	private final static String X_SECRET_API_BY_KEY = "vk9BfUUbzqVLDOnbGg5wwjLQAKjq1Vjk6fL91t0nJUWbx0c4d7";

	private final static String DATE_PATTERN = "yyyy-MM-dd";

	private static int updateCount;
	private static int createCount;


	public static void main(String[] args) {
		try {
			// 取得日(前日の名刺情報を取得する)
			LocalDateTime now = LocalDateTime.now().minusDays(1);
			String targetDate = now.format(DateTimeFormatter.ofPattern(DATE_PATTERN));

			Optional<IBusinessCardBatch> opt = new IBusinessCardBatchRepository().findByTargetDate(targetDate);
			if (!opt.isPresent()) {
				createIBusinessCardBatch(targetDate, false);
			}

			List<IBusinessCardBatch> iBusinessCardBatchList = new IBusinessCardBatchRepository().getFailList();
			for(IBusinessCardBatch iBusinessCardBatch : iBusinessCardBatchList) {
				updateCount = 0;
				createCount = 0;
				int requestCount = getRequestCount(iBusinessCardBatch);
				boolean isSuccess = execute(iBusinessCardBatch, requestCount);
				updateIBucinessCardBatch(iBusinessCardBatch, targetDate, isSuccess);
			}

		} catch (Exception e) {
			e.printStackTrace();
            System.err.println(String.format("名刺情報取り込み実行中にエラーが発生しました"));
		}
	}

	/** 名刺情報の件数を取得し、WebAPIのリクエスト回数をセット **/
	private static Integer getRequestCount(IBusinessCardBatch iBusinessCardBatch) {
		String parameterString = "?target_date=" + iBusinessCardBatch.getTargetDate();
		String responseString = null;
		String requestString = URL + parameterString;
		int total = 0;

		try {
			HttpHelper httpHelper = new HttpHelper(X_CLIENT_API_BY_KEY, X_ACCESS_API_BY_KEY, X_SECRET_API_BY_KEY);
			responseString = httpHelper.doGet(requestString);

			JSONObject json = new JSONObject(responseString);
			total = json.getInt("total");

			updateIBucinessCardBatch(iBusinessCardBatch, total);

			switch(total / 100) {
			case 0:
				return 1;
			case 1:
				return 2;
			case 2:
				return 3;
			case 3:
				return 4;
			case 4:
				return 5;
			case 5:
				return 6;
			case 6:
				return 7;
			case 7:
				return 8;
			case 8:
				return 9;
			case 9:
				return 10;
			case 10:
				return 11;
			}
		} catch (Exception e) {
			// 通信エラー
			e.printStackTrace();
            System.err.println(String.format("名刺情報取り込み実行中にエラーが発生しました"));
		}
		return 0;
	}

	/** 名刺情報し登録する **/
	private static Boolean execute(IBusinessCardBatch iBusinessCardBatch, int requestCount) {
		String targetDate = iBusinessCardBatch.getTargetDate();
		String offset = null;
		String parameterString = null;
		String responseString = null;
		String requestString = null;

		boolean isSuccess = false;

		try {
			for(int i = 1; i <= requestCount; i++) {
				offset = getOffset(i);
				parameterString = "?target_date=" + targetDate + "&offset=" + offset;
				requestString = URL + parameterString;
				HttpHelper httpHelper = new HttpHelper(X_CLIENT_API_BY_KEY, X_ACCESS_API_BY_KEY, X_SECRET_API_BY_KEY);
				responseString = httpHelper.doGet(requestString);

				// 名刺情報取得API is success
				if(StringUtils.isNotEmpty(responseString) && !responseString.contains("error")) {
					List<IBusinessCard> iBusinessCardList = parser(responseString);
					updateAll(iBusinessCardList, iBusinessCardBatch);

					// 名刺情報の登録に成功
					isSuccess = true;
				} else if(StringUtils.isNotEmpty(responseString)){
					System.out.println(responseString);
				}
			}

		} catch (Exception e) {
			// 通信エラー
			e.printStackTrace();
            System.err.println(String.format("名刺情報取り込み実行中にエラーが発生しました"));
		}
		return isSuccess;
	}

	private static String getOffset(int i) {
		switch(i) {
		case 1:
			return "0";
		case  2:
			return "100";
		case 3:
			return "200";
		case 4:
			return "300";
		case 5:
			return "400";
		case 6:
			return "500";
		case 7:
			return "600";
		case 8:
			return "700";
		case 9:
			return "800";
		case 10:
			return "900";
		case 11:
			return "1000";
		default:
			return "";
		}
	}

	private static void createIBusinessCardBatch(String targetDate, Boolean isSuccess) {
		IBusinessCardBatch iBusinessCardBatch = new IBusinessCardBatch();
		iBusinessCardBatch.setIsSuccess(isSuccess);
		iBusinessCardBatch.setTargetDate(targetDate);
		iBusinessCardBatch.setCreatedAccountCode("batch");
		iBusinessCardBatch.setCreatedDatetime(LocalDateTime.now());
		iBusinessCardBatch.setUpdateAccountCode("batch");
		iBusinessCardBatch.setUpdateDatetime(LocalDateTime.now());
        iBusinessCardBatch.create();
	}

	private static void updateIBucinessCardBatch(IBusinessCardBatch iBusinessCardBatch, String targetDate, Boolean isSuccess) {
		iBusinessCardBatch.setIsSuccess(isSuccess);
		iBusinessCardBatch.setRecoveredDate(targetDate);
		iBusinessCardBatch.setUpdateAccountCode("batch");
		iBusinessCardBatch.setUpdateDatetime(LocalDateTime.now());
        iBusinessCardBatch.update();
	}

	private static void updateIBucinessCardBatch(IBusinessCardBatch iBusinessCardBatch, int total) {
		iBusinessCardBatch.setTotal(total);
		iBusinessCardBatch.setUpdateAccountCode("batch");
		iBusinessCardBatch.setUpdateDatetime(LocalDateTime.now());
        iBusinessCardBatch.update();
	}

	private static void updateIBucinessCardBatch(IBusinessCardBatch iBusinessCardBatch, int createCount, int updateCount) {
		iBusinessCardBatch.setCreateCount(createCount);
		iBusinessCardBatch.setUpdateCount(updateCount);
		iBusinessCardBatch.setUpdateAccountCode("batch");
		iBusinessCardBatch.setUpdateDatetime(LocalDateTime.now());
        iBusinessCardBatch.update();
	}


	/** JSONパーサー */
	private static List<IBusinessCard> parser(String responseString) {
		List<IBusinessCard> iBusinessCardList = new ArrayList<>();

		try {
			JSONObject json = new JSONObject(responseString);
			JSONArray jsonArray = json.getJSONArray("data");

			for (int i = 0; i < jsonArray.length(); i++) {
				DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				IBusinessCard iBusinessCard = new IBusinessCard();
				iBusinessCard.setCardId(jsonArray.getJSONObject(i).getLong("card_id"));
				iBusinessCard.setCompanyName(jsonArray.getJSONObject(i).getString("company_name").replace("'", "''"));
				iBusinessCard.setCompanyNameKana(jsonArray.getJSONObject(i).getString("company_name_kana").replace("'", "''"));
				iBusinessCard.setDepartmentName(jsonArray.getJSONObject(i).getString("department_name").replace("'", "''"));
				iBusinessCard.setPositionName(jsonArray.getJSONObject(i).getString("position_name").replace("'", "''"));
				iBusinessCard.setName(jsonArray.getJSONObject(i).getString("name"));
				iBusinessCard.setLastName(jsonArray.getJSONObject(i).getString("last_name"));
				iBusinessCard.setFirstName(jsonArray.getJSONObject(i).getString("first_name"));
				iBusinessCard.setNameKana(jsonArray.getJSONObject(i).getString("name_kana"));
				iBusinessCard.setFirstNameKana(jsonArray.getJSONObject(i).getString("first_name_kana"));
				iBusinessCard.setLastNameKana(jsonArray.getJSONObject(i).getString("last_name_kana"));
				iBusinessCard.setEmail(jsonArray.getJSONObject(i).getString("email"));
				iBusinessCard.setZipCode(jsonArray.getJSONObject(i).getString("zip_code"));
				iBusinessCard.setAddressFull(jsonArray.getJSONObject(i).getString("address_full"));
				iBusinessCard.setAddress1(jsonArray.getJSONObject(i).getString("address_1"));
				iBusinessCard.setAddress2(jsonArray.getJSONObject(i).getString("address_2"));
				iBusinessCard.setAddress3(jsonArray.getJSONObject(i).getString("address_3"));
				iBusinessCard.setAddress4(jsonArray.getJSONObject(i).getString("address_4"));
				iBusinessCard.setTelNumberCompany(jsonArray.getJSONObject(i).getString("tel_number_company"));
				iBusinessCard.setTelNumberDepartment(jsonArray.getJSONObject(i).getString("tel_number_department"));
				iBusinessCard.setFaxNumber(jsonArray.getJSONObject(i).getString("fax_number"));
				iBusinessCard.setMobileNumber(jsonArray.getJSONObject(i).getString("mobile_number"));
				iBusinessCard.setCompanyUrl(jsonArray.getJSONObject(i).getString("company_url"));
				iBusinessCard.setCardOwnerId(jsonArray.getJSONObject(i).getInt("card_owner_id"));
				iBusinessCard.setCardOwnerName(jsonArray.getJSONObject(i).getString("card_owner_name"));
				iBusinessCard.setCardOwnerFirstName(jsonArray.getJSONObject(i).getString("card_owner_first_name"));
				iBusinessCard.setCardOwnerLastName(jsonArray.getJSONObject(i).getString("card_owner_last_name"));
				iBusinessCard.setCardOwnerCompanyName(jsonArray.getJSONObject(i).getString("card_owner_company_name").replace("'", "''"));
				iBusinessCard.setContactDate(LocalDateTime.parse(jsonArray.getJSONObject(i).getString("contact_date"), f));
				iBusinessCard.setCardIndexNo(jsonArray.getJSONObject(i).getString("card_index_no"));
				iBusinessCard.setIsPrivate(jsonArray.getJSONObject(i).getInt("is_private"));
				iBusinessCard.setComplianceFlg(jsonArray.getJSONObject(i).getInt("compliance_flg"));
				iBusinessCard.setCreatedAccountCode("batch");
				iBusinessCard.setUpdateAccountCode("batch");

				iBusinessCardList.add(iBusinessCard);
			}

		} catch (Exception e) {
			e.printStackTrace();
            System.err.println(String.format("名刺情報取り込み実行中にエラーが発生しました"));
		}
		return iBusinessCardList;
	}

	private static void updateAll(List<IBusinessCard> iBusinessCardList, IBusinessCardBatch iBusinessCardBatch) {

		for (IBusinessCard iBusinessCard : iBusinessCardList) {
			Optional<IBusinessCard> opt = new IBusinessCardRepository().findByEmail(iBusinessCard.getEmail());
			if (opt.isPresent()) {
				if(opt.get().getContactDate().compareTo(iBusinessCard.getContactDate()) < 0) {
					saveBusinessCard(opt, iBusinessCard);
					updateCount++;
				} else {
					continue;
				}
			} else {
				iBusinessCard.create();
				createCount++;
			}
		}

		updateIBucinessCardBatch(iBusinessCardBatch, createCount, updateCount);
	}

	private static void saveBusinessCard(Optional<IBusinessCard> opt, IBusinessCard iBusinessCard) {
		opt.get().setCardId(iBusinessCard.getCardId());
		opt.get().setCompanyName(iBusinessCard.getCompanyName());
		opt.get().setCompanyNameKana(iBusinessCard.getCompanyNameKana());
		opt.get().setDepartmentName(iBusinessCard.getDepartmentName());
		opt.get().setPositionName(iBusinessCard.getPositionName());
		opt.get().setName(iBusinessCard.getName());
		opt.get().setLastName(iBusinessCard.getLastName());
		opt.get().setFirstName(iBusinessCard.getFirstName());
		opt.get().setNameKana(iBusinessCard.getNameKana());
		opt.get().setFirstNameKana(iBusinessCard.getFirstNameKana());
		opt.get().setLastNameKana(iBusinessCard.getLastNameKana());
		opt.get().setEmail(iBusinessCard.getEmail());
		opt.get().setZipCode(iBusinessCard.getZipCode());
		opt.get().setAddressFull(iBusinessCard.getAddressFull());
		opt.get().setAddress1(iBusinessCard.getAddress1());
		opt.get().setAddress2(iBusinessCard.getAddress2());
		opt.get().setAddress3(iBusinessCard.getAddress3());
		opt.get().setAddress4(iBusinessCard.getAddress4());
		opt.get().setTelNumberCompany(iBusinessCard.getTelNumberCompany());
		opt.get().setTelNumberDepartment(iBusinessCard.getTelNumberDepartment());
		opt.get().setFaxNumber(iBusinessCard.getFaxNumber());
		opt.get().setMobileNumber(iBusinessCard.getMobileNumber());
		opt.get().setCompanyUrl(iBusinessCard.getCompanyUrl());
		opt.get().setCardOwnerId(iBusinessCard.getCardOwnerId());
		opt.get().setCardOwnerName(iBusinessCard.getCardOwnerName());
		opt.get().setCardOwnerFirstName(iBusinessCard.getCardOwnerFirstName());
		opt.get().setCardOwnerLastName(iBusinessCard.getCardOwnerLastName());
		opt.get().setCardOwnerCompanyName(iBusinessCard.getCardOwnerCompanyName());
		opt.get().setContactDate(iBusinessCard.getContactDate());
		opt.get().setCardIndexNo(iBusinessCard.getCardIndexNo());
		opt.get().setIsPrivate(iBusinessCard.getIsPrivate());
		opt.get().setComplianceFlg(iBusinessCard.getComplianceFlg());
		opt.get().setUpdateAccountCode("batch");
		opt.get().setUpdateDatetime(LocalDateTime.now());
		opt.get().update();
	}
}

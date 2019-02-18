package jp.co.world.storedevelopment.model.service;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.AccountData;

public class AccountDataService {

	public void updateNegotiationCondition(Account a, String condition) {
		update(a, AccountData.NEGOTIATION_CONDITION, condition);
	}

	public void updateProjectConditionSP(Account a, String condition) {
		update(a, AccountData.PROJECT_CONDITION_SP, condition);
	}
	
	public void updateProjectConditionPC(Account a, String condition) {
		update(a, AccountData.PROJECT_CONDITION_PC, condition);
	}

	private void update(Account a, String type, String condition) {
		if (a.hasDataUser()) {
			AccountData currAccountData = a.fetchAccountData().get();

			switch (type) {
			case AccountData.NEGOTIATION_CONDITION:
				currAccountData.setNegotiationCondition(condition);
				break;
			case AccountData.PROJECT_CONDITION_SP:
				currAccountData.setProjectConditionSP(condition);
				break;
			case AccountData.PROJECT_CONDITION_PC:
				currAccountData.setProjectConditionPC(condition);
				break;
			default:
				break;
			}

			updateDataUser(a, currAccountData);
		} else {
			AccountData newAccountData = new AccountData();

			switch (type) {
			case AccountData.NEGOTIATION_CONDITION:
				newAccountData.setNegotiationCondition(condition);
				break;
			case AccountData.PROJECT_CONDITION_SP:
				newAccountData.setProjectConditionSP(condition);
				break;
			case AccountData.PROJECT_CONDITION_PC:
				newAccountData.setProjectConditionPC(condition);
				break;
			default:
				break;
			}

			createDataUser(a, newAccountData);
		}
	}

	private void updateDataUser(Account a, AccountData data) {
		data.setIgnoreFields(new String[] { "dataUserObj", "dataUserObjField", "negotiationCondition",
				"projectConditionSP", "projectConditionPC", "createdAccountCode", "createdDatetime" });
		data.setAccountId(a.getId());
		data.setUpdateAccount(a);
		data.update();
	}

	private void createDataUser(Account a, AccountData data) {
		data.setAccountId(a.getId());
		data.setCreateAccount(a);
		data.create();
	}
}
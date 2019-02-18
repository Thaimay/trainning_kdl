package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.AccountDataModelMapper;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class AccountData extends ActiveModel<AccountData> {

	public static final String NEGOTIATION_CONDITION = "NEGOTIATION_CONDITION";
	public static final String PROJECT_CONDITION_SP = "PROJECT_CONDITION_SP";
	public static final String PROJECT_CONDITION_PC = "PROJECT_CONDITION_PC";

	private Long accountId;
	private String dataUser;

	private String[] ignoreFields = new String[] { "dataUserObj", "dataUserObjField", "negotiationCondition",
			"projectConditionSP", "projectConditionPC" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	public AccountData() {

	}

	public AccountData(Long accountId, String data) {
		this.accountId = accountId;
		this.dataUser = data;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getDataUser() {
		return dataUser;
	}

	public void setDataUser(String dataUser) {
		this.dataUser = dataUser;
	}

	@Override
	protected ModelMapper<AccountData> modelMapper(SqlSession session) {
		return session.getMapper(AccountDataModelMapper.class);
	}

	private JSONObject getDataUserObj() {
		if (dataUser == null || dataUser.isEmpty()) {
			return new JSONObject();
		}

		try {
			return new JSONObject(dataUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new JSONObject();
		}
	}

	private void setDataUserObjField(String name, String value) {
		try {
			dataUser = getDataUserObj().putOpt(name, value).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private String getDataUserObjField(String name) {
		return getDataUserObj().optString(name);
	}

	public String getNegotiationCondition() {
		return getDataUserObjField(NEGOTIATION_CONDITION);
	}

	public void setNegotiationCondition(String condition) {
		setDataUserObjField(NEGOTIATION_CONDITION, condition);
	}

	public String getProjectConditionSP() {
		return getDataUserObjField(PROJECT_CONDITION_SP);
	}

	public void setProjectConditionSP(String condition) {
		setDataUserObjField(PROJECT_CONDITION_SP, condition);
	}

	public String getProjectConditionPC() {
		return getDataUserObjField(PROJECT_CONDITION_PC);
	}

	public void setProjectConditionPC(String condition) {
		setDataUserObjField(PROJECT_CONDITION_PC, condition);
	}
}

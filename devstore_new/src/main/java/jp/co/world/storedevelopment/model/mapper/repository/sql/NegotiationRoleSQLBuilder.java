package jp.co.world.storedevelopment.model.mapper.repository.sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.tierline.mybatis.activemodel.SQL;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Role;
import jp.co.world.storedevelopment.model.SubAccount;

public class NegotiationRoleSQLBuilder extends SQL {
	private Role role;
	private Account account;
	private String areaCd = "";
	private String companyCd = "";
	private String incomeCd = "";

	public String getIncomeCd() {
		return incomeCd;
	}

	public void setIncomeCd(String incomeCd) {
		this.incomeCd = incomeCd;
	}

	public NegotiationRoleSQLBuilder(Account account) {
		setRole(account.getRole());
		setAccount(account);
		setVariables(account.getSubAccount());
		find();
	}

	private void setVariables(Optional<SubAccount> sub) {
		if (sub.isPresent()) {
			SubAccount subAccount = sub.get();
			setAreaCd(subAccount.getAreaCode());
			setCompanyCd(subAccount.getCompanyCode());
			setIncomeCd(subAccount.getIncomeUnitCode());
		}
	}

	private void find() {
		Account account = getAccount();
		String sql = String.format("(N.created_account_code = '%s' OR N.update_account_code = '%s' OR %s OR %s OR %s OR %s OR %s OR %s OR %s) AND %s",
				account.getEmployeCode(), account.getEmployeCode(), levelOneQuery(), levelOneLimitQuery(), levelTwoCloseQuery(), levelTwoCloseLimitQuery(), levelThreeQuery(),
				negotiationEditableQuery(), levelLimitQuery(), draftQuery());
		WHERE(sql);
	}

	private String draftQuery() {
		Account account = getAccount();
		String q = "(N.draft = false OR (N.draft = true AND (N.created_account_code = '%s' OR N.update_account_code = '%s')) OR %s)";
		return format(q, account.getEmployeCode(), account.getEmployeCode(), negotiationEditableQuery());
	}
	
	private String levelLimitQuery() {
		String level = "NEGOTIATION_LIMIT";
		List<String> permissions = getRole().getPermissions();
		String query = String.format("(N.release_level IN %s AND '%s' IN %s", IN_String(Arrays.asList("NEGOTIATION_LV1", "NEGOTIATION_LV2")), level, IN_String(permissions));
		
		if (getRole().isOnlyTargetArea()) {
			query = targetAreaQuery(query);
		}

		if (getRole().isOnlyMyCompany()) {
			query = targetCompanyQuery(query);
		}
		
		if (getRole().isOnlyIncomeUnit()) {
			query = targetIncomeQuery(query);
		}
		
		return query + ")";
	}

	private String levelOneQuery() {
		String level = "NEGOTIATION_LV1";
		List<String> permissions = getRole().getPermissions();
		String query = String.format("('%s' IN %s AND N.release_level = '%s'", level, IN_String(permissions), level);

		return query + ")";
	}

	private String levelOneLimitQuery() {
		String level = "NEGOTIATION_LV1_LIMITED";
		List<String> permissions = getRole().getPermissions();
		String query = String.format("('%s' IN %s AND N.release_level = '%s'", level, IN_String(permissions), "NEGOTIATION_LV1");

		if (getRole().isOnlyTargetArea()) {
			query = targetAreaQuery(query);
		}

		if (getRole().isOnlyMyCompany()) {
			query = targetCompanyQuery(query);
		}
		
		if (getRole().isOnlyIncomeUnit()) {
			query = targetIncomeQuery(query);
		}

		return query + ")";
	}

	private String levelTwoCloseQuery() {
		String level = "NEGOTIATION_CLOSE_LV2";
		List<String> permissions = getRole().getPermissions();
		String query = String.format("('NEGOTIATION_LV2' IN %s OR ('%s' IN %s", IN_String(permissions), level, IN_String(permissions));

		List<String> list = getAccount().companyCodeList();
		
		if (list.size() > 0) {
			String companyQuery = format("(N.release_level = 'NEGOTIATION_LV2' AND N.created_company_cd IN %s)", IN_String(list));
			query += String.format(" AND %s", companyQuery);			
		}

		return query + "))";
	}

	private String levelTwoCloseLimitQuery() {
		String level = "NEGOTIATION_CLOSE_LV2_LIMITED";
		List<String> permissions = getRole().getPermissions();
		String query = String.format("('NEGOTIATION_LV2' IN %s OR ('%s' IN %s", IN_String(permissions), level, IN_String(permissions));

		if (getRole().isOnlyTargetArea()) {
			query = targetAreaQuery(query);
		}

		if (getRole().isOnlyMyCompany()) {
			query = targetCompanyQuery(query);
		}
		
		if (getRole().isOnlyIncomeUnit()) {
			query = targetIncomeQuery(query);
		}
		
		List<String> list = getAccount().companyCodeList();

		if (list.size() > 0) {
			String companyQuery = format("(N.release_level = 'NEGOTIATION_LV2' AND N.created_company_cd IN %s)", IN_String(list));
			query += String.format(" AND %s", companyQuery);			
		}

		return query + "))";
	}

	private String levelThreeQuery() {
		String q = "(N.release_level = 'NEGOTIATION_LV3' AND %d IN "
				+ "(SELECT account_id FROM NEGOTIATION_NOTICE_ACCOUNT NNA WHERE N.id = NNA.negotiation_id))";
		return format(q, getAccount().getId());
	}

	private String targetAreaQuery(String query) {
		String subQuery = "";
		List<String> codes = getAccount().areaCodes();

		if (codes.size() > 0) {
			String sub = "(N.id IN (SELECT negotiation_id FROM project_negotiation WHERE project_id IN ("
			+ "SELECT id FROM project WHERE building_id IN ("
			+ "SELECT id FROM building WHERE i_area_id IN ("
			+ "SELECT id FROM i_area WHERE area_cd IN %s))))"
			+ " OR N.id IN (SELECT negotiation_id FROM negotiation_interview_building WHERE building_id IN ("
			+ "SELECT id FROM building WHERE i_area_id IN ("
			+ "SELECT id FROM i_area WHERE area_cd IN %s))))";
			
			subQuery += format(sub, IN_String(codes), IN_String(codes));
		}
		
		if (getAreaCds().size() > 0) {
			String q = "N.id IN (SELECT negotiation_id FROM project_negotiation WHERE project_id IN"
					+ "(SELECT id FROM project WHERE building_id IN"
					+ "(SELECT id FROM building WHERE i_area_id IN"
					+ "(SELECT id FROM i_area WHERE area_cd IN %s))))"
					+ "OR N.id IN (SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_BUILDING WHERE building_id IN"
					+ "(SELECT id FROM building WHERE i_area_id IN"
					+ "(SELECT id FROM i_area WHERE area_cd IN %s)))";

			if (subQuery.isEmpty()) {
				subQuery += format(q, IN_String(getAreaCds()), IN_String(getAreaCds()));								
			} else {
				subQuery += " OR " + format(q, IN_String(getAreaCds()), IN_String(getAreaCds()));				
			}
		}

		if (subQuery.isEmpty()) {
			subQuery += "(0 IN (SELECT count(building_id) FROM NEGOTIATION_INTERVIEW_BUILDING NIB WHERE N.id = NIB.negotiation_id)"
					+ "AND 0 IN (SELECT count(id) from project WHERE id IN (SELECT project_id FROM project_negotiation WHERE negotiation_id = N.id) AND i_area_id is not null))";			
		} else {
			subQuery += " OR (0 IN (SELECT count(building_id) FROM NEGOTIATION_INTERVIEW_BUILDING NIB WHERE N.id = NIB.negotiation_id)"
					+ "AND 0 IN (SELECT count(id) from project WHERE id IN (SELECT project_id FROM project_negotiation WHERE negotiation_id = N.id) AND i_area_id is not null))";						
		}

		return query + String.format("AND (%s)", subQuery);			
	}
	
	private String targetIncomeQuery(String query) {
		String subQuery = "";

		String sub = "N.id IN (SELECT negotiation_id FROM negotiation_interview_brand WHERE brand_id IN" 
		+ "(SELECT id FROM i_brand_income_unit WHERE income_unit_id IN"
		+ "(SELECT income_unit_id FROM i_income_unit WHERE income_unit_cd IN"
		+ "(SELECT income_unit_cd_1 FROM i_dept WHERE dept_cd IN"
		+ "(SELECT expenses_department_cd FROM i_account WHERE id = %s)))))"
		+ " OR N.id IN (SELECT negotiation_id FROM project_negotiation WHERE project_id IN"
		+ "(SELECT id FROM project WHERE brand_id IN" 
		+ "(SELECT id FROM i_brand_income_unit WHERE income_unit_id IN"
		+ "(SELECT income_unit_id FROM i_income_unit WHERE income_unit_cd IN"
		+ "(SELECT income_unit_cd_1 FROM i_dept WHERE dept_cd IN"
		+ "(SELECT expenses_department_cd FROM i_account WHERE id = %s))))))";

		subQuery += format(sub, getAccount().getId(), getAccount().getId());
		
		if (incomeCds().size() > 0) {
			String q = "OR N.id IN (SELECT negotiation_id FROM negotiation_interview_brand WHERE brand_id IN" 
			+ "(SELECT id FROM i_brand_income_unit WHERE income_unit_id IN"
			+ "(SELECT income_unit_id FROM i_income_unit WHERE income_unit_cd IN %s)))"
			+ "OR N.id IN (SELECT negotiation_id FROM project_negotiation WHERE project_id IN"
			+ "(SELECT id FROM project WHERE brand_id IN"
			+ "(SELECT id FROM i_brand_income_unit WHERE income_unit_id IN"
			+ "(SELECT income_unit_id FROM i_income_unit WHERE income_unit_cd IN %s))))";

			subQuery += format(q, IN_String(incomeCds()), IN_String(incomeCds()));
		}
		
		return query + String.format("AND (%s)", subQuery);
	}
		
	private String targetCompanyQuery(String query) {
		String subQuery = "";
		List<Long> list = getAccount().commonAccount().stream().map(a -> {
			return a.getId();
		}).collect(Collectors.toList());

		String q = "( N.id IN (SELECT negotiation_id FROM negotiation_interview_brand WHERE brand_id IN" 
		+ "(SELECT id FROM i_brand_income_unit WHERE income_unit_id IN"
		+ "(SELECT income_unit_id FROM i_income_unit WHERE company_id IN"
		+ "(SELECT company_id FROM i_shop_company WHERE company_cd IN"
		+ "(SELECT company_cd FROM i_dept WHERE dept_cd IN"
		+ "(SELECT expenses_department_cd FROM i_account WHERE id IN%s))))))"
		+ " OR N.id IN (SELECT negotiation_id FROM project_negotiation WHERE project_id IN"
		+ "(SELECT id FROM project WHERE brand_id IN" 
		+ "(SELECT id FROM i_brand_income_unit WHERE income_unit_id IN"
		+ "(SELECT income_unit_id FROM i_income_unit WHERE company_id IN"
		+ "(SELECT company_id FROM i_shop_company WHERE company_cd IN"
		+ "(SELECT company_cd FROM i_dept WHERE dept_cd IN"
		+ "(SELECT expenses_department_cd FROM i_account WHERE id IN%s))))))))";

		subQuery = format(q, IN(list), IN(list));
		
		if (companyCds().size() > 0) {
			String sub = "OR N.id IN (SELECT negotiation_id FROM negotiation_interview_brand WHERE brand_id IN" 
			+ "(SELECT id FROM i_brand_income_unit WHERE income_unit_id IN"
			+ "(SELECT income_unit_id FROM i_income_unit WHERE company_id IN"
			+ "(SELECT company_id FROM i_shop_company where company_id IN %s))))"
			+ "OR N.id IN (SELECT negotiation_id FROM project_negotiation WHERE project_id IN"
			+ "(SELECT id FROM project WHERE brand_id IN"
			+ "(SELECT id FROM i_brand_income_unit WHERE income_unit_id IN"
			+ "(SELECT income_unit_id FROM i_income_unit WHERE company_id IN"
			+ "(SELECT company_id FROM i_shop_company where company_id IN %s)))))";

			subQuery += format(sub, IN_String(companyCds()), IN_String(companyCds()));
		}

		return query + String.format(" AND (%s)", subQuery);
	}

	private String negotiationEditableQuery() {
		String level = "NEGOTIATION_EDITABLE";
		List<String> permissions = getRole().getPermissions();
		String q = "('%s' IN %s)";

		return format(q, level, IN_String(permissions));
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<String> getAreaCds() {
		if (StringUtils.isBlank(areaCd)) {
			return Arrays.asList();
		}
		return Arrays.asList(StringUtils.split(areaCd, ",")).stream().map(String::trim).collect(Collectors.toList());
	}

	public List<String> companyCds() {
		if (StringUtils.isBlank(companyCd)) {
			return Arrays.asList();
		}
		return Arrays.asList(StringUtils.split(companyCd, ",")).stream().map(String::trim).collect(Collectors.toList());		
	}
	
	public List<String> incomeCds() {
		if (StringUtils.isBlank(incomeCd)) {
			return Arrays.asList();
		}
		return Arrays.asList(StringUtils.split(incomeCd, ",")).stream().map(String::trim).collect(Collectors.toList());
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	
}

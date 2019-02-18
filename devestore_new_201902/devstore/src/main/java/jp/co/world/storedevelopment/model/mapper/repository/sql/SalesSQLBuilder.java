package jp.co.world.storedevelopment.model.mapper.repository.sql;

import com.tierline.mybatis.activemodel.SQL;

import jp.co.world.storedevelopment.pc.controller.form.SalesDeleteForm;
import jp.co.world.storedevelopment.pc.controller.form.SalesFindForm;

public class SalesSQLBuilder {
	public String build(final SalesFindForm form) {
		String sql = new SQL() {
			{
				SELECT_DISTINCT("F.*");
				FROM("FILE F ");
				WHERE("negotiation_id IS NULL");
				WHERE("building_id IS NULL");
				WHERE("shop_id IS NULL");

				if (isNotBlank(form.getFileName())) {
					WHERE("F.display_name LIKE '%'|| #{fileName} ||'%'");
				}
				
				if (!form.getDivision().equals("ALL")) {
					if (isNotBlank(form.getDivision())) {
						WHERE("F.division = #{division}");
					}
				}

				if (isNotBlank(form.getUpdateDate())) {
					WHERE("to_char(F.update_datetime, 'YYYY-MM-DD') = #{updateDate}");
				}

				if (isNotBlank(form.getUpdateAccountName())) {
					WHERE("F.update_account_code IN (SELECT employee_cd FROM I_ACCOUNT A WHERE A.full_name LIKE '%'|| #{updateAccountName} ||'%')");
				}

				ORDER_BY("update_datetime DESC");
			}
		}.toString();
		return sql;
	}

	public String buildDelete(final SalesDeleteForm form) {
		String sql = new SQL() {
			{
				DELETE_FROM("FILE");
				WHERE(format("id IN %s", IN(form.getIds())));
			}
		}.toString();
		return sql;
	}
}

package jp.co.world.storedevelopment.model.mapper.repository.sql;

import java.util.Map;

import com.tierline.mybatis.activemodel.SQL;

public class BusinessCardSQLBuilder {
	public String build(final Map params) {
		String sql = new SQL() {
			{
				SELECT_DISTINCT("IBC.*");
				FROM("I_BUSINESS_CARD IBC");

				WHERE("(IBC.name LIKE #{text} || '%' OR IBC.name LIKE #{hankaku} || '%')");
				OR();
				WHERE("(IBC.company_name LIKE #{text} || '%' OR IBC.company_name LIKE #{hankaku} || '%')");
				OR();
				WHERE("(IBC.position_name LIKE #{text} || '%' OR IBC.position_name LIKE #{hankaku} || '%')");
			}
		}.toString();

		return sql + " LIMIT #{limit}";
	}
}

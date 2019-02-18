package jp.co.world.storedevelopment.model.mapper.repository.sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tierline.mybatis.activemodel.SQL;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationFindForm;

public class ImportantInfomationFindSQLBuilder {
	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public String build(final ImportantInformationFindForm dto) {
		String sql = new SQL() {
			{

				SELECT_DISTINCT("I.*");
				FROM("IMPORTANT_INFORMATION I ");
				WHERE("I.is_deleted = false");
				WHERE(format("I.show_end_datetime >= '%s'", DATE_FORMAT.format(LocalDateTime.now())));

				if (isNotBlank(dto.getText())) {
					AND();
					WHERE("(I.content like '%' || #{text} || '%'" + "OR I.content like '%' || #{hankakuText} || '%'"
							+ "OR I.id IN("
							+ "SELECT IIC.important_information_id FROM important_information_corporation IIC WHERE IIC.is_deleted = false AND IIC.corporation_id IN("
							+ "SELECT C.id FROM i_corporation C WHERE C.is_deleted = false AND (C.corporation_name LIKE '%' || #{text} || '%'"
							+ "OR C.corporation_name LIKE '%' || #{hankakuText} || '%')))" + "OR I.id IN("
							+ "SELECT IIB.important_information_id FROM important_information_building IIB WHERE IIB.is_deleted = false AND IIB.building_id IN("
							+ "SELECT B.id FROM building B WHERE B.is_deleted = false AND (B.name LIKE '%' || #{hankakuText} || '%'"
							+ "OR B.name LIKE '%' || #{hankakuText} || '%')))"
							+ "OR I.update_account_code IN( SELECT IA.employee_cd FROM i_account IA WHERE IA.is_deleted = false AND (IA.full_name LIKE '%' || #{hankakuText} || '%'"
							+ "OR IA.full_name LIKE '%' || #{hankakuText} || '%')))");
				}

				ORDER_BY("I.created_datetime DESC");
			}
		}.toString();

		// TODO: ページネーションをするか検討中のため一時対応
		return sql + " LIMIT #{pagingSize} OFFSET #{pagingSize} * #{numberOfPage}";
	}

	public String buildByNegotiation(final Negotiation n) {
		String sql = new SQL() {
			{
				SELECT_DISTINCT("I.*");
				FROM("IMPORTANT_INFORMATION I");
				WHERE("I.is_deleted = false");

				WHERE("I.id IN (SELECT IIN.important_information_id FROM IMPORTANT_INFORMATION_NEGOTIATION IIN WHERE IIN.negotiation_id = #{id})");
			}
		}.toString();

		return sql;
	}
}

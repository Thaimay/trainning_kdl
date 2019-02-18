package jp.co.world.storedevelopment.model.mapper.repository.sql;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.tierline.mybatis.activemodel.SQL;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.pc.controller.form.NegotiationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationFindFormDTO;

public class NegotiationFindSQLBuilder {
	public String build(final NegotiationFindFormDTO dto) {
		String sql = new NegotiationRoleSQLBuilder(dto.getAccount()) {
			{
				Long accountId = dto.getAccountId();
				String accountCode = dto.getAccountCode();
				SELECT_DISTINCT(
						"N.*, coalesce(N.implementation_end_datetime, N.schedule_end_datetime) as negotiation_datetime,"
						+ "(select max(x.update_datetime) from (select update_datetime from negotiation_comment_update_history where negotiation_id = N.id union all select N.update_datetime) x ) as update_date");
				FROM("NEGOTIATION N ");
				WHERE("N.is_deleted = false");

				if (isTrue(dto.getIsOpened())) {
					WHERE("N.id NOT IN(SELECT negotiation_id FROM NEGOTIATION_OPEN_ACCOUNT noa"
							+ " WHERE noa.account_id = #{accountId})");
				}
				if (isTrue(dto.getIsScheduled())) {
					WHERE("N.implementation_start_datetime is NULL");
					WHERE("N.implementation_end_datetime is NULL");
				}
				if (isTrue(dto.getIsImplemented())) {
					WHERE("N.implementation_start_datetime is NOT NULL");
					WHERE("N.implementation_end_datetime is NOT NULL");
				}
				if (isTrue(dto.getIsOwn())) {
					WHERE("N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_ACCOUNT IA"
							+ " WHERE IA.account_id = #{accountId})");
				}
				if (isTrue(dto.getIsReadLater())) {
					WHERE("N.id IN(SELECT negotiation_id FROM NEGOTIATION_READ_LATER_ACCOUNT AR "
							+ " WHERE AR.account_id = #{accountId})");
				}
				if (isNotBlank(dto.getTitle())) {
					WHERE("N.title like '%' || #{title} || '%'");
				}
				if (isNotBlank(dto.getFillText())) {
					WHERE("(N.title like '%' || #{fillText} || '%' or N.content like '%' || #{fillText} || '%' "
							+ " or N.next_action_content like '%' || #{fillText} || '%')");
				}
				if (dto.getAccountIds().size() > 0) {
					WHERE(format(
							"N.id IN (SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_ACCOUNT WHERE account_id IN %s)",
							IN(dto.getAccountIds())));
				}
				if (dto.getCorporationIds().size() > 0) {
					List<String> ids = dto.getCorporationIds().stream().map(i -> i.toString())
							.collect(Collectors.toList());
					WHERE(format(
							"N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_CORPORATION WHERE corporation_id IN %s)",
							IN_String(ids)));
				}

				if (dto.getBuildingIds().size() > 0) {
					WHERE(format(
							"N.id IN (SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_BUILDING WHERE building_id IN %s)",
							IN(dto.getBuildingIds())));
				}

				if (dto.getShopIds().size() > 0) {
					String q = "N.id IN (SELECT negotiation_id " + 
							"FROM NEGOTIATION_INTERVIEW_BUILDING NIB " + 
							"WHERE NIB.building_id IN " + 
							"(" + 
							"	SELECT b.id " + 
							"	FROM building b " + 
							"	where b.origin_building_id in " + 
							"	(" + 
							"		select ip.origin_building_id" + 
							"		from i_place ip" + 
							"		where ip.id in " + 
							"		(" + 
							"			select ish.place_id" + 
							"			from i_shop ish" + 
							"			where ish.id in %s" + 
							"		)" + 
							"	)" + 
							"))";
					WHERE(format(q, IN(dto.getShopIds())));
				}

				if (dto.getInterviewIds().size() > 0) {
					List<String> ids = dto.getInterviewIds().stream().map(i -> i.toString())
							.collect(Collectors.toList());
					WHERE(format(
							"N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_BUSINESS_CARD WHERE business_card_id IN %s)",
							IN_String(ids)));
				}
				if (isNotBlank(dto.getImplementationStartDate())) {
					WHERE("(to_char(N.Implementation_start_datetime, 'YYYY-MM-DD') >= #{implementationStartDate} OR to_char(N.schedule_start_datetime, 'YYYY-MM-DD') >= #{implementationStartDate})");
				}
				if (isNotBlank(dto.getImplementationEndDate())) {
					WHERE("(to_char(N.Implementation_end_datetime, 'YYYY-MM-DD') <= #{implementationEndDate} OR to_char(N.schedule_end_datetime, 'YYYY-MM-DD') <= #{implementationEndDate})");
				}
				if (dto.getDivision().size() > 0) {
					WHERE(format("N.division IN %s", IN_String(dto.getDivision())));
				}
				if (dto.getUpdateAccountIds().size() > 0) {
					List<String> ids = dto.getUpdateAccountIds().stream().map(i -> i.toString())
							.collect(Collectors.toList());
					WHERE(format("N.update_account_code IN(select employee_cd from i_account WHERE id IN %s)",
							IN_String(ids)));
				}
				// Order by
				switch (dto.getOrderByOption().toUpperCase()) {
				case "UPDATE_DATETIME_ASC":
					ORDER_BY("update_date asc nulls last");
					break;
				case "UPDATE_DATETIME_DESC":
					ORDER_BY("update_date desc nulls last");
					break;
				case "NEGOTIATION_DATETIME_ASC":
					ORDER_BY("negotiation_datetime asc nulls last");
					break;
				case "NEGOTIATION_DATETIME_DESC":
					ORDER_BY("negotiation_datetime desc nulls last");
					break;
				default:
					break;
				}
			}

		}.toString();
		return sql + " LIMIT #{pagingSize} OFFSET #{pagingSize} * #{numberOfPage}";
	}

	public String buildForPC(final NegotiationFindForm dto) {
		String sql = new findSQL(dto) {
			{
				SELECT_DISTINCT(
						"N.*, coalesce(N.implementation_end_datetime, N.schedule_end_datetime) as negotiation_datetime,"
								+ "(select max(x.update_datetime) from (select update_datetime from negotiation_comment_update_history where negotiation_id = N.id union all select N.update_datetime) x ) as update_date");
				FROM("NEGOTIATION N ");
				WHERE("N.is_deleted = false");

				// Order by
				switch (dto.getOrderByOption().toUpperCase()) {
				case "UPDATE_DATETIME_ASC":
					ORDER_BY("update_date asc nulls last");
					break;
				case "UPDATE_DATETIME_DESC":
					ORDER_BY("update_date desc nulls last");
					break;
				case "NEGOTIATION_DATETIME_ASC":
					ORDER_BY("negotiation_datetime asc nulls last");
					break;
				case "NEGOTIATION_DATETIME_DESC":
					ORDER_BY("negotiation_datetime desc nulls last");
					break;
				default:
					break;
				}
			}
		}.toString();

		return sql + " LIMIT #{pagingSize} OFFSET #{pagingSize} * #{numberOfPage}";
	}

	public String countForPC(final NegotiationFindForm dto) {
		String sql = new findSQL(dto) {
			{
				SELECT_DISTINCT("COUNT(*)");
				FROM("NEGOTIATION N ");
				WHERE("N.is_deleted = false");
			}
		}.toString();
		return sql;
	}

	private class findSQL extends NegotiationRoleSQLBuilder {
		private Long accountId;
		private NegotiationFindForm dto;

		public void setAccountId(Long accountId) {
			this.accountId = accountId;
		}

		public void setDto(NegotiationFindForm dto) {
			this.dto = dto;
		}

		public findSQL(NegotiationFindForm dto) {
			super(dto.getAccount());
			setDto(dto);
			setAccountId(dto.getAccountId());

			find();
		}

		private void find() {
			if (isTrue(dto.getIsOpened())) {
				WHERE("N.id NOT IN(SELECT negotiation_id FROM NEGOTIATION_OPEN_ACCOUNT noa"
						+ " WHERE noa.account_id = #{accountId})");
			}
			if (isTrue(dto.getIsScheduled())) {
				WHERE("N.implementation_start_datetime is NULL");
				WHERE("N.implementation_end_datetime is NULL");
			}
			if (isTrue(dto.getIsImplemented())) {
				WHERE("N.implementation_start_datetime is NOT NULL");
				WHERE("N.implementation_end_datetime is NOT NULL");
			}
			if (isTrue(dto.getIsOwn())) {
				WHERE("N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_ACCOUNT IA"
						+ " WHERE IA.account_id = #{accountId})");
			}
			if (isTrue(dto.getIsReadLater())) {
				WHERE("N.id IN(SELECT negotiation_id FROM NEGOTIATION_READ_LATER_ACCOUNT AR "
						+ " WHERE AR.account_id = #{accountId})");
			}
			if (isNotBlank(dto.getFillText())) {
				WHERE("(N.title like '%' || #{fillText} || '%' or N.content like '%' || #{fillText} || '%')");
			}
			if (dto.getAccountIds().size() > 0) {
				List<String> ids = dto.getAccountIds().stream().map(i -> i.toString()).collect(Collectors.toList());
				WHERE(format("N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_ACCOUNT WHERE account_id IN %s)",
						IN_String(ids)));
			}
			if (dto.getCorporationIds().size() > 0) {
				List<String> ids = dto.getCorporationIds().stream().map(i -> i.toString()).collect(Collectors.toList());
				WHERE(format(
						"N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_CORPORATION WHERE corporation_id IN %s)",
						IN_String(ids)));
			}
			if (dto.getInterviewIds().size() > 0) {
				List<String> ids = dto.getInterviewIds().stream().map(i -> i.toString()).collect(Collectors.toList());
				WHERE(format(
						"N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_BUSINESS_CARD WHERE business_card_id IN %s)",
						IN_String(ids)));
			}
			if (dto.getBuildingIds().size() > 0) {
				WHERE(format(
						"N.id IN(SELECT negotiation_id FROM NEGOTIATION_INTERVIEW_BUILDING WHERE building_id IN %s)",
						IN(dto.getBuildingIds())));
			}
			if (dto.getShopIds().size() > 0) {
				String q = "N.id IN (SELECT negotiation_id " + 
						"FROM NEGOTIATION_INTERVIEW_BUILDING NIB " + 
						"WHERE NIB.building_id IN " + 
						"(" + 
						"	SELECT b.id " + 
						"	FROM building b " + 
						"	where b.origin_building_id in " + 
						"	(" + 
						"		select ip.origin_building_id" + 
						"		from i_place ip" + 
						"		where ip.id in " + 
						"		(" + 
						"			select ish.place_id" + 
						"			from i_shop ish" + 
						"			where ish.id in %s" + 
						"		)" + 
						"	)" + 
						"))";
				WHERE(format(q, IN(dto.getShopIds())));
			}
			if (isNotBlank(dto.getImplementationStartDate())) {
				WHERE("(to_char(N.Implementation_start_datetime, 'YYYY-MM-DD') >= #{implementationStartDate} OR to_char(N.schedule_start_datetime, 'YYYY-MM-DD') >= #{implementationStartDate})");
			}
			if (isNotBlank(dto.getImplementationEndDate())) {
				WHERE("(to_char(N.Implementation_end_datetime, 'YYYY-MM-DD') <= #{implementationEndDate} OR to_char(N.schedule_end_datetime, 'YYYY-MM-DD') <= #{implementationEndDate})");
			}
			if (dto.getTitle() != null) {
				WHERE("N.title like '%' || #{title} || '%'");
			}
			if (dto.getDivision().size() > 0) {
				WHERE(format("N.division IN %s", IN_String(dto.getDivision())));
			}
			if (dto.getUpdateAccountIds().size() > 0) {
				List<String> ids = dto.getUpdateAccountIds().stream().map(i -> i.toString())
						.collect(Collectors.toList());
				WHERE(format("N.update_account_code IN(select employee_cd from i_account WHERE id IN %s)",
						IN_String(ids)));
			}
		}
	}

	public String buildByDate(final LocalDate date, final Account account) {
		String sql = new SQL() {
			{
				SELECT_DISTINCT("N.*");
				FROM("NEGOTIATION N ");
				WHERE("N.is_deleted = false");
				WHERE("(N.created_account_code = #{arg1.employeCode} OR N.id IN "
						+ "(select NIA.negotiation_id from NEGOTIATION_INTERVIEW_ACCOUNT NIA WHERE NIA.account_id = #{arg1.id}))");
				WHERE("to_char(schedule_start_datetime, 'yyyy-mm-dd') = #{arg0} and implementation_start_datetime IS NULL");
			}
		}.toString();
		return sql;
	}
}

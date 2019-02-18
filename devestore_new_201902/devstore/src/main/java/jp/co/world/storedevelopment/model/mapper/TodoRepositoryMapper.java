package jp.co.world.storedevelopment.model.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.tierline.mybatis.activemodel.RepositoryMapper;
import com.tierline.mybatis.activemodel.SQL;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.Todo;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindByDeadlineFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TodoFindFormDTO;

public interface TodoRepositoryMapper extends RepositoryMapper<Todo> {
	@SelectProvider(type = TodoSQLBuilder.class, method = "buildFindByDetail")
	List<Todo> find(TodoFindFormDTO dto);
	
	@SelectProvider(type = TodoSQLBuilder.class, method = "buildFindBy")
	List<Todo> findByBetweenDate(TodoFindByDeadlineFormDTO dto);

	@Delete("delete from todo where is_deleted = false AND project_id = ${id} AND details_division_id IN (12, 13, 14)")
	int deleteProjectTaskBy(@Param("id") Long id);

	@Delete("delete from todo where negotiation_id = ${negotiation.id}")
	int deleteByNegotiation(@Param("negotiation") Negotiation n);

	@Delete("delete from todo where details_division_id = ${detailsDivisionId}")
	int deleteByDetailsDivisionId(@Param("detailsDivisionId") Integer detailsDivisionId);

	@Delete("delete from todo where project_id = ${id} AND details_division_id = ${number}")
	int deleteBy(@Param("id") Long id, @Param("number") Integer number);
	
	class TodoSQLBuilder {
		private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		public String buildFindByDetail(final TodoFindFormDTO dto) {
			String sql = new SQL() {
				{
					Long accountId = dto.getAccount().getId();
					SELECT_DISTINCT("TD.*");
					FROM("TODO TD");
					WHERE("TD.is_deleted = false");

					if (dto.getAccount() != null) {
						String q = "TD.account_id = %d";
						WHERE(format(q, accountId));
					}
					if (dto.getFillText() != "") {
						WHERE("(TD.content like '%' || #{fillText} || '%') or (TD.content_sub like '%' || #{fillText} || '%')");
					}
					if (dto.getShowStartDatetime() != null) {
						WHERE(format("(TD.show_start_datetime >= '%s')", DATE_FORMAT.format(LocalDateTime.now())));
					}
					if (dto.getShowEndDatetime() != null) {
						WHERE(format("(TD.show_end_datetime >= '%s')", DATE_FORMAT.format(LocalDateTime.now())));
					}
					ORDER_BY("TD.update_datetime desc");
				}

			}.toString();
			return sql + String.format(" LIMIT %d OFFSET %d", Application.PAGING_SIZE,
					dto.getNumberOfPage() * Application.PAGING_SIZE);
		}

		public String buildFindBy(final TodoFindByDeadlineFormDTO dto) {
			String sql = new SQL() {
				{
					Long accountId = dto.getAccount().getId();
					SELECT_DISTINCT("*");
					FROM("Todo");
					WHERE("is_deleted = false");
					if (dto.getAccount() != null) {
						String q = "account_id = %d";
						WHERE(format(q, accountId));
					}
					WHERE(format("(show_end_datetime >= '%s')", DATE_FORMAT.format(LocalDateTime.now())));

					ORDER_BY("update_datetime desc");
				}
			}.toString();
			return sql;
		}
	}

}

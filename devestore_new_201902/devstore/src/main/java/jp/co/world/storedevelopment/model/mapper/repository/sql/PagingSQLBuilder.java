package jp.co.world.storedevelopment.model.mapper.repository.sql;

import jp.co.world.storedevelopment.sp.controller.dto.FindFormDTO;

public class PagingSQLBuilder {

	public static String build(FindFormDTO dto) {
		return " LIMIT #{pagingSize} OFFSET #{pagingSize} * #{numberOfPage}";
	}

}

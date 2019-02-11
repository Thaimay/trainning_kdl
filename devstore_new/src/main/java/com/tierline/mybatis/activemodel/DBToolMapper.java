package com.tierline.mybatis.activemodel;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DBToolMapper {

	@Select("${sql}")
	List<Map<String, Object>> query(@Param("sql") String sql);

	@Select("show columns from ${table}")
	String descForH2(@Param("table") String table);

	// 17-12-28 this sql for h2db. you must change this sql,if you use diffrent db.
	@Select("SELECT LASTVAL();")
	Long lastInsertID();

}

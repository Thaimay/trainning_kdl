package com.saisystem.kdldb.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.saisystem.kdldb.Model.ProjectInfo;

@Mapper
public interface MapperProject {
		
	
	final String SELECT_ALL_PROJECTID_BUILDING_ISHOP = "SELECT project.id, building.name, i_shop.shop_name_zenkaku" + 
			" FROM project" + 
			" JOIN building ON project.building_id = building.id" + 
			" JOIN i_shop ON project.i_shop_id = i_shop.id" + 
			" ORDER BY project.id";
	
	
	@Select(SELECT_ALL_PROJECTID_BUILDING_ISHOP)
	@Results(value = {
			  @Result(id = true, property = "id", column = "id"),
			  @Result(property = "buildingName", column = "name"),
			  @Result(property = "shopName", column = "shop_name_zenkaku")
			})
	public List<ProjectInfo> fillAll();
}

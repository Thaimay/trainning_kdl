package com.saisystem.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.saisystem.springboot.model.Product;

@Mapper
public interface ProductMapper {

	@Select("SELECT * FROM product")
	List<Product> fillAll();
}

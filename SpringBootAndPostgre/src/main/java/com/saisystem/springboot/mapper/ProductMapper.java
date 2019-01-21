package com.saisystem.springboot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saisystem.springboot.Model.Product;

@Mapper
public interface ProductMapper {

	@Select("SELECT * FROM product")
	public List<Product> fillAll();
	
	@Select("SELECT * FROM product WHERE id = #{id}")
	public Product getProductById(int id);
	
	@Update("UPDATE product SET fullname = #{fullname}, price = #{price}, producer = #{producer} WHERE id = #{id}")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void updateProduct(Product product);
	
	@Delete("DELETE FROM product WHERE id = #{id}")
	public void deleteProduct(int id);
	
	@Insert("INSERT INTO product(fullname, price, producer) VALUES ( #{fullname}, #{price}, #{producer}")
	public void insertProduct(Product product);
}

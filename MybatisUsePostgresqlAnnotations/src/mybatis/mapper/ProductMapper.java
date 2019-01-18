package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mybatis.mapper.entity.Product;

public interface ProductMapper {
	
	final String GET_ALL = "SELECT * FROM product";
	@Select(GET_ALL)
	@Results(value= {@Result(property="id", column="id"),
		@Result(property="name", column="fullname"),
		@Result(property="price", column="price"),
		@Result(property="producer", column="producer")
	})
	public List<Product> getAll();
	
	final String GET_BY_ID = "SELECT * FROM product WHERE id = #{id}";
	@Select(GET_BY_ID)
	public Product getById(int id);
	
	final String DELETE_PRODUCT = "DELETE FROM product WHERE id = #{id}";
	@Delete(DELETE_PRODUCT)
	public void deleteProduct(int id);
	
	final String UPDATE_PRODUCT = "UPDATE product SET fullname = #{name}, price = #{price}, producer = #{producer} WHERE id = #{id}";
	@Update(UPDATE_PRODUCT)
	public void updateProduct(Product product);
	

}

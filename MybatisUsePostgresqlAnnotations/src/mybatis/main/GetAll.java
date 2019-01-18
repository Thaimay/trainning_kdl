package mybatis.main;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.ProductMapper;
import mybatis.mapper.entity.Product;

public class GetAll {
	public static void main(String[] args) throws IOException {
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		ProductMapper productMapper = session.getMapper(ProductMapper.class);
		
		List<Product> productList = productMapper.getAll();
		for(Product product:productList) {
			System.out.println(product.toString());
		}
		
		session.close();
	}

}

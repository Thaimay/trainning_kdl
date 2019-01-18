package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.ProductMapper;
import mybatis.mapper.entity.Product;

public class UpdateProduct {
	public static void main(String[] args) throws IOException{
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		ProductMapper proMapper = session.getMapper(ProductMapper.class);
		
		Product product = proMapper.getById(3);
		
		product.setName("Priv");
		product.setPrice(450);
		product.setProducer("BlackBerry");
		
		proMapper.updateProduct(product);
		session.commit();
		System.out.println("Update Sucess!");
		session.close();
	}

}

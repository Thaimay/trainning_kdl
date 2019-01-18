package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.ProductMapper;

public class DeleteProduct {
	public static void main(String[] args) throws IOException{
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		ProductMapper proMapper = session.getMapper(ProductMapper.class);
		proMapper.deleteProduct(4);
		session.commit();
		System.out.println("DELETE SUCESS!");
		session.close();
		
	}

}

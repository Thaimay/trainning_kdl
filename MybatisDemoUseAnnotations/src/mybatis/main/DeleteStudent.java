package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.StudentMapper;

public class DeleteStudent {
	public static void main(String[] args) throws IOException{
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		
		studentMapper.deleteStudent(12);
		session.commit();
		System.out.println("Delete Sucess!");
		session.close();
	}
}

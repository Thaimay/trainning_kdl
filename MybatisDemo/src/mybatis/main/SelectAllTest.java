package mybatis.main;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.entity.Student;

public class SelectAllTest {
	public static void main(String[] args) throws IOException{
		
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		List<Student> studentList = session.selectList("Student.selectAllStudent");
		
		for(Student student : studentList ) {
			System.out.println(student);
		}
		
	}

}

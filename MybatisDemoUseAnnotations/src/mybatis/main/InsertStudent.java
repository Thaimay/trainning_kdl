package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.StudentMapper;
import mybatis.mapper.entity.Student;

public class InsertStudent {
	public static void main(String[] args) throws IOException {
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		
		Student student = new Student("Kathira", "KR", 12, 123021542, "kathira@saisystem.vn");
		studentMapper.insertStudent(student);
		session.commit();
		System.out.println("Insert Sucess!");
		
		session.close();
	}

}

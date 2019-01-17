package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.StudentMapper;
import mybatis.mapper.entity.Student;

public class UpdateStudent {
	public static void main(String[] args) throws IOException{
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student student  = studentMapper.getStudentById(6);
		
		student.setName("Lisa");
		student.setBranch("EU");
		student.setPercentage(36256);
		student.setPhone(326521529);
		student.setEmail("lyly@saisystem.vn");
		
		studentMapper.updateStudent(student);
		session.commit();
		System.out.println("Update Sucess!");
		
		session.close();
	}
}

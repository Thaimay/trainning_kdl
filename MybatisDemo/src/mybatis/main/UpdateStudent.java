package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.entity.Student;

public class UpdateStudent {
	public static void main(String[] args) throws IOException {
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		
		Student student = session.selectOne("Student.selectStudentById", 3);
		student.setName("Martin");
		student.setBranch("VN");
		student.setPercentage(250);
		student.setEmail("martin@saisystem.vn");
		session.update("Student.updateStudent", student);
		session.commit();
		
		session.close();
	}

}

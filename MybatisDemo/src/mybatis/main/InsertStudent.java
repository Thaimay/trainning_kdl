package mybatis.main;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mybatis.mapper.entity.Student;

public class InsertStudent {
	public static void main(String[] args) throws IOException {
		Reader reader = Resources.getResourceAsReader("SqlMapconfig.xml");
		
		SqlSessionFactory sqlSesstionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession session = sqlSesstionFactory.openSession();
		
		Student student = new Student("Handi", "FR", 21, 8989878, "handi@saisytem.vn");
		
		session.insert("Student.insertStudent", student);
		session.commit();
		session.close();

	}
}

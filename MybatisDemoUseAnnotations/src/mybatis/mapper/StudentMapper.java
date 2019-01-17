package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mybatis.mapper.entity.Student;

public interface StudentMapper {
	final String SELECT_ALL = "SELECT * FROM STUDENT";
	
	@Select(SELECT_ALL)
	@Results(value= {@Result(property="id", column="ID"),
			@Result(property="name", column="NAME"),
			@Result(property="branch", column="BRANCH"),
			@Result(property="percentage", column="PERCENTAGE"),
			@Result(property="phone", column="PHONE"),
			@Result(property="email", column="EMAIL")
	})
	public List<Student> getAll();
	
	
	final String SELECT_BY_ID = "SELECT * FROM STUDENT WHERE ID = #{id}";
	@Select(SELECT_BY_ID)
	public Student getStudentById(int id);
	
	
	final String INSERT_STUDENT ="INSERT INTO STUDENT(ID, NAME, BRANCH, PERCENTAGE, PHONE, EMAIL)"
			+ "VALUES (#{id}, #{name}, #{branch}, #{percentage}, #{phone}, #{email})";
	@Insert(INSERT_STUDENT)
	public void insertStudent(Student student);
	
	
	final String UPDATE_STUDENT = "UPDATE STUDENT SET NAME = #{name}, BRANCH = #{branch}, PERCENTAGE = #{percentage}, PHONE = #{phone}, EMAIL = #{email}"
			+ "WHERE ID = #{id}";
	@Update(UPDATE_STUDENT)
	//@Options(useGeneratedKeys = true, keyProperty = "id")
	public void updateStudent(Student student);
	
	
	final String DELETE_STUDENT = "DELETE FROM STUDENT WHERE ID = #{id}";
	@Delete(DELETE_STUDENT)
	public void deleteStudent(int id);

}

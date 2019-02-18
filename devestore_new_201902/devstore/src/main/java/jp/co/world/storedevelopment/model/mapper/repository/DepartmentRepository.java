package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Department;
import jp.co.world.storedevelopment.model.mapper.DepartmentRepositoryMapper;

public class DepartmentRepository extends Repository<Department, DepartmentRepositoryMapper> {

	@Override
	protected DepartmentRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(DepartmentRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return "I_DEPT";
	}

	public Optional<Department> findByAccount(Account account) {
		return execute((mapper) -> {
			Department department = mapper.findByAccount(account);
			return Optional.ofNullable(department);
		});
	}

	public Optional<Department> findByImportCode(Department model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getAppropriatingYearMonth(), model.getDeptCd()));
		});
	}

}

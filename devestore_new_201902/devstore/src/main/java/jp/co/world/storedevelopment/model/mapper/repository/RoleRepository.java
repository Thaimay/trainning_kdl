package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Role;
import jp.co.world.storedevelopment.model.RoleFinder;
import jp.co.world.storedevelopment.model.SubAccount;
import jp.co.world.storedevelopment.model.mapper.RoleRepositoryMapper;

public class RoleRepository extends Repository<Role, RoleRepositoryMapper> {

	@Override
	protected RoleRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(RoleRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Role.class);
	}

	public Optional<Role> findByAccount(Account account) {
		return execute((mapper) -> {
			Optional<SubAccount> subOpt = account.getSubAccount();
			List<Role> roles = mapper.findAllOrderById();

			if (subOpt.isPresent()) {
				return findByAccountCode(roles, account, subOpt.get());
			} else {
				return findByAccountCode(roles, account.createRoleFinder());
			}
		});
	}

	private Optional<Role> findByAccountCode(List<Role> roles, Account account, SubAccount subAccount) {
		Optional<Role> role = findByAccountCode(roles, account.createRoleFinder());

		if (role.isPresent()) {
			return role;
		} else {
			return findByAccountCode(roles, subAccount.createRoleFinder());
		}
	}

	private Optional<Role> findByAccountCode(List<Role> roles, RoleFinder role) {
		Optional<Role> roleByEmplayeeCode = roles.stream()
				.filter(r -> r.containEmployeeCode(role.getEmployeeCode()) && role.getEmployeeCode().isEmpty() == false)
				.findFirst();
		if (roleByEmplayeeCode.isPresent()) {
			return roleByEmplayeeCode;
		}
		Optional<Role> roleByIncome = roles.stream().filter(
				r -> r.containImcomeUnitCode(role.getIncomeUnitCode()) && role.getIncomeUnitCode().isEmpty() == false)
				.findFirst();
		if (roleByIncome.isPresent()) {
			return roleByIncome;
		}
		Optional<Role> roleByCompany = roles.stream()
				.filter(r -> r.containCompanyCode(role.getCompanyCode()) && role.getCompanyCode().isEmpty() == false)
				.findFirst();
		if (roleByCompany.isPresent()) {
			return roleByCompany;
		}
		return Optional.empty();
	}

}

package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Department;

public interface DepartmentRepositoryMapper extends RepositoryMapper<Department> {

	@Select("select * from I_DEPT D where D.is_deleted is false and D.dept_cd = '${account.expensesDepartmentCd}' order by appropriating_year_month DESC LIMIT 1")
	Department findByAccount(@Param("account") Account account);

	@Select("select * from I_DEPT where is_deleted is false and appropriating_year_month = '${appropriatingYearMonth}' and dept_cd = '${code}'")
	Department findByImportCode(@Param("appropriatingYearMonth") String appropriatingYearMonth, @Param("code") String deptCd);

}
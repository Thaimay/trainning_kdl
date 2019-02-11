package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public interface AccountRepositoryMapper extends RepositoryMapper<Account> {

	@Select("select * from I_ACCOUNT where is_deleted is false and (full_name like '${account.text}%' or full_name like '${account.textHankaku}%' or kana_full_name like '${account.text}%' or kana_full_name like '${account.textHankaku}%') and mail_address != '' and mail_address is not null LIMIT ${limit}")
	List<Account> findByText(@Param("account") NegotiationRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from I_ACCOUNT where is_deleted is false and (kana_full_name like '${account.text}%' or kana_full_name like '${account.textHankaku}%') and mail_address != '' and mail_address is not null LIMIT ${limit}")
	List<Account> findKanaByText(@Param("account") NegotiationRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from I_ACCOUNT where employee_cd = '${code}' ")
	Account findByCode(@Param("code") String code);

	@Select("select * from I_ACCOUNT where common_employee_cd = '${code}' OR employee_cd = '${code}'")
	List<Account> findCommon(@Param("code") String code);

	@Select("select * from I_ACCOUNT where mail_address = '${email}' ")
	Account findByEmail(@Param("email") String email);

	@Select("select * from I_ACCOUNT where employee_cd = '${code}'")
	Account findByImportCode(@Param("code") String employeeCd);

	@Select("(SELECT area_cd FROM m_income_unit_area WHERE income_unit_cd IN"
			+ "(SELECT income_unit_cd_1 FROM i_dept WHERE dept_cd IN"
			+ "(SELECT expenses_department_cd FROM i_account WHERE id = ${id})))"
			)
	List<String> areaCodesByAccountId(@Param("id") Long id);
}

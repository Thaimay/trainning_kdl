package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.OtherProjectAccount;

public interface OtherProjectAccountRepositoryMapper extends ProjectRelatedRepositoryMapper<OtherProjectAccount> {
	@Select("select * from i_account WHERE id IN (select i_account_id from other_project_account WHERE project_id = ${id})")
	public List<Account> findAccountByProjectId(@Param("id") Long id);

	@Select("select * from i_account WHERE id IN (select i_account_id from other_project_account WHERE project_id = ${id} AND category IN ('SALES', 'STORE'))")
	public List<Account> findStoreSalesByProjectId(@Param("id") Long id);

	@Select("select * from i_account WHERE id IN (select i_account_id from other_project_account WHERE project_id = ${id} AND category = 'STORE')")
	public List<Account> findStoreByProjectId(@Param("id") Long id);
}

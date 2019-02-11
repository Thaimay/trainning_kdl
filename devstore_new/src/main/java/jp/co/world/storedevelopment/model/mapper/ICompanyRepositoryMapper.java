package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ICompany;

public interface ICompanyRepositoryMapper extends RepositoryMapper<ICompany> {
	@Select("select * from i_company where is_deleted is false and company_code = '${code}'")
	ICompany findByImportCode(@Param("code") String companyCd);

	@Select("select * from i_company where is_deleted is false and company_code IN (${code})")
	List<ICompany> findListByCode(@Param("code") String companyCd);
}

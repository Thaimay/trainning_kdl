package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.ICompany;
import jp.co.world.storedevelopment.model.mapper.ICompanyRepositoryMapper;

public class ICompanyRepository extends Repository<ICompany, ICompanyRepositoryMapper> {

	@Override
	protected ICompanyRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ICompanyRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ICompany.class);
	}

	public Optional<ICompany> findByCode(String code) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(code));
		});		
	}
	
	public List<ICompany> findListByCode(String code) {
		return execute((mapper) -> {
			return mapper.findListByCode(code);
		});
	}
	
	public Optional<ICompany> findByImportCode(ICompany model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getCompanyCode()));
		});
	}
}

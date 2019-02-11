package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IShopCompany;
import jp.co.world.storedevelopment.model.mapper.IShopCompanyRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class IShopCompanyRepository extends Repository<IShopCompany, IShopCompanyRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IShopCompany.class);
	}

	@Override
	protected IShopCompanyRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IShopCompanyRepositoryMapper.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IShopCompany> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getCompanyName()))
					.collect(Collectors.toList());
		});
	}

	public Optional<IShopCompany> findByImportCode(IShopCompany model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getCompanyId().toString()));
		});
	}
}

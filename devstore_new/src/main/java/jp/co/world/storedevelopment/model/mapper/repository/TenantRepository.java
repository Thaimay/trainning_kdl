package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Tenant;
import jp.co.world.storedevelopment.model.mapper.TenantRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.TenantListDTO;

public class TenantRepository extends Repository<Tenant, TenantRepositoryMapper> {

	@Override
	protected TenantRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(TenantRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Tenant.class);
	}

	public List<TenantListDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<Tenant> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new TenantListDTO(a))
					.collect(Collectors.toList());
		});
	}

}

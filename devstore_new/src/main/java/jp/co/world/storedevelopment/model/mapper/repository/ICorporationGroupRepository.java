package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.ICorporationGroup;
import jp.co.world.storedevelopment.model.mapper.ICorporationGroupRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class ICorporationGroupRepository extends Repository<ICorporationGroup, ICorporationGroupRepositoryMapper> {

	@Override
	protected ICorporationGroupRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ICorporationGroupRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ICorporationGroup.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<ICorporationGroup> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(c -> new BuildingRelationDTO(c.getId(), c.getCorporationGpName()))
					.collect(Collectors.toList());
		});
	}

	public Optional<ICorporationGroup> findByImportCode(ICorporationGroup model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getCorporationGpCd()));
		});
	}
}

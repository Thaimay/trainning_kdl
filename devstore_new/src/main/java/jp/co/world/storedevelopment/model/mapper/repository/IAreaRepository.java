package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.mapper.IAreaRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class IAreaRepository extends Repository<IArea, IAreaRepositoryMapper> {

	@Override
	protected IAreaRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IAreaRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IArea.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IArea> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getAreaName()))
					.collect(Collectors.toList());
		});
	}

	public Optional<IArea> findByImportCode(IArea model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getAreaCd()));
		});
	}
}

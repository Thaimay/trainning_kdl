package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.MBuildingSalesTypes;
import jp.co.world.storedevelopment.model.mapper.MBuildingSalesTypesRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class MBuildingSalesTypesRepository
		extends Repository<MBuildingSalesTypes, MBuildingSalesTypesRepositoryMapper> {

	@Override
	protected MBuildingSalesTypesRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MBuildingSalesTypesRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MBuildingSalesTypes.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<MBuildingSalesTypes> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getValue()))
					.collect(Collectors.toList());
		});
	}
}

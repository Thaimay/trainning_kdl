package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.MBuildingSalesClassifications;
import jp.co.world.storedevelopment.model.mapper.MBuildingSalesClassificationsRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class MBuildingSalesClassificationsRepository
		extends Repository<MBuildingSalesClassifications, MBuildingSalesClassificationsRepositoryMapper> {

	@Override
	protected MBuildingSalesClassificationsRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MBuildingSalesClassificationsRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MBuildingSalesClassifications.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<MBuildingSalesClassifications> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getValue()))
					.collect(Collectors.toList());
		});
	}
}

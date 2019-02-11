package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.ParticipatingStoreCorporation;
import jp.co.world.storedevelopment.model.mapper.ParticipatingStoreCorporationRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class ParticipatingStoreCorporationRepository
		extends Repository<ParticipatingStoreCorporation, ParticipatingStoreCorporationRepositoryMapper> {

	@Override
	protected ParticipatingStoreCorporationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ParticipatingStoreCorporationRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ParticipatingStoreCorporation.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<ParticipatingStoreCorporation> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getCorporationName()))
					.collect(Collectors.toList());
		});
	}

}

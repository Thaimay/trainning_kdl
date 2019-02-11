package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.MCreditRank;
import jp.co.world.storedevelopment.model.mapper.MCreditRankRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class MCreditRankRepository extends Repository<MCreditRank, MCreditRankRepositoryMapper> {

	@Override
	protected MCreditRankRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(MCreditRankRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(MCreditRank.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<MCreditRank> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getCreditRank()))
					.collect(Collectors.toList());
		});
	}
	
	public MCreditRank findByCorporationCd(String corporationCd) {
		return execute((mapper) -> {
			return mapper.findByCorporationCd(corporationCd);
		});
	}
}

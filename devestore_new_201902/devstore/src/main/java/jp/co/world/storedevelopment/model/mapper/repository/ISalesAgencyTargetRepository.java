package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.ISalesAgencyTarget;
import jp.co.world.storedevelopment.model.mapper.ISalesAgencyTargetRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class ISalesAgencyTargetRepository extends Repository<ISalesAgencyTarget, ISalesAgencyTargetRepositoryMapper> {

	@Override
	protected ISalesAgencyTargetRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ISalesAgencyTargetRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ISalesAgencyTarget.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<ISalesAgencyTarget> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getSalesAgencyTargetName()))
					.collect(Collectors.toList());
		});
	}

	public Optional<ISalesAgencyTarget> findByImportCode(ISalesAgencyTarget model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getSalesAgencyTargetId().toString()));
		});
	}
	
	public Optional<ISalesAgencyTarget> findBySalesAgencyTargetId(Long salesAgencyTargetId) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(salesAgencyTargetId.toString()));
		});
	}
}

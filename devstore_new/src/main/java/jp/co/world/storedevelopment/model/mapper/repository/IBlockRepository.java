package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.mapper.IBlockRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class IBlockRepository extends Repository<IBlock, IBlockRepositoryMapper> {

	@Override
	protected IBlockRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IBlockRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IBlock.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IBlock> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getBlockName()))
					.collect(Collectors.toList());
		});
	}

	public Optional<IBlock> findByImportCode(IBlock model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getBlockCd()));
		});
	}
}

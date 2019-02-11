package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.ISalesChannel;
import jp.co.world.storedevelopment.model.mapper.ISalesChannelRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class ISalesChannelRepository extends Repository<ISalesChannel, ISalesChannelRepositoryMapper> {

	@Override
	protected ISalesChannelRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ISalesChannelRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ISalesChannel.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<ISalesChannel> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getSalesChannelName()))
					.collect(Collectors.toList());
		});
	}

	public Optional<ISalesChannel> findByImportCode(ISalesChannel model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getSalesChannelId()));
		});
	}

}

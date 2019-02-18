package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.mapper.IPrefecturesRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public class IPrefecturesRepository extends BuildingRelatedRepository<IPrefectures, IPrefecturesRepositoryMapper> {

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IPrefectures.class);
	}

	@Override
	protected IPrefecturesRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IPrefecturesRepositoryMapper.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IPrefectures> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new BuildingRelationDTO(a.getId(), a.getPrefecturesName()))
					.collect(Collectors.toList());
		});
	}

	public Optional<IPrefectures> findByJisPrefecturesId(IPrefectures model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByJisPrefecturesId(model.getJisPrefecturesId()));
		});
	}

}

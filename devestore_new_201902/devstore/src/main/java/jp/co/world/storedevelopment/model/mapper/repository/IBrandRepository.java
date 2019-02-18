package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IBrand;
import jp.co.world.storedevelopment.model.mapper.IBrandRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class IBrandRepository extends Repository<IBrand, IBrandRepositoryMapper> {

	@Override
	protected IBrandRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IBrandRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IBrand.class);
	}

	public Optional<IBrand> findByImportCode(IBrand model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getBrandCd()));
		});
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IBrand> list = mapper.findByText(dto.getText(), HankakuUtils.toHankaku(dto.getText()),
					Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(b -> new NegotiationRelationDTO(b.getId(), b.getBrandName()))
					.collect(Collectors.toList());
		});
	}
}

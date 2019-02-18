package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Brand;
import jp.co.world.storedevelopment.model.mapper.BrandRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class BrandRepository extends Repository<Brand, BrandRepositoryMapper> {

	@Override
	protected BrandRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BrandRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Brand.class);
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<Brand> list = mapper.findByText(dto.getText(), HankakuUtils.toHankaku(dto.getText()),
					Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(b -> new NegotiationRelationDTO(b.getId(), b.getName()))
					.collect(Collectors.toList());
		});
	}

}

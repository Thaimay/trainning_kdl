package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.mapper.ICorporationRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ICorporationListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class ICorporationRepository extends Repository<ICorporation, ICorporationRepositoryMapper> {

	@Override
	protected ICorporationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ICorporationRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(ICorporation.class);
	}

	public ICorporation getICorporationByICorporationCd(String iCorporationCd) {
		return execute((mapper) -> {
			return mapper.getICorporationByICorporationCd(iCorporationCd);
		});
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<ICorporation> list = mapper.findByText(dto.getText(), HankakuUtils.toHankaku(dto.getText()),
					ZenkakuUtils.toZenkaku(dto.getText()), Application.SUGGEST_LIMIT_SIZE);
			return list.stream()
					.map(c -> new NegotiationRelationDTO(c.getId(), ZenkakuUtils.toZenkaku(c.getCorporationName())))
					.collect(Collectors.toList());
		});
	}

	public List<BuildingRelationDTO> findByString(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<ICorporation> list = mapper.findByString(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream()
					.map(c -> new BuildingRelationDTO(c.getId(), c.getCorporationName(), c.getCorporationGpId()))
					.collect(Collectors.toList());
		});
	}

	public List<ICorporationListDTO> findByCorporationGroupId(Long iCorporationGpId) {
		return execute((mapper) -> {
			List<ICorporation> iCorporations = mapper.findByCorporationGroupId(iCorporationGpId);
			return iCorporations.stream().map(n -> new ICorporationListDTO(n)).collect(Collectors.toList());
		});
	}

	public Optional<ICorporation> findByImportCode(ICorporation model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getCorporationCd()));
		});
	}
}

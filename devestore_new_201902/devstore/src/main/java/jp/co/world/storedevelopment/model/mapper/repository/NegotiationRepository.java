package jp.co.world.storedevelopment.model.mapper.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.NegotiationRepositoryMapper;
import jp.co.world.storedevelopment.pc.controller.form.NegotiationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelatedFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedNegotiationFindFormDTO;

public class NegotiationRepository extends Repository<Negotiation, NegotiationRepositoryMapper> {

	@Override
	protected NegotiationRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(NegotiationRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Negotiation.class);
	}

	public List<Negotiation> find(NegotiationFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.find(dto);
		});
	}

	public List<Negotiation> findRelatedNegotiation(Negotiation negotiation) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiation(new NegotiationRelatedFindFormDTO(negotiation));
		});
	}

	public List<Negotiation> findRelatedNegotiation(Building building) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiation(new NegotiationRelatedFindFormDTO(building));
		});
	}

	public List<Negotiation> findRelatedNegotiation(NegotiationRelatedFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiation(dto);
		});
	}

	public List<Negotiation> findForPC(NegotiationFindForm form) {
		return execute((mapper) -> {
			return mapper.findForPC(form);
		});
	}

	public List<Negotiation> findByScheduleDate(LocalDate date, Account account) {
		return execute((mapper) -> {
			return mapper.findByScheduleDate(date, account);
		});
	}

	public List<Negotiation> findByScheduleStartDatetime(LocalDateTime currentDateTime) {
		return execute((mapper) -> {
			return mapper.findByScheduleStartDatetime(currentDateTime);
		});
	}

	public List<Negotiation> findByNegotiationId(Long negotiationId) {
		return execute((mapper) -> {
			return mapper.findByNegotiationId(negotiationId);
		});
	}

	public int countForPC(NegotiationFindForm form) {
		return execute((mapper) -> {
			return mapper.countForPC(form);
		});
	}

	public List<Negotiation> findByBuilding(Long buildingId) {
		return execute((mapper) -> {
			return mapper.findByBuilding(buildingId);
		});
	}

	public List<Negotiation> findByBuildingHistory(Long buildingId) {
		return execute((mapper) -> {
			return mapper.findByBuildingHistory(buildingId);
		});
	}

	public List<Negotiation> findRelatedNegotiationByProject(RelatedNegotiationFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiationByProject(dto);
		});
	}

	public List<Negotiation> findRelatedNegotiationByCorporation(RelatedNegotiationFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiationByCorporation(dto);
		});
	}

	public List<Negotiation> findRelatedNegotiationByBusinessCard(RelatedNegotiationFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiationByBusinessCard(dto);
		});
	}

	public List<Negotiation> findRelatedNegotiationByBusinessCardFree(RelatedNegotiationFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiationByBusinessCardFree(dto);
		});
	}

	public List<Negotiation> findRelatedNegotiationByBuilding(RelatedNegotiationFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiationByBuilding(dto);
		});
	}

	public List<Negotiation> findRelatedNegotiationByBrand(RelatedNegotiationFindFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findRelatedNegotiationByBrand(dto);
		});
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<Negotiation> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> new NegotiationRelationDTO(a.getId(), a.getTitle()))
					.collect(Collectors.toList());
		});
	}
}

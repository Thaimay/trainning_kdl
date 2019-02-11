package jp.co.world.storedevelopment.model.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.repository.sql.NegotiationFindSQLBuilder;
import jp.co.world.storedevelopment.model.mapper.repository.sql.NegotiationRelatedFindSQLBuilder;
import jp.co.world.storedevelopment.pc.controller.form.NegotiationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelatedFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.RelatedNegotiationFindFormDTO;

public interface NegotiationRepositoryMapper extends RepositoryMapper<Negotiation> {

	@SelectProvider(type = NegotiationFindSQLBuilder.class, method = "build")
	List<Negotiation> find(NegotiationFindFormDTO dto);

	@SelectProvider(type = NegotiationFindSQLBuilder.class, method = "buildForPC")
	List<Negotiation> findForPC(NegotiationFindForm form);

	@SelectProvider(type = NegotiationFindSQLBuilder.class, method = "countForPC")
	int countForPC(NegotiationFindForm form);

	@SelectProvider(type = NegotiationFindSQLBuilder.class, method = "buildByDate")
	List<Negotiation> findByScheduleDate(LocalDate date, Account account);

	@Select("select * from negotiation where id in (select negotiation_id from NEGOTIATION_INTERVIEW_BUILDING where building_id = ${buildingId})")
	List<Negotiation> findByBuilding(@Param("buildingId") Long buildingId);

	@SelectProvider(type = NegotiationRelatedFindSQLBuilder.class, method = "build")
	List<Negotiation> findRelatedNegotiation(NegotiationRelatedFindFormDTO dto);

	@Select("SELECT * FROM Negotiation WHERE schedule_start_datetime <= '${currentDateTime}' and implementation_start_datetime is null and is_deleted is false and stop is false")
	List<Negotiation> findByScheduleStartDatetime(@Param("currentDateTime") LocalDateTime currentDateTime);

	@Select("select * from negotiation where id = '${negotiationId}'")
	List<Negotiation> findByNegotiationId(@Param("negotiationId") Long negotiationId);

	@Select("select * from NEGOTIATION_HISTORY where id in (select negotiation_id from NEGOTIATION_INTERVIEW_BUILDING_HISTORY where building_id = ${buildingId})")
	List<Negotiation> findByBuildingHistory(@Param("buildingId") Long buildingId);

	@Select("select * from negotiation where title like '${negotiation.text}%' or title like '${negotiation.textHankaku}%' limit ${limit}")
	List<Negotiation> findByText(@Param("negotiation") NegotiationRelationFindByTextFormDTO dto,
			@Param("limit") int limit);

	List<Negotiation> findRelatedNegotiationByProject(RelatedNegotiationFindFormDTO dto);

	List<Negotiation> findRelatedNegotiationByCorporation(RelatedNegotiationFindFormDTO dto);

	List<Negotiation> findRelatedNegotiationByBusinessCard(RelatedNegotiationFindFormDTO dto);

	List<Negotiation> findRelatedNegotiationByBusinessCardFree(RelatedNegotiationFindFormDTO dto);

	List<Negotiation> findRelatedNegotiationByBuilding(RelatedNegotiationFindFormDTO dto);

	List<Negotiation> findRelatedNegotiationByBrand(RelatedNegotiationFindFormDTO dto);
}

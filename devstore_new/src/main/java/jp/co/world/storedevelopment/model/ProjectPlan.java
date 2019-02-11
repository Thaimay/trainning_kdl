package jp.co.world.storedevelopment.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ProjectPlanModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.ISalesAgencyTargetRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MPeriodRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ParticipatingStoreCorporationRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class ProjectPlan extends ActiveModel<ProjectPlan> {

	private Long projectId;
	private String category;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private Float numberOfYear;
	private Long salesAgencyTargetId;
	private Long participatingStoreCorporationId;

	private String[] ignoreFields = new String[] {"salesAgencyCurrent", "salesAgencyProgress", "participatingStoreCurrent", "participatingStoreProgress", "term"};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<ProjectPlan> modelMapper(SqlSession session) {
		return session.getMapper(ProjectPlanModelMapper.class);
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Float getNumberOfYear() {
		return numberOfYear;
	}

	public void setNumberOfYear(Float numberOfYear) {
		this.numberOfYear = numberOfYear;
	}

	public Long getSalesAgencyTargetId() {
		return salesAgencyTargetId;
	}

	public void setSalesAgencyTargetId(Long salesAgencyTargetId) {
		this.salesAgencyTargetId = salesAgencyTargetId;
	}

	public Long getParticipatingStoreCorporationId() {
		return participatingStoreCorporationId;
	}

	public void setParticipatingStoreCorporationId(Long participatingStoreCorporationId) {
		this.participatingStoreCorporationId = participatingStoreCorporationId;
	}

	public NegotiationRelationDTO getSalesAgencyCurrent() {
		NegotiationRelationDTO salesAgencyCurrent = new NegotiationRelationDTO();
		if(this.category.equals("CURRENT") && this.salesAgencyTargetId != null) {
			Optional<ISalesAgencyTarget> iSalesAgencyTarget = new ISalesAgencyTargetRepository().findBySalesAgencyTargetId(this.salesAgencyTargetId);
			if (iSalesAgencyTarget.isPresent()) {
				salesAgencyCurrent = iSalesAgencyTarget.map(sale -> new NegotiationRelationDTO(sale.getId(), sale.getSalesAgencyTargetName())).get();
			}
		}
		return salesAgencyCurrent;
	}

	public NegotiationRelationDTO getSalesAgencyProgress() {
		NegotiationRelationDTO salesAgency = new NegotiationRelationDTO();
		if(this.category.equals("PROGRESS") && this.salesAgencyTargetId != null) {
			Optional<ISalesAgencyTarget> iSalesAgencyTarget = new ISalesAgencyTargetRepository().findBySalesAgencyTargetId(this.salesAgencyTargetId);
			if (iSalesAgencyTarget.isPresent()) {
				salesAgency = iSalesAgencyTarget.map(sale -> new NegotiationRelationDTO(sale.getId(), sale.getSalesAgencyTargetName())).get();
			}
		}
		return salesAgency;
	}

	public NegotiationRelationDTO getParticipatingStoreCurrent() {
		NegotiationRelationDTO salesAgencyCurrent = new NegotiationRelationDTO();
		if(this.category.equals("CURRENT") && this.participatingStoreCorporationId != null) {
			Optional<ParticipatingStoreCorporation> participatingStoreCorporation = new ParticipatingStoreCorporationRepository().findById(this.participatingStoreCorporationId);
			if (participatingStoreCorporation.isPresent()) {
				salesAgencyCurrent = participatingStoreCorporation.map(store -> new NegotiationRelationDTO(store.getId(), store.getCorporationName())).get();
			}
		}
		return salesAgencyCurrent;
	}

	public NegotiationRelationDTO getParticipatingStoreProgress() {
		NegotiationRelationDTO salesAgency = new NegotiationRelationDTO();
		if(this.category.equals("PROGRESS")  && this.participatingStoreCorporationId != null) {
			Optional<ParticipatingStoreCorporation> participatingStoreCorporation = new ParticipatingStoreCorporationRepository().findById(this.participatingStoreCorporationId);
			if (participatingStoreCorporation.isPresent()) {
				salesAgency = participatingStoreCorporation.map(store -> new NegotiationRelationDTO(store.getId(), store.getCorporationName())).get();
			}
		}
		return salesAgency;
	}
	
	public String getTerm() {
		if (endDate != null) {
			Optional<MPeriod> opt = new MPeriodRepository()
					.getPeriod(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(endDate));
			if (opt.isPresent()) {
				return opt.get().getPeriod() + "期";
			}
		}

		return StringUtils.EMPTY;
	}
}

package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectNegotiationRepository;

public class RelatedNegotiationDTO {

	private Long id = 0L;

	private RelatedNegotiationFindFormDTO findDTO;

	private Account account;

	public RelatedNegotiationDTO(RelatedNegotiationFindFormDTO findDTO, Account account) {
		setId(findDTO.getId());
		setFindDTO(findDTO);
		setAccount(account);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public RelatedNegotiationFindFormDTO getFindDTO() {
		return findDTO;
	}

	public void setFindDTO(RelatedNegotiationFindFormDTO findDTO) {
		this.findDTO = findDTO;
	}

	private Negotiation getNegotiation() {
		if (id > 0) {
			Optional<Negotiation> negotiation = new NegotiationRepository().findById(id);
			if (negotiation.isPresent()) {
				return negotiation.get();
			}
		}
		return null;
	}

	public List<RelatedNegotiationFindOptionDTO> getRelatedOptionProjects() {
		List<RelatedNegotiationFindOptionDTO> dto = new ArrayList<>();
		if (getNegotiation() != null) {
			getNegotiation().getProjectNegotiations().forEach(x -> {
				dto.add(new RelatedNegotiationFindOptionDTO(x.getProjectId(), x.getProjectName(),
						new ProjectNegotiationRepository().findNegotiationByProjectId(x.getProjectId())
								.size() <= 1));
			});
		}
		return dto;
	}

	public List<RelatedNegotiationFindOptionDTO> getRelatedOptionCorporations() {
		List<RelatedNegotiationFindOptionDTO> dto = new ArrayList<>();
		if (getNegotiation() != null) {
			getNegotiation().getInterviewCorporations().forEach(x -> {
				dto.add(new RelatedNegotiationFindOptionDTO(x.getCorporationId(), x.getCorporationName(),
						new NegotiationInterviewCorporationRepository().findByCorporationId(x.getCorporationId())
								.size() <= 1));
			});
		}
		return dto;
	}

	public List<RelatedNegotiationFindOptionDTO> getRelatedOptionBusinessCards() {
		List<RelatedNegotiationFindOptionDTO> dto = new ArrayList<>();
		if (getNegotiation() != null) {
			getNegotiation().getInterviewBussinessCards().forEach(x -> {
				dto.add(new RelatedNegotiationFindOptionDTO(x.getBusinessCardId(), x.getBusinessCardFullValue(),
						new NegotiationInterviewBusinessCardRepository().findByBusinessCardId(x.getBusinessCardId())
								.size() <= 1));
			});
		}
		return dto;
	}

	public String getRelatedOptionBusinessCardFree() {
		if (getNegotiation() != null) {
			return getNegotiation().getBusinessCardFree();
		}
		return StringUtils.EMPTY;
	}

	public List<RelatedNegotiationFindOptionDTO> getRelatedOptionBuildings() {
		List<RelatedNegotiationFindOptionDTO> dto = new ArrayList<>();
		if (getNegotiation() != null) {
			getNegotiation().getInterviewBuildings().forEach(x -> {
				dto.add(new RelatedNegotiationFindOptionDTO(x.getBuildingId(), x.getBuilding().getName(),
						new NegotiationInterviewBuildingRepository().findByBuildingId(x.getBuildingId()).size() <= 1));
			});
		}
		return dto;
	}

	public List<RelatedNegotiationFindOptionDTO> getRelatedOptionBrands() {
		List<RelatedNegotiationFindOptionDTO> dto = new ArrayList<>();
		if (getNegotiation() != null) {
			getNegotiation().getInterviewBrands().forEach(x -> {
				dto.add(new RelatedNegotiationFindOptionDTO(x.getBrandId(), x.getBrandName(),
						new NegotiationInterviewBrandRepository().findByBrandId(x.getBrandId()).size() <= 1));
			});
		}
		return dto;
	}
	
	private List<Negotiation> getAllByProject() {
		return new NegotiationRepository().findRelatedNegotiationByProject(new RelatedNegotiationFindFormDTO(id));
	}

	private List<Negotiation> getFindByProject() {
		if (findDTO.getProjectIds().size() > 0) {
			return new NegotiationRepository().findRelatedNegotiationByProject(findDTO);
		} else {
			return new ArrayList<>();
		}
	}

	private List<Negotiation> getAllByCorporation() {
		return new NegotiationRepository().findRelatedNegotiationByCorporation(new RelatedNegotiationFindFormDTO(id));
	}

	private List<Negotiation> getFindByCorporation() {
		if (findDTO.getCorporationIds().size() > 0) {
			return new NegotiationRepository().findRelatedNegotiationByCorporation(findDTO);
		} else {
			return new ArrayList<>();
		}
	}

	private List<Negotiation> getAllByBusinessCard() {
		return new NegotiationRepository().findRelatedNegotiationByBusinessCard(new RelatedNegotiationFindFormDTO(id));
	}

	private List<Negotiation> getFindByBusinessCard() {
		if (findDTO.getInterviewBusinessCardIds().size() > 0) {
			return new NegotiationRepository().findRelatedNegotiationByBusinessCard(findDTO);
		} else {
			return new ArrayList<>();
		}
	}

	private List<Negotiation> getAllByBusinessCardFree() {
		return new NegotiationRepository()
				.findRelatedNegotiationByBusinessCardFree(new RelatedNegotiationFindFormDTO(id));
	}

	private List<Negotiation> getFindByBusinessCardFree() {
		if (!findDTO.getInterviewBusinessCardFree().isEmpty()) {
			return new NegotiationRepository().findRelatedNegotiationByBusinessCardFree(findDTO);
		} else {
			return new ArrayList<>();
		}
	}

	private List<Negotiation> getAllByBuilding() {
		return new NegotiationRepository().findRelatedNegotiationByBuilding(new RelatedNegotiationFindFormDTO(id));
	}

	private List<Negotiation> getFindByBuilding() {
		if (findDTO.getBuildingIds().size() > 0) {
			return new NegotiationRepository().findRelatedNegotiationByBuilding(findDTO);
		} else {
			return new ArrayList<>();
		}
	}

	private List<Negotiation> getAllByBrand() {
		return new NegotiationRepository().findRelatedNegotiationByBrand(new RelatedNegotiationFindFormDTO(id));
	}

	private List<Negotiation> getFindByBrand() {
		if (findDTO.getBrandIds().size() > 0) {
			return new NegotiationRepository().findRelatedNegotiationByBrand(findDTO);
		} else {
			return new ArrayList<>();
		}
	}

	public List<NegotiationListDTO> getFindRelated() {
		List<NegotiationListDTO> findRelated = new ArrayList<>();
		List<Negotiation> findList = Stream
				.of(getFindByProject(), getFindByCorporation(), getFindByBusinessCard(), getFindByBusinessCardFree(), getFindByBuilding(),
						getFindByBrand())
				.flatMap(Collection::stream)
				.sorted((n1, n2) -> n2.getUpdateDatetime().compareTo(n1.getUpdateDatetime()))
				.collect(Collectors.toList());

		findList.forEach(x -> {
			if (!findRelated.stream().map(y -> y.getId()).collect(Collectors.toList()).contains(x.getId())) {
				findRelated.add(new NegotiationListDTO(x, account));
			}
		});
		return findRelated;
	}

	public boolean getExistRelatedProject() {
		return getAllByProject().size() > 0;
	}

	public boolean getExistRelatedCorporation() {
		return getAllByCorporation().size() > 0;
	}

	public boolean getExistRelatedBusinessCard() {
		return getAllByBusinessCard().size() > 0;
	}

	public boolean getExistRelatedBusinessCardFree() {
		return getAllByBusinessCardFree().size() > 0;
	}

	public boolean getExistRelatedBuilding() {
		return getAllByBuilding().size() > 0;
	}

	public boolean getExistRelatedBrand() {
		return getAllByBrand().size() > 0;
	}
	
	public boolean getExistFindRelatedProject() {
		return getFindByProject().size() > 0;
	}

	public boolean getExistFindRelatedCorporation() {
		return getFindByCorporation().size() > 0;
	}

	public boolean getExistFindRelatedBusinessCard() {
		return getFindByBusinessCard().size() > 0;
	}

	public boolean getExistFindRelatedBusinessCardFree() {
		return getFindByBusinessCardFree().size() > 0;
	}

	public boolean getExistFindRelatedBuilding() {
		return getFindByBuilding().size() > 0;
	}

	public boolean getExistFindRelatedBrand() {
		return getFindByBrand().size() > 0;
	}
}

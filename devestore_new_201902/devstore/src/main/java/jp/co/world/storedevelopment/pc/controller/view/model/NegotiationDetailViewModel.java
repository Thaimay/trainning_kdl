package jp.co.world.storedevelopment.pc.controller.view.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectNegotiation;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationDetailDTO;

public class NegotiationDetailViewModel extends NegotiationDetailDTO {

	private List<ProjectViewModel> relatedProjectViewModels = new ArrayList<>();

	public NegotiationDetailViewModel(Negotiation n, Account a) {
		super(n, a);
		setRelatedProjectViewModels(n.getProjectNegotiations(), a);
	}

	public String getProjectTitle() {
		return String.join(" ", getProjects());
	}

	public String getBussinessCardName() {
		return String.join(" ", getInterviewBusinessCard());
	}

	public String getAccountName() {
		return String.join(" ", getInterviewAccounts());
	}

	public String getBrandName() {
		return String.join(" ", getInterviewBrands());
	}

	public String getBuildingName() {
		if (getBuildings().size() > 0) {
			return getBuildings().stream().map(x -> x.getName()).collect(Collectors.joining(" "));
		}
		return StringUtils.EMPTY;
	}

	public String getCorporationName() {
		return String.join(" ", getCorporations());
	}

	public List<ProjectViewModel> getRelatedProjectViewModels() {
		return relatedProjectViewModels;
	}

	public void setRelatedProjectViewModels(List<ProjectNegotiation> projectNegotiations, Account a) {
		List<Project> list = new ArrayList<>();
		projectNegotiations.forEach(x -> {
			Optional<Project> opt = new ProjectRepository().findById(x.getProjectId());
			if (opt.isPresent()) {
				list.add(opt.get());
			}
		});

		if (list.size() > 0) {
			relatedProjectViewModels = ProjectViewModel.toList(list, a);
		}
	}
}

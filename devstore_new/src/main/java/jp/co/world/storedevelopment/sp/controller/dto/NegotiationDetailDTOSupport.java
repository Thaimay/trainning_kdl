package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;
import jp.co.world.storedevelopment.model.NegotiationInterviewBrand;
import jp.co.world.storedevelopment.model.NegotiationInterviewBusinessCard;
import jp.co.world.storedevelopment.model.NegotiationInterviewCorporation;
import jp.co.world.storedevelopment.model.ProjectNegotiation;

public abstract class NegotiationDetailDTOSupport {

	private Long id;

	private List<String> interviewAccounts = new ArrayList<String>();

	private List<String> interviewBrands = new ArrayList<String>();

	private List<String> interviewBusinessCard = new ArrayList<String>();

	private List<String> corporations = new ArrayList<String>();

	private List<String> projects = new ArrayList<String>();

	public NegotiationDetailDTOSupport(Negotiation n) {
		setInterviewAccountsList(n.getInterviewAccounts());
		setInterviewBrandsList(n.getInterviewBrands());
		setInterviewBussinesCartList(n.getInterviewBussinessCards());
		setCorporationNameList(n.getInterviewCorporations());
		setProjectNameList(n.getProjectNegotiations());
	}

	public NegotiationDetailDTOSupport() {
	}

	public List<String> getInterviewAccounts() {
		return interviewAccounts;
	}

	public void setInterviewAccounts(List<String> interviewAccounts) {
		this.interviewAccounts = interviewAccounts;
	}

	private void setProjectNameList(List<ProjectNegotiation> list) {
		if (list.size() > 0) {
			List<String> names = list.stream().map(p -> p.getProjectName()).filter(x -> x != null && !x.isEmpty())
					.collect(Collectors.toList());
			setProjects(names);
		}
	}

	private void setCorporationNameList(List<NegotiationInterviewCorporation> list) {
		if (list.size() > 0) {
			List<String> names = list.stream().map(c -> c.getCorporationName()).filter(x -> x != null && !x.isEmpty())
					.collect(Collectors.toList());
			setCorporations(names);
		}
	}

	private void setInterviewAccountsList(List<NegotiationInterviewAccount> interviewAccounts) {
		if (interviewAccounts.size() > 0) {
			setInterviewAccounts(interviewAccounts.stream().map(ia -> ia.getAccountName())
					.filter(x -> x != null && !x.isEmpty()).collect(Collectors.toList()));
		}
	}

	private void setInterviewBrandsList(List<NegotiationInterviewBrand> interviewBrands) {
		if (interviewBrands.size() > 0) {
			setInterviewBrands(interviewBrands.stream().map(ia -> ia.getBrandName())
					.filter(x -> x != null && !x.isEmpty()).collect(Collectors.toList()));
		}
	}

	private void setInterviewBussinesCartList(List<NegotiationInterviewBusinessCard> interviewBusinessCard) {
		setInterviewBusinessCard(interviewBusinessCard.stream().map(ib -> ib.getBusinessCardFullValue())
				.filter(x -> x != null && !x.isEmpty()).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getInterviewBrands() {
		return interviewBrands;
	}

	public void setInterviewBrands(List<String> interviewBrands) {
		this.interviewBrands = interviewBrands;
	}

	public List<String> getInterviewBusinessCard() {
		return interviewBusinessCard;
	}

	public void setInterviewBusinessCard(List<String> interviewBuisinessCard) {
		interviewBusinessCard = interviewBuisinessCard;
	}

	public List<String> getCorporations() {
		return corporations;
	}

	public void setCorporations(List<String> corporations) {
		this.corporations = corporations;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

}

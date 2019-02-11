package jp.co.world.storedevelopment.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.MProjectProgressStatus;

public class ActionStatusCalculationService {
	private Optional<MProjectProgressStatus> company;
	private Optional<MProjectProgressStatus> negotiation;
	private Optional<MProjectProgressStatus> other;
	
	public MProjectActionStatus calculation(List<MProjectActionStatus> list, String inHouse, String negotiationProgress, String contractProgress) {
		MProjectActionStatus last = list.get(list.size() - 1);
		setCompany(MProjectProgressStatus.companyStatus(last.getProjectCategoryId(), inHouse));
		setNegotiation(MProjectProgressStatus.negotiationStatus(last.getProjectCategoryId(), negotiationProgress));
		setOther(MProjectProgressStatus.otherStatus(last.getProjectCategoryId(), contractProgress));

		for (Integer i = 0; i < list.size(); i++) {
			if (match(list, list.get(i), inHouse, negotiationProgress, contractProgress)) {
				return list.get(i);
			}
		}

		return last;
	}
	
	private Boolean match(List<MProjectActionStatus> list, MProjectActionStatus status, String inHouse, String negotiationProgress, String contractProgress) {
		list = list.stream().filter(s -> {
			return s.getSort() < status.getSort();
		}).collect(Collectors.toList());

		if (status.sameCompany(getCompany())) {
			return negotiationStatusMatch(list)
					&& otherStatusMatch(list);
		} else if (status.sameNegotiation(getNegotiation())) {
			return companyStatusMatch(list)
					&& otherStatusMatch(list);
		} else if (status.sameOther(getOther())) {
			return companyStatusMatch(list) 
					&& negotiationStatusMatch(list);
		} else {
			return false;
		}
	}

	private Boolean companyStatusMatch(List<MProjectActionStatus> list) {
		for (Integer i = 0; i < list.size(); i++) {
			MProjectActionStatus code = list.get(i);
			
			if (code.getCompanyStatusCode().isEmpty() == false && code.sameCompany(getCompany()) == false) {
				return false;
			} else if (code.sameCompany(getCompany())) {
				return true;
			}
		}
		return true;
	}
	
	private Boolean negotiationStatusMatch(List<MProjectActionStatus> list) {
		for (Integer i = 0; i < list.size(); i++) {
			MProjectActionStatus code = list.get(i);
			
			if (code.getNegotiationStatusCode().isEmpty() == false && code.sameNegotiation(getNegotiation()) == false) {
				return false;
			} else if (code.sameNegotiation(getNegotiation())) {
				return true;
			}
		}
		return true;
	}

	private Boolean otherStatusMatch(List<MProjectActionStatus> list) {
		for (Integer i = 0; i < list.size(); i++) {
			MProjectActionStatus code = list.get(i);
			
			if (code.getOtherStatusCode().isEmpty() == false && code.sameOther(getOther()) == false) {
				return false;
			} else if (code.sameOther(getOther())) {
				return true;
			}
		}
		return true;
	}

	public Optional<MProjectProgressStatus> getCompany() {
		return company;
	}

	public void setCompany(Optional<MProjectProgressStatus> company) {
		this.company = company;
	}
	
	public Optional<MProjectProgressStatus> getNegotiation() {
		return negotiation;
	}

	public void setNegotiation(Optional<MProjectProgressStatus> negotiation) {
		this.negotiation = negotiation;
	}

	public Optional<MProjectProgressStatus> getOther() {
		return other;
	}

	public void setOther(Optional<MProjectProgressStatus> other) {
		this.other = other;
	}

}

package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.Negotiation;

public class NegotiationRelatedFindFormDTO extends FindFormDTO {

	private Long id = 0L;

	private List<Long> buildingIds = new ArrayList<Long>();

	private Account account;

	public NegotiationRelatedFindFormDTO() {
		//
	}

	public NegotiationRelatedFindFormDTO(Negotiation negotiation) {
		setId(negotiation.getId());
	}

	public NegotiationRelatedFindFormDTO(Building building) {
		List<Long> list = Arrays.asList(building.getId());
		setBuildingIds(list);
	}

	public List<Long> getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;

public class TopNegotiationListDTO extends NegotiationListDTO {
	private String startTime;

	private String endTime;

	public static List<TopNegotiationListDTO> toListDTO(List<Negotiation> list, Account account) {
		return list.stream().map(n -> {
			return new TopNegotiationListDTO(n, account);
		}).collect(Collectors.toList());
	}

	public TopNegotiationListDTO(Negotiation n, Account a) {
		super(n, a);
		setStartTime(n.getScheduleStartTime());
		setEndTime(n.getScheduleEndTime());
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}

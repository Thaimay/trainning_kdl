package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.Arrays;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Negotiation;

public class UpdateInformationListDTO {
	private Long id;

	private String title;

	private String startEndDate;

	private String place;

	private String createAccountName;

	private String division;

	private String attendees = "";

	public UpdateInformationListDTO(Negotiation n) {
		setId(n.getId());
		setTitle(n.getTitle());
		setStartEndDate(n.getScheduleDateValue());
		setPlace(n.getPlace());
		setCreateAccountName(n.createAccountName());
		setDivision(n.getDivisionValue());

		if (n.getNoticeAccounts() != null && n.getNoticeAccounts().size() > 0) {
			setAttendees(String.join("、", n.getNoticeAccounts().stream().map(x -> x.getAccountName())
					.filter(x -> !x.isEmpty()).collect(Collectors.toList())));
		}
	}

	public String getStartEndDate() {
		return startEndDate;
	}

	public void setStartEndDate(String startEndDate) {
		this.startEndDate = startEndDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCreateAccountName() {
		return createAccountName;
	}

	public void setCreateAccountName(String createAccountName) {
		this.createAccountName = createAccountName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getAttendees() {
		return attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}

	public String getCreateAccountAndAttendeeNames() {
		return String.join("、", Arrays.asList(getCreateAccountName(), getAttendees()).stream().filter(x -> !x.isEmpty())
				.collect(Collectors.toList()));
	}
}

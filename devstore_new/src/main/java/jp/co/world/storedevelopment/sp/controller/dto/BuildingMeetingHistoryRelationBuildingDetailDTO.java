package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.BuildingMeetingHistory;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class BuildingMeetingHistoryRelationBuildingDetailDTO implements DTO<BuildingMeetingHistory> {

	private Long id;
	private String overview;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;
	private String attendee;
	private Boolean isDeleted;
	private String createdAccountCode;

	public BuildingMeetingHistoryRelationBuildingDetailDTO() {

	}

	public BuildingMeetingHistoryRelationBuildingDetailDTO(BuildingMeetingHistory buildingMeetingHistory) {
		copyProperties(this, buildingMeetingHistory);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public BuildingMeetingHistory createModel() {
		return new BuildingMeetingHistory();
	}

	public String getAttendee() {
		return attendee;
	}

	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreatedAccountCode() {
		return createdAccountCode;
	}

	public void setCreatedAccountCode(String createdAccountCode) {
		this.createdAccountCode = createdAccountCode;
	}

	public String getCreatedAccountName() {
		if (getCreatedAccountCode() != null && !getCreatedAccountCode().isEmpty()) {
			Optional<Account> account = new AccountRepository().findByCode(getCreatedAccountCode());
			return account.isPresent() ? account.get().getFullName() : "";
		}
		return "";
	}

}

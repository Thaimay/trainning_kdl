package jp.co.world.storedevelopment.sp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.SalesFile;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;

public class SalesFileListDTO {
	private Long id;

	private String name;

	private String displayName;

	private String type;

	private String comment;

	private String division;

	private String size;

	private String createdDate;

	private String path;

	private LocalDateTime createdDatetime;

	private CreateAccountDTO account = new CreateAccountDTO();

	private static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public SalesFileListDTO(SalesFile s) {
		Account account = new AccountRepository().findByCode(s.getCreatedAccountCode())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:"));
		setAccount(new CreateAccountDTO(account));
		setId(s.getId());
		setPath(s.getUrlPath());
		setName(s.getName());
		setDisplayName(s.getDisplayName());
		setType(s.getType());
		setComment(s.getComment());
		setSize(s.getReadableSize());
		setDivision(s.divisionValue());
		setCreatedDate(formatDate(s.getCreatedDatetime()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public CreateAccountDTO getAccount() {
		return account;
	}

	public void setAccount(CreateAccountDTO account) {
		this.account = account;
	}

	public String getDivisionName(String division) {
		switch (division) {
		case "BUILDING":
			return "館";
		case "NEGOTIATION":
			return "商談";
		case "SHOP":
			return "店舗";
		case "CASE":
			return "案件";
		default:
			return "";
		}
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	private String formatDate(LocalDateTime date) {
		return DATE_FORMAT.format(date);
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}

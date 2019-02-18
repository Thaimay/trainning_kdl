package jp.co.world.storedevelopment.sp.controller.dto;

public class NoticeDTO {
	private Long id;
	private String name;
	private String sendType;
	
	public NoticeDTO(Long id, String name, String sendType) {
		this.id = id;
		this.name = name;
		this.sendType = sendType;
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

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

}

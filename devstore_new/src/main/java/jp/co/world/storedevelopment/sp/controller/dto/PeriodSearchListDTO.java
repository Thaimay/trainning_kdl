package jp.co.world.storedevelopment.sp.controller.dto;

public class PeriodSearchListDTO {
	public static final String UP = "上期";
	public static final String DOWN = "下期";
	
	private int id;
	private String upDown;

	public PeriodSearchListDTO(int id, String upDown) {
		this.id = id;
		this.upDown = upDown;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUpDown() {
		return upDown;
	}

	public void setUpDown(String upDown) {
		this.upDown = upDown;
	}

	public String getCheckId() {
		return id + upDown;
	}

	public String getName() {
		return id + " " + upDown;
	}

}

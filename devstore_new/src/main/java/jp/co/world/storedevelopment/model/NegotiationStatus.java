package jp.co.world.storedevelopment.model;

public enum NegotiationStatus {
	SCHEDULE, REPORT;

	public static String getScheduleLabel() {
		return "商談予定";
	}

	public static String getImplementationLabel() {
		return "商談報告";
	}

}

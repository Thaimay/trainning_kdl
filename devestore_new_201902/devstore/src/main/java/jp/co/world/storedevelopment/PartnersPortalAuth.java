package jp.co.world.storedevelopment;

public class PartnersPortalAuth {
	public Boolean check() {
		return false;
	}

	protected String getRunningMode() {
		String mode = Application.mode();

		switch (mode) {
		case "production":
			return "";
		default:
			return "";
		}
	}
}

package jp.co.world.storedevelopment.sp.controller.dto;

import jp.co.world.storedevelopment.model.Account;

public class NegotiationRelationImportantInformationDTO {

	private Boolean isPrintImportant;

	public NegotiationRelationImportantInformationDTO() {
		//
	}

	public NegotiationRelationImportantInformationDTO(Account a) {
		setIsPrintImportantValue(a);
	}

	public Boolean getIsPrintImportant() {
		return isPrintImportant;
	}

	public void setIsPrintImportant(Boolean isPrintImportant) {
		this.isPrintImportant = isPrintImportant;
	}

	public void setIsPrintImportantValue(Account a) {
		this.setIsPrintImportant(a.getRole().hasPermission("IMPORTANT_INFORMATION"));
	}

}

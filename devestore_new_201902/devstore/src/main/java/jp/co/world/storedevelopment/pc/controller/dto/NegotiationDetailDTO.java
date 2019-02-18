package jp.co.world.storedevelopment.pc.controller.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationListDTO;

public class NegotiationDetailDTO {

	private List<ImportantInformationListDTO> importantInformationList = new ArrayList<ImportantInformationListDTO>();

	public List<ImportantInformationListDTO> getImportantInformationList() {
		return importantInformationList;
	}

	public void setImportantInformationList(List<ImportantInformationListDTO> importantInformationList) {
		this.importantInformationList = importantInformationList;
	}
}

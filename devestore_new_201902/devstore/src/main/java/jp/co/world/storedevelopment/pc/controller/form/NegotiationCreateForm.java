package jp.co.world.storedevelopment.pc.controller.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFormDTO;

public class NegotiationCreateForm extends NegotiationCreateFormDTO {
	private List<MultipartFile> uploadFiles = new ArrayList<MultipartFile>();

	public List<MultipartFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<MultipartFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String getCreateType() {
		if (getScheduleStartDatetime() == null) {
			return "schedule";
		} else {
			return "report";
		}
	}
}

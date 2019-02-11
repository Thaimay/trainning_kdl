package jp.co.world.storedevelopment.pc.controller.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.sp.controller.dto.NegotiationUpdateFormViewDTO;

public class NegotiationUpdateForm extends NegotiationUpdateFormViewDTO {
	private List<MultipartFile> uploadFiles = new ArrayList<MultipartFile>();

	public List<MultipartFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<MultipartFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
}

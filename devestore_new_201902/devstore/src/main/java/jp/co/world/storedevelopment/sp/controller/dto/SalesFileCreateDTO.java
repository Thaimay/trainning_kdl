package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.SalesFile;
import jp.co.world.storedevelopment.model.validate.UploadFileNotEmpty;

public class SalesFileCreateDTO {
	private String division;

	private String comment;

	@UploadFileNotEmpty
	private MultipartFile file;

	private Account account;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static SalesFileCreateDTO toDTO(String json) {
		try {
			return MAPPER.readValue(json, SalesFileCreateDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	public SalesFile toModel() {
		SalesFile s = new SalesFile(getFile(), getDivision(), getAccount());
		s.setComment(this.getComment());
		return s;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

}

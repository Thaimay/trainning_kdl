package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NegotiationCreateFormDTO extends NegotiationFormDTO {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static NegotiationCreateFormDTO toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, NegotiationCreateFormDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	private List<Long> projectIds = new ArrayList<>();
	private List<Long> corporationIds = new ArrayList<>();
	private List<Long> buildingIds = new ArrayList<>();
	private List<Long> accountIds = new ArrayList<>();
	private List<Long> brandIds = new ArrayList<>();
	private List<Long> noticeIds = new ArrayList<>();
	private List<String> noticeSendTypes = new ArrayList<>();
	private List<Long> bussinessCardIds = new ArrayList<>();
	private List<NegotiationCreateFileDTO> fileInformation = new ArrayList<>();
	private NegotiationImportantInfomationCreateDTO importantInformation;

	private Optional<List<MultipartFile>> multipartFiles = Optional.empty();

	public Optional<List<MultipartFile>> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(Optional<List<MultipartFile>> files) {
		multipartFiles = files;
	}

	public void setMultipartFileList(List<MultipartFile> files) {
//		if (files.size() == 1) {
//			MultipartFile head = files.get(0);
//			if (head.isEmpty()) {
//				return;
//			}
//		}
		multipartFiles = Optional.ofNullable(files);
	}

	public void setMultipartFile(MultipartFile file) {
		if (file == null || file.getSize() == 0) {
			return;
		}
		multipartFiles = Optional.of(new ArrayList<MultipartFile>());
		multipartFiles.get().add(file);
	}

	public List<Long> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<Long> projectIds) {
		this.projectIds = projectIds;
	}

	public List<Long> getCorporationIds() {
		return corporationIds;
	}

	public void setCorporationIds(List<Long> corporationIds) {
		this.corporationIds = corporationIds;
	}

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}

	public List<Long> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Long> brandIds) {
		this.brandIds = brandIds;
	}

	public List<Long> getNoticeIds() {
		return noticeIds;
	}

	public void setNoticeIds(List<Long> noticeIds) {
		this.noticeIds = noticeIds;
	}

	public List<String> getNoticeSendTypes() {
		return noticeSendTypes;
	}

	public void setNoticeSendTypes(List<String> noticeSendTypes) {
		this.noticeSendTypes = noticeSendTypes;
	}

	public List<Long> getBuildingIds() {
		return buildingIds;
	}

	public void setBuildingIds(List<Long> buildingIds) {
		this.buildingIds = buildingIds;
	}

	public List<Long> getBussinessCardIds() {
		return bussinessCardIds;
	}

	public void setBussinessCardIds(List<Long> bussinessCardIds) {
		this.bussinessCardIds = bussinessCardIds;
	}

	public NegotiationImportantInfomationCreateDTO getImportantInformation() {
		return importantInformation;
	}

	public void setImportantInformation(NegotiationImportantInfomationCreateDTO importantInformation) {
		this.importantInformation = importantInformation;
	}

	public List<NegotiationCreateFileDTO> getFileInformation() {
		return fileInformation;
	}

	public void setFileInformation(List<NegotiationCreateFileDTO> fileInformation) {
		this.fileInformation = fileInformation;
	}

}

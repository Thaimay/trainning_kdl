package jp.co.world.storedevelopment.model;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.value.FileExtention;
import jp.co.world.storedevelopment.model.value.FileName;

public abstract class File<T> extends ActiveModel<T> {
	public static final String IMAGE_FILE_PATH = Application.resourcePath() + "image/";
	public static final String DOCUMENT_FILE_PATH = Application.resourcePath() + "document/";
	public static final String VIDEO_FILE_PATH = Application.resourcePath() + "video/";

	private Long negotiationId;
	private Long buildingId;
	private Long projectId;
	private Long shopId;
	private String name;
	private String displayName;
	private String type;
	private String division = "";
	private String comment = "";
	private Long size;
	private String originFileName;
	private String outputFlag;
	private Integer outputNumber;
	private String fileDivision;
	private MultipartFile file;

	private String[] ignoreFields = new String[] { "account", "accountName", "urlPath", "readableSize", "byTheTime",
			"file", "originFileName", "documentPath", "isDefaultImage" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public void setIgnoreFields(String[] ignoreFields) {
		this.ignoreFields = ignoreFields;
	}

	abstract protected String basePath();

	abstract protected String filePrefix();

	public File() {
		//
	}

	public File(Negotiation negotiation, MultipartFile file, Account account) {
		this(file, account);
		setNegotiationId(negotiation.getId());
	}

	public File(MultipartFile file, Account account) {
		setCreateAccount(account);
		setDisplayName(Paths.get(file.getOriginalFilename()).getFileName().toString());
		originFileName = file.getOriginalFilename();
		setSize(file.getSize());
		setType(filetype(file));
		setFile(file);
	}
	
	// Insert by QuyenLS
	public File(MultipartFile file) {
		setDisplayName(Paths.get(file.getOriginalFilename()).getFileName().toString());
		originFileName = file.getOriginalFilename();
		setSize(file.getSize());
		setType(filetype(file));
		setFile(file);
	}

	public String urlPath() {
		return "";
	}

	@Override
	public String createTableName() {
		return ActiveModelStringUtils.toSneke(File.class.getSimpleName());
	}

	public Boolean isImage() {
		return FileExtention.isImage(getFile().getOriginalFilename());
	}

	public Boolean isDocument() {
		return FileExtention.isDocument(getFile().getOriginalFilename());
	}

	public Boolean isVideo() {
		return FileExtention.isVideo(getFile().getOriginalFilename());
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getNegotiationId() {
		return negotiationId;
	}

	public void setNegotiationId(Long negotiationId) {
		this.negotiationId = negotiationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String fileName) {
		name = fileName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String fileComment) {
		comment = fileComment;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	private String filetype(MultipartFile file) {
		String type = FileExtention.getExtentionType(originFileName);
		if (StringUtils.isBlank(type)) {
			throw new IllegalStateException("拡張子が不正です:" + type);
		} else {
			return type;
		}
	}

	private String extention() {
		return FilenameUtils.getExtension(originFileName);
	}

	@Override
	public T create() {
		setName(filePrefix() + FileName.randomImageName() + "." + extention());
		return writeImage();
	}

	public String getAccountName() {
		return getAccount().getFullName();
	}

	protected T writeImage() {
		java.io.File f = new java.io.File(basePath() + getName());
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));) {
			stream.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("ファイルシステムへのの作成に失敗しました。データベースへの書き込みを中止しました。");
		}
		return super.create();
	}

	private Account getAccount() {
		return new AccountRepository().findByCode(getCreatedAccountCode())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:" + getCreatedAccountCode()));
	}

	public String getReadableSize() {
		return FileUtils.byteCountToDisplaySize(getSize());
	}

	public String getUrlPath() {
		return urlPath() + getName();
	}

	public String getDocumentPath() {
		return urlPath() + getName();
	}

	public Long calcByTheTime() {
		return Duration.between(getUpdateDatetime(), LocalDateTime.now()).toHours();
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getOutputFlag() {
		return outputFlag;
	}

	public void setOutputFlag(String outputFlag) {
		this.outputFlag = outputFlag;
	}

	public Integer getOutputNumber() {
		return outputNumber;
	}

	public void setOutputNumber(Integer outputNumber) {
		this.outputNumber = outputNumber;
	}

	/**
	 * @return the fileDivision
	 */
	public String getFileDivision() {
		return fileDivision;
	}

	/**
	 * @param fileDivision the fileDivision to set
	 */
	public void setFileDivision(String fileDivision) {
		this.fileDivision = fileDivision;
	}

}
package jp.co.world.storedevelopment.sp.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewBuilding;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectNegotiation;
import jp.co.world.storedevelopment.model.SubAccount;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;

public class NegotiationDetailDTO extends NegotiationDetailDTOSupport {

	private CreateAccountDTO account = new CreateAccountDTO();

	private String title;

	private Boolean release = false;

	private Boolean draft = false;

	private Boolean stop = false;

	private String content;

	private String purpose;

	private String nextAction;

	private int priority = 0;

	private String updateDate;

	private String implemetentionDate;

	private String scheduleDate;

	private String place;

	private String divisionValue;

	private String businessCardFree;

	private String interviewAccountFree;

	private String releaseLevelValue;

	private String companyName;
	
	private List<NegotiationCommentDTO> comments = new ArrayList<NegotiationCommentDTO>();

	private NegotiationDetailNoticeDTO noticeDTO = new NegotiationDetailNoticeDTO();

	private List<FileUploadViewDetailDTO> fileUploadList = new ArrayList<>();

	private List<NegotiationRelatedBuildingListDTO> buildings = new ArrayList<NegotiationRelatedBuildingListDTO>();

	private List<NegotiationListDTO> relatedNegotiations = new ArrayList<NegotiationListDTO>();

	private List<NegotiationFindByBuildingDTO> relatedNegotiationBuildings = new ArrayList<NegotiationFindByBuildingDTO>();
	
	private List<ProjectListDTO> relatedProjects= new ArrayList<ProjectListDTO>();

	private List<ImportantInformationListDTO> importantInformationList = new ArrayList<ImportantInformationListDTO>();

	private Boolean editable;

	private Boolean isPrintImportant;

	public NegotiationDetailDTO(Negotiation n, Account a) {
		super(n);
		Account account = new AccountRepository().findByCode(n.getUpdateAccountCode())
				.orElseThrow(() -> new IllegalStateException("存在しないアカウントコードです:"));
		List<Negotiation> relatedList = n.findRelatedNegotiaions();
		setCreateAccount(new CreateAccountDTO(account, n));
		setId(n.getId());
		setCompanyName(n.companyName());
		setTitle(n.getTitle());
		setPriority(n.getPriority());
		setContent(n.getContent());
		setUpdateDate(n.getUpdateDatetimeValue());
		setImplemetentionDate(n.getImplementationDateValue());
		setScheduleDate(n.getScheduleDateValue());
		setPlace(n.getPlace());
		setDivisionValue(n.getDivisionValue());
		setDraft(n.isDraft());
		setStop(n.isStop());
		setPurpose(n.getPurpose());
		setNextAction(n.getNextActionContent());
		setBusinessCardFree(n.getBusinessCardFree());
		setInterviewAccountFree(n.getInterviewAccountFree());
		setReleaseLevelValue(n.getReleaseLevelValue());

		setNoticeDTO(new NegotiationDetailNoticeDTO(n));
		setImportantInformationList(n, a);
		setBuildings(n);
		setCommentList(n, a);

		List<FileUploadViewDetailDTO> files = new ArrayList<>();
		n.getImages().forEach(x -> files.add(new FileUploadViewDetailDTO(x)));
		n.getFiles().forEach(x -> files.add(new FileUploadViewDetailDTO(x)));
		n.getVideos().forEach(x -> files.add(new FileUploadViewDetailDTO(x)));
		setFileUploadList(files.stream().sorted((x2, x1) -> x1.getUpdateDatetime().compareTo(x2.getUpdateDatetime()))
				.collect(Collectors.toList()));

		setRelatedNegotiationDTOList(relatedList, account);
		setRelatedNegotiationBuildings(relatedList);
		setRelatedProjects(n.getProjectNegotiations(), a);

		setEditableValue(n, a);
		setIsPrintImportantValue(a);
	}

	private void setCommentList(Negotiation n, Account a) {
		setComments(NegotiationCommentDTO.toList(n.getComments(), a));
	}

	private void setRelatedNegotiationDTOList(List<Negotiation> relateds, Account account) {
		relateds.stream().forEach(n -> relatedNegotiations.add(new NegotiationListDTO(n, account)));
	}

	public List<NegotiationRelatedBuildingListDTO> getBuildings() {
		return buildings;
	}

	public void setBuildings(Negotiation n) {
		this.buildings = n.getInterviewBuildings().stream().map(b -> {
			Building building = b.getBuilding();
			return new NegotiationRelatedBuildingListDTO(building);
		}).collect(Collectors.toList());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImplemetentionDate() {
		return implemetentionDate;
	}

	public void setImplemetentionDate(String implemetentionDate) {
		this.implemetentionDate = implemetentionDate;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public CreateAccountDTO getCreateAccount() {
		return account;
	}

	public void setCreateAccount(CreateAccountDTO account) {
		this.account = account;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getTitle() {
		return title;
	}

	public Boolean getRelease() {
		return release;
	}

	public void setRelease(Boolean release) {
		this.release = release;
	}

	public int getPriority() {
		return priority;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public NegotiationDetailNoticeDTO getNoticeDTO() {
		return noticeDTO;
	}

	public void setNoticeDTO(NegotiationDetailNoticeDTO negotiationDetailNoticeDTO) {
		noticeDTO = negotiationDetailNoticeDTO;
	}

	public void setFileUploadList(List<FileUploadViewDetailDTO> fileUploadList) {
		this.fileUploadList = fileUploadList;
	}

	public List<FileUploadViewDetailDTO> getFileUploadList() {
		return fileUploadList;
	}

	public void setComments(List<NegotiationCommentDTO> comments) {
		this.comments = comments;
	}

	public List<NegotiationCommentDTO> getComments() {
		return comments;
	}

	public List<NegotiationListDTO> getRelatedNegotiations() {
		return relatedNegotiations;
	}

	public void setRelatedNegotiations(List<NegotiationListDTO> relatedNegotiations) {
		this.relatedNegotiations = relatedNegotiations;
	}

	public String getDivisionValue() {
		return divisionValue;
	}

	public void setDivisionValue(String divisionValue) {
		this.divisionValue = divisionValue;
	}

	public List<NegotiationFindByBuildingDTO> getRelatedNegotiationBuildings() {
		return relatedNegotiationBuildings;
	}

	public void setRelatedNegotiationBuildings(List<Negotiation> negotiations) {
		negotiations.stream().forEach(n -> {
			n.getInterviewBuildings().stream().forEach(b -> {
				if (existsRelatedBuilding(b.getBuildingId())) {
					relatedNegotiationBuildings.add(new NegotiationFindByBuildingDTO(b.getBuilding()));
				}
			});
		});
	}
	
	

	public List<ProjectListDTO> getRelatedProjects() {
		return relatedProjects;
	}

	public void setRelatedProjects(List<ProjectNegotiation> projectNegotiations, Account a) {
		projectNegotiations.forEach(x -> {
			Optional<Project> opt = new ProjectRepository().findById(x.getProjectId());
			if(opt.isPresent()) {
				relatedProjects.add(new ProjectListDTO(opt.get(), a));
			}
		});
	}

	private Boolean existsRelatedBuilding(Long id) {
		return relatedNegotiationBuildings.stream().noneMatch(b -> b.getId().equals(id));
	}

	public List<ImportantInformationListDTO> getImportantInformationList() {
		return importantInformationList;
	}

	public void setImportantInformationList(Negotiation n, Account a) {
		importantInformationList = n.getImportantInformations().stream().map(i -> {
			return new ImportantInformationListDTO(i, a);
		}).collect(Collectors.toList());
	}

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	public Boolean getStop() {
		return stop;
	}

	public void setStop(Boolean stop) {
		this.stop = stop;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public void setEditableValue(Negotiation n, Account a) {
		List<Long> accountIds = new NegotiationInterviewAccountRepository().findByNegotiation(n).stream().map(na -> {
			return na.getAccountId();
		}).collect(Collectors.toList());

		Boolean containInterviewAccountId = accountIds.contains(a.getId());
		Boolean isSameCreateAccount = n.getCreatedAccountCode().equals(a.getEmployeCode());

		Boolean isSameArea = false;

		if (a.getSubAccount().isPresent()) {
			SubAccount subAccount = a.getSubAccount().get();

			for (NegotiationInterviewBuilding nib : n.getInterviewBuildings()) {
				Building building = nib.getBuilding();
				if (building != null && building.getiAreaId() != null) {
					Optional<IArea> area = new IAreaRepository().findById(building.getiAreaId());
					if (area.isPresent() && subAccount.hasAreaCode(area.get().getAreaCd())) {
						isSameArea = true;
						break;
					}
				}
			}
		}

		setEditable(containInterviewAccountId || isSameCreateAccount
				|| a.getRole().hasPermission("NEGOTIATION_EDITABLE") || isSameArea);
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public String getBusinessCardFree() {
		return businessCardFree;
	}

	public void setBusinessCardFree(String businessCardFree) {
		this.businessCardFree = businessCardFree;
	}

	public String getInterviewAccountFree() {
		return interviewAccountFree;
	}

	public void setInterviewAccountFree(String interviewAccountFree) {
		this.interviewAccountFree = interviewAccountFree;
	}

	public String getReleaseLevelValue() {
		return releaseLevelValue;
	}

	public void setReleaseLevelValue(String releaseLevelValue) {
		this.releaseLevelValue = releaseLevelValue;
	}

	public Boolean getIsPrintImportant() {
		return isPrintImportant;
	}

	public void setIsPrintImportant(Boolean isPrintImportant) {
		this.isPrintImportant = isPrintImportant;
	}

	public void setIsPrintImportantValue(Account a) {
		this.setIsPrintImportant(a.getRole().hasPermission("IMPORTANT_INFORMATION")
				|| a.getRole().hasPermission("IMPORTANT_INFORMATION_EDITABLE"));
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

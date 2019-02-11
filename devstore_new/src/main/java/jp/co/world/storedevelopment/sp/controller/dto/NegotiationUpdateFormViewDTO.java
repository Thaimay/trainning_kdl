package jp.co.world.storedevelopment.sp.controller.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICompany;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;
import jp.co.world.storedevelopment.model.NegotiationInterviewBusinessCard;
import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;

public class NegotiationUpdateFormViewDTO extends NegotiationUpdateFormDTO {
	protected static final ObjectMapper MAPPER = new ObjectMapper();
	private List<ValueDTO> projectValues = new ArrayList<ValueDTO>();
	private List<ValueDTO> corporationValues = new ArrayList<ValueDTO>();
	private List<ValueDTO> accountValues = new ArrayList<ValueDTO>();
	private List<ValueDTO> brandValues = new ArrayList<ValueDTO>();
	private List<NoticeDTO> noticeValues = new ArrayList<NoticeDTO>();
	private List<ValueDTO> buildingValues = new ArrayList<ValueDTO>();

	private List<ValueDTO> bussinesCardValues = new ArrayList<ValueDTO>();
	private List<ValueDTO> bussinessCardUnmanagedValeus = new ArrayList<ValueDTO>();
	private List<ValueDTO> accountUnmanagedValues = new ArrayList<ValueDTO>();

	private NegotiationImportantFormViewDTO important = new NegotiationImportantFormViewDTO();

	private List<FileUploadViewUpdateDTO> files = new ArrayList<>();
	private List<SelectOptionDTO> companySelect = new ArrayList<>();
	private Boolean canEditCompany;

	private Boolean canEditImportant;
	private Boolean draft;
	private String companyName;

	public NegotiationUpdateFormViewDTO() {
		//
	}

	public static NegotiationUpdateFormViewDTO toDTO(String json) {
		try {
			MAPPER.registerModule(new JavaTimeModule());
			return MAPPER.readValue(json, NegotiationUpdateFormViewDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	public NegotiationUpdateFormViewDTO(Negotiation n) {
		copyProperties(this, n);
		setCompanyCd(n.getCreatedCompanyCd());
		setProjectValuesDTO(n);
		setCorporationValuesDTO(n);
		setBuildingValuesDTO(n);
		setNoticeValuesDTO(n);
		setBrandValuesDTO(n);
		setFilesDTO(n);

		List<NegotiationInterviewBusinessCard> list = n.getInterviewBussinessCards();
		setBusinessCardValuesDTO(list);
		setNoticeAccountValuesDTO(n.getNoticeAccounts());
		setBussinessCardUnmanagedValeusDTO(list);

		List<NegotiationInterviewAccount> niaList = n.getInterviewAccounts();
		setAccountValuesDTO(niaList);
		setAccountUnmanagedValuesDTO(niaList);

		List<ImportantInformation> iList = n.getImportantInformations();
		if (iList.size() > 0) {
			setImportant(new NegotiationImportantFormViewDTO(iList.get(0)));
		}
	}
	
	public NegotiationUpdateFormViewDTO(Negotiation n, Account a) {
		this(n);
		setCompanySelect(companyListValue(n, a));
		setCanEditCompany(n.sameCreatedAccount(a));
	}

	
	private List<SelectOptionDTO> companyListValue(Negotiation n, Account a) {
		if (n.sameCreatedAccount(a)) {
			return a.companyList().stream().map(c -> {
				return new SelectOptionDTO(c.getCompanyKanjiName(), c.getCompanyCode());
			}).collect(Collectors.toList());
		} else {
			Optional<ICompany> c = n.company();
			
			if (c.isPresent()) {
				return Arrays.asList(
						new SelectOptionDTO(c.get().getCompanyKanjiName(), c.get().getCompanyCode()));
			} else {
				return new ArrayList<>();
			}
		}
	}

	private void setFilesDTO(Negotiation n) {
		List<FileUploadViewUpdateDTO> list = new ArrayList<>();
		n.getImages().forEach(x -> {
			list.add(new FileUploadViewUpdateDTO(x));
		});
		n.getFiles().forEach(x -> {
			list.add(new FileUploadViewUpdateDTO(x));
		});
		n.getVideos().forEach(x -> {
			list.add(new FileUploadViewUpdateDTO(x));
		});
		setFiles(list.stream().sorted((x1, x2) -> x1.getUpdateDatetime().compareTo(x2.getUpdateDatetime()))
				.collect(Collectors.toList()));
	}

	private void setNoticeValuesDTO(Negotiation n) {
		List<NoticeDTO> list = n.getNoticeAccounts().stream().map(na -> {
			Account a = na.getAccount();
			return new NoticeDTO(a.getId(), a.getFullName(), na.getSendType());
		}).collect(Collectors.toList());
		setNoticeValues(list);
	}

	private void setProjectValuesDTO(Negotiation n) {
		List<ValueDTO> list = n.getProjectNegotiations().stream().map(pn -> {
			Project c = pn.getProject();
			return new ValueDTO(c.getId(), c.getTitle());
		}).collect(Collectors.toList());
		setProjectValues(list);
	}

	private void setCorporationValuesDTO(Negotiation n) {
		List<ValueDTO> list = n.getInterviewCorporations().stream().map(ic -> {
			ICorporation c = ic.getiCorporation();
			return new ValueDTO(c.getId(), c.getCorporationName());
		}).collect(Collectors.toList());
		setCorporationValues(list);
	}

	private void setAccountValuesDTO(List<NegotiationInterviewAccount> list) {
		List<ValueDTO> result = list.stream().filter(ic -> ic.isUnmanaged() == false).map(ic -> {
			return new ValueDTO(ic.getAccountId(), ic.getAccountName());
		}).collect(Collectors.toList());
		setAccountValues(result);
	}

	private void setNoticeAccountValuesDTO(List<NegotiationNoticeAccount> list) {
		List<NoticeDTO> result = list.stream().map(n -> {
			return new NoticeDTO(n.getAccountId(), n.getAccountName(), n.getSendType());
		}).collect(Collectors.toList());
		setNoticeValues(result);
	}

	private void setBuildingValuesDTO(Negotiation n) {
		List<ValueDTO> list = n.getInterviewBuildings().stream().map(ic -> {
			Building c = ic.getBuilding();
			return new ValueDTO(c.getId(), c.getName());
		}).collect(Collectors.toList());
		setBuildingValues(list);
	}

	private void setBrandValuesDTO(Negotiation n) {
		List<ValueDTO> list = n.getInterviewBrands().stream().map(br -> {
			IBrandIncomeUnit b = br.getIBrandIncomeUnit();
			return new ValueDTO(b.getId(), b.getBrandIncomeUnitName());
		}).collect(Collectors.toList());
		setBrandValues(list);
	}

	private void setBusinessCardValuesDTO(List<NegotiationInterviewBusinessCard> list) {
		List<ValueDTO> result = list.stream().filter(bc -> bc.isUnmanaged() == false).map(bc -> {
			IBusinessCard b = new IBusinessCardRepository().findById(bc.getBusinessCardId()).orElseGet(() -> {
				throw new IllegalStateException("存在しない面談者です:" + bc.getBusinessCardId().toString());
			});

			return new ValueDTO(bc.getBusinessCardId(),
					String.format("%s_%s_%s", b.getName(), b.getCompanyName(), b.getPositionName()));
		}).collect(Collectors.toList());
		setBussinesCardValues(result);
	}

	private void setBussinessCardUnmanagedValeusDTO(List<NegotiationInterviewBusinessCard> list) {
		List<ValueDTO> result = list.stream().filter(bc -> bc.isUnmanaged()).map(bc -> {
			return new ValueDTO(bc.getBusinessCardId(), bc.getBusinessCardName());
		}).collect(Collectors.toList());
		setBussinessCardUnmanagedValeus(result);
	}

	private void setAccountUnmanagedValuesDTO(List<NegotiationInterviewAccount> list) {
		List<ValueDTO> result = list.stream().filter(a -> a.isUnmanaged()).map(a -> {
			return new ValueDTO(a.getAccountId(), a.getAccountName());
		}).collect(Collectors.toList());
		setAccountUnmanagedValues(result);
	}

	public Boolean getCanEditCompany() {
		return canEditCompany;
	}

	public void setCanEditCompany(Boolean canEditCompany) {
		this.canEditCompany = canEditCompany;
	}

	public List<SelectOptionDTO> getCompanySelect() {
		return companySelect;
	}

	public void setCompanySelect(List<SelectOptionDTO> companySelect) {
		this.companySelect = companySelect;
	}

	public List<ValueDTO> getProjectValues() {
		return projectValues;
	}

	public void setProjectValues(List<ValueDTO> projectValues) {
		this.projectValues = projectValues;
	}

	public List<ValueDTO> getCorporationValues() {
		return corporationValues;
	}

	public void setCorporationValues(List<ValueDTO> corporationIds) {
		corporationValues = corporationIds;
	}

	public List<ValueDTO> getAccountValues() {
		return accountValues;
	}

	public void setAccountValues(List<ValueDTO> accountIds) {
		accountValues = accountIds;
	}

	public List<ValueDTO> getBrandValues() {
		return brandValues;
	}

	public void setBrandValues(List<ValueDTO> brandIds) {
		brandValues = brandIds;
	}

	public List<NoticeDTO> getNoticeValues() {
		return noticeValues;
	}

	public void setNoticeValues(List<NoticeDTO> noticeValues) {
		this.noticeValues = noticeValues;
	}

	public List<ValueDTO> getBuildingValues() {
		return buildingValues;
	}

	public void setBuildingValues(List<ValueDTO> buildingIds) {
		buildingValues = buildingIds;
	}

	public List<ValueDTO> getBussinesCardValues() {
		return bussinesCardValues;
	}

	public void setBussinesCardValues(List<ValueDTO> bussinesCardValues) {
		this.bussinesCardValues = bussinesCardValues;
	}

	public List<ValueDTO> getBussinessCardUnmanagedValeus() {
		return bussinessCardUnmanagedValeus;
	}

	public void setBussinessCardUnmanagedValeus(List<ValueDTO> bussinessCardUnmanagedValeus) {
		this.bussinessCardUnmanagedValeus = bussinessCardUnmanagedValeus;
	}

	public List<ValueDTO> getAccountUnmanagedValues() {
		return accountUnmanagedValues;
	}

	public void setAccountUnmanagedValues(List<ValueDTO> accountUnmanagedValues) {
		this.accountUnmanagedValues = accountUnmanagedValues;
	}

	public NegotiationImportantFormViewDTO getImportant() {
		return important;
	}

	public void setImportant(NegotiationImportantFormViewDTO important) {
		this.important = important;
	}

	public List<FileUploadViewUpdateDTO> getFiles() {
		return files;
	}

	public void setFiles(List<FileUploadViewUpdateDTO> files) {
		this.files = files;
	}

	public Boolean getCanEditImportant() {
		return canEditImportant;
	}

	public void setCanEditImportant(Boolean canEditImportant) {
		this.canEditImportant = canEditImportant;
	}

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

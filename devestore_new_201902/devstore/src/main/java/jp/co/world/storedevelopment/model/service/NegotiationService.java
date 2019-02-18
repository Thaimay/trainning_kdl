package jp.co.world.storedevelopment.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.BuildingFile;
import jp.co.world.storedevelopment.model.BuildingImage;
import jp.co.world.storedevelopment.model.IBrandIncomeUnit;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.ICorporation;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.ImportantInformationBuilding;
import jp.co.world.storedevelopment.model.ImportantInformationCorporation;
import jp.co.world.storedevelopment.model.Mail;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationFile;
import jp.co.world.storedevelopment.model.NegotiationImage;
import jp.co.world.storedevelopment.model.NegotiationInterviewAccount;
import jp.co.world.storedevelopment.model.NegotiationInterviewBrand;
import jp.co.world.storedevelopment.model.NegotiationInterviewBuilding;
import jp.co.world.storedevelopment.model.NegotiationInterviewBusinessCard;
import jp.co.world.storedevelopment.model.NegotiationInterviewCorporation;
import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;
import jp.co.world.storedevelopment.model.NegotiationVideo;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.ProjectImage;
import jp.co.world.storedevelopment.model.ProjectNegotiation;
import jp.co.world.storedevelopment.model.RelatedTask;
import jp.co.world.storedevelopment.model.SendReserve;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopFile;
import jp.co.world.storedevelopment.model.ShopImage;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBrandIncomeUnitRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ICorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationNegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBrandRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBusinessCardRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewCorporationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationNoticeAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationOpenAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationVideoRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectNegotiationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ShopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.TodoRepository;
import jp.co.world.storedevelopment.model.value.FileExtention;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCompareDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFileDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationCreateFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationImportantInfomationCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationUpdateFormDTO;
import jp.co.world.storedevelopment.utils.JsonParseUtils;

public class NegotiationService {
	public void updateAll(Negotiation n, NegotiationUpdateFormDTO dto, Account account) {
		resetOpenNegotiation(n.getId(), dto);
		commonUpdate(n, dto, account);
		updateNoticeMail(n, dto, account);
	}

	public void updateNoSendMail(Negotiation n, NegotiationUpdateFormDTO dto, Account account) {
		resetOpenNegotiation(n.getId(), dto);
		commonUpdate(n, dto, account);
	}

	public Negotiation createAll(Negotiation n, NegotiationCreateFormDTO dto, Account account) {
		Negotiation negotiation = createCommon(n, dto, account);
		createNoticeMail(negotiation, dto, account);
		return negotiation;
	}

	public Negotiation createNoSendMail(Negotiation n, NegotiationCreateFormDTO dto, Account account) {
		Negotiation negotiation = createCommon(n, dto, account);
		return negotiation;
	}

	public Negotiation saveTemporary(Negotiation n, NegotiationCreateFormDTO dto, Account account) {
		n.setDraft(true);
		return createCommon(n, dto, account);
	}

	private void resetOpenNegotiation(Long id, NegotiationUpdateFormDTO updateDTO) {
		Optional<Negotiation> opt = new NegotiationRepository().findById(id);
		if (opt.isPresent()) {
			Negotiation n = opt.get();
			NegotiationCompareDTO currData = new NegotiationCompareDTO(n);
			NegotiationCompareDTO updData = new NegotiationCompareDTO(updateDTO);

			if (!currData.isSame(updData)) {
				new NegotiationOpenAccountRepository().findByNegotiation(n).forEach(x -> x.delete());
			}
		}
	}

	private void commonUpdate(Negotiation n, NegotiationUpdateFormDTO dto, Account account) {
		n.setUpdateAccountCode(account.getEmployeCode());
		n.setUpdateDatetime(LocalDateTime.now());
		n.setCreatedCompanyCd(dto.getCompanyCd());
		n.update();
		deleteRelatedModels(n);
		deleteImages(n, dto);
		deleteFiles(n, dto);
		deleteVideos(n, dto);
		deleteTodo(n);
		updateImages(n, dto.getMultipartFiles(), account, dto);
		updateRelatedModels(n, dto, account);
		createRelatedKeyman(dto);
		createPushNotification(n, dto, account);
	}

	private Negotiation createCommon(Negotiation n, NegotiationCreateFormDTO dto, Account account) {

		n.setCreatedAccountCode(account.getEmployeCode());
		n.setUpdateAccountCode(account.getEmployeCode());
		n.setCreatedDatetime(LocalDateTime.now());
		n.setUpdateDatetime(LocalDateTime.now());
		n.setCreatedCompanyCd(dto.getCompanyCd());

		Negotiation newNecotiation = n.create();
		createRelatedModels(newNecotiation, dto, account);
		updateImages(newNecotiation, dto.getMultipartFiles(), account, dto);
		createUpdateInformations(n, account);
		createRelatedKeyman(dto);
		createPushNotification(n, dto, account);
		return newNecotiation;
	}

	private void createPushNotification(Negotiation n, NegotiationCreateFormDTO dto, Account account) {
		if (n.hasImplementationDatetime() == false && dto.getAccountIds().isEmpty() == false && n.isDraft() == false) {
			SendReserve reserve = new SendReserve();
			reserve.setEmployeeCdList(notificationEmployeeCodes(dto, account));
			reserve.setMessage(String.format("商談予定が入りました\n%s %s", n.getTitle(), n.getScheduleDateValue()));
			reserve.setInitializeStatus();
			reserve.setCreateAccount(account);
			reserve.create();
		}
	}

	private void deleteTodo(Negotiation n) {
		if (n.hasImplementationDatetime() || n.isStop()) {
			new TodoRepository().deleteByNegotiation(n);
		}
	}

	private String notificationEmployeeCodes(NegotiationCreateFormDTO dto, Account account) {
		List<String> interviews = dto.getAccountIds().stream().map(id -> {
			return new AccountRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException("存在しないアカウントです");
			}).getEmployeCode();
		}).collect(Collectors.toList());

		return String.join(",", interviews);
	}

	private void createRelatedKeyman(NegotiationCreateFormDTO dto) {
		dto.getBuildingIds().stream().forEach(buildingId -> {
			Building b = new BuildingRepository().findById(buildingId).orElseThrow(() -> {
				throw new IllegalStateException("存在しない館です:" + buildingId.toString());
			});
			dto.getBussinessCardIds().stream().forEach(cardId -> {
				IBusinessCard card = new IBusinessCardRepository().findById(cardId).orElseThrow(() -> {
					throw new IllegalStateException("存在しない館です:" + buildingId.toString());
				});

				b.addKeyman(card);
			});
		});
	}

	private void createNoticeMail(Negotiation n, NegotiationCreateFormDTO dto, Account account) {
		List<Long> noticeIds = dto.getNoticeIds();
		List<String> sendTypes = dto.getNoticeSendTypes();
		List<String> mails = new ArrayList<>();
		List<String> mailCcs = new ArrayList<>();

		IntStream.range(0, noticeIds.size()).forEach(i -> {
			Account a = new AccountRepository().findById(noticeIds.get(i)).orElseGet(() -> {
				throw new IllegalStateException("存在しないアカウントです:" + noticeIds.get(i).toString());
			});

			if (sendTypes.get(i).equals("TO")) {
				mails.add(a.getMailAddress());
			} else {
				mailCcs.add(a.getMailAddress());
			}
		});

		Mail mail = new Mail();
		mail.setCreateNegotiation(n, account);
		mail.setMailAddress(String.join(",", mails));
		mail.setMailAddressCc(String.join(",", mailCcs));
		mail.create();
	}

	private void updateNoticeMail(Negotiation n, NegotiationCreateFormDTO dto, Account account) {
		List<Long> noticeIds = dto.getNoticeIds();
		List<String> sendTypes = dto.getNoticeSendTypes();
		List<String> mails = new ArrayList<>();
		List<String> mailCcs = new ArrayList<>();

		IntStream.range(0, noticeIds.size()).forEach(i -> {
			Account a = new AccountRepository().findById(noticeIds.get(i)).orElseGet(() -> {
				throw new IllegalStateException("存在しないアカウントです:" + noticeIds.get(i).toString());
			});

			if (sendTypes.get(i).equals("TO")) {
				mails.add(a.getMailAddress());
			} else {
				mailCcs.add(a.getMailAddress());
			}
		});

		Mail mail = new Mail();
		mail.setUpdateNegotiation(n, account);
		mail.setMailAddress(String.join(",", mails));
		mail.setMailAddressCc(String.join(",", mailCcs));
		mail.create();
	}

	private void createUpdateInformations(Negotiation n, Account myself) {
		if (n.getScheduleStartDatetime() != null && n.getImplementationStartDatetime() == null) {
			n.getInterviewAccounts().stream().filter(a -> a.isNotSame(myself)).forEach(a -> {
				Account account = a.findAccount();
				RelatedTask task = new RelatedTask(account);
				task.setNegotiationId(n.getId());
				task.setDivision("NEGOTIATION");
				task.setContent(n.getTitle());
				task.create();
			});
		}
	}

	private void deleteImages(Negotiation n, NegotiationCreateFormDTO dto) {
		List<NegotiationImage> list = new NegotiationImageRepository().findByNegotiation(n);
		list.stream().forEach(image -> {
			Boolean exists = dto.getFileInformation().stream().filter(f -> image.getId().equals(f.getId()))
					.collect(Collectors.toList()).size() > 0;
			if (!exists) {
				image.delete();
			}
		});
	}

	private void deleteFiles(Negotiation n, NegotiationCreateFormDTO dto) {
		List<NegotiationFile> list = new NegotiationFileRepository().findByNegotiation(n);
		list.stream().forEach(file -> {
			Boolean exists = dto.getFileInformation().stream().filter(f -> file.getId().equals(f.getId()))
					.collect(Collectors.toList()).size() > 0;
			if (!exists) {
				file.delete();
			}
		});
	}

	private void deleteVideos(Negotiation n, NegotiationCreateFormDTO dto) {
		List<NegotiationVideo> list = new NegotiationVideoRepository().findByNegotiation(n);
		list.stream().forEach(video -> {
			Boolean exists = dto.getFileInformation().stream().filter(v -> video.getId().equals(v.getId()))
					.collect(Collectors.toList()).size() > 0;
			if (!exists) {
				video.delete();
			}
		});
	}

	private void updateImages(Negotiation newNecotiation, Optional<List<MultipartFile>> multipartFiles, Account account,
			NegotiationCreateFormDTO dto) {
		List<NegotiationCreateFileDTO> informations = dto.getFileInformation();
		for (NegotiationCreateFileDTO item : informations) {
			if (item.getId() != null) {
				NegotiationImage image = new NegotiationImageRepository().findById(item.getId()).orElseGet(() -> {
					throw new IllegalArgumentException("存在しないファイルです");
				});
				if (image!= null && (!image.getDivision().equals(item.getDivision()) || !image.getComment().equals(item.getComment()))) {
					image.setDivision(item.getDivision());
					image.setFileDivision(item.getFileDivision());
					image.setComment(item.getComment());
					image.setUpdateAccount(account);
					image.update();

					updateCopyImageBSP(image, item, account);
				}

				NegotiationFile file = new NegotiationFileRepository().findById(item.getId()).orElseGet(() -> {
					throw new IllegalArgumentException("存在しないファイルです");
				});
				if (file!= null && (!file.getDivision().equals(file.getDivision()) || !file.getComment().equals(item.getComment()))) {
					file.setDivision(item.getDivision());
					file.setFileDivision(item.getFileDivision());
					file.setComment(item.getComment());
					file.setUpdateAccount(account);
					file.update();

					updateCopyFileBSP(file, item, account);
				}

				NegotiationVideo video = new NegotiationVideoRepository().findById(item.getId()).orElseGet(() -> {
					throw new IllegalArgumentException("存在しないファイルです");
				});
				if (video!= null && (!video.getDivision().equals(video.getDivision()) || !video.getComment().equals(video.getComment()))) {
					video.setDivision(item.getDivision());
					video.setFileDivision(item.getFileDivision());
					video.setComment(item.getComment());
					video.setUpdateAccount(account);
					video.update();
				}
			}
		}

		if (multipartFiles.isPresent()) {
			List<MultipartFile> files = multipartFiles.get();
			for (int i = 0; i < files.size(); i++) {
				updateImage(files.get(i), informations.get(i), newNecotiation, account);
			}
		}
	}

	private void updateImage(MultipartFile file, NegotiationCreateFileDTO dto, Negotiation n, Account a) {
		if (file != null) {
			if (FileExtention.isImage(file.getOriginalFilename())) {
				if (dto.unregistered()) {
					NegotiationImage image = new NegotiationImage(file, n, a);
					image.setDivision(dto.getDivision());
					image.setComment(dto.getComment());
					image.setFileDivision(dto.getFileDivision());
					image.create();

					createCopyImageBSP(file, dto, a);
				}
			} else if (FileExtention.isDocument(file.getOriginalFilename())) {
				if (dto.unregistered()) {
					NegotiationFile f = new NegotiationFile(file, n, a);
					f.setDivision(dto.getDivision());
					f.setComment(dto.getComment());
					f.setFileDivision(dto.getFileDivision());
					f.create();

					createCopyFileBSP(file, dto, a);
				}
			} else if (FileExtention.isVideo(file.getOriginalFilename())) {
				if (dto.unregistered()) {
					NegotiationVideo video = new NegotiationVideo(file, n, a);
					video.setDivision(dto.getDivision());
					video.setComment(dto.getComment());
					video.create();
				}
			}
		}
	}

	public Negotiation createAll(Account account) {
		return createAll(new NegotiationCreateFormDTO(), account);
	}

	public Negotiation createAll(NegotiationCreateFormDTO dto, Account account) {
		return this.createAll(new Negotiation(), dto, account);
	}

	private void deleteRelatedModels(Negotiation n) {
		new ProjectNegotiationRepository().deleteByNegotiation(n);
		new NegotiationInterviewCorporationRepository().deleteByNegotiation(n);
		new NegotiationInterviewAccountRepository().deleteByNegotiation(n);
		new NegotiationInterviewBuildingRepository().deleteByNegotiation(n);
		new NegotiationNoticeAccountRepository().deleteByNegotiation(n);
		new NegotiationInterviewBusinessCardRepository().deleteByNegotiation(n);
		new NegotiationInterviewBrandRepository().deleteByNegotiation(n);
	}

	private void createRelatedModels(Negotiation n, NegotiationCreateFormDTO dto, Account account) {
		newCreateRecord(n);
		updateRelatedModels(n, dto, account);
	}

	private void updateRelatedModels(Negotiation n, NegotiationCreateFormDTO dto, Account account) {
		newCreateImportantNotice(n, dto.getImportantInformation(), account);
		newCreateInterviewAccount(n, dto.getAccountIds());
		newCreateProjectNegotiation(n, dto.getProjectIds());
		newCreateInterviewCorporation(n, dto.getCorporationIds());
		newCreateInterviewBulding(n, dto.getBuildingIds());
		newCreateInterviewBrand(n, dto.getBrandIds());
		newCreateNoticeAccount(n, dto.getNoticeIds(), dto.getNoticeSendTypes());
		newCreateBusinessCard(n, dto.getBussinessCardIds());
	}

	private void newCreateImportantNotice(Negotiation n, NegotiationImportantInfomationCreateDTO dto, Account account) {
		List<ImportantInformation> iList = new ImportantInformationRepository().findByNegotiation(n);

		if (dto != null) {

			if (iList.isEmpty()) {
				createImportantInformation(n, dto, account);
			} else {
				updateImportantInformation(iList.get(0), n, dto, account);
			}
		} else {
			new ImportantInformationNegotiationRepository().deleteByNegotiationId(n.getId());

			iList.forEach(x -> new ImportantInformationRepository().deleteById(x.getId()));
		}
	}

	private void createImportantInformation(Negotiation n, NegotiationImportantInfomationCreateDTO dto,
			Account account) {
		ImportantInformation model = new ImportantInformation();
		model.setShowStartDatetime(LocalDateTime.now());
		model.setShowEndDatetime(dto.getShowEndDate());
		model.setContent(dto.getContent());
		model.setCreateAccount(account);
		model.create();
		model.addRelatedNegotiation(n);
		importantRelatedUpdate(model, dto);
		if (n.isDraft() == false) {
			createImportantSendReserve(dto, account);
		}
	}

	private void updateImportantInformation(ImportantInformation model, Negotiation n,
			NegotiationImportantInfomationCreateDTO dto, Account account) {
		model.setShowEndDatetime(dto.getShowEndDate());
		model.setContent(dto.getContent());
		model.setCreateAccount(account);
		model.update();
		new ImportantInformationBuildingRepository().deleteBy(model);
		new ImportantInformationCorporationRepository().deleteBy(model);
		importantRelatedUpdate(model, dto);
		createImportantSendReserve(dto, account);
	}

	private void createImportantSendReserve(NegotiationImportantInfomationCreateDTO dto, Account account) {
		SendReserve model = new SendReserve();
		model.setEmployeeCodeListValue(new AccountRepository().findAll());
		model.setMessage("【重要情報】\n" + pushNotificationCorporationNames(dto) + pushNotificationBuildingNames(dto)
				+ dto.getContent());
		model.setInitializeStatus();
		model.setCreateAccount(account);
		model.create();
	}

	private String pushNotificationCorporationNames(NegotiationImportantInfomationCreateDTO dto) {
		String corporationNames = dto.getCorporationIds().stream().map(i -> {
			return new ICorporationRepository().findById(i).orElseGet(() -> {
				throw new IllegalStateException("存在しない法人です:" + i.toString());
			}).getCorporationName();
		}).collect(Collectors.joining(" "));
		String unmanagedNames = dto.getCorporationUnmanagedNames().stream().collect(Collectors.joining(" "));

		if (corporationNames.isEmpty() && unmanagedNames.isEmpty()) {
			return "";
		} else {
			return String.format("法人名:%s %s\n", corporationNames, unmanagedNames);
		}
	}

	private String pushNotificationBuildingNames(NegotiationImportantInfomationCreateDTO dto) {
		String buildingNames = dto.getBuildingIds().stream().map(i -> {
			return new BuildingRepository().findById(i).orElseGet(() -> {
				throw new IllegalStateException("存在しない館です:" + i.toString());
			}).getName();
		}).collect(Collectors.joining(" "));
		String unmanagedNames = dto.getBuildingUnmanagedNames().stream().collect(Collectors.joining(" "));

		if (buildingNames.isEmpty()) {
			return "";
		} else {
			return String.format("館名:%s %s\n", buildingNames, unmanagedNames);
		}
	}

	private void importantRelatedUpdate(ImportantInformation model, NegotiationImportantInfomationCreateDTO dto) {
		dto.getBuildingIds().stream().forEach(id -> {
			Building building = new BuildingRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException("存在しない館です");
			});
			new ImportantInformationBuilding(model, building).create();
		});
		dto.getCorporationIds().stream().forEach(id -> {
			ICorporation c = new ICorporationRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException("存在しない法人です");
			});
			new ImportantInformationCorporation(model, c).create();
		});
		dto.getBuildingUnmanagedNames().stream()
				.forEach(name -> new ImportantInformationBuilding(model, name).create());
		dto.getCorporationUnmanagedNames().stream()
				.forEach(name -> new ImportantInformationCorporation(model, name).create());
	}

	private void newCreateRecord(Negotiation n) {
		// 件数が多い場合があるのでDBの中で実施すべき??(select insert 18/02/01) matsushita
		List<Account> accounts = new AccountRepository().findAll();
		accounts.stream().forEach(a -> {
			// n.addAccessRecord(a);
		});
	}

	private void newCreateUnmanagedInteviewAccount(Negotiation n, List<String> values) {
		values.stream().forEach(v -> {
			new NegotiationInterviewAccount(n, v).create();
		});
	}

	private void newCreateUnmanagedBusinessCard(Negotiation n, List<String> values) {
		values.stream().forEach(v -> {
			new NegotiationInterviewBusinessCard(n, v).create();
		});
	}

	private void newCreateInterviewAccount(Negotiation n, List<Long> values) {
		values.stream().forEach(v -> {
			Account inteviewer = new AccountRepository().findById(v).orElseGet(() -> {
				throw new IllegalArgumentException("存在しないアカウントです:id = " + v);
			});
			new NegotiationInterviewAccount(n, inteviewer).create();
		});
	}

	private void newCreateProjectNegotiation(Negotiation n, List<Long> values) {
		values.stream().forEach(v -> {
			Project project = new ProjectRepository().findById(v).orElseGet(() -> {
				throw new IllegalArgumentException("存在しない案件です:id = " + v);
			});
			
			String jsonCurrent = JsonParseUtils.parse(project);
			
			project.registerProjectNegotiation();
			if (project.getiSalesChannelId() != null) {
				project.registerActionStatus(project.getiSalesChannelId());
			}
			
			String jsonChange = JsonParseUtils.parse(project);
			
			// update when data change
			if(!jsonCurrent.equals(jsonChange)) {
				// create project history
				new ProjectHistoryRepository().updateProjectHistory(project);
				
				// update project
				project.setUpdateDatetime(LocalDateTime.now());
				project.update();
			}
			
			new ProjectNegotiation(n, project).create();
		});
	}

	private void newCreateInterviewCorporation(Negotiation n, List<Long> values) {
		values.stream().forEach(v -> {
			ICorporation corporation = new ICorporationRepository().findById(v).orElseGet(() -> {
				throw new IllegalArgumentException("存在しない法人です:id = " + v);
			});
			new NegotiationInterviewCorporation(n, corporation).create();
		});
	}

	private void newCreateInterviewBulding(Negotiation n, List<Long> values) {
		values.stream().forEach(v -> {
			Building building = new BuildingRepository().findById(v).orElseGet(() -> {
				throw new IllegalArgumentException("存在しない法人です:id = " + v);
			});
			new NegotiationInterviewBuilding(n, building).create();
		});
	}

	private void newCreateInterviewBrand(Negotiation n, List<Long> values) {
		values.stream().forEach(v -> {
			IBrandIncomeUnit brand = new IBrandIncomeUnitRepository().findById(v).orElseGet(() -> {
				throw new IllegalArgumentException("存在しない法人です:id = " + v);
			});
			new NegotiationInterviewBrand(n, brand).create();
		});
	}

	private void newCreateNoticeAccount(Negotiation n, List<Long> values, List<String> sendTypes) {
		IntStream.range(0, values.size()).forEach(i -> {
			Account inteviewer = new AccountRepository().findById(values.get(i)).orElseGet(() -> {
				throw new IllegalArgumentException("存在しないアカウントです:id = " + values.get(i));
			});
			new NegotiationNoticeAccount(n, inteviewer, sendTypes.get(i)).create();
		});
	}

	private void newCreateBusinessCard(Negotiation n, List<Long> values) {
		values.stream().forEach(v -> {
			IBusinessCard businessCard = new IBusinessCardRepository().findById(v).orElseGet(() -> {
				throw new IllegalArgumentException("存在しない面談者です:id = " + v);
			});
			new NegotiationInterviewBusinessCard(n, businessCard).create();
		});
	}

	private void updateCopyImageBSP(NegotiationImage image, NegotiationCreateFileDTO dto, Account a) {
		Path path = Paths.get(Application.resourcePath() + image.getUrlPath());
		InputStream is;
		try {
			is = Files.newInputStream(path);
			MultipartFile file = new MockMultipartFile(image.getName(), image.getName(), null, is);
			createCopyImageBSP(file, dto, a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateCopyFileBSP(NegotiationFile file, NegotiationCreateFileDTO dto, Account a) {
		Path path = Paths.get(Application.resourcePath() + file.getUrlPath());
		InputStream is;
		try {
			is = Files.newInputStream(path);
			MultipartFile mfile = new MockMultipartFile(file.getName(), file.getName(), null, is);
			createCopyFileBSP(mfile, dto, a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createCopyFileBSP(MultipartFile file, NegotiationCreateFileDTO dto, Account a) {
		if (dto.getBuildingIds().size() > 0) {
			for (Long item : dto.getBuildingIds()) {
				BuildingFile buildingFile = new BuildingFile(file, item, a);
				buildingFile.setDivision(dto.getDivision());
				buildingFile.setComment(dto.getComment());
				buildingFile.setFileDivision(dto.getFileDivision());
				buildingFile.create();
			}
		}

		if (dto.getShopIds().size() > 0) {
			for (Long item : dto.getShopIds()) {
				Shop shop = new ShopRepository().findById(item).get();
				if (shop != null) {
					ShopFile shopFile = new ShopFile(file, shop.getId(), a);
					shopFile.setDivision(dto.getDivision());
					shopFile.setComment(dto.getComment());
					shopFile.setFileDivision(dto.getFileDivision());
					shopFile.create();
				} else {
					Shop newShop = new Shop();
					newShop.setiShopId(item);
					newShop.create();
					ShopFile shopFile = new ShopFile(file, newShop.getId(), a);
					shopFile.setDivision(dto.getDivision());
					shopFile.setComment(dto.getComment());
					shopFile.setFileDivision(dto.getFileDivision());
					shopFile.create();
				}
			}
		}

		if (dto.getProjectIds().size() > 0) {
			for (Long item : dto.getProjectIds()) {
				ProjectFile projectFile = new ProjectFile(file, item, a);
				projectFile.setDivision(dto.getDivision());
				projectFile.setComment(dto.getComment());
				projectFile.setFileDivision(dto.getFileDivision());
				projectFile.create();
			}
		}

	}

	private void createCopyImageBSP(MultipartFile file, NegotiationCreateFileDTO dto, Account a) {
		if (dto.getBuildingIds().size() > 0) {
			for (Long item : dto.getBuildingIds()) {
				BuildingImage buildingImage = new BuildingImage(file, item, a);
				buildingImage.setDivision(dto.getDivision());
				buildingImage.setComment(dto.getComment());
				buildingImage.setFileDivision(dto.getFileDivision());
				buildingImage.create();
			}
		}

		if (dto.getShopIds().size() > 0) {
			for (Long item : dto.getShopIds()) {
				Shop shop = new ShopRepository().findById(item).get();
				if (shop != null) {
					ShopImage shopImage = new ShopImage(file, shop.getId(), a);
					shopImage.setDivision(dto.getDivision());
					shopImage.setComment(dto.getComment());
					shopImage.setFileDivision(dto.getFileDivision());
					shopImage.create();
				} else {
					Shop newShop = new Shop();
					newShop.setiShopId(item);
					newShop.create();
					ShopImage shopImage = new ShopImage(file, newShop.getId(), a);
					shopImage.setDivision(dto.getDivision());
					shopImage.setComment(dto.getComment());
					shopImage.setFileDivision(dto.getFileDivision());
					shopImage.create();
				}
			}
		}

		if (dto.getProjectIds().size() > 0) {
			for (Long item : dto.getProjectIds()) {
				ProjectImage projectImage = new ProjectImage(file, item, a);
				projectImage.setDivision(dto.getDivision());
				projectImage.setComment(dto.getComment());
				projectImage.setFileDivision(dto.getFileDivision());
				projectImage.create();
			}
		}
	}
}
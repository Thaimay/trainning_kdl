package jp.co.world.storedevelopment.sp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.world.storedevelopment.common.controller.CommonNegotiationController;
import jp.co.world.storedevelopment.model.ImportantInformation;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationRepository;
import jp.co.world.storedevelopment.model.service.ImportantInformationService;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationCreateFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationDetailDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.ImportantInformationListDTO;

@RestController
@RequestMapping("/sp/important/notice")
public class ImportantInformationRestController extends CommonNegotiationController {

	@RequestMapping("/find")
	public List<ImportantInformationListDTO> find(@RequestBody @Valid ImportantInformationFindForm dto) {
		try {
			logStartMethod("find", dto);
			dto.setAccount(getAccount());
			List<ImportantInformationListDTO> listDto = new ImportantInformationRepository().find(dto);
			logEndMethod("find");
			return listDto;
		} catch (Exception ex) {
			logException("find", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/detail/{id}")
	public ImportantInformationDetailDTO detail(@PathVariable Long id) throws Throwable {
		try {
			logStartMethod("detail");
			ImportantInformation importantInformation = new ImportantInformationRepository().findById(id)
					.orElseThrow(() -> {
						throw new IllegalArgumentException("存在しないIDです");
					});

			importantInformation.read(getAccount());
			ImportantInformationDetailDTO dto = new ImportantInformationDetailDTO(importantInformation, getAccount());
			logEndMethod("detail");
			return dto;
		} catch (Exception ex) {
			logException("detail", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/create")
	public ImportantInformationDetailDTO create(@RequestBody @Valid ImportantInformationCreateFormDTO dto) {
		try {
			logStartMethod("create");
			ImportantInformation in = dto.toModel();

			new ImportantInformationService().createAll(in, dto, getAccount());
			ImportantInformation createdImportantNotice = new ImportantInformationRepository().findById(in.getId())
					.orElseThrow(() -> {
						throw new IllegalArgumentException("存在しないIDです");
					});

			ImportantInformationDetailDTO detailDto = new ImportantInformationDetailDTO(createdImportantNotice,
					getAccount());
			logEndMethod("create");
			return detailDto;
		} catch (Exception ex) {
			logException("create", ex.getMessage());
			return null;
		}
	}

	@RequestMapping("/read/{id}")
	public void toRead(@PathVariable Long id) {
		try {
			logStartMethod("toRead");
			ImportantInformation importantInformation = new ImportantInformationRepository().findById(id)
					.orElseThrow(() -> {
						throw new IllegalArgumentException("存在しないIDです");
					});

			importantInformation.read(getAccount());
			logEndMethod("toRead");
		} catch (Exception ex) {
			logException("toRead", ex.getMessage());
		}
	}

	@RequestMapping("/nice/{id}")
	public void toNice(@PathVariable Long id) {
		try {
			logStartMethod("toNice");
			ImportantInformation importantInformation = new ImportantInformationRepository().findById(id)
					.orElseThrow(() -> {
						throw new IllegalArgumentException("存在しないIDです");
					});

			importantInformation.switchNice(getAccount());
			logEndMethod("toNice");
		} catch (Exception ex) {
			logException("toNice", ex.getMessage());
		}
	}

	@RequestMapping("/delete/{id}")
	public void toDelete(@PathVariable Long id) {
		try {
			logStartMethod("toDelete");
			ImportantInformation importantInformation = new ImportantInformationRepository().findById(id)
					.orElseThrow(() -> {
						throw new IllegalArgumentException("存在しないIDです");
					});

			importantInformation.setId(id);
			importantInformation.setIsDeleted(true);
			importantInformation.update();
			logEndMethod("toDelete");
		} catch (Exception ex) {
			logException("toDelete", ex.getMessage());
		}
	}
}

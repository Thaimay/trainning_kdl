package jp.co.world.storedevelopment.sp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.common.controller.CommonSalesFileController;
import jp.co.world.storedevelopment.model.SalesFile;
import jp.co.world.storedevelopment.model.mapper.repository.SalesFileRepository;
import jp.co.world.storedevelopment.model.service.SalesFileService;
import jp.co.world.storedevelopment.model.value.FileDivision;
import jp.co.world.storedevelopment.sp.controller.dto.FileDivisionDTO;
import jp.co.world.storedevelopment.sp.controller.dto.SalesFileCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.SalesFileFindDTO;
import jp.co.world.storedevelopment.sp.controller.dto.SalesFileListDTO;

@RestController
@RequestMapping("/sp/sales/file")
public class SalesFileRestController extends CommonSalesFileController {

	@RequestMapping(value = "/list")
	@ResponseStatus(HttpStatus.OK)
	public List<SalesFileListDTO> list() {
		try {
			logStartMethod("list");
			List<SalesFileListDTO> listDto = new SalesFileRepository().findAll().stream().map(fd -> {
				return new SalesFileListDTO(fd);
			}).collect(Collectors.toList());
			logEndMethod("list");
			return listDto;
		} catch (Exception ex) {
			logException("list", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/find/text")
	@ResponseStatus(HttpStatus.OK)
	public List<SalesFileListDTO> findByText(@RequestBody @Valid SalesFileFindDTO dto) {
		try {
			logStartMethod("findByText", dto);
			List<SalesFileListDTO> listDto = new SalesFileRepository().findByText(dto.getText()).stream().map(fd -> {
				return new SalesFileListDTO(fd);
			}).collect(Collectors.toList());
			logEndMethod("findByText");
			return listDto;
		} catch (Exception ex) {
			logException("findByText", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/division/list")
	@ResponseStatus(HttpStatus.OK)
	public List<FileDivisionDTO> divisionList() {
		try {
			logStartMethod("divisionList");

			List<FileDivisionDTO> listDto = Arrays.asList(FileDivision.values()).stream().map(d -> {
				FileDivisionDTO dto = new FileDivisionDTO();
				dto.setDivision(d.toString());
				dto.setLabel(FileDivision.toLavel(d.toString()));
				return dto;
			}).collect(Collectors.toList());
			logEndMethod("divisionList");
			return listDto;
		} catch (Exception ex) {
			logException("divisionList", ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/create")
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestParam("json") String json, @RequestParam("file") MultipartFile file) {
		try {
			logStartMethod("create");
			SalesFileCreateDTO dto = SalesFileCreateDTO.toDTO(json);
			dto.setFile(file);
			new SalesFileService().createSalesFile(dto, file, getAccount());
			logEndMethod("create");
		} catch (Exception ex) {
			logException("create", ex.getMessage());
		}
	}

	@RequestMapping(value = "/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SalesFileListDTO delete(@PathVariable Long id) {
		try {
			logStartMethod("delete");
			SalesFile sf = new SalesFileRepository().findById(id).orElseGet(() -> {
				throw new IllegalStateException("存在しない営業資料です");
			});
			new SalesFileService().deleteAll(id, getAccount());

			SalesFileListDTO dto = new SalesFileListDTO(sf);
			logEndMethod("delete");
			return dto;
		} catch (Exception ex) {
			logException("delete", ex.getMessage());
			return null;
		}
	}

}

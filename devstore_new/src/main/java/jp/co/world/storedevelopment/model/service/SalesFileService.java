package jp.co.world.storedevelopment.model.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.RelatedTask;
import jp.co.world.storedevelopment.model.SalesFile;
import jp.co.world.storedevelopment.model.mapper.repository.SalesFileRepository;
import jp.co.world.storedevelopment.pc.controller.form.SalesCreateForm;
import jp.co.world.storedevelopment.sp.controller.dto.SalesFileCreateDTO;

public class SalesFileService {

	private PropertiesConfiguration config;

	public SalesFileService() {
		config = readConfig();
	}

	public void createSalesFile(SalesFileCreateDTO dto, MultipartFile file, Account a) {
		dto.setFile(file);
		dto.setAccount(a);
		SalesFile sf = dto.toModel().create();

		createRelatedTaskSalesFile("output.relatedtask.sales.create", sf.getDisplayName(), a.getId());
	}

	public void createSalesFileForPC(SalesCreateForm dto, Account a) {
		dto.setAccount(a);
		SalesFile sf = dto.toModel().create();

		createRelatedTaskSalesFile("output.relatedtask.sales.create", sf.getDisplayName(), a.getId());
	}

	public void deleteAll(Long id, Account a) {
		deleteSalesFile(id, a);
	}

	public void deleteAllForPC(List<Long> ids, Account a) {
		deleteSalesFiles(ids, a);
	}

	public void deleteSalesFiles(List<Long> ids, Account a) {
		ids.forEach(id -> deleteSalesFile(id, a));
	}

	public void deleteSalesFile(Long id, Account a) {
		Optional<SalesFile> opt = new SalesFileRepository().findById(id);

		if (opt.isPresent() && opt.get().delete()) {
			createRelatedTaskSalesFile("output.relatedtask.sales.delete", opt.get().getDisplayName(), a.getId());
		}
	}

	private void createRelatedTaskSalesFile(String key, String displayName, Long accountId) {
		RelatedTask rt = new RelatedTask();
		rt.setAccountId(accountId);
		rt.setDivision("SALES");
		rt.setContent(getContent(key, displayName));
		rt.create();
	}

	private String getContent(String key, Object... params) {
		return String.format(config.getString(key), params);
	}

	private PropertiesConfiguration readConfig() {
		try {
			String fileName = "application.properties";
			return new PropertiesConfiguration(fileName);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}
}
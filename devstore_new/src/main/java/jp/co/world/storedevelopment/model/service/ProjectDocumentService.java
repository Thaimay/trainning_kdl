package jp.co.world.storedevelopment.model.service;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.ProjectDocument;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.pc.controller.form.BuildingCreateForm;
import jp.co.world.storedevelopment.pc.controller.form.ProjectDocumentForm;

public class ProjectDocumentService {
	private BuildingRepository buildingRepository;
	private PropertiesConfiguration config;
	
	public ProjectDocumentService() {
		buildingRepository = new BuildingRepository();
		config = readConfig();
	}
	
	public void createAllForPC(ProjectDocumentForm dto, Account a) {
		ProjectDocument b = createProjectDoc(dto.toModel(), a);
		
	}
	
	private ProjectDocument createProjectDoc(ProjectDocument b, Account a) {
		b.setCreateAccount(a);
		return b.create();
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

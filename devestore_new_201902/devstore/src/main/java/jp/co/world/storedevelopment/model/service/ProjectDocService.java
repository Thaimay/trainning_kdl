package jp.co.world.storedevelopment.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Building;
import jp.co.world.storedevelopment.model.IRentContract;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Mail;
import jp.co.world.storedevelopment.model.OtherProjectAccount;
import jp.co.world.storedevelopment.model.OtherProjectTeam;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.ProjectContractProgress;
import jp.co.world.storedevelopment.model.ProjectDocument;
import jp.co.world.storedevelopment.model.ProjectFile;
import jp.co.world.storedevelopment.model.ProjectImage;
import jp.co.world.storedevelopment.model.ProjectOpinion;
import jp.co.world.storedevelopment.model.ProjectPlan;
import jp.co.world.storedevelopment.model.ProjectSchedule;
import jp.co.world.storedevelopment.model.ProjectSectionProgress;
import jp.co.world.storedevelopment.model.ProjectTask;
import jp.co.world.storedevelopment.model.ProjectTaskAccount;
import jp.co.world.storedevelopment.model.ProjectVideo;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.mapper.repository.IRentContractRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.OtherProjectTeamRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectContractProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectDocumentRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectFileRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectImageRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectOpinionRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectPlanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectScheduleRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectSectionProgressRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskAccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ProjectTaskRepository;
import jp.co.world.storedevelopment.model.value.FileExtention;
import jp.co.world.storedevelopment.sp.controller.dto.ActionStatusFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDocCreateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDocUpdateDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectDocUpdateDTO2;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectMediaDocumentDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ProjectUpdateDTO;

public class ProjectDocService {
	ProjectDocumentRepository projectDocRp = new ProjectDocumentRepository();
	
	
	public ProjectDocument createAll(ProjectDocCreateDTO dto) {
		ProjectDocument projectDoc = createProjectDoc(dto);
		//projectDoc.setIgnoreFields(new String[] { "implementationDateValue", "currentISalesAgencyTargetId", "progressISalesAgencyTargetId" });
//		ProjectSectionProgress psp = new ProjectSectionProgress();
//		if(dto.getProjectSectionProgressDto() != null) {
//			Iterator<ProjectSectionProgress> iter = dto.getProjectSectionProgressDto().iterator();
//			while (iter.hasNext()) {
//				ProjectSectionProgress temp = iter.next();
//				if (StringUtils.equals(temp.getCategory(), "NEGOTIATION")) {
//					if (temp.getFloor() != null) {
//						temp.setFloor(temp.getFloor() + "Ｆ");
//					}
//					psp = temp;
//				}
//			}
		
		return projectDoc;
		}
		
		private ProjectDocument createProjectDoc(ProjectDocCreateDTO dto) {
			ProjectDocument p = dto.toModel();

//			if(p.getSalesPrediction() != null) {
//				p.setSalesPrediction(p.getSalesPrediction() * 1000);
//			}
//			if(p.getProfitExpectation() != null) {
//				p.setProfitExpectation(p.getProfitExpectation() * 1000);
//			}
//			p.setCreateAccount(a);
//			if (dto.getProjectSectionProgressDto() != null) {
//				dto.getProjectSectionProgressDto().forEach(projectSectionProgress -> {
//					p.registerTsubo(projectSectionProgress);
//					p.registerSection(projectSectionProgress);
//				});
//			}
			
			ProjectDocument newProjectDocument = new ProjectDocument();
			newProjectDocument.setIgnoreFields(new String[] {"id", "corporationGroup", "isDeleted" });
			
			newProjectDocument.setProjectId(p.getProjectId()); 
			newProjectDocument.setFileId(p.getFileId());		   
			newProjectDocument.setName(p.getName()); 
			newProjectDocument.setProjectDocumentCoversheetClassification(p.getProjectDocumentCoversheetClassification());
			newProjectDocument.setMeetingPoint(p.getMeetingPoint()); 
			newProjectDocument.setOutputStatus(p.getOutputStatus());
			newProjectDocument.setCreatedDatetime(p.getCreatedDatetime());
			newProjectDocument.setUpdateDatetime(p.getUpdateDatetime());
			newProjectDocument.setCreatedAccountCode(p.getCreatedAccountCode());
			newProjectDocument.setUpdateAccountCode(p.getCreatedAccountCode());
			
			return newProjectDocument.create();
			
			
			//return p.create();
		}
		
		public Long updateAll(ProjectDocUpdateDTO2 dto) {
			ProjectDocument p = dto.toModel();
//			ProjectSectionProgress psp = new ProjectSectionProgress();
//			Iterator<ProjectSectionProgress> iter = dto.getProjectSectionProgressDto().iterator();
//			while (iter.hasNext()) {
//				ProjectSectionProgress temp = iter.next();
//				if (StringUtils.equals(temp.getCategory(), "NEGOTIATION")) {
//					if (temp.getFloor() != null) {
//						temp.setFloor(temp.getFloor() + "Ｆ");
//					}
//					psp = temp;
//				}
//			}

//			p.checkDifferenceColumns(dto);
//
//			updateProjectHistory(p);
//
			updateProject(p);

//			updateRelatedModels(p, dto, a);
//
//			updateCopyShop(p, psp);
//
//			p.refreshTodo();
//
//			updateConference(p);

			return p.getId();
		}
		
		
		private void updateProject(ProjectDocument p) {
			ProjectDocument newProjectDocument = new ProjectDocument();
			newProjectDocument.setIgnoreFields(new String[] {"corporationGroup", "isDeleted", "projectId", "fileId",
					"createdDatetime", "createdAccountCode", "updateAccountCode", "updateDatetime"});
			newProjectDocument.setId(p.getId());
			newProjectDocument.setName(p.getName()); 
			newProjectDocument.setProjectDocumentCoversheetClassification(p.getProjectDocumentCoversheetClassification());
			newProjectDocument.setMeetingPoint(p.getMeetingPoint()); 
			newProjectDocument.setOutputStatus(p.getOutputStatus());
			
			newProjectDocument.update();
		}
}

package jp.co.world.storedevelopment.model.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.junit.Test;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.MProjectActionStatus;
import jp.co.world.storedevelopment.model.MProjectProgressStatus;
import jp.co.world.storedevelopment.model.Project;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectActionStatusRepository;
import jp.co.world.storedevelopment.model.mapper.repository.MProjectProgressStatusRepository;

public class ActionStatusCalculationServiceTest {
	@Test
	public void actionStatus() {
		File f = new File(String.format("%s/csv/action_status_test.csv", Application.resourcePath()));
		try (CSVParser parser = CSVParser.parse(f, Charset.forName("UTF-8"), crateFormat())) {
			Integer index = 1;
			for (CSVRecord record : parser.getRecords()) {
				Long categoryId = Long.parseLong(record.get(0));
				Long salesChannelId = Long.parseLong(record.get(1));
				String companyCode = record.get(2);
				String negotiation = record.get(3);
				String contract = record.get(4);
				String result = record.get(5);
				
				actionStatusDecision(
					categoryId,
					salesChannelId,
					companyCode,
					negotiation,
					contract,
					result,
					index
				);
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actionStatusDecision(Long cId, Long sId, String companyCode, String negotiationCode, String contact, String result, Integer index) {
		MProjectProgressStatus company = new MProjectProgressStatusRepository().getCurrentProgress("COMPANY", cId, companyCode).orElseGet(() -> {
			throw new IllegalStateException("存在しないCompanyコード:" + companyCode);
		});
		MProjectProgressStatus negotiation = new MProjectProgressStatusRepository().getCurrentProgress("NEGOTIATION", cId, negotiationCode).orElseGet(() -> {
			throw new IllegalStateException("存在しないNegotiationコード:" + negotiationCode);
		});

		Project target = createProject(cId, sId, company.getId(), negotiation.getId(), contact);
		MProjectActionStatus status = calculation(target);
		
		if (status.getName().equals(result)) {
			assertTrue(true);
		} else {
			System.out.println(String.format("%d行目 %s %s %s %s result %s", index, companyCode, negotiationCode, contact, result, status.getName()));
			fail();
		}
	}
	
	private CSVFormat crateFormat() {
		return CSVFormat.EXCEL.withIgnoreEmptyLines().withIgnoreSurroundingSpaces().withQuoteMode(QuoteMode.ALL)
				.withTrim().withEscape('&');
	}

	private Project createProject(Long cId, Long sId, Long oId, Long nId, String contractProgress) {
		Project model = new Project();
		model.setProjectCategoryId(cId);
		model.setiSalesChannelId(sId);
		model.setOfficeProjectProgressId(oId);
		model.setNegotiationProjectProgressId(nId);		
		model.setContractProgress(contractProgress);

		return model;
	}
	
	private MProjectActionStatus calculation(Project project) {
		List<MProjectActionStatus> list = new MProjectActionStatusRepository().findSortDescBy(
				project.getProjectCategoryId(), project.getiSalesChannelId());

		project.inHouseProgress();
		project.negotiationProgress();
		return new ActionStatusCalculationService().calculation(
				list,
				project.inHouseProgress(), 
				project.negotiationProgress(),
				project.getContractProgress());
	}
}

package jp.co.world.storedevelopment.batch;

import jp.co.world.storedevelopment.batch.masterimport.IAreaBatch;
import jp.co.world.storedevelopment.model.mapper.repository.ActivationRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingKeymanRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingMeetingHistoryRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingPersonalDevelopRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingSalesRepository;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingTenantRepository;
import jp.co.world.storedevelopment.model.mapper.repository.IAreaRepository;
import jp.co.world.storedevelopment.model.mapper.repository.ImportantInformationBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.NegotiationInterviewBuildingRepository;
import jp.co.world.storedevelopment.model.mapper.repository.PmCorporationRepository;

public class IAreaTest extends BatchTest {

	@Override
	protected void deleteAll() {
		new PmCorporationRepository().deleteAllWithResetSerial();
		new NegotiationInterviewBuildingRepository().deleteAllWithResetSerial();
		new ImportantInformationBuildingRepository().deleteAllWithResetSerial();
		new ActivationRepository().deleteAllWithResetSerial();
		new BuildingMeetingHistoryRepository().deleteAllWithResetSerial();
		new BuildingTenantRepository().deleteAllWithResetSerial();
		new BuildingSalesRepository().deleteAllWithResetSerial();
		new BuildingPersonalDevelopRepository().deleteAllWithResetSerial();
		new BuildingKeymanRepository().deleteAllWithResetSerial();
		new BuildingRepository().deleteAllWithResetSerial();
		new IAreaRepository().deleteAllWithResetSerial();
	}

	@Override
	protected void execute() {
		IAreaBatch.main(null);
	}

	@Override
	protected int getActual() {
		return 30;
	}

	@Override
	protected int getExpected() {
		return new IAreaRepository().countAll();
	}
}

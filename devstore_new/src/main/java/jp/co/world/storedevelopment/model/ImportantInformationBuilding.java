package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.ImportantInformationBuildingModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.BuildingRepository;

public class ImportantInformationBuilding extends ActiveModel<ImportantInformationBuilding> {
	private Long importantInformationId;
	private Long buildingId;
	private String unmanagedName;

	private static String[] ignoreFields = new String[] { "buildingName" };

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	public ImportantInformationBuilding() {
	}

	public ImportantInformationBuilding(ImportantInformation in, Building b) {
		setImportantInformationId(in.getId());
		setBuildingId(b.getId());
	}

	public ImportantInformationBuilding(ImportantInformation in, String name) {
		setImportantInformationId(in.getId());
		setUnmanagedName(name);
	}

	@Override
	protected ModelMapper<ImportantInformationBuilding> modelMapper(SqlSession session) {
		return session.getMapper(ImportantInformationBuildingModelMapper.class);
	}

	public Boolean isUnmanaged() {
		return buildingId == null;
	}

	public Long getImportantInformationId() {
		return importantInformationId;
	}

	public void setImportantInformationId(Long importantInformationId) {
		this.importantInformationId = importantInformationId;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getUnmanagedName() {
		return unmanagedName;
	}

	public void setUnmanagedName(String unmanagedName) {
		this.unmanagedName = unmanagedName;
	}

	public String getBuildingName() {
		if (isUnmanaged()) {
			return getUnmanagedName();
		} else {
			return findBuilding().getName();
		}
	}

	public Building findBuilding() {
		return new BuildingRepository().findById(getBuildingId()).orElseGet(() -> {
			throw new IllegalStateException("存在しない館です:" + getBuildingId());
		});
	}
}

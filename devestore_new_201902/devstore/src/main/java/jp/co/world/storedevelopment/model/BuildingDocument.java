package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.BuildingDocumentModelMapper;

public class BuildingDocument extends File<BuildingDocument> {
	private Long buildingId;

	public BuildingDocument() {
	}

	public BuildingDocument(MultipartFile file, Building building, Account account) {
		super(file, account);
		setBuildingId(building.getId());
	}

	@Override
	public String filePrefix() {
		return "building_";
	}

	@Override
	public String basePath() {
		return File.DOCUMENT_FILE_PATH;
	}

	@Override
	protected ModelMapper<BuildingDocument> modelMapper(SqlSession session) {
		return session.getMapper(BuildingDocumentModelMapper.class);
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

}

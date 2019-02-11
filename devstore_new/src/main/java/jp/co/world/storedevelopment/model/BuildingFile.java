package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.BuildingFileModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

public class BuildingFile extends File<BuildingFile> {

	public BuildingFile() {
	}

	@Override
	public String filePrefix() {
		return "building_";
	}

	public BuildingFile(MultipartFile file, Building building, Account account) {
		super(file, account);
		setBuildingId(building.getId());
	}

	public BuildingFile(MultipartFile file, Long buildingId, Account account) {
		super(file, account);
		setBuildingId(buildingId);
	}

	@Override
	protected ModelMapper<BuildingFile> modelMapper(SqlSession session) {
		return session.getMapper(BuildingFileModelMapper.class);
	}

	@Override
	public String basePath() {
		return DOCUMENT_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "document/";
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

}

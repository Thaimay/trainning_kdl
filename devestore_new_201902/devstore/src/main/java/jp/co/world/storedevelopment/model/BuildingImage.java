package jp.co.world.storedevelopment.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.BuildingImageModelMapper;
import jp.co.world.storedevelopment.model.value.FileExtention;

@JsonIgnoreProperties({ "tableName" })
public class BuildingImage extends File<BuildingImage> {

	private Boolean isDefaultImage;

	public BuildingImage() {
	}

	public BuildingImage(MultipartFile file, Building building, Account account) {
		super(file, account);
		setBuildingId(building.getId());
	}

	public BuildingImage(MultipartFile file, Long buildingId, Account account) {
		super(file, account);
		setBuildingId(buildingId);
	}

	@Override
	public String filePrefix() {
		return "building_";
	}

	@Override
	public String basePath() {
		return File.IMAGE_FILE_PATH;
	}

	@Override
	public String urlPath() {
		return "image/";
	}

	@Override
	protected ModelMapper<BuildingImage> modelMapper(SqlSession session) {
		return session.getMapper(BuildingImageModelMapper.class);
	}

	@Override
	public Boolean isImage() {
		return FileExtention.isImage(getName());
	}

	@Override
	public Boolean isVideo() {
		return FileExtention.isVideo(getName());
	}
	
	@Override
	public Boolean isDocument() {
		return FileExtention.isDocument(getName());
	}

	public Boolean getIsDefaultImage() {
		return isDefaultImage;
	}

	public void setIsDefaultImage(Boolean isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}

}

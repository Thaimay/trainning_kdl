package jp.co.world.storedevelopment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tierline.mybatis.activemodel.ModelMapper;

import jp.co.world.storedevelopment.model.mapper.MLocalityModelMapper;
import jp.co.world.storedevelopment.model.mapper.repository.MLocalityRepository;

/**
 * @author tienpv
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "tableName" })
public class MLocality extends ActiveModel<MLocality> {

	private Long iAreaId;
	private Long iPrefectureId;
	private Long iBlockId;
	private Long mLocalityId;


	private String[] ignoreFields = new String[] {};

	@Override
	protected String[] ignoreFields() {
		return ignoreFields;
	}

	@Override
	protected ModelMapper<MLocality> modelMapper(SqlSession session) {
		return session.getMapper(MLocalityModelMapper.class);
	}

	public static List<IArea> areas() {
		return new MLocalityRepository().findiAreas();
	}

	public static List<IPrefectures> prefectures(Long iAreaId) {
		return new MLocalityRepository().findiprefectures(iAreaId);
	}

	public static List<IBlock> blocks(Long iAreaId,Long iPrefectureId) {
		return new MLocalityRepository().findiblocks(iAreaId,iPrefectureId);
	}

	public Long getiAreaId() {
		return iAreaId;
	}

	public void setiAreaId(Long iAreaId) {
		this.iAreaId = iAreaId;
	}

	public Long getiPrefectureId() {
		return iPrefectureId;
	}

	public void setiPrefectureId(Long iPrefectureId) {
		this.iPrefectureId = iPrefectureId;
	}

	public Long getiBlockId() {
		return iBlockId;
	}

	public void setiBlockId(Long iBlockId) {
		this.iBlockId = iBlockId;
	}

	public Long getmLocalityId() {
		return mLocalityId;
	}

	public void setmLocalityId(Long mLocalityId) {
		this.mLocalityId = mLocalityId;
	}
}

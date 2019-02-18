package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.ISegmentDivision;

public interface ISegmentDivisionRepositoryMapper extends RepositoryMapper<ISegmentDivision> {

	@Select("select * from i_segment_division where is_deleted is false and segment_division_cd = '${code}'")
	ISegmentDivision findByImportCode(@Param("code") String segmentDivisionCd);
}

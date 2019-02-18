package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IArea;
import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.model.IPrefectures;
import jp.co.world.storedevelopment.model.MLocality;

public interface MLocalityRepositoryMapper extends RepositoryMapper<MLocality> {

	@Select("select * from m_locality order by id ASC")
	public List<MLocality> findAllSort();

	@Select("select *, (select ml.id from m_locality ml where ml.i_area_id = i_area.id limit 1) as locality_id from i_area where id in (select i_area_id from m_locality) order by locality_id;")
	public List<IArea> findiAreas();

	@Select("select *, (select ml.id from m_locality ml where ml.i_prefecture_id = i_prefectures.id and ml.i_area_id = ${iAreaId} limit 1) as locality_id from i_prefectures where id in (select i_prefecture_id from m_locality where i_area_id = ${iAreaId}) order by locality_id;")
	public List<IPrefectures> findiprefectures(@Param("iAreaId") Long iAreaId);


	@Select("select *, (select ml.id from m_locality ml where ml.i_block_id = i_block.id and  ml.i_prefecture_id = ${iPrefectureId} limit 1) as locality_id from i_block where id in (select i_block_id from m_locality where m_locality.i_area_id = ${iAreaId} and m_locality.i_prefecture_id = ${iPrefectureId}) order by locality_id;")
	public List<IBlock> findiblocks( @Param("iAreaId") Long iAreaId,@Param("iPrefectureId") Long iPrefectureId);

}

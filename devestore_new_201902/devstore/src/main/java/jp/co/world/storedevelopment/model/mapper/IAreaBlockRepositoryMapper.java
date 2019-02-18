package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IAreaBlock;

public interface IAreaBlockRepositoryMapper extends RepositoryMapper<IAreaBlock> {
	@Select("select * from i_area_block where is_deleted is false and main_block_cd = '${code}'")
	IAreaBlock findByImportCode(@Param("code") String mainBlockCd);
}

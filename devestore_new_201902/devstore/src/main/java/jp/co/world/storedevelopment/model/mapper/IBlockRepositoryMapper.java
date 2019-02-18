package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IBlock;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;

public interface IBlockRepositoryMapper extends RepositoryMapper<IBlock> {

	@Select("select * from i_block where block_name like '%${iBlock.text}%' or block_name like '%${iBlock.textHankaku}%' limit ${limit}")
	List<IBlock> findByText(@Param("iBlock") BuildingRelationFindByTextFormDTO dto, @Param("limit") int limit);

	@Select("select * from i_block where is_deleted is false and block_cd = '${code}'")
	IBlock findByImportCode(@Param("code") String blockCd);

}

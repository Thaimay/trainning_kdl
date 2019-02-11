package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.FileDivision;

public interface FileDivisionRepositoryMapper extends RepositoryMapper<FileDivision> {
	@Select("select * from file_division where file_division_code = '${code}'")
	FileDivision getFileDivisionByCode(@Param("code") String code);
}

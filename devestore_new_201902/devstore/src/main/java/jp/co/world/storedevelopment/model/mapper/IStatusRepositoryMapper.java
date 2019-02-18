package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IStatus;

public interface IStatusRepositoryMapper extends RepositoryMapper<IStatus> {

	@Select("select * from i_status where is_deleted is false and status_cd = '${code}'")
	IStatus findByImportCode(@Param("code") String statusCd);

}

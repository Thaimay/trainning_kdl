package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IRemodelingHistory;

public interface IRemodelingHistoryRepositoryMapper extends RepositoryMapper<IRemodelingHistory> {
	@Select("select * from i_remodeling_history where is_deleted is false and remodeling_log_id = '${code}'")
	IRemodelingHistory findByImportCode(@Param("code") String remodelingLogId);
}

package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MStoreDevelopTeam;

public interface MStoreDevelopTeamRepositoryMapper extends RepositoryMapper<MStoreDevelopTeam> {
	@Select("select * from m_store_develop_team where dept_cd = '${deptCd}'")
	MStoreDevelopTeam findByDeptCd(@Param("deptCd") String deptCd);

	@Select("select * from m_store_develop_team where is_deleted is false order by id asc")
	List<MStoreDevelopTeam> findByALL();
}

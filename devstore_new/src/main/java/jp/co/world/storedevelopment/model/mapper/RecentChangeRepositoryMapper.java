package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.RecentChange;

public interface RecentChangeRepositoryMapper extends RepositoryMapper<RecentChange> {
	@Select("select * from recent_change where is_deleted = false and division = 'BUILDING' order by update_datetime DESC")
	public List<RecentChange> findRecentChangeBuilding();

	@Select("select * from recent_change where is_deleted = false and division = 'FILE' order by update_datetime DESC")
	public List<RecentChange> findRecentChangeBuildingFile();

	@Select("select * from recent_change where is_deleted = false and division = 'NEGOTIATION' OR division = 'NEGOTIATION_COMMENT'")
	public List<RecentChange> findRecentChangeNegotiation();
}

package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.MLocality;

public interface MLocalityRepositoryMapper extends RepositoryMapper<MLocality> {
	@Select("select * from m_locality order by id ASC")
	public List<MLocality> findAllSort();
}

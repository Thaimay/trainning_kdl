package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IWcpMember;

public interface IWcpMemberRepositoryMapper extends RepositoryMapper<IWcpMember> {
	@Select("select * from i_wcp_member where is_deleted is false and disp_order = '${code}'")
	IWcpMember findByImportCode(@Param("code") long getDispOrder);
}

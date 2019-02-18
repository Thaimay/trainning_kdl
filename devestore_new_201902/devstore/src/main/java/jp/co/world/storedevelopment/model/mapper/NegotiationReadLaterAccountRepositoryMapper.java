package jp.co.world.storedevelopment.model.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationReadLaterAccount;

public interface NegotiationReadLaterAccountRepositoryMapper extends RepositoryMapper<NegotiationReadLaterAccount> {

	@Select("select * from NEGOTIATION_READ_LATER_ACCOUNT where negotiation_id = #{negotiation.id} and account_id = #{account.id}  LIMIT 1 OFFSET 0")
	NegotiationReadLaterAccount findByAccount(@Param("negotiation") Negotiation negotiation,
			@Param("account") Account account);

}

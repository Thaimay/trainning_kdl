package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationNoticeAccount;

public interface NegotiationNoticeAccountRepositoryMapper
		extends NegotiationRelatedRepositoryMapper<NegotiationNoticeAccount> {

	Object findByAccount(Negotiation negotiation, Account account);

	@Select("SELECT * FROM negotiation_notice_account WHERE negotiation_id = ${id}")
	List<NegotiationNoticeAccount> findByNegotiationId(@Param("id") Long id);

}

package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.tierline.mybatis.activemodel.SQL;

import jp.co.world.storedevelopment.model.Account;
import jp.co.world.storedevelopment.model.Negotiation;
import jp.co.world.storedevelopment.model.NegotiationOpenAccount;

public interface NegotiationOpenAccountRepositoryMapper
		extends NegotiationRelatedRepositoryMapper<NegotiationOpenAccount> {

	@Select("select * from NEGOTIATION_OPEN_ACCOUNT where negotiation_id = #{negotiation.id} and account_id = #{account.id}  LIMIT 1 OFFSET 0")
	NegotiationOpenAccount findByAccount(@Param("negotiation") Negotiation negotiation,
			@Param("account") Account account);

	@SelectProvider(type = NegotiationCountIsOpenQLBuilder.class, method = "build")
	Integer countIsOpened(Negotiation negotiation, List<Long> accountIds);

	public class NegotiationCountIsOpenQLBuilder {
		public String build(final Negotiation negotiation, final List<Long> accountIds) {
			return new SQL() {
				{
					SELECT_DISTINCT("count(NO.*)");
					FROM("NEGOTIATION_OPEN_ACCOUNT NO");
					WHERE("NO.is_deleted = false");
					String q = " NO.negotiation_id = %d and NO.account_id IN%s";
					WHERE(format(q, negotiation.getId(), IN(accountIds)));
				}

			}.toString();
		}
	}
}

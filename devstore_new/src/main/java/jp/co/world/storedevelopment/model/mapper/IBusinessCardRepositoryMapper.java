package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.mapper.repository.sql.BusinessCardSQLBuilder;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IBusinessCardListDTO;

public interface IBusinessCardRepositoryMapper extends RepositoryMapper<IBusinessCard> {

	@SelectProvider(type = BusinessCardSQLBuilder.class, method = "build")
	List<IBusinessCard> findByText(@Param("text") String text, @Param("hankaku") String hankaku, @Param("limit") int limit);

	@Select("select * from i_business_Card where employe_code = '${code}' ")
	IBusinessCard findByCode(@Param("code") String code);

	@Select("select bc.*, " + "coalesce((select id from keyman where business_card_id = bc.id LIMIT 1), 0) keyman_id "
			+ "from i_business_card bc where bc.name like '${businessCard.text}%' or bc.name like '${businessCard.textHankaku}%' or "
			+ "bc.company_name like '${businessCard.text}%' or bc.company_name like '${businessCard.textHankaku}%' or "
			+ "bc.position_name like '${businessCard.text}%' or bc.position_name like '${businessCard.textHankaku}%' "
			+ "limit ${limit}")
	List<IBusinessCardListDTO> findByString(@Param("businessCard") BuildingRelationFindByTextFormDTO dto,
			@Param("limit") int limit);

	@Select("select * from i_business_card where email = '${email}'")
	IBusinessCard findByEmail(@Param("email") String email);

	@Select("select * from i_business_card where card_id = ${cardId}")
	IBusinessCard findByCardId(@Param("cardId") Long cardId);

}

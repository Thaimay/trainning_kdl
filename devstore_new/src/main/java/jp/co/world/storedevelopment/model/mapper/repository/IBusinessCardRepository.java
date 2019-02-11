package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IBusinessCard;
import jp.co.world.storedevelopment.model.mapper.IBusinessCardRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.IBusinessCardListDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

public class IBusinessCardRepository extends Repository<IBusinessCard, IBusinessCardRepositoryMapper> {

	@Override
	protected IBusinessCardRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IBusinessCardRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IBusinessCard.class);
	}

	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IBusinessCard> list = mapper.findByText(dto.getText(), HankakuUtils.toHankaku(dto.getText()),
					Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(a -> {
				String name = String.format("%s_%s_%s", a.getName(), a.getCompanyName(), a.getPositionName());
				return new NegotiationRelationDTO(a.getId(), ZenkakuUtils.toZenkaku(name));
			}).collect(Collectors.toList());
		});
	}

	public List<IBusinessCardListDTO> findByString(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			return mapper.findByString(dto, Application.SUGGEST_LIMIT_SIZE);
		});
	}

	public Optional<IBusinessCard> findByCode(String code) {

		return execute((mapper) -> {
			IBusinessCard businessCard = mapper.findByCode(code);
			return Optional.ofNullable(businessCard);
		});
	}

	public Optional<IBusinessCard> findByEmail(String email) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByEmail(email));
		});
	}

	public Optional<IBusinessCard> findByCardId(Long cardId) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByCardId(cardId));
		});
	}

}

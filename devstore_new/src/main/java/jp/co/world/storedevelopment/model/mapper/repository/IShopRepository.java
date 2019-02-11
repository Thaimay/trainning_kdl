package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.world.storedevelopment.utils.HankakuUtils;
import com.world.storedevelopment.utils.ZenkakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.mapper.IShopRepositoryMapper;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByTextFormDTO;

/**
 * @author TaiNM
 *
 */
public class IShopRepository extends ShopRelatedRepository<IShop, IShopRepositoryMapper> {
	@Override
	protected IShopRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IShopRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IShop.class);
	}
	
	public List<NegotiationRelationDTO> findByText(NegotiationRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IShop> list = mapper.findByText(dto.getText(), HankakuUtils.toHankaku(dto.getText()),
					Application.SUGGEST_LIMIT_SIZE);

			return list.stream().map(b -> new NegotiationRelationDTO(b.getId(), ZenkakuUtils.toZenkaku(b.getShopNameZenkakuHankaku())))
					.collect(Collectors.toList());
		});
	}

	public List<IShop> findNotDeleted() {
		return execute((mapper) -> {
			return mapper.findNotDeleted();
		});
	}

	public List<IShop> findByString(String text) {
		return execute((mapper) -> {
			return mapper.findByString(text, HankakuUtils.toHankaku(text), Application.SUGGEST_LIMIT_SIZE);
		});
	}
	public List<IShop> findShopCdByString(String text) {
		return execute((mapper) -> {
			return mapper.findShopCdByString(text, Application.SUGGEST_LIMIT_SIZE);
		});
	}
	public IShop findByPlaceId(Long placeId) {
		return execute((mapper) -> {
			return mapper.findByPlaceId(placeId);
		});
	}

	public Optional<IShop> findByImportId(IShop model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportId(model.getShopId()));
		});
	}
}

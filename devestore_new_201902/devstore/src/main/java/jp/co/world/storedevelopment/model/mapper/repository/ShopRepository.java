package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;
import com.world.storedevelopment.utils.HankakuUtils;

import jp.co.world.storedevelopment.Application;
import jp.co.world.storedevelopment.model.IShop;
import jp.co.world.storedevelopment.model.Shop;
import jp.co.world.storedevelopment.model.ShopHistory;
import jp.co.world.storedevelopment.model.mapper.ShopRepositoryMapper;
import jp.co.world.storedevelopment.pc.controller.form.ShopFindForm;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.BuildingRelationFindByTextFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationDTO;
import jp.co.world.storedevelopment.sp.controller.dto.NegotiationRelationFindByParamFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopFindFormDTO;
import jp.co.world.storedevelopment.sp.controller.dto.ShopHistoryListDTO;

/**
 * @author TaiNM
 *
 */
public class ShopRepository extends Repository<Shop, ShopRepositoryMapper> {
	@Override
	protected ShopRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(ShopRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Shop.class);
	}

	public List<BuildingRelationDTO> findByText(BuildingRelationFindByTextFormDTO dto) {
		return execute((mapper) -> {
			List<IShop> list = mapper.findByText(dto, Application.SUGGEST_LIMIT_SIZE);
			return list.stream().map(b -> new BuildingRelationDTO(b.getId(), b.getShopNameZenkaku()))
					.collect(Collectors.toList());
		});
	}

	public List<IShop> findShop(ShopFindFormDTO dto) {
		return execute((mapper) -> {

			return mapper.findShop(dto);
		});
	}

	public List<IShop> findForPC(ShopFindForm dto) {
		return execute((mapper) -> {

			return mapper.findForPC(dto);
		});
	}

	public IShop findShopById(Long id) {
		return execute((mapper) -> {
			return mapper.findShopById(id);
		});
	}

	public Integer getCountFindForPC(ShopFindForm dto) {
		return execute((mapper) -> {
			return mapper.getCountFindForPC(dto);
		});
	}

	public void updateShopHistory(Long id) {
		execute((mapper) -> {
			mapper.updateShopHistory(id);
		});
	}

	public ShopHistory findShopHistoryById(Long id) {
		return execute((mapper) -> {
			return mapper.findShopHistoryById(id);
		});
	}

	public List<ShopHistoryListDTO> getShopHistoryList(String shopId) {
		return execute((mapper) -> {
			return mapper.getShopHistoryList(shopId).stream().map(x -> new ShopHistoryListDTO(x))
					.sorted((s2, s1) -> s1.getId().compareTo(s2.getId())).collect(Collectors.toList());
		});
	}

	public Shop findByIShopId(Long iShopId) {
		return execute((mapper) -> {
			return mapper.findByIShopId(iShopId);
		});
	}

	public List<NegotiationRelationDTO> findByNameForNegotiationFile(NegotiationRelationFindByParamFormDTO dto) {
		return execute((mapper) -> {
			List<NegotiationRelationDTO> arrDto = new ArrayList<>();
			List<IShop> list = mapper.findByNameForNegotiationFile(dto.getText(), HankakuUtils.toHankaku(dto.getText()), dto.getParams(),
					Application.SUGGEST_LIMIT_SIZE);
			if (list.size() > 0) {
				list.forEach(s -> {
					Shop shop = new ShopRepository().findByIShopId(s.getId());
					if (shop != null) {
						arrDto.add(new NegotiationRelationDTO(shop.getId(), s.getShopNameZenkaku()));
					}
				});
			}
			return arrDto;
		});
	}
}

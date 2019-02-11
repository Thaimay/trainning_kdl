package jp.co.world.storedevelopment.model.mapper.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

import jp.co.world.storedevelopment.model.IPlace;
import jp.co.world.storedevelopment.model.mapper.IPlaceRepositoryMapper;

public class IPlaceRepository extends Repository<IPlace, IPlaceRepositoryMapper> {

	@Override
	protected IPlaceRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(IPlaceRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(IPlace.class);
	}

	public List<IPlace> findByOriginBuildingId(Long originBuildingId) {
	       return execute((mapper) -> {
	            return mapper.findByBuildingId(originBuildingId);
	        });
	}

	public IPlace findByPlaceId(Long placeId) {
	       return execute((mapper) -> {
	            return mapper.findByPlaceId(placeId);
	        });
	}

	public List<IPlace> getIPlaceListByOriginBuildingId(Long originBuildingId) {
	       return execute((mapper) -> {
	            return mapper.getIPlaceListByOriginBuildingId(originBuildingId);
	        });
	}

	public Optional<IPlace> findByImportPlaceId(IPlace model) {
		return execute((mapper) -> {
			return Optional.ofNullable(mapper.findByImportCode(model.getPlaceId()));
		});
	}
}

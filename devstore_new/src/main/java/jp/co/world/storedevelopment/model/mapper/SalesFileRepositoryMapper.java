package jp.co.world.storedevelopment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.tierline.mybatis.activemodel.RepositoryMapper;

import jp.co.world.storedevelopment.model.SalesFile;
import jp.co.world.storedevelopment.model.mapper.repository.sql.SalesSQLBuilder;
import jp.co.world.storedevelopment.pc.controller.form.SalesDeleteForm;
import jp.co.world.storedevelopment.pc.controller.form.SalesFindForm;

public interface SalesFileRepositoryMapper extends RepositoryMapper<SalesFile> {
	@Select("select * from FILE where display_name LIKE '%${text}%' AND  negotiation_id IS NULL AND building_id IS NULL AND shop_id IS NULL AND project_id IS NULL order by update_datetime DESC")
	List<SalesFile> findByText(@Param("text") String text);

	@SelectProvider(type = SalesSQLBuilder.class, method = "build")
	List<SalesFile> find(SalesFindForm form);

	@Select("select * from FILE where negotiation_id IS NULL AND building_id IS NULL AND shop_id IS NULL AND project_id IS NULL order by update_datetime DESC")
	List<SalesFile> findSalesFile();

	@SelectProvider(type = SalesSQLBuilder.class, method = "buildDelete")
	void deleteByIds(SalesDeleteForm form);
}

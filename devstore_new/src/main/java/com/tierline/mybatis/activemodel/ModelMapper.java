package com.tierline.mybatis.activemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;

public interface ModelMapper<T> {

	class MapInsertProvider extends SQL {
		public String toInsert(Map<String, String> params, ActiveModel<?> model) {
			INSERT_INTO(params.get(TABLE_NAME));
			removeNotNeedParams(params, model);
			for (Map.Entry<String, String> e : params.entrySet()) {
				String v = "null";
				if (e.getValue() != null) {
					v = new StringJoiner("", "'", "'").add(StringEscapeUtils.escapeSql(e.getValue())).toString();
				}
				VALUES(e.getKey(), v);
			}
			return toString();
		}
	}

	class ModelInsertProvider extends SQL {
		public String toInsert(ActiveModel<?> model, String... ignores) {
			Map<String, String> params = toParams(model, ignores);
			return new MapInsertProvider().toInsert(params, model);
		}

	}

	class ModelSaveProvider extends SQL {
		public String toSave(ActiveModel<?> model, String... ignores) {
			Map<String, String> params = toParams(model, ignores);
			return new MapInsertProvider().toInsert(params, model);
		}
	}

	class ModelDeleteProvider extends SQL {
		public String toDelete(ActiveModel<?> model) {
			DELETE_FROM(model.getTableName());
			WHERE(model.primaryKeyName() + " = " + model.getPrimaryKey());
			return toString();
		}
	}

	class MapUpdateProvider extends SQL {
		public String toUpdate(Map<String, String> params, ActiveModel<?> model) {
			UPDATE(params.get(TABLE_NAME));
			WHERE(model.primaryKeyName() + " = " + params.get("id"));
			removeNotNeedParams(params, model);
			List<String> sets = new ArrayList<>();
			for (Map.Entry<String, String> e : params.entrySet()) {
				String v = "";
				if (e.getValue() != null) {
					v = String.format("%s = '%s'", e.getKey(), StringEscapeUtils.escapeSql(e.getValue()));
				} else {
					v = String.format("%s = null", e.getKey());
				}
				sets.add(v);
			}
			SET(sets.toArray(new String[sets.size()]));
			return toString();
		}
	}

	class ModelUpdateProvider extends SQL {
		public String toUpdate(ActiveModel<?> model, String... ignores) {
			Map<String, String> params = toParams(model, ignores);
			return new MapUpdateProvider().toUpdate(params, model);
		}
	}

	@InsertProvider(type = ModelInsertProvider.class, method = "toInsert")
	public int insert(ActiveModel<T> model, String... ignores);

	@InsertProvider(type = ModelSaveProvider.class, method = "toSave")
	public T save(ActiveModel<T> model, String... ignores);

	@DeleteProvider(type = ModelDeleteProvider.class, method = "toDelete")
	public Boolean delete(ActiveModel<T> model);

	@DeleteProvider(type = ModelUpdateProvider.class, method = "toUpdate")
	public Boolean update(ActiveModel<T> model, String... ignores);

}

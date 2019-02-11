package com.tierline.mybatis.activemodel;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

public class SQL extends org.apache.ibatis.jdbc.SQL {

	protected final String TABLE_NAME = "table_name";

	protected Map<String, String> toParams(ActiveModel<?> model, String... ignores) {
		try {
			Map<String, String> params = BeanUtils.describe(model);
			Arrays.stream(ignores).forEach(key -> params.remove(key));
			Map<String, String> newParams = new HashMap<>();
			for (Map.Entry<String, String> e : params.entrySet()) {
				String key = toSneke(e.getKey());
				String value = e.getValue();
				newParams.put(key, value);
			}
			return newParams;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	private String toSneke(String key) {
		return ActiveModelStringUtils.toSneke(key);
	}

	protected Map<String, String> removeNotNeedParams(Map<String, String> params, ActiveModel<?> model) {
		params.remove(model.primaryKeyName());
		params.remove(model.primaryKeyName().toUpperCase());
		params.remove(model.primaryKeyName().toLowerCase());
		params.remove("table_name");
		params.remove("class");
		params.remove("primaryKey");
		return params;
	}

	protected Boolean isNotBlank(String value) {
		return StringUtils.isNotBlank(value);
	}

	protected String format(String format, Object... args) {
		return String.format(format, args);
	}

	protected boolean isTrue(Boolean bool) {
		return BooleanUtils.isTrue(bool);
	}

	protected <T> boolean isNotEmpty(List<T> list) {
		if (list != null) {
			return !list.isEmpty();
		} else {
			return false;
		}
	}

	protected String IN(List<Long> ids) {
		String s = ids.stream().map(Object::toString).collect(Collectors.joining(","));
		return format("(%s)", s);
	}

	protected String IN_String(List<String> list) {
		String s = list.stream().map(str -> "'" + str + "'").collect(Collectors.joining(","));
		return "(" + s + ")";
	}

	protected boolean isNotNull(Object... obj) {
		for (Object o : obj) {
			if (o == null) {
				return false;
			}
		}
		return true;
	}

}

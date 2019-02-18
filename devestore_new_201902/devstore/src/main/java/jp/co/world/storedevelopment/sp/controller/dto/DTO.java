package jp.co.world.storedevelopment.sp.controller.dto;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

import com.tierline.mybatis.activemodel.ActiveModel;

public interface DTO<T extends ActiveModel<T>> {

	public default T toModel() {
		T model = createModel();
		copyProperties(model, this);
		return model;
	}

	public T createModel();

	public default void copyProperties(Object dest, Object obj) {
		try {
			PropertyUtils.copyProperties(dest, obj);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalStateException("オブジェクトのコピーで失敗しました");
		}
	}
}

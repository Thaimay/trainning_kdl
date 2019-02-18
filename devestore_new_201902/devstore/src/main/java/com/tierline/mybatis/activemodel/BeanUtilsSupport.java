package com.tierline.mybatis.activemodel;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;

import com.tierline.mybatis.activemodel.converter.LocalDateTimeToStringConverter;
import com.tierline.mybatis.activemodel.converter.LocalDateToStringConverter;

import jp.co.world.storedevelopment.batch.masterimport.ImportBatch;

interface BeanUtilsSupport {

	public static void initBeanUtils() {
		if (ImportBatch.isRun()) {
			return;
		}
		ConvertUtils.register(new LocalDateToStringConverter(), String.class);
		ConvertUtils.register(new LocalDateTimeToStringConverter(), String.class);

		PropertyUtils.addBeanIntrospector(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
		PropertyUtils.clearDescriptors();
	}
}

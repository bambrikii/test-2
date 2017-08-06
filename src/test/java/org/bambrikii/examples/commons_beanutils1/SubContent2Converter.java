package org.bambrikii.examples.commons_beanutils1;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.bambrikii.examples.commons_beanutils1.content.SubContent;
import org.bambrikii.examples.commons_beanutils1.content2.SubContent2;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Alexander Arakelyan on 02/08/17 20:20.
 */
public class SubContent2Converter implements Converter {
	public SubContent2Converter(Object o) {
	}

	@Override
	public <T> T convert(Class<T> targetType, Object sourceValue) {
		if (sourceValue instanceof SubContent2) {
			SubContent subContent = new SubContent();
			SubContent2 subContent2 = (SubContent2) sourceValue;
			try {
				BeanUtilsBean.getInstance().copyProperties(subContent, subContent2);
			} catch (IllegalAccessException ex) {
				throw new RuntimeException(ex);
			} catch (InvocationTargetException ex) {
				throw new RuntimeException(ex);
			}
			return (T) subContent;
		}
		return (T) sourceValue;
	}
}

package org.bambrikii.examples.commons_beanutils1;

import org.apache.commons.beanutils.Converter;
import org.bambrikii.examples.commons_beanutils1.content.SubSubContent;
import org.bambrikii.examples.commons_beanutils1.content2.SubContent2;
import org.bambrikii.examples.commons_beanutils1.content2.SubSubContent2;

/**
 * Created by Alexander Arakelyan on 02/08/17 20:28.
 */
public class SubSubContent2Converter implements Converter {
	public SubSubContent2Converter(Object o) {
	}

	@Override
	public <T> T convert(Class<T> type, Object sourceValue) {
		if (sourceValue instanceof SubContent2) {
			SubSubContent2 subSubContent2 = (SubSubContent2) sourceValue;
			SubSubContent subSubContent = new SubSubContent();
			subSubContent.setContent(subSubContent2.getContent());
			return (T) subSubContent;
		}
		return (T) sourceValue;
	}
}

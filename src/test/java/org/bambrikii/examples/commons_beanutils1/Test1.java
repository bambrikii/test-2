package org.bambrikii.examples.commons_beanutils1;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.bambrikii.examples.commons_beanutils1.content.Content;
import org.bambrikii.examples.commons_beanutils1.content.SubContent;
import org.bambrikii.examples.commons_beanutils1.content.SubSubContent;
import org.bambrikii.examples.commons_beanutils1.content2.Content2;
import org.bambrikii.examples.commons_beanutils1.content2.SubContent2;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by Alexander Arakelyan on 02/08/17 20:10.
 */
public class Test1 {
	@Test
	public void test1() throws InvocationTargetException, IllegalAccessException {
		Content2 searchContent = new Content2();
		searchContent.setDate1(new Date());
		searchContent.setStr1("str1");

		SubContent2 subContent = new SubContent2();
		searchContent.setSubContent(subContent);
		subContent.setName1("name1");

		SubSubContent subSubContent = new SubSubContent();
		subSubContent.setContent("content1");
		subContent.setSubContent(subSubContent);

		Content content = new Content();

		BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();

		Converter subContentConverter = new SubContent2Converter(null);
		beanUtilsBean.getConvertUtils().register(subContentConverter, SubContent.class);

		Converter subSubContentConverter = new SubSubContent2Converter(null);
		beanUtilsBean.getConvertUtils().register(subSubContentConverter, SubSubContent.class);

		BeanUtils.copyProperties(content, searchContent);

		System.out.println(searchContent);
		System.out.println(content);
	}
}

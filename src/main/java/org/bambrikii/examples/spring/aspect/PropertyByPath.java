package org.bambrikii.examples.spring.aspect;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Log4j2
public class PropertyByPath {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class1 class1 = new Class1();
        class1.setProp1("val1");
        Class2 class2 = new Class2();
        class2.setProp2("val2");
        class1.setClass2(class2);

        Method utils = BeanUtils.findMethod(class1.getClass(), "getProp1");
        Object val = utils.invoke(class1);
//        val = utils.getProperty(class1, "class2.prop2");
        log.info("value1 = {}", val);
        PropertyUtilsBean utilsBean = new PropertyUtilsBean();
        log.info("value2 = {}", utilsBean.getProperty(class1, "class2.prop2"));

        Class3 cls3 = new Class3("val3");
        Class4 cls4 = new Class4("val5");
        cls4.setProp7("val7");
        cls3.setProp5(cls4);

        log.info("value3 = {}", utilsBean.getProperty(cls3, "prop3"));
        log.info("value4 = {}", utilsBean.getProperty(cls3, "prop4"));
        log.info("value6 = {}", utilsBean.getProperty(cls3, "prop5.prop6"));
        log.info("value7 = {}", utilsBean.getProperty(cls3, "prop5.prop7"));
    }
}

package org.bambrikii.examples.spring.aspect;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * https://howtodoinjava.com/spring-aop/aspectj-around-annotation-example/
 */
@Log4j2
@SpringBootApplication
public class MyAspectMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MyAspectConfig.class, args);
        MyAspectBean myAspectBean = ctx.getBean(MyAspectBean.class);
        Class1 class2 = new Class1();
        class2.setProp1("val1");
        class2.setProp2("val2");
        class2.setProp3("val3");

        log.info("before around");
        Class2 result = myAspectBean.shouldAround(class2);
        log.info("after around {}", result);

        log.info("before throw");
        myAspectBean.shouldThrow();
        log.info("after throw");
    }
}

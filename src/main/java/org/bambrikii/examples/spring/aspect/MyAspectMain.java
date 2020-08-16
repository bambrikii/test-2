package org.bambrikii.examples.spring.aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyAspectMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MyAspectConfig.class, args);
        MyAspectBean myAspectBean = ctx.getBean(MyAspectBean.class);
        myAspectBean.shouldThrow();
    }
}

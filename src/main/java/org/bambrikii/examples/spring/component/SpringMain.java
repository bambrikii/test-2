package org.bambrikii.examples.spring.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringMain.class, args);

        Service1 service1 = ctx.getBean(Service1.class);
        Service2 service2 = ctx.getBean(Service2.class);

        service1.print1();
        service1.print2();
        service2.print1();
        System.out.println("---");
        service1.print1_2();
        service1.print2_2();
        service2.print1_2();

        ctx.close();
    }
}

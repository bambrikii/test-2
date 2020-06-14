/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bambrikii.examples.springtransaction;

import lombok.extern.slf4j.Slf4j;
import org.bambrikii.examples.springtransaction.config.ExampleTransactionConfig;
import org.bambrikii.examples.springtransaction.services.Service1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.function.Consumer;

/**
 *
 * @author asd
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "org.bambrikii.examples.springtransaction")
public class SpringTransactionMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ExampleTransactionConfig.class, args);
        Service1 service1 = context.getBean(Service1.class);
        service1.method1();
        service1.transaction1();
        service1.method3();
        catchException((val) -> service1.method4());

        catchException((val) -> service1.method5());
        catchException((val) -> {
            try {
                service1.method6();
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        });
        catchException((val) -> service1.method7());
        catchException((val) -> {
            try {
                service1.method8();
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
        });
        service1.method9programmatic();
        SpringApplication.exit(context);
    }

    public static void catchException(Consumer<Void> consumer) {
        try {
            consumer.accept(null);
        } catch (Throwable ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}

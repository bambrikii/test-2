/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bambrikii.examples.springtransaction;

import org.bambrikii.examples.springtransaction.config.ExampleTransactionConfig;
import org.bambrikii.examples.springtransaction.services.Service1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author asd
 */
@SpringBootApplication(scanBasePackages = "org.bambrikii.examples.springtransaction")
public class SpringTransactionMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ExampleTransactionConfig.class, args);
        Service1 service1 = context.getBean(Service1.class);
        service1.method1();
        service1.transaction1();
        SpringApplication.exit(context);
    }
}

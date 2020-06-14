/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bambrikii.examples.springtransaction.config;

import org.bambrikii.examples.springtransaction.entities.UserDetails;
import org.bambrikii.examples.springtransaction.repositories.UserDetailsRepository;
import org.bambrikii.examples.springtransaction.services.Service1;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author asd
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {Service1.class})
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {UserDetails.class})
@EnableJpaRepositories(basePackageClasses = {UserDetailsRepository.class})
public class ExampleTransactionConfig {
    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }
}

package org.bambrikii.examples.spring.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig1 {
    @Bean
    @Scope("prototype")
    public Component2 component2() {
        return new Component2();
    }
}

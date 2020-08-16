package org.bambrikii.examples.spring.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MyAspect.class,
        MyAspectBean.class
})
@ComponentScan(basePackageClasses = {MyAspectConfig.class})
public class MyAspectConfig {
}

package org.bambrikii.examples.spring.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Log4j2
@Aspect
public class MyAspect {
    @AfterThrowing(pointcut = "execution(* org.bambrikii.examples.spring.aspect.MyAspectBean*.*(..))", throwing = "ex")
    public void afterThrow(Throwable ex) {
        log.error("Ex: {}", ex);
    }
}

package org.bambrikii.examples.spring.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.bambrikii.examples.spring.aspect.AspectFieldResolver.findValue;

@Log4j2
@Aspect
public class MyAspect {
    @AfterThrowing(pointcut = "execution(* org.bambrikii.examples.spring.aspect.MyAspectBean*.shouldThrow(..))", throwing = "ex")
    public void afterThrow(Throwable ex) {
        log.error("Ex: {}", ex);
    }

    @Around("execution(public * org.bambrikii.examples.spring.aspect.MyAspectBean*.shouldAround(..))")
    public Object logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Around: {}", joinPoint);
        try {
            before(joinPoint, "prop1", prop1Cache, "prop1", "prop3");
            before(joinPoint, "prop2", prop2Cache, "prop2");
            before(joinPoint, "prop5", prop5Cache, "prop5");
            return joinPoint.proceed();
        } finally {
            after("prop5");
            after("prop2");
            after("prop1");
            log.info("Around args");
        }
    }

    private static void before(ProceedingJoinPoint joinPoint, String mdcKey, Map<Class<?>, Field> prop1Cache, String... props) {
        MDC.put(mdcKey, (String) findValue(joinPoint, prop1Cache, props));
    }

    private static void after(String mdcKey) {
        MDC.remove(mdcKey);
    }

    private static final Map<Class<?>, Field> prop1Cache = new HashMap<>();
    private static final Map<Class<?>, Field> prop2Cache = new HashMap<>();
    private static final Map<Class<?>, Field> prop5Cache = new HashMap<>();
}

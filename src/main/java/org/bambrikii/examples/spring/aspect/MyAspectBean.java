package org.bambrikii.examples.spring.aspect;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MyAspectBean {
    public void shouldThrow() {
        throw new RuntimeException("ex1");
    }

    public Class2 shouldAround(Class1 cls1) {
        log.info("In method: {}", cls1);
        var cls2 = new Class2();
        cls2.setProp2(cls1.getProp1());
        log.info("In method end: {}", cls2);
        return cls2;
    }
}

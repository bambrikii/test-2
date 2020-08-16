package org.bambrikii.examples.spring.aspect;

import org.springframework.stereotype.Component;

@Component
public class MyAspectBean {
    public void shouldThrow() {
        throw new RuntimeException("ex1");
    }
}

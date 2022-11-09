package org.bambrikii.examples.spring.component;

import org.springframework.stereotype.Component;

@Component
public class Component1 {
    public void printMe() {
        System.out.println(this);
    }
}

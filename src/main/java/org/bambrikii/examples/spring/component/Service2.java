package org.bambrikii.examples.spring.component;

import org.springframework.stereotype.Service;

@Service
public class Service2 {
    private final Component1 component1;
    private final Component2 component2;

    public Service2(Component1 component1, Component2 component2) {
        this.component1 = component1;
        this.component2 = component2;
    }

    public void print1() {
        System.out.println("service2.print1()");
        component1.printMe();
    }

    public void print1_2() {
        System.out.println("service2.print1_2()");
        component1.printMe();
    }
}

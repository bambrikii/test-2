package org.bambrikii.examples.spring.component;

import org.springframework.stereotype.Service;

@Service
public class Service1 {
    private final Component1 component1;
    private final Component2 component2;

    public Service1(Component1 component1, Component2 component2) {
        this.component1 = component1;
        this.component2 = component2;
    }

    public void print1() {
        System.out.println("service1.print1()");
        component1.printMe();
    }

    public void print2() {
        System.out.println("service1.print2()");
        component1.printMe();
    }

    public void print1_2() {
        System.out.println("service1.print1_2()");
        component2.printMe();
    }

    public void print2_2() {
        System.out.println("service1.print2_2()");
        component2.printMe();
    }
}

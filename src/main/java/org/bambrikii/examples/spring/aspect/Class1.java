package org.bambrikii.examples.spring.aspect;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Class1 {
    private String prop1;
    private String prop2;
    private String prop3;
    private Class2 class2;
}

package org.bambrikii.examples.springmetrics.beans;

import org.springframework.boot.actuate.endpoint.InvocationContext;
import org.springframework.boot.actuate.endpoint.OperationType;

public class MyOperation implements org.springframework.boot.actuate.endpoint.Operation {
    public MyOperation(String s) {

    }

    @Override
    public OperationType getType() {
        return OperationType.READ;
    }

    @Override
    public Object invoke(InvocationContext context) {
        return String.class;
    }
}

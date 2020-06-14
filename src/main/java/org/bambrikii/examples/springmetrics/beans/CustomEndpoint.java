package org.bambrikii.examples.springmetrics.beans;

import org.springframework.boot.actuate.endpoint.EndpointId;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomEndpoint implements ExposableEndpoint<MyOperation> {

    @Override
    public EndpointId getEndpointId() {
        return EndpointId.of("customEndpoint");
    }

    @Override
    public boolean isEnableByDefault() {
        return true;
    }

    @Override
    public Collection<MyOperation> getOperations() {
        // Custom logic to build the output
        List<MyOperation> messages = new ArrayList<>();
        messages.add(new MyOperation("This is message 1"));
        messages.add(new MyOperation("This is message 2"));
        return messages;
    }
}
package org.bambrikii.examples.springintegration.errorhandling2;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;

/**
 * Created by Alexander Arakelyan on 01/06/18 00:15.
 */
public class Handler1 implements GenericHandler<Object> {
    @Override
    public Object handle(Object payload, MessageHeaders headers) {
        throw new RuntimeException();
    }
}

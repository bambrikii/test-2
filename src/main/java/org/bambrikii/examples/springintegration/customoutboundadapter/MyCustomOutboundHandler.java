package org.bambrikii.examples.springintegration.customoutboundadapter;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * Created by Alexander Arakelyan on 06/06/18 21:46.
 */
public class MyCustomOutboundHandler implements MessageHandler {
    private final String host;

    public MyCustomOutboundHandler(String host) {
        this.host = host;
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

    }
}

package org.bambrikii.examples.springintegration.aggregation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by Alexander Arakelyan on 05/06/18 21:33.
 */
public class AggregationMain {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AggregationConfig.class);
        MessageChannel inputChannel = (MessageChannel) context.getBean("inputChannel");

        inputChannel.send(new GenericMessage<Object>("message1"));
        inputChannel.send(new GenericMessage<Object>("message2"));
        inputChannel.send(new GenericMessage<Object>("message3"));

        Thread.sleep(1000);
    }
}

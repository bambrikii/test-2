package org.bambrikii.examples.springintegration.aggregation;

import org.springframework.integration.aggregator.CorrelationStrategy;
import org.springframework.messaging.Message;

/**
 * Created by Alexander Arakelyan on 05/06/18 22:01.
 */
class MyCorrelationStrategy implements CorrelationStrategy {
    @Override
    public Object getCorrelationKey(Message<?> message) {
        return message.getPayload().getClass();
    }
}

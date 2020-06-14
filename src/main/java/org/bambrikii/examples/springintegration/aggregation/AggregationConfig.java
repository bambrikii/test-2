package org.bambrikii.examples.springintegration.aggregation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

import java.text.MessageFormat;

/**
 * Created by Alexander Arakelyan on 05/06/18 21:33.
 */
@Configuration
@EnableIntegration
public class AggregationConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AggregationConfig.class);

    @Bean
    public MessageChannel inputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel outputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow outputFlow(MessageChannel outputChannel) {
        return IntegrationFlows
                .from(outputChannel)
                .handle(msg -> {
                    LOGGER.info("OUTPUT: {}", msg);
                    System.out.println(MessageFormat.format("OUTPUT: {0}", msg));
                })
                .get();
    }

    @Bean
    public IntegrationFlow inputFlow(
            MessageChannel inputChannel,
            MessageChannel outputChannel
    ) {
        return IntegrationFlows
                .from(inputChannel)
                .wireTap(outputChannel)
                .aggregate(spec ->
                        spec.correlationStrategy(new MyCorrelationStrategy())
                                .releaseStrategy(new MyReleaseStrategy())
                )
//                .handle(new LoggingHandler(LoggingHandler.Level.INFO.name()))
                .channel(outputChannel)
                .get();
    }

}

package org.bambrikii.examples.springintegration;

import java.util.concurrent.Executors;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Alexander Arakelyan on 11/11/17 20:51.
 */
@EnableIntegration
public class Test1Config {
    @Bean
    public MessageChannel inputChannel() {
        return new ExecutorChannel(Executors.newWorkStealingPool());
//		return new ExecutorChannel(Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors() / 2 + 1));
//		return MessageChannels.direct().get();
//		return MessageChannels.publishSubscribe(Executors.newWorkStealingPool()).get();
    }

    @Bean
    public MessageChannel outputChannel() {
//		return new ExecutorChannel(Executors.newWorkStealingPool());
        return MessageChannels.direct().get();
    }

    @ServiceActivator(inputChannel = "outputChannel")
    public void logOutput(Message<?> o) {
        System.out.println("sa: " + Thread.currentThread().getName() + " " + o);
    }

    @Bean
    public StandardIntegrationFlow flow1(MessageChannel inputChannel, MessageChannel outputChannel) {
        return IntegrationFlows.
                from(inputChannel)
                //				.split()
                .enrich(msg -> msg.header("h1", "a1"))
                //				.handle(message -> {
                //				}, messageHandlerGenericEndpointSpec -> {
                //				})
                .log(new Function() {
                    public Object apply(Object o) {
                        System.out.println("lg: " + Thread.currentThread().getName() + " " + o);
                        return o;
                    }

                })
                .channel(outputChannel)
                .get();
    }
}

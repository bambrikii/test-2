package org.bambrikii.examples.springintegration.errorhandling2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.Executors;

/**
 * Created by Alexander Arakelyan on 01/06/18 00:14.
 */
@Configuration
@EnableIntegration
public class ErrorHandlingConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingConfig.class);

	@Bean
	public Handler1 handler1() {
		return new Handler1();
	}

	@Bean
	public IntegrationFlow flow1(Handler1 handler1, MessageChannel inputChannel, MessageChannel outputChannel) {
		return IntegrationFlows
				.from(inputChannel)
				.handle(handler1)
				.channel(outputChannel)
				.get();
	}

	@Bean
	public MessageChannel inputChannel() {
		return MessageChannels.executor(Executors.newSingleThreadExecutor()).get();
	}

	@Bean
	public MessageChannel outputChannel() {
		return MessageChannels.direct().get();
	}

//	@Bean
//	public MessageChannel errorChannel() {
//		return MessageChannels.publishSubscribe().get();
//	}

	@Bean
	public LoggingHandler loggingHandler() {
		LoggingHandler loggingHandler1 = new LoggingHandler(LoggingHandler.Level.INFO);
		loggingHandler1.setLevel(LoggingHandler.Level.INFO);
		return loggingHandler1;
	}

	@Bean
	public IntegrationFlow errorLoggingFlow(MessageChannel errorChannel,
											LoggingHandler loggingHandler) {
		return IntegrationFlows
				.from(errorChannel)
				.handle(loggingHandler)
				.get();
	}

	@Bean
	public IntegrationFlow outputLoggingFlow(MessageChannel outputChannel) {
		return IntegrationFlows
				.from(outputChannel)
				.log()
				.handle(msg -> LOGGER.info("{}", msg))
				.get();
	}
}

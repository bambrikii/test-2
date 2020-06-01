package org.bambrikii.examples.springintegration.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.PollerSpec;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.integration.file.splitter.FileSplitter;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.ErrorHandler;

import java.io.File;

import static org.springframework.integration.dsl.file.Files.splitter;
import static org.springframework.integration.file.splitter.FileSplitter.FileMarker.Mark.END;

/**
 * Created by Alexander Arakelyan on 24/05/18 20:00.
 */
@Configuration
@EnableIntegration
public class ExceptionConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionConfiguration.class);

	@Autowired
	private ApplicationContext context;

//	@Bean
//	public Executor myPollingTaskScheduler() {
//		return Executors.newSingleThreadExecutor();
//	}


	@Bean
	public IntegrationFlow fileReadingFlow(/*Executor myPollingTaskScheduler, *//*ErrorHandler myErrorHandler, */MessageChannel errorChannel) {
		return IntegrationFlows
				.from(s -> s.file(new File("src/test/resources/org/bambrikii/examples/springintegration/")).patternFilter("*.sitest"),
						e -> {
							PollerSpec pollerMetadataSpec = Pollers.fixedDelay(100);
//							pollerMetadataSpec.errorHandler(myErrorHandler);
//							pollerMetadataSpec.taskExecutor(myPollingTaskScheduler);
							e.poller(pollerMetadataSpec);
						})
//						e -> e.poller(Pollers.fixedDelay(100)))
				.split(splitter(true, true))
				.<Object, String>route(p -> p.getClass().getSimpleName(),
						m -> m
								.ignoreSendFailures(true)
								.defaultOutputChannel(errorChannel)
								.channelMapping(String.class.getSimpleName(), "flow1.input")
								.channelMapping(FileSplitter.FileMarker.class.getSimpleName(), "flow2.input")
				)
				.get();
	}

	@Bean
	public ErrorHandler myErrorHandler() {
		return t -> LOGGER.error("ERROR: ", t);
	}

	@Bean
	public IntegrationFlow flow1(MessageChannel channel1, GenericHandler<String> handler1) {
		return f -> f
				.handle(handler1)
				.log()
				.handle(msg -> System.out.println(msg))
//				.channel(channel1)
				;
	}

	@Bean
	public IntegrationFlow flow2(MessageChannel channel2, GenericHandler<FileSplitter.FileMarker> handler2) {
		return f -> f
				.handle(handler2)
				.log()
				.handle(msg -> System.out.println(msg))
//				.channel(channel2)
				;
	}

	@Bean
	public GenericHandler<String> handler1() {
		return ((payload, headers) -> {
			Integer integer = Integer.valueOf(payload.substring(4));
			if (integer % 2 == 0) {
				throw new CustomException("some runtime exception: " + integer);
			}
			return payload;
		});
	}

//	@Bean
//	public ErrorHandler errorHandler() {
//		return new DefaultErrorHandler();
//	}

	@Bean
	public GenericHandler<FileSplitter.FileMarker> handler2() {
		return (payload, headers) -> {
			if (END.equals(payload.getMark())) {
				SpringApplication.exit(context);
			}
			return payload;
		};
	}

	@Bean
	public MessageChannel channel1() {
		return MessageChannels.direct().get();
	}

	@Bean
	public MessageChannel channel2() {
		return MessageChannels.direct().get();
	}


	@Bean
	public MessageChannel errorChannel() {
		return MessageChannels.publishSubscribe().get();
	}

	@Bean
	public IntegrationFlow channel1Flow(MessageChannel channel1) {
		return IntegrationFlows.from(channel1).handle(msg -> LOGGER.debug("channel1: {}", msg)).get();
	}

	@Bean
	public IntegrationFlow channel2Flow(MessageChannel channel2) {
		return IntegrationFlows.from(channel2).handle(msg -> LOGGER.debug("channel2: {}", msg)).get();
	}

	@Bean
	public IntegrationFlow errorFlow(MessageChannel errorChannel) {
		return IntegrationFlows
				.from(errorChannel)
				.handle(msg -> LOGGER.error("ERROR: {}", msg))
				.get();
	}

//	@Bean
//	public IntegrationFlow applicationErrorFlow(MessageChannel errorChannel) {
//		return IntegrationFlows
//				.from("application.errorChannel")
//				.handle(msg -> LOGGER.debug("error: {}", msg))
//				.get();
//	}
}

package org.bambrikii.examples.springmetrics.config;

import org.bambrikii.examples.springmetrics.beans.SampleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.metrics.annotation.Timed;
import org.springframework.metrics.instrument.Counter;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.metrics.instrument.Timer;
import org.springframework.metrics.instrument.simple.SimpleMeterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.bambrikii.examples.springmetrics.beans.SampleBean.RECEIVED_MESSAGES;
import static org.bambrikii.examples.springmetrics.beans.SampleBean.RECEIVED_TIMER;

/**
 * Created by Alexander Arakelyan on 25/04/18 19:50.
 */
@Configuration
@EnableScheduling
@ComponentScan({"org.bambrikii.examples.springmetrics.config", "org.bambrikii.examples.springmetrics.beans"})
@EnableMBeanExport
public class MainConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainConfig.class);

	@Autowired
	private SampleBean sampleBean;
	@Autowired
	private MeterRegistry meterRegistry;
	private Timer timer;

	@Bean
	public MeterRegistry meterRegistry() {
		MeterRegistry registry = new SimpleMeterRegistry();
		return registry;
	}

	@PostConstruct
	public void postConstruct() {
		timer = meterRegistry.timerBuilder("aws_scrape").tags("a", "b", "c", "d").create();
		meterRegistry.register(timer);
	}

	@Scheduled(fixedRate = 1000)
	@Timed(value = "aws_scrape")
	public void scheduled2() throws InterruptedException {
		timer.record(123, TimeUnit.MILLISECONDS);

	}

	@Scheduled(fixedRate = 1000)
	@Timed(value = "aws_scrape")
	public void scheduled3() throws InterruptedException {
		timer.record(123, TimeUnit.MILLISECONDS);
//		meterRegistry.gauge()

	}

	@Scheduled(fixedRate = 1000)
	public void schedule1() throws InterruptedException {
		sampleBean.handleMessage("");
		String receivedMessages = RECEIVED_MESSAGES;
		printCounter(receivedMessages);
		printTimer("aws_scrape", "a", "b");
		printTimer(RECEIVED_TIMER);
	}

	private void printCounter(String receivedMessages) {
		Optional<Counter> meter = meterRegistry.findMeter(Counter.class, receivedMessages);
		if (meter.isPresent()) {
			Counter counter = meter.get();
			LOGGER.info("{} {} {}", counter.getType(), counter.getName(), counter.count());
		}
	}

	private void printTimer(String aws_scrape, String... tags) {
		Optional<Timer> timer = meterRegistry.findMeter(Timer.class, aws_scrape, tags);
		if (timer.isPresent()) {
			Timer timer1 = timer.get();
			LOGGER.info("{} {} [{}] {}", timer1.getType(), timer1.getName(), timer1.getTags(), timer1.totalTime(TimeUnit.MILLISECONDS));
		}
	}
}

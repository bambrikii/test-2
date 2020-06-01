package org.bambrikii.examples.springmetrics.beans;

import org.springframework.metrics.instrument.Counter;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.metrics.instrument.Timer;
import org.springframework.stereotype.Component;

@Component
public class SampleBean {

	public static final String RECEIVED_MESSAGES = "received.messages";
	public static final String RECEIVED_TIMER = "received.timer";
	private final Counter counter;
	private final Timer timer;

	public SampleBean(MeterRegistry registry) {
		this.counter = registry.counter(RECEIVED_MESSAGES);
		this.timer = registry.timer(RECEIVED_TIMER);
	}

	public void handleMessage(String message) {
		this.counter.increment();
		// handle message implementation
//		timer.wrap();
	}

}
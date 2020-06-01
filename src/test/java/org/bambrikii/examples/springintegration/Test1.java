package org.bambrikii.examples.springintegration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by Alexander Arakelyan on 11/11/17 20:49.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableIntegration
//@EnableAutoConfiguration
@ContextConfiguration(
		loader = AnnotationConfigContextLoader.class,
		classes = {Test1Config.class}
)
public class Test1 {
	@Autowired
	private MessageChannel inputChannel;

	@Test
	public void test1() {
		for (int i = 0; i < 100; i++) {
			Message<String> msg = MessageBuilder.withPayload("msg").setHeader("header2", "header2content1").build();
			inputChannel.send(msg);
		}
	}
}

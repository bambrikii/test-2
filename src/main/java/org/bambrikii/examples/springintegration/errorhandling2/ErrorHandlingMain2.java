package org.bambrikii.examples.springintegration.errorhandling2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander Arakelyan on 01/06/18 00:14.
 */
public class ErrorHandlingMain2 {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ErrorHandlingConfig.class);
		MessageChannel inputChannel = (MessageChannel) context.getBean("inputChannel");
		Map<String, Object> headers = new HashMap<>();
		headers.put("errorChannel", "errorChannel");
		GenericMessage<Object> message = new GenericMessage<>("some payload", headers);
		inputChannel.send(message);

	}
}

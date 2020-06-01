package org.bambrikii.examples.springintegration.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class DefaultErrorHandler implements ErrorHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(DefaultErrorHandler.class);

	@Override
	public void handleError(Throwable t) {
		LOGGER.warn("spring jms custom error handling example");
		LOGGER.error(t.getCause().getMessage());
	}
}
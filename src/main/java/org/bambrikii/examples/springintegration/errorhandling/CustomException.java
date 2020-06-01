package org.bambrikii.examples.springintegration.errorhandling;

/**
 * Created by Alexander Arakelyan on 24/05/18 22:10.
 */
public class CustomException extends RuntimeException {
	public CustomException(String message) {
		super(message);
	}
}

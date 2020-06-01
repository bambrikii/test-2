package org.bambrikii.examples.springintegration.errorhandling2;

import org.springframework.integration.dsl.support.GenericHandler;

import java.util.Map;

/**
 * Created by Alexander Arakelyan on 01/06/18 00:15.
 */
public class Handler1 implements GenericHandler<Object> {
	@Override
	public Object handle(Object payload, Map<String, Object> headers) {
		throw new RuntimeException();
	}
}

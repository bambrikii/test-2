package org.bambrikii.examples.cron1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * Created by Alexander Arakelyan on 12.07.16 20:52.
 */
public class CustomJob3 implements Job {
	private static final Logger logger = LoggerFactory.getLogger(CustomJob3.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("custom " + Calendar.getInstance().getTime().toString());
		System.out.println("custom " + Calendar.getInstance().getTime().toString());
	}
}

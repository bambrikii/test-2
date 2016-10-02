package org.bambrikii.examples.cron1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * Created by Alexander Arakelyan on 11.07.16 20:47.
 */
public class HelloJob implements Job {
	private static final Logger logger = LoggerFactory.getLogger(HelloJob.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("info " + Calendar.getInstance().getTime().toString());
		System.out.println("info " + Calendar.getInstance().getTime().toString());
	}
}

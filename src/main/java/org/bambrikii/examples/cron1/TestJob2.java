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
public class TestJob2 implements Job {
	private static final Logger logger = LoggerFactory.getLogger(TestJob2.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("test " + Calendar.getInstance().getTime().toString());
		System.out.println("test " + Calendar.getInstance().getTime().toString());
	}
}

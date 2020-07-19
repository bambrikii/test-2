package org.bambrikii.examples.cron1;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;

/**
 * Created by Alexander Arakelyan on 11.07.16 20:13.
 */
public class CronExpressionTest {
	@Test
	public void testCronExpression() throws ParseException, SchedulerException {
		CronExpression expression = new CronExpression("0 15 10 * * ? 2005");
		Calendar calendar = Calendar.getInstance();
		expression.isSatisfiedBy(calendar.getTime());
	}

	@Test
	public void testScheduler() throws ParseException, SchedulerException, InterruptedException {
//		JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("dummyJobName", "group1").build();
//
//		CronTrigger trigger = TriggerBuilder.newTrigger()
//				.withIdentity("trigger3", "group1")
//				.withSchedule(CronScheduleBuilder.cronSchedule("*/1 * 8-21 * * ?").inTimeZone(TimeZone.getTimeZone("Europe/Moscow")))
//				.forJob("dummyJobName", "group1")
//				.build();
//		;
//
//		// schedule it
//		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//		scheduler.start();
//		scheduler.scheduleJob(job, trigger);
//		Thread.sleep(10000);
	}
}

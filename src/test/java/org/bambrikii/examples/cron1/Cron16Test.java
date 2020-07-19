package org.bambrikii.examples.cron1;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Alexander Arakelyan on 12.07.16 18:41.
 */
public class Cron16Test {
	@Test
	public void test1() throws ParseException, SchedulerException, InterruptedException {
		String group1 = "group1";
		JobDetail job = new JobDetail("job1", group1, HelloJob.class);
		String triggerGroup = "trigger-group1";
		CronTrigger trigger = new CronTrigger("trigger1", triggerGroup, "*/1 * * * * 98 * *");
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);

		JobDetail testJob2 = new JobDetail("job2", group1, TestJob2.class);
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.SECOND, 1);
		SimpleTrigger testTrigger2 = new SimpleTrigger("test-trigger-2", triggerGroup, instance.getTime());
		scheduler.scheduleJob(testJob2, testTrigger2);

		String job3 = "job3";
		JobDetail customJob3 = new JobDetail(job3, group1, CustomJob3.class);
		String customTriggerName1 = "custom-trigger-3";
		CustomTrigger1 customTrigger3 = new CustomTrigger1(customTriggerName1, triggerGroup);
		scheduler.scheduleJob(customJob3, customTrigger3);

//		scheduler.deleteJob(job3, group1);

		Thread.sleep(3000);
		String[] runningJobs = scheduler.getJobNames(group1);
		for (Object job2 : runningJobs) {
			System.out.format("%s %s%n", job2.toString(), job2.getClass().getName());
		}
		Thread.sleep(10000);
	}
}

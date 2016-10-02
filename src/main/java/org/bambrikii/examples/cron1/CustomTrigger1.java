package org.bambrikii.examples.cron1;

import org.quartz.*;
import org.quartz.Calendar;

import java.util.*;

/**
 * Created by Alexander Arakelyan on 12.07.16 20:48.
 */
public class CustomTrigger1 extends Trigger {
	private final TimeZone timeZone;
	private Date endTime;
	private Date nextFireTime;
	private Date previousFireTime;
	private Date startTime;

	public CustomTrigger1(String name, String group) {
		super(name, group);
		setStartTime(new Date());
		this.timeZone = TimeZone.getDefault();
	}

	public void triggered(Calendar calendar) {

	}

	public Date computeFirstFireTime(Calendar calendar) {
		for (this.nextFireTime = this.getFireTimeAfter(new Date(this.getStartTime().getTime() - 1000L)); this.nextFireTime != null && calendar != null && !calendar.isTimeIncluded(this.nextFireTime.getTime()); this.nextFireTime = this.getFireTimeAfter(this.nextFireTime)) {
			;
		}

		return this.nextFireTime;
	}

	public int executionComplete(JobExecutionContext jobExecutionContext, JobExecutionException e) {
		return 0;
	}

	public boolean mayFireAgain() {
		return this.getNextFireTime() != null;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date date) {
		this.startTime = date;
	}

	public void setEndTime(Date date) {
		this.endTime = date;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Date getNextFireTime() {
		return this.nextFireTime;
	}

	public Date getPreviousFireTime() {
		return this.previousFireTime;
	}

	public Date getFireTimeAfter(Date afterTime) {
		if (this.getStartTime().after(afterTime)) {
			afterTime = new Date(this.getStartTime().getTime() - 1000L);
		}

		if (this.getEndTime() != null && afterTime.compareTo(this.getEndTime()) >= 0) {
			return null;
		} else {
			Date pot = getTimeAfter(afterTime);
			return this.getEndTime() != null && pot != null && pot.after(this.getEndTime()) ? null : pot;
		}
	}

	private Date getTimeAfter(Date afterTime) {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.add(java.util.Calendar.SECOND, 1);
		return afterTime;
	}

	public Date getFinalFireTime() {
		return getNextFireTime();
	}

	protected boolean validateMisfireInstruction(int misfireInstruction) {
		return misfireInstruction < 0 ? false : misfireInstruction <= 2;
	}

	public void updateAfterMisfire(Calendar calendar) {

	}

	public void updateWithNewCalendar(Calendar calendar, long l) {

	}


	public static void main(String[] args) throws Exception {
		Date sdt = new Date();
		Date edt = new Date(sdt.getTime() + 55000L);
		CustomTrigger1 st = new CustomTrigger1("t", "g");
		System.err.println();
		st.computeFirstFireTime((Calendar) null);
		System.err.println("lastTime=" + st.getFinalFireTime());
		List times = TriggerUtils.computeFireTimes(st, (Calendar) null, 50);

		for (int i = 0; i < times.size(); ++i) {
			System.err.println("firetime = " + times.get(i));
		}

	}
}

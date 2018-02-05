package org.stathry.jdkdeep.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年11月22日
 */
public class TimerTest {

	@Test
	public void testschedule() {
		Timer timer = new Timer("exec-timerTask");
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}, TimeUnit.MINUTES.toMillis(3));
	}
	
	@Test
	public void testscheduleAtFixedRate() throws IOException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date now = new Date();
		System.out.println("scheduleOpenSwitchFlag service started at " + format.format(now));
		Timer timer = new Timer("schedule-openSwitch");
		
		Date firstTime = format0.parse(format0.format(now));
		System.out.println("firstTime " + format.format(firstTime));
//		long firstTime = TimeUnit.SECONDS.toMillis(10);
		long period = TimeUnit.SECONDS.toMillis(30);
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("scheduleAtFixedRate:" + format.format(new Date()));
			}
			
		}, firstTime, period);
		
		System.in.read();
	}
	
	@Test
	public void testschedulePool() throws IOException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		System.out.println("scheduleOpenSwitchFlag service started at " + format.format(now));
		
		Calendar c = Calendar.getInstance();
		long delay = TimeUnit.MINUTES.toMillis(1) - c.getTimeInMillis() % TimeUnit.MINUTES.toMillis(1);
		
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
		
		exec.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "--scheduleAtFixedRate1:" + format.format(new Date()));
			}
		}, delay , TimeUnit.SECONDS.toMillis(30), TimeUnit.MILLISECONDS);
		
		exec.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "--scheduleAtFixedRate2:" + format.format(new Date()));
			}
		}, delay , TimeUnit.SECONDS.toMillis(30), TimeUnit.MILLISECONDS);
		
		System.in.read();
	}

}

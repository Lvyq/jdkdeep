/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package com.pingan.jdk.deep.lang;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月12日
 */
public class TimerTest {
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new MyTimerTask();
		timer.schedule(task , 1000L, 1000L);
	}

}

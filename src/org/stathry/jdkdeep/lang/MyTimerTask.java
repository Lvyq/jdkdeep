/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.lang;

import java.util.Date;
import java.util.TimerTask;

/**
 * @author stathry@126.com
 * @date 2017年6月12日
 */
public class MyTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println(new Date().getTime());
	}

}

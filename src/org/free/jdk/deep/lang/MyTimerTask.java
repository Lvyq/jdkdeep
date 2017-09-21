/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang;

import java.util.Date;
import java.util.TimerTask;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月12日
 */
public class MyTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println(new Date().getTime());
	}

}

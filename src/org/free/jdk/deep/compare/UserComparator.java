/**
 * Copyright 2012-2016 Deppon Co., Ltd.
 */
package org.free.jdk.deep.compare;

import java.util.Comparator;
import java.util.Date;

/**
 * @author dongdaiming@deppon.com 2016年10月13日
 */
public class UserComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		return o1.getId() > o2.getId() ? 1 : (o1.getId() < o2.getId() ? -1 : 0);
	}

}

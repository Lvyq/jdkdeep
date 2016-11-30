/**
 * Copyright 2012-2016 Deppon Co., Ltd.
 */
package com.deppon.jdk.compare;

/**
 * @author dongdaiming@deppon.com 2016年10月13日
 */
public class User implements Comparable<User> {

	private int id;
	private String name;

	@Override
	public int compareTo(User o) {
		// return this.getId() - o.getId();
		return this.getId() > o.getId() ? 1 : (this.getId() < o.getId() ? -1 : 0);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}

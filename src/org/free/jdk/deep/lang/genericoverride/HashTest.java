package org.free.jdk.deep.lang.genericoverride;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2018年1月18日
 */
public class HashTest {

	@Test
	public void test1() {
		System.out.println(new Object().hashCode());
		System.out.println(new Object().hashCode());
		System.out.println(new Object().hashCode());
	}

	@Test
	public void test2() {
		System.out.println(new PhoneContact("张三", "17621166661").hashCode());
		System.out.println(new PhoneContact("张三", "17621166661").hashCode());
		System.out.println(new PhoneContact("张三", "17621166662").hashCode());
		System.out.println(new PhoneContact("李四", "17621166662").hashCode());
		Assert.assertEquals(new PhoneContact("张三", "17621166661").hashCode(), new PhoneContact("张三", "17621166661").hashCode());
		Assert.assertNotEquals(new PhoneContact("张三", "17621166661").hashCode(), new PhoneContact("李四", "17621166662").hashCode());
		Assert.assertNotEquals(new PhoneContact("张三", "17621166662").hashCode(), new PhoneContact("李四", "17621166662").hashCode());
	}

	static class PhoneContact {

		/** 联系人名称 */
		private String contactName;
		/** 联系人手机号 */
		private String phone;

		public PhoneContact(String contactName, String phone) {
			super();
			this.contactName = contactName;
			this.phone = phone;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
			result = prime * result + ((phone == null) ? 0 : phone.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PhoneContact other = (PhoneContact) obj;
			if (contactName == null) {
				if (other.contactName != null)
					return false;
			} else if (!contactName.equals(other.contactName))
				return false;
			if (phone == null) {
				if (other.phone != null)
					return false;
			} else if (!phone.equals(other.phone))
				return false;
			return true;
		}

	}

}

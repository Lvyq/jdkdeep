package org.free.jdk.deep.lang.genericoverride;

import java.util.Date;

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
			result = prime * result + contactName.hashCode();
			result = prime * result + phone.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if ((getClass() != obj.getClass())) {
				return false;
			}
			PhoneContact o = (PhoneContact) obj;
			return this.phone.equals(o.getPhone()) && this.contactName.equals(o.getContactName());
		}

		public String getContactName() {
			return contactName;
		}

		public void setContactName(String contactName) {
			this.contactName = contactName;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

	}
	
	static class PhoneContact3 {
		
		/** 联系人名称 */
		private String contactName;
		/** 联系人手机号 */
		private String phone;
		/** 创建时间 */
		private Date createTime;
		/** 创建时间 */
		private long sal;
		/** 创建时间 */
		private double height;
		

		public PhoneContact3(String contactName, String phone, Date createTime, long sal, double height) {
			this.contactName = contactName;
			this.phone = phone;
			this.createTime = createTime;
			this.sal = sal;
			this.height = height;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
			result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
			long temp;
			temp = Double.doubleToLongBits(height);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + ((phone == null) ? 0 : phone.hashCode());
			result = prime * result + (int) (sal ^ (sal >>> 32));
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
			PhoneContact3 other = (PhoneContact3) obj;
			if (contactName == null) {
				if (other.contactName != null)
					return false;
			} else if (!contactName.equals(other.contactName))
				return false;
			if (createTime == null) {
				if (other.createTime != null)
					return false;
			} else if (!createTime.equals(other.createTime))
				return false;
			if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
				return false;
			if (phone == null) {
				if (other.phone != null)
					return false;
			} else if (!phone.equals(other.phone))
				return false;
			if (sal != other.sal)
				return false;
			return true;
		}

		public long getSal() {
			return sal;
		}

		public void setSal(long sal) {
			this.sal = sal;
		}

		public double getHeight() {
			return height;
		}

		public void setHeight(double height) {
			this.height = height;
		}

		public String getContactName() {
			return contactName;
		}
		
		public void setContactName(String contactName) {
			this.contactName = contactName;
		}
		
		public String getPhone() {
			return phone;
		}
		
		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		
	}

}

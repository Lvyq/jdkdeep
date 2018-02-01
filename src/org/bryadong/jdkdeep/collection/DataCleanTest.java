package org.bryadong.jdkdeep.collection;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * 数据去重
 * 
 * @date 2018年1月25日
 */
public class DataCleanTest {

	/** 比较次数 */
	private static int count = 0;

	// 可去重
	// TreeSet + Comparator
	@Test
	public void testTreeSetComparator() {
		TreeSet<PhoneContact> contacts = new TreeSet<>((o1, o2) -> {
			count++;
			int c = 0;
			int pc = o1.getPhone().compareTo(o2.getPhone());
			if (pc != 0) {
				c = pc;
			} else {
				c = o1.getContactName().compareTo(o2.getContactName());
			}
			return c;
		});
		contacts.add(new PhoneContact("张三", "17621166661"));
		contacts.add(new PhoneContact("张三", "17621166661"));
		contacts.add(new PhoneContact("李四", "17621166661"));
		contacts.add(new PhoneContact("李四", "17621166662"));
		System.out.println(contacts);
		System.out.println(count);
		Assert.assertEquals(3, contacts.size());
	}
	
	// 可去重
	// TreeSet + impl Comparable
	@Test
	public void testTreeSetComparable() {
		Set<PhoneContact2> contacts = new TreeSet<>();
		contacts.add(new PhoneContact2("张三", "17621166661"));
		contacts.add(new PhoneContact2("张三", "17621166661"));
		contacts.add(new PhoneContact2("李四", "17621166661"));
		contacts.add(new PhoneContact2("李四", "17621166662"));
		Assert.assertEquals(3, contacts.size());
		System.out.println(contacts);
	}

	// 无法去重
	// HashSet + impl Comparable
	@Test
	public void testHashSetComparable() {
		Set<PhoneContact2> contacts = new HashSet<>();
		contacts.add(new PhoneContact2("张三", "17621166661"));
		contacts.add(new PhoneContact2("张三", "17621166661"));
		contacts.add(new PhoneContact2("李四", "17621166661"));
		contacts.add(new PhoneContact2("李四", "17621166662"));
		Assert.assertEquals(4, contacts.size());
		System.out.println(contacts);
	}
	
	// 可去重
	// HashSet + override equals hashcode
	@Test
	public void testHashSetEquals() {
		Set<PhoneContact3> contacts = new HashSet<>();
		contacts.add(new PhoneContact3("张三", "17621166661"));
		contacts.add(new PhoneContact3("张三", "17621166661"));
		contacts.add(new PhoneContact3("李四", "17621166661"));
		contacts.add(new PhoneContact3("李四", "17621166662"));
		Assert.assertEquals(3, contacts.size());
		System.out.println(contacts);
	}


	static class PhoneContact implements Serializable {

		private static final long serialVersionUID = -1444008089771890266L;

		/** 联系人名称 */
		private String contactName;
		/** 联系人手机号 */
		private String phone;

		public PhoneContact(String contactName, String phone) {
			this.contactName = contactName;
			this.phone = phone;
		}

		@Override
		public String toString() {
			return "PhoneContact [contactName=" + contactName + ", phone=" + phone + "]";
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

	static class PhoneContact2 implements Comparable<PhoneContact2>, Serializable {

		private static final long serialVersionUID = 5171332548996647380L;

		/** 联系人名称 */
		private String contactName;
		/** 联系人手机号 */
		private String phone;

		public PhoneContact2(String contactName, String phone) {
			this.contactName = contactName;
			this.phone = phone;
		}

		@Override
		public String toString() {
			return "PhoneContact [contactName=" + contactName + ", phone=" + phone + "]";
		}

		@Override
		public int compareTo(PhoneContact2 o) {
			int c = 0;
			int pc = this.phone.compareTo(o.getPhone());
			if (pc != 0) {
				c = pc;
			} else {
				c = this.contactName.compareTo(o.getContactName());
			}
			return c;
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
	
	static class PhoneContact3 implements Serializable {
		
		private static final long serialVersionUID = 5171332548996647380L;
		
		/** 联系人名称 */
		private String contactName;
		/** 联系人手机号 */
		private String phone;
		
		public PhoneContact3(String contactName, String phone) {
			this.contactName = contactName;
			this.phone = phone;
		}
		
		@Override
		public String toString() {
			return "PhoneContact [contactName=" + contactName + ", phone=" + phone + "]";
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
			if((getClass() != obj.getClass())) {
				return false;
			}
			PhoneContact3 o = (PhoneContact3) obj;
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
}

package org.free.jdk.deep.collection;

import java.io.Serializable;
import java.util.TreeSet;

import org.junit.Test;

/**
 * TODO
 * 
 * @date 2018年1月25日
 */
public class TreeSetTest {

	private static int count = 0;

	@Test
	public void testCleanContacts() {
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
	}

	private static class PhoneContact implements Serializable {

		private static final long serialVersionUID = -1444008089771890266L;

		/** 联系人名称 */
		private String contactName;
		/** 联系人手机号（处理后） */
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
}

package org.bryadong.jdkdeep.classload;

/**
 * TODO
 * 
 * @date 2018年2月2日
 */
public class TempClass2 {

	private String realName;

	private String phone;

	private String cardId;

	public TempClass2(String realName, String phone, String cardId) {
		this.realName = realName;
		this.phone = phone;
		this.cardId = cardId;
	}

	@Override
	public String toString() {
		return "TempClass [realName=" + realName + ", phone=" + phone + ", cardId=" + cardId + "]";
	}

}

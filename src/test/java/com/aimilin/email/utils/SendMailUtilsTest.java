package com.aimilin.email.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

import com.aimilin.domain.MailBean;

public class SendMailUtilsTest {

	@Test
	public void testSend() throws EmailException {
		List<String> toList = new ArrayList<String>();
		for (int i = 1; i <= 4; i++) {
			toList.add("javamail" + i + i + i + i + "@163.com");
		}
		MailBean mail = new MailBean("javamail1111@163.com", "java123456", toList, "测试邮件", "测试邮件内容");
		SendMailUtils mailUtil = new SendMailUtils();
		mailUtil.send(mail);
	}
}

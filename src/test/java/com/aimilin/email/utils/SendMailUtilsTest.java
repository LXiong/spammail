package com.aimilin.email.utils;

import java.util.List;

import org.junit.Test;

import com.aimilin.domain.MailBean;
import com.aimilin.parse.EmailAddressParse;
import com.aimilin.utils.TestUtils;

public class SendMailUtilsTest {

	@Test
	public void testSend() throws Exception {
		EmailAddressParse addressParse = new EmailAddressParse();
		List<String> emails = addressParse.parse("res/收件人.txt");
		System.out.println("邮件总个数:" + emails.size());
		addressParse.setNoRepeat(true);
		TestUtils.print(emails);
		MailBean mail = new MailBean("javamail1111@gmail.com", "java123456", emails, "测试邮件", "测试邮件内容");
		SendMailUtils mailUtil = new SendMailUtils();
		mailUtil.send(mail);
	}
}

package com.aimilin.parse;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.aimilin.domain.EmailAccount;
import com.aimilin.utils.TestUtils;

/**
 * 发件人邮件解析测试类
 * @author LiuJunGuang
 * @date 2013-3-30下午9:54:42
 */
public class EmailAccountParseTest {

	@Test
	public void testEmailAccountParse() throws IOException {
		EmailAccountParse parse = new EmailAccountParse();
		parse.setIgnoreErrorEmail(false);
		List<EmailAccount> accountList = parse.parse("res/发件人.txt");
		TestUtils.print(accountList);
	}

}

package com.aimilin.parse;

import java.io.IOException;

import org.junit.Test;

import com.aimilin.utils.TestUtils;

/**
 * email地址文件解析测试类
 * @author LiuJunGuang
 * @date 2013-3-29下午10:46:22
 */
public class EmailAddressParseTest {

	@Test
	public void testParseString() throws IOException {
		EmailAddressParse parse = new EmailAddressParse();
		parse.setIgnoreErrorEmail(true);
		parse.parse("D:\\workspace\\java\\spammail\\文档\\收件人.txt");
		TestUtils.print(parse.getEmails());
	}
}

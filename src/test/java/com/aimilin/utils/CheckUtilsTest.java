package com.aimilin.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * CheckUtils测试类
 * @author LiuJunGuang
 * @date 2013-3-28下午8:25:16
 */
public class CheckUtilsTest {

	@Test
	public void testEmailAdress() throws IllegalArgumentException {
		Assert.assertTrue(CheckUtils.isEmailAdress("javamail111@163.com"));
		Assert.assertTrue(CheckUtils.isEmailAdress("javamail111@yeah.com"));
		Assert.assertTrue(CheckUtils.isEmailAdress("javamail111@sina.com"));
		Assert.assertTrue(CheckUtils.isEmailAdress("javamail111@gmail.com"));
		Assert.assertTrue(CheckUtils.isEmailAdress("javamail111@gmail.com.cn"));
		Assert.assertFalse(CheckUtils.isEmailAdress("javamail111@gmail."));
		Assert.assertFalse(CheckUtils.isEmailAdress("javamail111@.com"));
		Assert.assertFalse(CheckUtils.isEmailAdress("javamail111gmail.com"));
		Assert.assertFalse(CheckUtils.isEmailAdress("@gmail.com"));
		Assert.assertFalse(CheckUtils.isEmailAdress("j@asdf@gmail.com"));
		Assert.assertFalse(CheckUtils.isEmailAdress("javamail@111@gmail.y.cd.ea"));
		Assert.assertTrue(CheckUtils.isEmailAdress(null));
		Assert.assertTrue(CheckUtils.isEmailAdress(""));
		Assert.assertTrue(CheckUtils.isEmailAdress("  "));
	}

	@Test
	public void testGetSMTPServer() {
		Assert.assertEquals("smtp.yeah.net", CheckUtils.getSMTPServer("aaa@yeah.net"));
		Assert.assertEquals("smtp.yeah.net", CheckUtils.getSMTPServer("aaa@yeah.NET"));
		Assert.assertEquals("smtp.yeah.net", CheckUtils.getSMTPServer("aaa@yeaH.net"));
		Assert.assertEquals("smtp.yeah.net", CheckUtils.getSMTPServer("bbbb@yeah.net"));
	}

}

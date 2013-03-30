package com.aimilin.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.aimilin.exception.NotEmailAddressException;

/**
 * 数据有效性检验工具类
 */
public class CheckUtils {
	private static Pattern pattern = Pattern
			.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

	/**
	 * 检验email地址是否合法，如果合法则返回true，否则返回false
	 * @author LiuJunGuang
	 * @param emailAddress 需要检验的email地址，如果该参数为null或empty则抛出IllegalArgumentException异常
	 * @return boolean 
	 * @date 2013-3-28下午8:20:05
	 */
	public static boolean isEmailAdress(String emailAddress) {
		if (StringUtils.isEmpty(emailAddress))
			throw new IllegalArgumentException("The emailAddress must be not  null or empty!");
		return pattern.matcher(emailAddress).matches();
	}

	/**
	 * 根据邮件地址返回smtp服务器，返回的smtp服务器地址都为小写字母。
	 * 刚方法是将邮件地址"@"符号前面的字符替换为smtp并将"@"符号替换成"."号。
	 * <pre>
	 *    例如：
	 *    java@yeah.net  ==>  smtp.yeah.net
	 *    java@YeaH.net  ==>  smtp.yeah.net
	 *    java@163.com   ==>  smtp.163.com
	 *    java@163.COM   ==>  smtp.163.com
	 * </pre>
	 * 如果emailAddress为null或empty则抛出IllegalArgumentException异常
	 * 如果指定的emailAddress不是一个有效的邮件地址则抛出NotEmailAddressException异常
	 * @author LiuJunGuang
	 * @param emailAddress 电子邮件地址
	 * @return String smtp服务器
	 * @date 2013-3-30下午10:22:09
	 */
	public static String getSMTPServer(String emailAddress) {
		if (!CheckUtils.isEmailAdress(emailAddress))
			throw new NotEmailAddressException(String.format("指定的\"%s\"不是正确的邮件地址！", emailAddress));
		String smtpService = Constants.SMTP_PREFIX + "." + emailAddress.substring(emailAddress.indexOf("@") + 1);
		return smtpService.toLowerCase();
	}
}
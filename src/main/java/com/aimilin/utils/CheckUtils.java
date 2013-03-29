package com.aimilin.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

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
}
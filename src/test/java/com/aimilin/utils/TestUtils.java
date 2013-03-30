package com.aimilin.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * 
 * @author LiuJunGuang
 * @date 2013-3-29下午10:47:44
 */
public class TestUtils {

	public static void print(List<?> strList) {
		if (CollectionUtils.isEmpty(strList))
			return;
		for (Object str : strList) {
			str = str == null ? "" : str.toString();
			System.out.println(str);
		}
	}
}

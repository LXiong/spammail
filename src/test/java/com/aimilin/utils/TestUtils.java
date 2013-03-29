package com.aimilin.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * 
 * @author LiuJunGuang
 * @date 2013-3-29下午10:47:44
 */
public class TestUtils {

	public static void print(List<String> strList) {
		if (CollectionUtils.isEmpty(strList))
			return;
		for (String str : strList) {
			System.out.println(str);
		}
	}
}

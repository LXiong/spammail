package com.aimilin.spammail;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

import com.aimilin.utils.ConfigUtils;

/**
 * 简单的测试类
 * @author LiuJunGuang
 * @date 2013-3-26下午10:16:05
 */
public class MyTest {

	@Test
	public void test() throws ConfigurationException {
		ConfigUtils conf = ConfigUtils.getInstance();
		int width = conf.getInt("window.width");
		System.out.println(width);
		conf.setProperty("aa", "sadfasdf");
		conf.addProperty("asdfasdf", "adsfasdfasdf");
		conf.setAutoSave(true);
		conf.save();
	}
}

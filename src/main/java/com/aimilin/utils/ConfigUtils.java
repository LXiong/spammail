package com.aimilin.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 * 系统配置文件加载类,本类是单例类
 * @author LiuJunGuang
 * @date 2013-3-26下午8:31:47
 */
public class ConfigUtils extends PropertiesConfiguration implements Configuration {
	private static Logger log = Logger.getLogger(ConfigUtils.class);
	private static ConfigUtils conf = null;

	private ConfigUtils() {

	}

	private ConfigUtils(String fileName) throws ConfigurationException {
		super(fileName);
	}

	public static synchronized ConfigUtils getInstance() {
		if (conf != null) {
			return conf;
		}
		try {
			conf = new ConfigUtils(Constants.CONFIG_SYSTEM);
			if (log.isDebugEnabled())
				log.debug(String.format("加载配置文件\"%s\"完成！", Constants.CONFIG_SYSTEM));
		} catch (ConfigurationException e) {
			log.error(String.format("加载配置文件\"%s\"失败！", Constants.CONFIG_SYSTEM), e);
		}
		return conf;
	}

}

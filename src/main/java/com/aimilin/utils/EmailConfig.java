package com.aimilin.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 * 与发送邮件有关的配置信息
 * @author LiuJunGuang
 * @date 2013-4-9下午8:25:36
 */
public class EmailConfig extends PropertiesConfiguration implements Configuration {
	private static Logger log = Logger.getLogger(EmailConfig.class);
	private static EmailConfig conf = null;

	private EmailConfig() {

	}

	private EmailConfig(String fileName) throws ConfigurationException {
		super(fileName);
	}

	public static synchronized EmailConfig getInstance() {
		if (conf != null) {
			return conf;
		}
		try {
			conf = new EmailConfig(Constants.CONFIG_EMAIL);
			if (log.isDebugEnabled())
				log.debug(String.format("加载配置文件\"%s\"完成！", Constants.CONFIG_EMAIL));
		} catch (ConfigurationException e) {
			log.error(String.format("加载配置文件\"%s\"失败！", Constants.CONFIG_EMAIL), e);
		}
		return conf;
	}
}

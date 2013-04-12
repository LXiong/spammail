package com.aimilin.email.utils;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.aimilin.domain.ServerConfigBean;
import com.aimilin.utils.TestUtils;
import com.aimilin.utils.XML2BeanUtils;

/**
 * 服务器配置测试类
 * @author LiuJunGuang
 * @date 2013-4-12下午8:27:30
 */
public class ServerConfigUtilsTest {

	@Test
	public void testSetServerConfigs() throws IOException, SAXException, IntrospectionException {
		List<ServerConfigBean> beans = getServerConfigs();
		ServerConfigUtils configUtils = new ServerConfigUtils();
		configUtils.setServerConfigs(beans);
		String xml = XML2BeanUtils.bean2Xml(configUtils);
		FileUtils.writeStringToFile(FileUtils.getFile("res/serverConfig.xml"), xml);
	}

	@Test
	public void testServerConfig() throws IntrospectionException, IOException, SAXException {
		ServerConfigUtils confs = XML2BeanUtils.xml2Bean(ServerConfigUtils.class,
				FileUtils.readFileToString(FileUtils.getFile("res/serverConfig.xml")));
		TestUtils.print(confs.getServerConfigs());
	}

	private List<ServerConfigBean> getServerConfigs() {
		String[][] serverConfigs = { { "163.com", "smtp.163.com", "pop.163.com", "imap.163.com" },
				{ "yeah.net", "smtp.yeah.net", "pop.yeah.net", "imap.yeah.net" },
				{ "sohu.com", "smtp.sohu.com", "pop3.sogou.com", "imap.sogou.com" },
				{ "sina.cn", "smtp.sina.cn", " pop.sina.cn", "imap.sina.cn" },
				{ "gmail.com", "smtp.gmail.com", "pop.gmail.com", "imap.gmail.com" },
				{ "126.com", "smtp.126.com", "pop.126.com", "imap.126.com" }, };
		List<ServerConfigBean> serverconfigs = new ArrayList<ServerConfigBean>();
		for (String[] strs : serverConfigs) {
			ServerConfigBean conf = new ServerConfigBean();
			conf.setDomain(strs[0]);
			conf.setSmtp(strs[1]);
			conf.setPop(strs[2]);
			conf.setImap(strs[3]);
			conf.setInterval(20);
			conf.setMaxRecipient(30);
			serverconfigs.add(conf);
		}
		return serverconfigs;
	}
}

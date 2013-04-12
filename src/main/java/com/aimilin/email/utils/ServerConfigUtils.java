package com.aimilin.email.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.aimilin.domain.ServerConfigBean;

/**
 * 服务器配置工具类，该类主要是加载配置文件列表和先本地写配置文件
 * @author LiuJunGuang
 * @date 2013-4-12下午8:01:03
 */
public class ServerConfigUtils implements Serializable {
	private static final long serialVersionUID = -561097388314238065L;
	/**
	 * @fields serverConfigs 邮件服务配置列表
	 */
	private List<ServerConfigBean> serverConfigs = null;

	/**
	 * 获取邮件服务配置bean列表
	 * @return serverConfigs
	 */
	public List<ServerConfigBean> getServerConfigs() {
		return serverConfigs;
	}

	/**
	 * 设置邮件服务配置bean列表
	 * @param serverConfigs
	 */
	public void setServerConfigs(List<ServerConfigBean> serverConfigs) {
		this.serverConfigs = serverConfigs;
	}

	/**
	 * 向邮件服务配置列表中添加一个bean对象
	 * @author LiuJunGuang
	 * @param serverConfig
	 * @date 2013-4-12下午8:25:49
	 */
	public void addServerConfig(ServerConfigBean serverConfig) {
		if (CollectionUtils.isEmpty(this.serverConfigs)) {
			this.setServerConfigs(new ArrayList<ServerConfigBean>());
		}
		this.getServerConfigs().add(serverConfig);
	}

}

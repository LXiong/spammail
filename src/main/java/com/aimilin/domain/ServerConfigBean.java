package com.aimilin.domain;

import java.io.Serializable;

/**
 * 邮件服务器配置bean类
 * @author LiuJunGuang
 * @date 2013-4-12下午7:38:19
 */
public class ServerConfigBean implements Serializable {
	private static final long serialVersionUID = -7444491499007838112L;
	/**
	 * @fields domain 邮件域名 例如：163.com
	 */
	private String domain;

	/**
	 * @fields smtp SMTP服务器地址
	 */
	private String smtp;

	/**
	 * @fields imap IMAP服务器地址
	 */
	private String imap;

	/**
	 * @fields pop POP3服务器地址
	 */
	private String pop;

	/**
	 * @fields maxRecipient 收件人最多个数
	 */
	private Integer maxRecipient;

	/**
	 * @fields interval 每逢邮件发送间隔
	 */
	private Integer interval;

	/**
	 * 获取域名
	 * @return domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * 设置域名 ，例如 163.com
	 * @param domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * 获取smtp服务器地址
	 * @return smtp
	 */
	public String getSmtp() {
		return smtp;
	}

	/**
	 * 设置smtp服务器地址
	 * @param smtp
	 */
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	/**
	 * 获取IMAP服务器地址
	 * @return imap
	 */
	public String getImap() {
		return imap;
	}

	/**
	 * 设置IMAP服务器地址
	 * @param imap
	 */
	public void setImap(String imap) {
		this.imap = imap;
	}

	/**
	 * 获取POP3服务器地址
	 * @return pop
	 */
	public String getPop() {
		return pop;
	}

	/**
	 * 设置POP3服务器地址
	 * @param pop
	 */
	public void setPop(String pop) {
		this.pop = pop;
	}

	/**
	 * 获取最多收件人格式
	 * @return maxRecipient
	 */
	public Integer getMaxRecipient() {
		return maxRecipient;
	}

	/**
	 * 设置最多收件人个数
	 * @param maxRecipient
	 */
	public void setMaxRecipient(Integer maxRecipient) {
		this.maxRecipient = maxRecipient;
	}

	/**
	 * 获取每逢邮件发送间隔，单位-秒
	 * @return interval
	 */
	public Integer getInterval() {
		return interval;
	}

	/**
	 * 设置每逢邮件发送间隔，单位-秒
	 * @param interval
	 */
	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "ServerConfigBean [domain=" + domain + ", smtp=" + smtp + ", imap=" + imap + ", pop=" + pop
				+ ", maxRecipient=" + maxRecipient + ", interval=" + interval + "]";
	}

}

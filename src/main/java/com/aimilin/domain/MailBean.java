package com.aimilin.domain;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.aimilin.exception.NotEmailAddressException;
import com.aimilin.utils.Assert;
import com.aimilin.utils.CheckUtils;

/**
 * 发送邮件类
 * @author LiuJunGuang
 * @date 2013-4-8下午9:23:39
 */
public class MailBean {
	/**
	 * @fields userName 用户名
	 */
	private String userName;
	/**
	 * @fields password 密码
	 */
	private String password;
	/**
	 * @fields subject 主题
	 */
	private String subject;
	/**
	 * @fields toList 收件人列表
	 */
	private List<String> toList;
	/**
	 * @fields content 内容
	 */
	private String content;
	/**
	 * @fields to 收件人
	 */
	private String to;

	/**
	 * @fields smtpHost 发送邮件服务器地址
	 */
	private String smtpHost;

	public MailBean() {
		super();
	}

	/**
	 * 发送邮件，如果没有指定主题则默认将根据邮件内容前十五个字符生成主题
	 * @param userName 用户名
	 * @param password 密码
	 * @param toList 收件人列表
	 * @param content 内容
	 */
	public MailBean(String userName, String password, List<String> toList, String content) {
		super();
		this.userName = userName;
		this.password = password;
		this.toList = toList;
		this.content = content;
	}

	/**
	 * 发送邮件，如果没有指定主题则默认将根据邮件内容前十五个字符生成主题
	 * @param userName 用户名
	 * @param password 密码
	 * @param toList 收件人列表
	 * @param subject 主题
	 * @param content 内容
	 */
	public MailBean(String userName, String password, List<String> toList, String subject, String content) {
		super();
		this.userName = userName;
		this.password = password;
		this.toList = toList;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * 发送邮件，如果没有指定主题则默认将根据邮件内容前十五个字符生成主题
	 * @param userName 用户名
	 * @param password 密码
	 * @param to 收件人
	 * @param subject 主题
	 * @param content 内容
	 */
	public MailBean(String userName, String password, String to, String subject, String content) {
		super();
		this.userName = userName;
		this.password = password;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * 发送邮件
	 * @param userName 用户名
	 * @param password 密码
	 * @param toList 收件人列表
	 * @param subject 主题
	 * @param content 内容
	 * @param smtpHost 发送邮件服务器地址
	 */
	public MailBean(String userName, String password, List<String> toList, String subject, String content,
			String smtpHost) {
		super();
		this.userName = userName;
		this.password = password;
		this.subject = subject;
		this.toList = toList;
		this.content = content;
		this.smtpHost = smtpHost;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getToList() {
		return toList;
	}

	public void setToList(List<String> toList) {
		this.toList = toList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * 检测邮件内容的有效性，主题未检测
	 * @author LiuJunGuang
	 * @date 2013-4-8下午10:57:48
	 */
	public void check() {
		Assert.notBlank(this.getUserName(), "发件人不能为空！");
		Assert.notBlank(this.getPassword(), "发件人密码不能为空！");
		Assert.notBlank(this.getContent(), "邮件内容不能为空！");
		if (StringUtils.isEmpty(to) && CollectionUtils.isEmpty(toList))
			throw new IllegalArgumentException("收件人不能为空！");
		if (!CheckUtils.isEmailAdress(this.getUserName()))
			throw new NotEmailAddressException(String.format("发件人\"%s\"不是有效的邮件地址！", this.getUserName()));
		if (StringUtils.isNotEmpty(getTo())) {
			if (!CheckUtils.isEmailAdress(this.getTo()))
				throw new NotEmailAddressException(String.format("收件人\"%s\"不是有效的邮件地址！", this.getTo()));
		}
		if (CollectionUtils.isNotEmpty(toList)) {
			for (String address : toList) {
				if (!CheckUtils.isEmailAdress(address))
					throw new NotEmailAddressException(String.format("收件人列表中\"%s\"不是有效的邮件地址！", address));
			}
		}
	}

	/**
	 * 获取邮件地址，如果收件人列表或收件人都为null或空则返回null，否则返回地址列表
	 * @author LiuJunGuang
	 * @return List<InternetAddress>
	 * @throws AddressException
	 * @date 2013-4-8下午10:14:41
	 */
	public List<InternetAddress> getInternetAddress() {
		if (CollectionUtils.isEmpty(toList) && StringUtils.isEmpty(to))
			return null;
		if (CollectionUtils.isEmpty(toList))
			toList = new ArrayList<String>();
		CollectionUtils.addIgnoreNull(toList, to);
		List<InternetAddress> intAdds = new ArrayList<InternetAddress>();
		for (String toStr : toList) {
			try {
				intAdds.add(new InternetAddress(toStr));
			} catch (AddressException e) {
				Logger.getLogger(MailBean.class).error("错误的邮件地址：" + toStr, e);
			}
		}
		return intAdds;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

}

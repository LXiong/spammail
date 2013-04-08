package com.aimilin.email.utils;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.aimilin.domain.MailBean;
import com.aimilin.utils.Assert;
import com.aimilin.utils.CheckUtils;

/**
 * 邮件发送工具类
 * @author LiuJunGuang
 * @date 2013-4-8下午9:42:50
 */
public class SendMailUtils {
	private boolean isBcc = true;
	private boolean isDebug = true;
	private int port = 465;
	private boolean isSSL = true;
	private String charset = "UTF-8";

	private HtmlEmail email = null;

	/**
	 * 发送邮件
	 * @author LiuJunGuang
	 * @param mail
	 * @throws EmailException
	 * @date 2013-4-8下午9:42:57
	 */
	public void send(MailBean mail) throws EmailException {
		Assert.notNull(mail);
		try {
			mail.check();
		} catch (Exception e) {
			throw new EmailException("发送失败！原因：" + e.getMessage());
		}
		email = new HtmlEmail();
		init(mail);
		email.setAuthentication(mail.getUserName(), mail.getPassword());
		email.send();
	}

	/**
	 * 初始化Email服务器相关的信息
	 * @author LiuJunGuang
	 * @param email
	 * @throws EmailException
	 * @throws AddressException
	 * @date 2013-4-8下午9:51:10
	 */
	private void init(MailBean mail) throws EmailException {
		email.setHostName(CheckUtils.getSMTPServer(mail.getUserName()));
		email.setFrom(mail.getUserName());
		setTo(mail.getInternetAddress());
		email.setDebug(isDebug);
		email.setSubject(mail.getSubject());
		email.setHtmlMsg(mail.getContent());
		email.setSmtpPort(port);
		email.setCharset(charset);
		email.setSSLOnConnect(isSSL);
	}

	private void setTo(List<InternetAddress> internetAddress) throws EmailException {
		if (isBcc) {
			email.setTo(internetAddress.subList(0, 1));
			if (internetAddress.size() > 1)
				email.setBcc(internetAddress.subList(1, internetAddress.size()));
		} else {
			email.setTo(internetAddress);
		}
	}
}

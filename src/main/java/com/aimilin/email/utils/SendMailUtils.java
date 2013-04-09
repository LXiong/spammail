package com.aimilin.email.utils;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.aimilin.domain.MailBean;
import com.aimilin.utils.Assert;
import com.aimilin.utils.CheckUtils;
import com.aimilin.utils.EmailConfig;

/**
 * 邮件发送工具类
 * @author LiuJunGuang
 * @date 2013-4-8下午9:42:50
 */
public class SendMailUtils {
	private EmailConfig conf = EmailConfig.getInstance();
	private boolean isBcc = conf.getBoolean("email.isBcc", true);
	private boolean isDebug = conf.getBoolean("email.isDebug", true);
	private int port = conf.getInt("email.port", 465);
	private boolean isSSL = conf.getBoolean("email.isSSL", true);
	private String charset = conf.getString("email.cherset", "UTF-8");

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
		// 设置收件人地址
		setTo(mail.getInternetAddress());
		email.setDebug(isDebug);
		email.setSubject(createSubject(mail.getSubject(), mail.getContent()));
		email.setHtmlMsg(mail.getContent());
		email.setCharset(charset);
		if (isSSL) {
			email.setSSLOnConnect(isSSL);
			email.setSmtpPort(port);
		}
	}

	/**
	 * 设置邮件主题内容，如果指定的mail没有主题内容则根据邮件内容自动生成。
	 * 
	 * <pre>
	 * 生成规则：根据邮件内容指定的前 n（配置文件 email.subject_char_count）个字符自动生成主题。
	 * 如果内容字符数少于n则取全部内容，并且主题最多为50个字符
	 * </pre>
	 * @author LiuJunGuang
	 * @param mail
	 * @return
	 * @date 2013-4-9下午8:38:54
	 */
	private String createSubject(String subject, String content) {
		if (StringUtils.isNotEmpty(subject))
			return subject;
		int count = conf.getInt("email.subject_char_count", 50);
		count = (0 < count && count <= 50) ? count : 50;
		count = count <= content.length() ? count : content.length();
		return content.substring(0, count);
	}

	/**
	 * 设置收件人，如果采用密送方式则设置列表中的第一个地址为收件人地址其他为密送地址，否则全为收件人地址
	 * @author LiuJunGuang
	 * @param internetAddress
	 * @throws EmailException
	 * @date 2013-4-9下午8:38:56
	 */
	private void setTo(List<InternetAddress> internetAddress) throws EmailException {
		if (CollectionUtils.isEmpty(internetAddress)) {
			throw new EmailException("指定的收件人地址不能为空！");
		}
		if (isBcc) {
			email.setTo(internetAddress.subList(0, 1));
			if (internetAddress.size() > 1)
				email.setBcc(internetAddress.subList(1, internetAddress.size()));
		} else {
			email.setTo(internetAddress);
		}
	}
}

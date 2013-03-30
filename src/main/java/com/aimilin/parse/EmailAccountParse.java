package com.aimilin.parse;

import java.util.ArrayList;
import java.util.List;

import com.aimilin.domain.EmailAccount;
import com.aimilin.exception.LineParseException;
import com.aimilin.exception.NotEmailAddressException;
import com.aimilin.utils.CheckUtils;

/**
 * Email账户邮件解析器，解析出发件人的信息包括发件地址和发送密码,待解析的文件必须符合以下格式：
 * <pre>
 *  #注释行
 *  第一列用户email地址，第二列密码
 *  aaaa@gmail.com     aaaaa
 * </pre>
 * @author LiuJunGuang
 * @date 2013-3-30下午9:29:24
 */
public class EmailAccountParse extends FileParseTemplate {
	/**
	 * @fields emailAccountList 用户邮件账户列表
	 */
	private List<EmailAccount> emailAccountList;

	/**
	 * @fields ignoreErrorEmail 是否忽略错误的邮件地址，true忽略，false不忽略。
	 * 默认为true 及忽略错误的邮件地址
	 */
	private boolean ignoreErrorEmail = true;

	public EmailAccountParse() {
		super();
		emailAccountList = new ArrayList<EmailAccount>();
	}

	@Override
	protected void parseLine(String lineStr, int lineNum) throws LineParseException {
		if (this.isComment(lineStr) || this.isEmpty(lineStr))
			return;
		String[] strArray = lineStr.trim().split("\\s+");
		if (strArray == null || strArray.length < 2) {
			if (ignoreErrorEmail)
				return;
			throw new LineParseException(String.format("指定的行%d格式不正确！正确的格式如：aaa@163.com    aaaaa", lineNum));
		}
		try {
			String email = strArray[0];
			if (!CheckUtils.isEmailAdress(email)) {
				if (ignoreErrorEmail)
					return;
				throw new NotEmailAddressException(String.format("指定的\"%s\"不是正确的邮件地址！", email));
			}
			EmailAccount account = new EmailAccount();
			account.setEmail(email.trim());
			account.setPassword(strArray[1].trim());
			emailAccountList.add(account);
		} catch (Exception e) {
			if (ignoreErrorEmail)
				return;
			throw new LineParseException(String.format("解析第%d行时出错！" + e.getMessage(), lineNum));
		}

	}

	/**
	 * 获取用户邮件账户列表
	 * @author LiuJunGuang
	 * @return List<EmailAccount>
	 * @date 2013-3-30下午9:35:38
	 */
	public List<EmailAccount> getEmailAccountList() {
		return emailAccountList;
	}

	/**
	 * 获取是否忽略错误的邮件地址状态值，true忽略，false不忽略
	 * @author LiuJunGuang
	 * @return boolea
	 * @date 2013-3-30下午9:37:56
	 */
	public boolean isIgnoreErrorEmail() {
		return ignoreErrorEmail;
	}

	/**
	 * 设置是否忽略错误的邮件地址，true忽略，false不忽略，如果设置为true则直接跳过错误的邮件地址，
	 * 否则抛出NotEmailAddressException异常
	 * @author LiuJunGuang
	 * @param ignoreErrorEmail boolean true 忽略错误的邮件地址，false不忽略错误的邮件地址
	 * @date 2013-3-30下午9:37:57
	 */
	public void setIgnoreErrorEmail(boolean ignoreErrorEmail) {
		this.ignoreErrorEmail = ignoreErrorEmail;
	}

}

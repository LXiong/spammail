package com.aimilin.parse;

import com.aimilin.exception.LineParseException;
import com.aimilin.exception.NotEmailAddressException;
import com.aimilin.utils.CheckUtils;

/**
 * 文件地址解析器，要求给定的文件必须是每个单独的邮件地址占一行，使用#开头的行为注释行，注释行将不解析
 * @author LiuJunGuang
 * @date 2013-3-29下午10:23:26
 */
public class EmailAddressParse extends FileParseTemplate<String> {
	/**
	 * @fields ignoreErrorEmail 是否忽略错误的邮件地址，true - 忽略，false 不忽略， 不忽略错误的邮件地址当解析到错误的邮件地址时会抛出NotEmailAddressException异常
	 */
	private boolean ignoreErrorEmail = true;

	public EmailAddressParse() {
		super();
	}

	@Override
	protected String parseLine(String lineStr, int lineNum) throws LineParseException {
		if (this.isComment(lineStr) || this.isEmpty(lineStr))
			return null;
		String emailAdress = lineStr.trim();
		if (CheckUtils.isEmailAdress(emailAdress)) {
			return emailAdress;
		} else {
			try {
				if (ignoreErrorEmail)
					return null;
				throw new NotEmailAddressException(String.format("指定的\"%s\"不是正确的邮件地址！", emailAdress));
			} catch (Exception e) {
				throw new LineParseException(String.format("解析第%d行时出错！" + e.getMessage(), lineNum));
			}
		}
	}

	/**
	 * 获取是否忽略错误邮件地址值
	 * @author LiuJunGuang
	 * @return boolean， 忽略返回 true，不忽略返回false
	 * @date 2013-3-29下午10:37:33
	 */
	public boolean isIgnoreErrorEmail() {
		return ignoreErrorEmail;
	}

	/**
	 * 设置是否忽略错误的邮件地址，true - 忽略，false 不忽略， 不忽略错误的邮件地址当解析到错误的邮件地址时会抛出NotEmailAddressException异常
	 * @author LiuJunGuang
	 * @param ignoreErrorEmail
	 * @date 2013-3-29下午10:37:07
	 */
	public void setIgnoreErrorEmail(boolean ignoreErrorEmail) {
		this.ignoreErrorEmail = ignoreErrorEmail;
	}

}

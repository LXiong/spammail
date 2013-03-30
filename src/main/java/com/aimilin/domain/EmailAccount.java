package com.aimilin.domain;

/**
 * email账户信息
 * @author LiuJunGuang
 * @date 2013-3-30下午9:30:58
 */
public class EmailAccount {
	/**
	 * @fields email 发件人邮件地址
	 */
	private String email;
	/**
	 * @fields password 账户密码
	 */
	private String password;

	public EmailAccount() {
		super();
	}

	/**
	 * 初始化用户邮件账户
	 * @param email 邮件账户
	 * @param password 密码
	 */
	public EmailAccount(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	/**
	 * 获取邮件账户
	 * @author LiuJunGuang
	 * @return String 
	 * @date 2013-3-30下午9:33:40
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 获取密码
	 * @author LiuJunGuang
	 * @return String
	 * @date 2013-3-30下午9:33:41
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置邮件地址
	 * @author LiuJunGuang
	 * @param email
	 * @date 2013-3-30下午9:33:43
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 设置密码
	 * @author LiuJunGuang
	 * @param password
	 * @date 2013-3-30下午9:33:44
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmailAccount [email=" + email + ", password=" + password + "]";
	}

}

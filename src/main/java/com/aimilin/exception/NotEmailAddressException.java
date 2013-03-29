package com.aimilin.exception;

/**
 * 如果解析的邮件地址不是邮件地址，将抛出该异常信息
 * @author LiuJunGuang
 * @date 2013-3-29下午10:28:13
 */
public class NotEmailAddressException extends RuntimeException {

	private static final long serialVersionUID = -8368399898382867455L;

	public NotEmailAddressException() {
		super();
	}

	public NotEmailAddressException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEmailAddressException(String message) {
		super(message);
	}

	public NotEmailAddressException(Throwable cause) {
		super(cause);
	}

}

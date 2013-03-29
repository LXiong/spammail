package com.aimilin.exception;

/**
 * 解析文件某一行失败将抛出改异常信息
 * @author LiuJunGuang
 * @date 2013-3-29下午9:55:06
 */

public class LineParseException extends RuntimeException {
	private static final long serialVersionUID = -6003989098910275628L;

	public LineParseException() {
		super();
	}

	public LineParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public LineParseException(String message) {
		super(message);
	}

	public LineParseException(Throwable cause) {
		super(cause);
	}

}

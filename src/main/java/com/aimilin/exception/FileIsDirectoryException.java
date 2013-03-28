package com.aimilin.exception;

/**
 * 如果指定的文件是目录将会抛出该异常
 * @author LiuJunGuang
 * @date 2013-3-28下午8:42:34
 */
public class FileIsDirectoryException extends RuntimeException {
	private static final long serialVersionUID = -681232819394896403L;

	public FileIsDirectoryException() {
		super();
	}

	public FileIsDirectoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileIsDirectoryException(String message) {
		super(message);
	}

	public FileIsDirectoryException(Throwable cause) {
		super("Need a file but get a directory！", cause);
	}
}

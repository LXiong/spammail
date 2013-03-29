package com.aimilin.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.aimilin.exception.FileIsDirectoryException;

/**
 * 文件解析类
 * @author LiuJunGuang
 * @date 2013-3-29下午9:20:04
 */
public interface Parse {

	/**
	 * 文件解析类，该方法主要是去除文件中的注释行（以#开头的行）和空行，并返回文件的所有行
	 * @author LiuJunGuang
	 * @param file 需要解析的文件信息
	 * @return List&lt;String&gt; 所有的文件行信息
	 * @throws IOException  如果文件读取失败将会抛出改异常信息
	 * @throws FileNotFoundException 如果文件不存在则抛出该异常
	 * @throws FileIsDirectoryException 如果指定的文件是个目录将抛出该异常
	 * @date 2013-3-28下午8:37:12
	 */
	public void parse(File file) throws IOException;

	/**
	 * 根据指纹的文件名称解析文件内容，该方法主要是去除文件中的注释行（以#开头的行）和空行，并返回文件的所有行
	 * @author LiuJunGuang
	 * @param  fileName 文件名称
	 * @return List&lt;String&gt; 所有的文件行信息
	 * @throws IOException  如果文件读取失败将会抛出改异常信息
	 * @throws FileNotFoundException 如果文件不存在则抛出该异常
	 * @throws FileIsDirectoryException 如果指定的文件是个目录将抛出该异常
	 * @throws IOException
	 * @date 2013-3-28下午8:59:18
	 */
	public void parse(String fileName) throws IOException;

}

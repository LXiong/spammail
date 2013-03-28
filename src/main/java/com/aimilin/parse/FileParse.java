package com.aimilin.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.aimilin.exception.FileIsDirectoryException;
import com.aimilin.utils.Assert;

/**
 * 该类是所有文件解析类的父类，该类主要是去除文件中注释行
 * @author LiuJunGuang
 * @date 2013-3-28下午8:35:56
 */
public class FileParse {

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
	public List<String> prase(File file) throws IOException {
		Assert.notNull(file, "指定的文件不能为null！");
		if (!file.exists()) {
			throw new FileNotFoundException("指定的文件\"" + file + "\"找到不到！");
		}
		if (file.isDirectory()) {
			throw new FileIsDirectoryException("文件\"" + file + "\"是个目录不是文件！");
		}
		return removeComment(FileUtils.readLines(file));
	}

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
	public List<String> parse(String fileName) throws IOException {
		File file = FileUtils.getFile(fileName);
		return prase(file);
	}

	/**
	 * 去除字符串中的所有空字符串和所有以#开头的字符串，如果给定的参数为null或空列表则返回null或空列表
	 * @author LiuJunGuang
	 * @param lines 需要去除的字符串列表
	 * @return List&lt;String&gt; 字符串列表
	 * @date 2013-3-28下午9:04:08
	 */
	private List<String> removeComment(List<String> lines) {
		if (CollectionUtils.isEmpty(lines)) {
			return lines;
		}
		List<String> strList = new ArrayList<String>(lines.size());
		for (String line : lines) {
			if (StringUtils.isBlank(line))
				continue;
			if (line.trim().startsWith("#"))
				continue;
			strList.add(line);
		}
		return strList;
	}
}

package com.aimilin.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.aimilin.exception.FileIsDirectoryException;
import com.aimilin.exception.LineParseException;
import com.aimilin.utils.Assert;

/**
 * 简单的文件解析类，将文件解析成String列表
 * @author LiuJunGuang
 * @date 2013-3-29下午9:22:56
 */
public abstract class FileParseTemplate implements Parse {

	@Override
	public void parse(File file) throws IOException {
		Assert.notNull(file, "指定的文件不能为null！");
		if (!file.exists()) {
			throw new FileNotFoundException("指定的文件\"" + file + "\"找到不到！");
		}
		if (file.isDirectory()) {
			throw new FileIsDirectoryException("文件\"" + file + "\"是个目录不是文件！");
		}
		List<String> lines = FileUtils.readLines(file);
		if (CollectionUtils.isEmpty(lines))
			return;
		for (int i = 0; i < lines.size(); i++) {
			parseLine(lines.get(i), i + 1);
		}
	}

	@Override
	public void parse(String fileName) throws IOException {
		File file = FileUtils.getFile(fileName);
		this.parse(file);
	}

	/**
	 * 解析指定行,所有子类都需要实现该方法
	 * @author LiuJunGuang
	 * @param lineStr 行字符串
	 * @param lineNum 行号
	 * @throws LineParseException 如果行解析失败将抛出该异常信息
	 * @date 2013-3-29下午9:50:47
	 */
	protected abstract void parseLine(String lineStr, int lineNum) throws LineParseException;

	/**
	 * 判断指定的行字符串是否是注释行，如果是注释行（以#开头行）则返回true，否则返回false，空行将返回false
	 * @author LiuJunGuang
	 * @param lineStr 行字符串
	 * @return true 注释行（#开头的行），false 不是注释行
	 * @date 2013-3-29下午10:01:47
	 */
	public boolean isComment(String lineStr) {
		if (StringUtils.isBlank(lineStr))
			return false;
		if (lineStr.trim().startsWith("#"))
			return true;
		return false;
	}

	/**
	 * 判断给定的行是否是空行，如果字符串为null或空字符串则返回true,否则返回false
	 * @author LiuJunGuang
	 * @param lineStr
	 * @return
	 * @date 2013-3-29下午10:01:48
	 */
	public boolean isEmpty(String lineStr) {
		if (StringUtils.isBlank(lineStr))
			return true;
		return false;
	}

}

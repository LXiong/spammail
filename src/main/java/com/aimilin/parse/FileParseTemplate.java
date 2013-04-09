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
import com.aimilin.exception.LineParseException;
import com.aimilin.utils.Assert;

/**
 * 简单的文件解析类，将文件解析成String列表
 * @author LiuJunGuang
 * @date 2013-3-29下午9:22:56
 */
public abstract class FileParseTemplate<T> implements Parse<T> {
	/**
	 * @fields noRepeat 是否出去重复，如果为true则去除重复数据，否则保存所有数据
	 */
	private boolean noRepeat = true;

	@Override
	public List<T> parse(File file) throws IOException {
		Assert.notNull(file, "指定的文件不能为null！");
		if (!file.exists()) {
			throw new FileNotFoundException("指定的文件\"" + file + "\"找到不到！");
		}
		if (file.isDirectory()) {
			throw new FileIsDirectoryException("文件\"" + file + "\"是个目录不是文件！");
		}
		List<String> lines = FileUtils.readLines(file);
		if (CollectionUtils.isEmpty(lines))
			return null;
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < lines.size(); i++) {
			T obj = parseLine(lines.get(i), i + 1);
			if (obj == null)
				continue;
			if (noRepeat) {// 去除重复数据
				if (list.contains(obj))
					continue;
			}
			list.add(obj);
		}
		return list;
	}

	@Override
	public List<T> parse(String fileName) throws IOException {
		File file = FileUtils.getFile(fileName);
		return this.parse(file);
	}

	/**
	 * 获取是否出去重复数据的值，true为去除重复数据，false为保存所有数据
	 * @author LiuJunGuang
	 * @return boolean
	 * @date 2013-4-9下午10:18:10
	 */
	public boolean isNoRepeat() {
		return noRepeat;
	}

	/**
	 * 设置是否除去重复数据，true为除去重复数据，false为保存所有数据，默认为true
	 * @author LiuJunGuang
	 * @param noRepeat 是否去除重复数据
	 * @date 2013-4-9下午10:18:12
	 */
	public void setNoRepeat(boolean noRepeat) {
		this.noRepeat = noRepeat;
	}

	/**
	 * 解析指定行,所有子类都需要实现该方法
	 * @author LiuJunGuang
	 * @param lineStr 行字符串
	 * @param lineNum 行号
	 * @throws LineParseException 如果行解析失败将抛出该异常信息
	 * @date 2013-3-29下午9:50:47
	 */
	protected abstract T parseLine(String lineStr, int lineNum) throws LineParseException;

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

package com.aimilin.parse;

import java.util.ArrayList;
import java.util.List;

import com.aimilin.exception.LineParseException;

/**
 * 去除字符串中的所有空字符串和所有以#开头的字符串，如果给定的参数为null或空列表则返回null或空列表
 * @author LiuJunGuang
 * @date 2013-3-28下午8:35:56
 */
public class CommentFileParse extends FileParseTemplate {
	private List<String> lines;

	public CommentFileParse() {
		super();
		lines = new ArrayList<String>();
	}

	@Override
	protected void parseLine(String lineStr, int lineNum) throws LineParseException {
		if (this.isComment(lineStr) || this.isEmpty(lineStr))
			return;
		lines.add(lineStr);
	}

	/**
	 * 获取解析之后的非空行和注释行
	 * @author LiuJunGuang
	 * @return
	 * @date 2013-3-29下午10:12:21
	 */
	public List<String> getLines() {
		return lines;
	}

}

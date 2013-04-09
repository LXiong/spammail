package com.aimilin.parse;

import com.aimilin.exception.LineParseException;

/**
 * 去除字符串中的所有空字符串和所有以#开头的字符串，如果给定的参数为null或空列表则返回null或空列表
 * @author LiuJunGuang
 * @date 2013-3-28下午8:35:56
 */
public class CommentFileParse extends FileParseTemplate<String> {

	@Override
	protected String parseLine(String lineStr, int lineNum) throws LineParseException {
		if (this.isComment(lineStr) || this.isEmpty(lineStr))
			return null;
		return lineStr;
	}
}

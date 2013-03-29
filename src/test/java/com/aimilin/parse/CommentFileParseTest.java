package com.aimilin.parse;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

/**
 * 文件解析测试类
 * @author LiuJunGuang
 * @date 2013-3-28下午9:10:45
 */
public class CommentFileParseTest {

	@Test
	public void testPraseFileName() throws IOException {
		CommentFileParse parse = new CommentFileParse();
		String path = CommentFileParseTest.class.getClassLoader().getResource("conf.properties").getPath();
		System.out.println(path);
		parse.parse(path);
		print(parse.getLines());
	}

	private void print(List<String> strList) {
		if (CollectionUtils.isEmpty(strList))
			return;
		for (String str : strList) {
			System.out.println(str);
		}
	}

}

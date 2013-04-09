package com.aimilin.parse;

import java.io.IOException;

import org.junit.Test;

import com.aimilin.utils.TestUtils;

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
		TestUtils.print(parse.parse(path));
	}

}

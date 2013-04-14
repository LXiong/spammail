package com.aimilin.utils;

import javax.swing.JComponent;

import com.aimilin.frame.SendFrame;

/**
 * 界面创建工厂类，单例类实现
 * @author LiuJunGuang
 * @date 2013-4-11下午9:22:40
 */
public class FrameFactory {
	private static FrameFactory factory = new FrameFactory();

	/**
	 * @fields sendFrame 发送邮件界面
	 */
	private SendFrame sendFrame = null;

	/**
	 * 私有的构造子
	 */
	private FrameFactory() {
		super();
	}

	/**
	 * 获取工厂类的实例方法
	 * @author LiuJunGuang
	 * @return FrameFactory
	 * @date 2013-4-11下午9:24:31
	 */
	public static FrameFactory getInstance() {
		return factory;
	}

	/**
	 * 获取发送邮件界面类
	 * @author LiuJunGuang
	 * @return JComponent
	 * @date 2013-4-11下午9:27:13
	 */
	public JComponent getSendMailUI() {
		if (sendFrame == null) {
			sendFrame = new SendFrame();
		}
		return sendFrame;
	}

}

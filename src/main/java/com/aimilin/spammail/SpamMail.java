package com.aimilin.spammail;

import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.aimilin.frame.MainFrame;

/**
 * 程序入口主类
 * @author LiuJunGuang
 * @date 2013-3-26下午8:21:18
 */
public class SpamMail {
	private static final Logger logger = Logger.getLogger(SpamMail.class);
	public static MainFrame mainFrame = null;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// 设置界面为本地模式
		} catch (Exception e) {
			logger.error("不支持的界面风格！", e);
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame = new MainFrame();
				mainFrame.setVisible(true);
			}
		});
	}
}

package com.aimilin.utils;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import com.aimilin.spammail.SpamMail;

public class FrameUtils {
	private static final Logger logger = Logger.getLogger(FrameUtils.class);
	private static ConfigUtils conf = ConfigUtils.getInstance();

	/**
	 * 显示一条消息
	 * @author LiuJunGuang
	 * @param message 要显示的消息内容
	 * @date 2012-11-17下午5:23:04
	 */
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(SpamMail.mainFrame, message, conf.getString("message.title", "提示"),
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 弹出窗口选择文件，并返回文件的路径，如果没有选择文件则返回null
	 * @author LiuJunGuang
	 * @return path 文件的全路径
	 * @date 2012-11-18下午6:03:08
	 */
	public static String selectFile() {
		JFileChooser chooser = new JFileChooser();// 构造一个当前路径的文件选择器
		FileNameExtensionFilter filter = new FileNameExtensionFilter(conf.getString("file.filter.message", "选择文件"),
				conf.getStringArray("file.filter"));
		chooser.setFileFilter(filter);
		if (chooser.showOpenDialog(SpamMail.mainFrame) == JFileChooser.APPROVE_OPTION) {// 如果选择确定键
			File file = chooser.getSelectedFile();
			String path = file.getPath();
			logger.debug("您选择的文件是: " + path);
			return path;
		}
		return null;
	}

	/**
	 * 选择文件夹，如果选中则返回文件夹的全路径，否则返回null
	 * @author LiuJunGuang
	 * @param fileDirTF
	 * @date 2012-11-21下午7:24:29
	 */
	public static String selectDirFile() {
		File f = new File(".");// 得到当前user工作目录
		JFileChooser chooser = new JFileChooser(f);// 构造一个当前路径的文件选择器
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setApproveButtonText("确定");
		chooser.setDialogTitle("选择文件夹");
		if (chooser.showOpenDialog(SpamMail.mainFrame) == JFileChooser.APPROVE_OPTION) {// 如果选择确定键
			File f1 = chooser.getSelectedFile();// 得到选择的文件
			String path = f1.getPath();
			logger.debug("您选择的文件夹是：" + path);
			return path;
		}
		return null;
	}

	/**
	 * 创建按钮
	 * @author LiuJunGuang
	 * @param name 按钮上显示的字符串
	 * @param showText 是否显示文字，ture 显示，false不显示
	 * @param iconName icon名称
	 * @param showIcon 是否显示图标，true显示，false不显示
	 * @return JButton
	 * @date 2013-4-2下午9:53:24
	 */
	public static JButton createButton(String name, boolean showText, String iconName, boolean showIcon,
			ActionListener al) {
		JButton bt = new JButton();
		bt.addActionListener(al);
		bt.setToolTipText(name);
		if (showText)
			bt.setText(name);
		if (showIcon)
			bt.setIcon(ImageUtils.loadImage(iconName));
		return bt;
	}
}

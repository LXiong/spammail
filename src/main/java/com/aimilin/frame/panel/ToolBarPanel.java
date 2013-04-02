package com.aimilin.frame.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.apache.commons.lang3.StringUtils;

import com.aimilin.utils.ConfigUtils;
import com.aimilin.utils.FrameUtils;

/**
 * 工具条面板类
 * @author LiuJunGuang
 * @date 2013-4-2下午10:55:49
 */
public class ToolBarPanel implements ActionListener {
	private ConfigUtils conf = ConfigUtils.getInstance();
	private JButton sendBt = null;// 发送
	private JButton saveBt = null;// 保存
	private JButton importBt = null;// 保存
	private JButton copyToBt = null;// 添加抄送人
	private JButton secretToBt = null;// 添加密送人
	private JButton attachBt = null;// 添加附件

	/**
	 * 初始化工具条
	 * @author LiuJunGuang
	 * @date 2013-4-1下午8:59:37
	 */
	public JToolBar initToolBar() {
		JToolBar toolBar = new JToolBar();
		sendBt = createMenuButton(conf.getString("sendFrame.button.send", "发送"), "send_email.png");
		toolBar.add(sendBt);
		toolBar.addSeparator();
		saveBt = createMenuButton(conf.getString("sendFrame.button.save", "保存"), "save.png");
		toolBar.add(saveBt);
		importBt = createMenuButton(conf.getString("sendFrame.button.import", "导入"), "import.png");
		toolBar.add(importBt);
		toolBar.addSeparator();
		copyToBt = createMenuButton(conf.getString("sendFrame.button.copyTo", "添加抄送"), "user.png");
		toolBar.add(copyToBt);
		secretToBt = createMenuButton(conf.getString("sendFrame.button.secretTo", "添加密送"), "user_red.png");
		toolBar.add(secretToBt);
		attachBt = createMenuButton(conf.getString("sendFrame.button.attach", "添加附件"), "attach.png");
		toolBar.add(attachBt);
		return toolBar;
	}

	// 创建按钮
	private JButton createMenuButton(String name, String iconName) {
		boolean showText = conf.getBoolean("window.toolbar.show_text", true);
		boolean showIcon = conf.getBoolean("window.toolbar.show_icon", true) && StringUtils.isNotEmpty(iconName);
		return FrameUtils.createButton(name, showText, iconName, showIcon, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

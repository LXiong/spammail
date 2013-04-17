package com.aimilin.frame.panel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.apache.commons.lang3.StringUtils;

import com.aimilin.domain.ToolBarBean;
import com.aimilin.utils.ConfigUtils;
import com.aimilin.utils.FrameUtils;

/**
 * 工具条面板类
 * @author LiuJunGuang
 * @date 2013-4-2下午10:55:49
 */
public class ToolBarPanel extends BasePanel {
	private ConfigUtils conf = ConfigUtils.getInstance();
	private ToolBarBean toolBarBean;// 工具条对象信息封装
	private ActionListener actionListener;// 事件处理

	public ToolBarPanel() {
		super();
		toolBarBean = new ToolBarBean();
	}

	/**
	 * 初始化工具条
	 * @author LiuJunGuang
	 * @date 2013-4-1下午8:59:37
	 */
	public JToolBar initToolBar() {
		JToolBar toolBar = new JToolBar();
		String[][] buttonArray = { { "sendBt", "sendFrame.button.send", "发送", "send_email.png" }, { this.SEPARATOR },
				{ "saveBt", "sendFrame.button.save", "保存", "save.png" },
				{ "importBt", "sendFrame.button.import", "导入", "import.png" }, { this.SEPARATOR },
				{ "copyToBt", "sendFrame.button.copyTo", "添加抄送人", "user.png" },
				{ "secretToBt", "sendFrame.button.secretTo", "添加密送人", "user_red.png" }, { this.SEPARATOR },
				{ "attachBt", "sendFrame.button.attach", "添加附件", "attach.png" }, };
		this.initButton(toolBarBean, toolBar, buttonArray);
		return toolBar;
	}

	// 创建按钮
	@Override
	public JButton createButton(String name, String iconName) {
		boolean showText = conf.getBoolean("window.toolbar.show_text", true);
		boolean showIcon = conf.getBoolean("window.toolbar.show_icon", true) && StringUtils.isNotEmpty(iconName);
		return FrameUtils.createButton(name, showText, iconName, showIcon, actionListener);
	}

	/**
	 * 获取工具条bean对象
	 * @author LiuJunGuang
	 * @return ToolBarBean
	 * @date 2013-4-14下午10:39:45
	 */
	public ToolBarBean getToolBarBean() {
		return toolBarBean;
	}

	/**
	 * 获取工具条事件处理类
	 * @return actionListener
	 */
	public ActionListener getActionListener() {
		return actionListener;
	}

	/**
	 * 设置工具条事件处理类
	 * @param actionListener
	 */
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

}

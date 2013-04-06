package com.aimilin.frame.panel;

import java.awt.event.ActionEvent;
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
public class ToolBarPanel extends BasePanel implements ActionListener {
	private ConfigUtils conf = ConfigUtils.getInstance();
	private ToolBarBean toolBarBean;

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
				{ "copyToBt", "sendFrame.button.copyTo", "添加抄送", "user.png" },
				{ "secretToBt", "sendFrame.button.secretTo", "添加密送", "user_red.png" }, { this.SEPARATOR },
				{ "attachBt", "sendFrame.button.attach", "添加附件", "attach.png" }, };
		this.initButton(toolBarBean, toolBar, buttonArray);
		return toolBar;
	}

	// 创建按钮
	@Override
	public JButton createButton(String name, String iconName) {
		boolean showText = conf.getBoolean("window.toolbar.show_text", true);
		boolean showIcon = conf.getBoolean("window.toolbar.show_icon", true) && StringUtils.isNotEmpty(iconName);
		return FrameUtils.createButton(name, showText, iconName, showIcon, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public ToolBarBean getToolBarBean() {
		return toolBarBean;
	}

	public void setToolBarBean(ToolBarBean toolBarBean) {
		this.toolBarBean = toolBarBean;
	}

}

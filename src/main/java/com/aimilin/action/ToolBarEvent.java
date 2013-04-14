package com.aimilin.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

import com.aimilin.frame.panel.ContentPanel;
import com.aimilin.frame.panel.FunctionPanel;
import com.aimilin.frame.panel.ToolBarPanel;

/**
 * 工具条事件类处理
 * @author LiuJunGuang
 * @date 2013-4-14下午10:07:26
 */
public class ToolBarEvent implements ActionListener {
	private Logger log = Logger.getLogger(ToolBarEvent.class);
	private ContentPanel contentPanel = null;// 发送邮件主界面类(邮件编辑界面)
	private ToolBarPanel toolBarPanel = null;// 工具条面板
	private FunctionPanel functionPanel = null;// 发送邮件收件人界面

	@Override
	public void actionPerformed(ActionEvent e) {
		log.debug(e.getActionCommand());
	}

	/**
	 * @param contentPanel 发送邮件主界面类(邮件编辑界面)
	 */
	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	/**
	 * @param toolBarPanel 工具条面板
	 */
	public void setToolBarPanel(ToolBarPanel toolBarPanel) {
		this.toolBarPanel = toolBarPanel;
	}

	/**
	 * @param functionPanel 发送邮件收件人界面
	 */
	public void setFunctionPanel(FunctionPanel functionPanel) {
		this.functionPanel = functionPanel;
	}

}

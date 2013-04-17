package com.aimilin.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.aimilin.domain.ToolBarBean;
import com.aimilin.frame.panel.ContentPanel;
import com.aimilin.frame.panel.FunctionPanel;
import com.aimilin.frame.panel.ToolBarPanel;
import com.aimilin.utils.ConfigUtils;

/**
 * 工具条事件类处理
 * @author LiuJunGuang
 * @date 2013-4-14下午10:07:26
 */
public class ToolBarEvent implements ActionListener {
	private ConfigUtils conf = ConfigUtils.getInstance();
	private ContentPanel contentPanel = null;// 发送邮件主界面类(邮件编辑界面)
	private ToolBarPanel toolBarPanel = null;// 工具条面板
	private FunctionPanel functionPanel = null;// 发送邮件收件人界面
	private ToolBarBean toolBarBean = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		toolBarBean = toolBarPanel.getToolBarBean();
		if (e.getSource() == toolBarBean.getCopyToBt()) {
			addCcAction();// 添加抄送人
		} else if (e.getSource() == toolBarBean.getSecretToBt()) {
			addBccAction();// 添加密送人
		}
	}

	// 添加抄送人事件处理
	private void addCcAction() {
		functionPanel.addOrRemove(FunctionPanel.ADDRESS_TYPE_CC);
		String text = functionPanel.getAddCc() ? conf.getString("sendFrame.button.copyTo") : conf
				.getString("sendFrame.button.removeCopyTo");
		toolBarBean.getCopyToBt().setText(text);
	}

	// 添加密送人事件处理
	private void addBccAction() {
		functionPanel.addOrRemove(FunctionPanel.ADDRESS_TYPE_BCC);
		String text = functionPanel.getAddBcc() ? conf.getString("sendFrame.button.secretTo") : conf
				.getString("sendFrame.button.removeSecretTo");
		toolBarBean.getSecretToBt().setText(text);
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

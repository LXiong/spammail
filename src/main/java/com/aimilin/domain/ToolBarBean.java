package com.aimilin.domain;

import java.io.Serializable;

import javax.swing.JButton;

/**
 * 工具条对象类
 * @author LiuJunGuang
 * @date 2013-4-6下午7:33:28
 */
public class ToolBarBean implements Serializable {
	private static final long serialVersionUID = 754314058755715139L;
	private JButton sendBt = null;// 发送
	private JButton saveBt = null;// 保存
	private JButton importBt = null;// 导入
	private JButton copyToBt = null;// 添加抄送人
	private JButton secretToBt = null;// 添加密送人
	private JButton attachBt = null;// 添加附件

	public JButton getSendBt() {
		return sendBt;
	}

	public void setSendBt(JButton sendBt) {
		this.sendBt = sendBt;
	}

	public JButton getSaveBt() {
		return saveBt;
	}

	public void setSaveBt(JButton saveBt) {
		this.saveBt = saveBt;
	}

	public JButton getImportBt() {
		return importBt;
	}

	public void setImportBt(JButton importBt) {
		this.importBt = importBt;
	}

	public JButton getCopyToBt() {
		return copyToBt;
	}

	public void setCopyToBt(JButton copyToBt) {
		this.copyToBt = copyToBt;
	}

	public JButton getSecretToBt() {
		return secretToBt;
	}

	public void setSecretToBt(JButton secretToBt) {
		this.secretToBt = secretToBt;
	}

	public JButton getAttachBt() {
		return attachBt;
	}

	public void setAttachBt(JButton attachBt) {
		this.attachBt = attachBt;
	}

}

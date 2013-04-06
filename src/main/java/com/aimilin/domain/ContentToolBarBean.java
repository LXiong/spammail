package com.aimilin.domain;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class ContentToolBarBean implements Serializable {
	private static final long serialVersionUID = 2833606558067038550L;
	private JTextPane contentTp;// 邮件内容
	private JButton boldBt;// 加粗
	private JButton italicBt;// 斜体
	private JButton underlineBt;// 下划线
	private JButton fontColorBt;// 字体颜色
	private JButton fontBackgroundColorBt;// 字体背景颜色
	private JButton leftBt;// 居左
	private JButton centerBt;// 居中
	private JButton rightBt;// 居右
	private JButton unorderListBt;// 无序列表
	private JButton orderListBt;// 有序列表
	private JButton linkBt;// 链接
	private JComboBox fontCb;// 字体列表
	private JComboBox fontSizeCb;// 字体大小列表

	public JTextPane getContentTp() {
		return contentTp;
	}

	public void setContentTp(JTextPane contentTp) {
		this.contentTp = contentTp;
	}

	public JButton getBoldBt() {
		return boldBt;
	}

	public void setBoldBt(JButton boldBt) {
		this.boldBt = boldBt;
	}

	public JButton getItalicBt() {
		return italicBt;
	}

	public void setItalicBt(JButton italicBt) {
		this.italicBt = italicBt;
	}

	public JButton getUnderlineBt() {
		return underlineBt;
	}

	public void setUnderlineBt(JButton underlineBt) {
		this.underlineBt = underlineBt;
	}

	public JButton getFontColorBt() {
		return fontColorBt;
	}

	public void setFontColorBt(JButton fontColorBt) {
		this.fontColorBt = fontColorBt;
	}

	public JButton getFontBackgroundColorBt() {
		return fontBackgroundColorBt;
	}

	public void setFontBackgroundColorBt(JButton fontBackgroundColorBt) {
		this.fontBackgroundColorBt = fontBackgroundColorBt;
	}

	public JButton getLeftBt() {
		return leftBt;
	}

	public void setLeftBt(JButton leftBt) {
		this.leftBt = leftBt;
	}

	public JButton getCenterBt() {
		return centerBt;
	}

	public void setCenterBt(JButton centerBt) {
		this.centerBt = centerBt;
	}

	public JButton getRightBt() {
		return rightBt;
	}

	public void setRightBt(JButton rightBt) {
		this.rightBt = rightBt;
	}

	public JButton getUnorderListBt() {
		return unorderListBt;
	}

	public void setUnorderListBt(JButton unorderListBt) {
		this.unorderListBt = unorderListBt;
	}

	public JButton getOrderListBt() {
		return orderListBt;
	}

	public void setOrderListBt(JButton orderListBt) {
		this.orderListBt = orderListBt;
	}

	public JButton getLinkBt() {
		return linkBt;
	}

	public void setLinkBt(JButton linkBt) {
		this.linkBt = linkBt;
	}

	public JComboBox getFontCb() {
		return fontCb;
	}

	public void setFontCb(JComboBox fontCb) {
		this.fontCb = fontCb;
	}

	public JComboBox getFontSizeCb() {
		return fontSizeCb;
	}

	public void setFontSizeCb(JComboBox fontSizeCb) {
		this.fontSizeCb = fontSizeCb;
	}

}

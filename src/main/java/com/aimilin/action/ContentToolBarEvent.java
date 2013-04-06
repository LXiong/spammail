package com.aimilin.action;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import com.aimilin.domain.ContentToolBarBean;
import com.aimilin.spammail.SpamMail;
import com.aimilin.utils.ConfigUtils;

/**
 * 邮件正文内容编辑器事件处理
 * @author LiuJunGuang
 * @date 2013-4-6下午12:22:12
 */
public class ContentToolBarEvent implements ActionListener {
	private Logger log = Logger.getLogger(ContentToolBarEvent.class);
	private ContentToolBarBean toolBarBean = null;
	private ConfigUtils conf = ConfigUtils.getInstance();

	public ContentToolBarEvent(ContentToolBarBean toolBarBean) {
		super();
		this.toolBarBean = toolBarBean;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug(String.format("触发：%s事件！", e.getActionCommand()));
		}
		if (e.getSource() == toolBarBean.getBoldBt()) {
			boldEvent(e);// 加粗事件
		} else if (e.getSource() == toolBarBean.getCenterBt()) {
			centerEvent(e);// 居中事件
		} else if (e.getSource() == toolBarBean.getFontBackgroundColorBt()) {
			fontBackgroundColorEvent(e);// 字体背景颜色
		} else if (e.getSource() == toolBarBean.getFontCb()) {
			fontEvent(e);// 字体
		} else if (e.getSource() == toolBarBean.getFontColorBt()) {
			fontColorEvent(e);// 字体颜色
		} else if (e.getSource() == toolBarBean.getFontSizeCb()) {
			fontSizeEvent(e);// 字体大小
		} else if (e.getSource() == toolBarBean.getItalicBt()) {
			italicEvent(e);// 斜体
		} else if (e.getSource() == toolBarBean.getLeftBt()) {
			leftEvent(e);// 居左
		} else if (e.getSource() == toolBarBean.getLinkBt()) {
			linkEvent(e);// 链接
		} else if (e.getSource() == toolBarBean.getOrderListBt()) {
			orderListEvent(e);// 有序列表
		} else if (e.getSource() == toolBarBean.getRightBt()) {
			righrEvent(e);// 居右
		} else if (e.getSource() == toolBarBean.getUnderlineBt()) {
			underlineEvent(e);// 下划线
		} else if (e.getSource() == toolBarBean.getUnorderListBt()) {
			unorderListEvent(e);// 无序列表
		}
	}

	private void underlineEvent(ActionEvent e) {
		new StyledEditorKit.UnderlineAction().actionPerformed(e);
	}

	private void righrEvent(ActionEvent e) {
		new StyledEditorKit.AlignmentAction(e.getActionCommand(), StyleConstants.ALIGN_RIGHT).actionPerformed(e);
	}

	private void unorderListEvent(ActionEvent e) {
		new HTMLEditorKit.InsertHTMLTextAction(e.getActionCommand(), "<ul><li></li></ul>", HTML.Tag.UL, HTML.Tag.LI,
				HTML.Tag.BODY, HTML.Tag.UL).actionPerformed(e);
	}

	private void orderListEvent(ActionEvent e) {
		new HTMLEditorKit.InsertHTMLTextAction(e.getActionCommand(), "<ol><li></li></ol>", HTML.Tag.OL, HTML.Tag.LI,
				HTML.Tag.BODY, HTML.Tag.OL).actionPerformed(e);
	}

	private void linkEvent(ActionEvent e) {
		try {
			JTextPane pane = toolBarBean.getContentTp();
			MutableAttributeSet attr = pane.getInputAttributes();
			boolean anchor = attr.isDefined(HTML.Tag.A);
			String initValue = conf.getString("content.toolbar.link_initValue", "http://");
			String url = initValue;
			if (anchor) {// 判断超链接是否存在，如果存在则回显
				SimpleAttributeSet as = (SimpleAttributeSet) attr.getAttribute(HTML.Tag.A);
				url = (String) as.getAttribute(HTML.Attribute.HREF);
				url = URLDecoder.decode(url, "UTF-8");
			}

			String inputValue = JOptionPane.showInputDialog(SpamMail.mainFrame,
					conf.getString("content.toolbar.link_message", "添加链接地址"), url);
			if (StringUtils.isBlank(inputValue)) {// 如果输入为null则直接返回
				return;
			}
			if (initValue.equalsIgnoreCase(inputValue.trim())) {// 如果输入为http:// 则取消超链接
				if (anchor) {
					attr.removeAttribute(HTML.Tag.A);
					pane.setCharacterAttributes(attr, true);
				}
				return;
			}
			url = URLEncoder.encode(inputValue, "UTF-8");
			if (anchor) {
				attr.removeAttribute(HTML.Tag.A);
			}
			SimpleAttributeSet as = new SimpleAttributeSet();
			as.addAttribute(HTML.Attribute.HREF, url);
			attr.addAttribute(HTML.Tag.A, as);
			pane.setCharacterAttributes(attr, false);
		} catch (UnsupportedEncodingException e1) {
			log.error(e1);
		}
	}

	private void leftEvent(ActionEvent e) {
		new StyledEditorKit.AlignmentAction(e.getActionCommand(), StyleConstants.ALIGN_LEFT).actionPerformed(e);
	}

	private void italicEvent(ActionEvent e) {
		new StyledEditorKit.ItalicAction().actionPerformed(e);
	}

	private void fontSizeEvent(ActionEvent e) {
		String fontsize = (String) toolBarBean.getFontSizeCb().getSelectedItem();
		new StyledEditorKit.FontSizeAction(e.getActionCommand(), NumberUtils.toInt(fontsize)).actionPerformed(e);
	}

	private void fontColorEvent(ActionEvent e) {
		Color color = JColorChooser.showDialog(SpamMail.mainFrame,
				conf.getString("content.toolbar.select_color_title", "字体颜色"), Color.black);
		if (color != null) {
			new StyledEditorKit.ForegroundAction(e.getActionCommand(), color).actionPerformed(e);// 添加颜色侦听器
		}
	}

	private void fontEvent(ActionEvent e) {
		String font = (String) toolBarBean.getFontCb().getSelectedItem();
		new StyledEditorKit.FontFamilyAction(font, font).actionPerformed(e);
	}

	private void fontBackgroundColorEvent(ActionEvent e) {
		Color color = JColorChooser.showDialog(SpamMail.mainFrame,
				conf.getString("content.toolbar.select_background_color_title", "字体背景颜色"), Color.BLACK);
		if (color != null) {
			MutableAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setBackground(attr, color);
			toolBarBean.getContentTp().setCharacterAttributes(attr, false);
		}
	}

	private void centerEvent(ActionEvent e) {
		new StyledEditorKit.AlignmentAction(e.getActionCommand(), StyleConstants.ALIGN_CENTER).actionPerformed(e);
	}

	private void boldEvent(ActionEvent e) {
		new StyledEditorKit.BoldAction().actionPerformed(e);
	}

	public ContentToolBarBean getToolBarBean() {
		return toolBarBean;
	}

	public void setToolBarBean(ContentToolBarBean toolBarBean) {
		this.toolBarBean = toolBarBean;
	}

}

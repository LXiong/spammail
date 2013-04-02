package com.aimilin.frame.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.commons.lang3.StringUtils;

import com.aimilin.utils.ConfigUtils;
import com.aimilin.utils.FrameUtils;
import com.aimilin.utils.ImageUtils;

/**
 * 发送邮件界面中编辑邮件主界面
 * @author LiuJunGuang
 * @date 2013-4-2下午10:30:36
 */
public class ContentPanel implements Serializable, ActionListener {
	private static final long serialVersionUID = 2396848127573872426L;
	private ConfigUtils conf = ConfigUtils.getInstance();

	private JTextPane contentTp;// 邮件内容

	// 初始化编辑面板
	public JPanel initContentPanel() {
		JPanel editPanel = new JPanel(new BorderLayout());
		JPanel editorToolBarPanel = createEditToolBarPanel();
		editPanel.add(editorToolBarPanel, BorderLayout.NORTH);
		JScrollPane contentScrollPane = createContentScrollPane();
		editPanel.add(contentScrollPane, BorderLayout.CENTER);
		return editPanel;
	}

	// 初始化文本操作工具条
	private JPanel createEditToolBarPanel() {
		JPanel panel = new JPanel();
		JToolBar toolBar = new JToolBar();
		toolBar.add(createFont());// 字体
		toolBar.add(createFontSize());// 字号
		toolBar.addSeparator();
		toolBar.add(createTextButton(conf.getString("content.tootbar.bold", "加粗"), "text_bold.png"));
		toolBar.add(createTextButton(conf.getString("content.tootbar.italic", "斜体"), "text_italic.png"));
		toolBar.add(createTextButton(conf.getString("content.tootbar.underline", "下划线"), "text_underline.png"));
		toolBar.addSeparator();
		toolBar.add(createTextButton(conf.getString("content.tootbar.font_color", "字体颜色"), "text_font_color.png"));
		toolBar.add(createTextButton(conf.getString("content.tootbar.font_background_color", "背景颜色"),
				"text_background_color.png"));
		toolBar.addSeparator();
		toolBar.add(createTextButton(conf.getString("content.tootbar.left", "居左"), "text_align_left.png"));
		toolBar.add(createTextButton(conf.getString("content.tootbar.center", "居中"), "text_align_center.png"));
		toolBar.add(createTextButton(conf.getString("content.tootbar.right", "居右"), "text_align_right.png"));
		toolBar.addSeparator();
		toolBar.add(createTextButton(conf.getString("content.tootbar.unorder_list", "无序列表"), "text_unorder_list.png"));
		toolBar.add(createTextButton(conf.getString("content.tootbar.order_list", "有序列表"), "text_order_list.png"));
		toolBar.addSeparator();
		toolBar.add(createTextButton(conf.getString("content.tootbar.link", "超链接"), "text_link.png"));
		panel.add(toolBar);
		return panel;
	}

	// 字号大小
	private JPanel createFontSize() {
		JPanel fontSizePanel = new JPanel();
		// 字号列表
		JLabel fontSizeLabel = new JLabel();
		String fontSize = conf.getString("content.tootbar.font_size", "字号");
		fontSizeLabel.setToolTipText(fontSize);
		if (conf.getBoolean("content.toolbar.show_text", true)) {
			fontSizeLabel.setText(fontSize);
		}
		if (conf.getBoolean("content.toolbar.show_icon", true))
			fontSizeLabel.setIcon(ImageUtils.loadImage("text_font_size.png"));
		String fontSizes[] = conf.getStringArray("content.toolbar.text_size");
		JComboBox fontSizeCB = new JComboBox(fontSizes);
		fontSizeCB.setPreferredSize(new Dimension(45, 23));
		fontSizePanel.add(fontSizeLabel);
		fontSizePanel.add(fontSizeCB);
		return fontSizePanel;
	}

	// 字体大小
	private JPanel createFont() {
		JPanel fontPanel = new JPanel();
		JLabel fontLabel = new JLabel();
		String font = conf.getString("content.tootbar.font", "字体");
		fontLabel.setToolTipText(font);
		if (conf.getBoolean("content.toolbar.show_text", true)) {
			fontLabel.setText(font);
		}
		if (conf.getBoolean("content.toolbar.show_icon", true))
			fontLabel.setIcon(ImageUtils.loadImage("text_font.png"));
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();// 获得本地计算机上字体可用的名称
		String fonts[] = ge.getAvailableFontFamilyNames();
		JComboBox fontCB = new JComboBox(fonts);
		fontPanel.add(fontLabel);
		fontPanel.add(fontCB);
		return fontPanel;
	}

	// 初始化内容滚动面板
	private JScrollPane createContentScrollPane() {
		contentTp = new JTextPane();
		contentTp.setPreferredSize(new Dimension(400, 100));
		HTMLDocument html = new HTMLDocument();
		HTMLEditorKit editorKit = new HTMLEditorKit();
		contentTp.setStyledDocument(html);
		contentTp.setContentType("text/html; charset=UTF-8");
		contentTp.setEditorKit(editorKit);
		JScrollPane scrollPane = new JScrollPane(contentTp);
		return scrollPane;
	}

	private JButton createTextButton(String buttonName, String iconName) {
		boolean showText = conf.getBoolean("content.toolbar.show_text", true);
		boolean showIcon = conf.getBoolean("content.toolbar.show_icon", true) && StringUtils.isNotEmpty(iconName);
		return FrameUtils.createButton(buttonName, showText, iconName, showIcon, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

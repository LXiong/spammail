package com.aimilin.frame.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.html.HTMLDocument;

import org.apache.commons.lang3.StringUtils;

import com.aimilin.action.CaretListenerLabel;
import com.aimilin.action.ContentToolBarEvent;
import com.aimilin.action.MyDocumentListener;
import com.aimilin.action.MyUndoableEditListener;
import com.aimilin.domain.ContentToolBarBean;
import com.aimilin.utils.FrameUtils;
import com.aimilin.utils.ImageUtils;

/**
 * 发送邮件界面中编辑邮件主界面
 * @author LiuJunGuang
 * @date 2013-4-2下午10:30:36
 */
public class ContentPanel extends BasePanel implements Serializable {
	private static final long serialVersionUID = 2396848127573872426L;
	private ContentToolBarBean toolBarBean = null;
	private ContentToolBarEvent toolBarEvent = null;
	private CaretListenerLabel caretListenerLabel;// 状态条

	public ContentPanel() {
		toolBarBean = new ContentToolBarBean();
		toolBarEvent = new ContentToolBarEvent(toolBarBean);
		caretListenerLabel = new CaretListenerLabel();
	}

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
		String[][] buttonArray = {
				{ "boldBt", "content.toolbar.bold", "加粗", "text_bold.png" },
				{ "italicBt", "content.toolbar.italic", "斜体", "text_italic.png" },
				{ "underlineBt", "content.toolbar.underline", "下划线", "text_underline.png" },
				{ this.SEPARATOR },
				{ "fontColorBt", "content.toolbar.font_color", "字体颜色", "text_font_color.png" },
				{ "fontBackgroundColorBt", "content.toolbar.font_background_color", "背景颜色", "text_background_color.png" },
				{ this.SEPARATOR }, { "leftBt", "content.toolbar.left", "居左", "text_align_left.png" },
				{ "centerBt", "content.toolbar.center", "居中", "text_align_center.png" },
				{ "rightBt", "content.toolbar.right", "居右", "text_align_right.png" }, { this.SEPARATOR },
				{ "unorderListBt", "content.toolbar.unorder_list", "无序列表", "text_unorder_list.png" },
				{ "orderListBt", "content.toolbar.order_list", "有序列表", "text_order_list.png" }, { this.SEPARATOR },
				{ "linkBt", "content.toolbar.link", "超链接", "text_link.png" } };
		this.initButton(toolBarBean, toolBar, buttonArray);// 初始化toolbar按钮
		panel.add(toolBar);
		return panel;
	}

	// 字号大小
	private JPanel createFontSize() {
		JPanel fontSizePanel = new JPanel();
		// 字号列表
		JLabel fontSizeLabel = new JLabel();
		String fontSize = conf.getString("content.toolbar.font_size", "字号");
		fontSizeLabel.setToolTipText(fontSize);
		if (conf.getBoolean("content.toolbar.show_text", true)) {
			fontSizeLabel.setText(fontSize);
		}
		if (conf.getBoolean("content.toolbar.show_icon", true))
			fontSizeLabel.setIcon(ImageUtils.loadImage("text_font_size.png"));
		String fontSizes[] = conf.getStringArray("content.toolbar.text_size");
		JComboBox fontSizeCb = new JComboBox(fontSizes);
		fontSizeCb.setPreferredSize(new Dimension(45, 23));
		fontSizeCb.addActionListener(toolBarEvent);
		fontSizePanel.add(fontSizeLabel);
		fontSizePanel.add(fontSizeCb);
		toolBarBean.setFontSizeCb(fontSizeCb);
		return fontSizePanel;
	}

	// 字体大小
	private JPanel createFont() {
		JPanel fontPanel = new JPanel();
		JLabel fontLabel = new JLabel();
		String font = conf.getString("content.toolbar.font", "字体");
		fontLabel.setToolTipText(font);
		if (conf.getBoolean("content.toolbar.show_text", true)) {
			fontLabel.setText(font);
		}
		if (conf.getBoolean("content.toolbar.show_icon", true))
			fontLabel.setIcon(ImageUtils.loadImage("text_font.png"));
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();// 获得本地计算机上字体可用的名称
		String fonts[] = ge.getAvailableFontFamilyNames();
		JComboBox fontCb = new JComboBox(fonts);
		fontCb.addActionListener(toolBarEvent);
		fontPanel.add(fontLabel);
		fontPanel.add(fontCb);
		toolBarBean.setFontCb(fontCb);
		return fontPanel;
	}

	// 初始化内容滚动面板
	private JScrollPane createContentScrollPane() {
		JTextPane contentTp = new JTextPane();
		contentTp.setContentType("text/html; charset=UTF-8");
		contentTp.setCaretPosition(0);
		contentTp.setMargin(new Insets(5, 5, 5, 5));
		HTMLDocument htmlDoc = new HTMLDocument();
		htmlDoc.addUndoableEditListener(new MyUndoableEditListener());
		htmlDoc.addDocumentListener(new MyDocumentListener());
		contentTp.setStyledDocument(htmlDoc);
		contentTp.addCaretListener(caretListenerLabel);

		JScrollPane scrollPane = new JScrollPane(contentTp);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		toolBarBean.setContentTp(contentTp);
		return scrollPane;
	}

	@Override
	public JButton createButton(String buttonName, String iconName) {
		boolean showText = conf.getBoolean("content.toolbar.show_text", true);
		boolean showIcon = conf.getBoolean("content.toolbar.show_icon", true) && StringUtils.isNotEmpty(iconName);
		return FrameUtils.createButton(buttonName, showText, iconName, showIcon, toolBarEvent);
	}

	/**
	 * 获取工具条和编辑区bean
	 * @author LiuJunGuang
	 * @return ContentToolBarBean
	 * @date 2013-4-6下午12:27:25
	 */
	public ContentToolBarBean getToolBarBean() {
		return toolBarBean;
	}

	/**
	 * 获取文本编辑状态
	 * @author LiuJunGuang
	 * @return
	 * @date 2013-4-6下午9:47:53
	 */
	public CaretListenerLabel getCaretListenerLabel() {
		return caretListenerLabel;
	}

}

package com.aimilin.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.aimilin.action.ToolBarEvent;
import com.aimilin.frame.panel.ContentPanel;
import com.aimilin.frame.panel.FunctionPanel;
import com.aimilin.frame.panel.ToolBarPanel;

/**
 * 发送邮件类
 * @author LiuJunGuang
 * @date 2013-4-1下午8:56:49
 */
public class SendFrame extends JPanel {
	private static final long serialVersionUID = 594927382035366078L;
	private FunctionPanel functionPanel = null;// 发送邮件收件人界面
	private ContentPanel contentPanel = null;// 发送邮件主界面类(邮件编辑界面)
	private ToolBarPanel toolBarPanel = null;// 工具条面板
	private ToolBarEvent toolBarEvent = null;// 工具条事件处理类

	public SendFrame() {
		super();
		initPanelObject();// 初始化各个面板对象
		this.setLayout(new BorderLayout());
		// 初始化工具条类
		this.add(toolBarPanel.initToolBar(), BorderLayout.NORTH);
		// 初始化主Panel
		Component mainPanel = initMainPanel();
		this.add(mainPanel, BorderLayout.CENTER);
		// 初始化状态栏Panel
		Component statePanel = initStatePanel();
		this.add(statePanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.validate();
	}

	/**
	 * 初始化各个面板对象
	 * @author LiuJunGuang
	 * @date 2013-4-14下午10:43:31
	 */
	private void initPanelObject() {
		toolBarEvent = new ToolBarEvent();
		contentPanel = new ContentPanel();
		functionPanel = new FunctionPanel();
		toolBarPanel = new ToolBarPanel();
		toolBarPanel.setActionListener(toolBarEvent);
		toolBarEvent.setContentPanel(contentPanel);
		toolBarEvent.setToolBarPanel(toolBarPanel);
		toolBarEvent.setFunctionPanel(functionPanel);
	}

	/**
	 * 初始化主panel
	 * @author LiuJunGuang
	 * @return JPanel
	 * @date 2013-4-1下午9:18:13
	 */
	private Component initMainPanel() {
		Component funPanel = functionPanel.initFunctionPanel();
		Component editPanel = contentPanel.initContentPanel();
		// 添加一个分割窗口
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, funPanel, editPanel);
		splitPane.setOneTouchExpandable(true);// 在分隔条上提供一个 UI 小部件来快速展开/折叠分隔条
		splitPane.setDividerSize(10);// 设置分隔条的大小。
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(splitPane, BorderLayout.CENTER);
		return mainPanel;
	}

	/**
	 * 初始化状态栏
	 * @author LiuJunGuang
	 * @return
	 * @date 2013-4-1下午9:19:53
	 */
	private Component initStatePanel() {
		JPanel statusPanel = new JPanel(new GridLayout(1, 1));
		statusPanel.add(contentPanel.getCaretListenerLabel());
		return statusPanel;
	}

	// 测试窗口
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new SendFrame());
		f.setVisible(true);
		f.validate();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
	}

}

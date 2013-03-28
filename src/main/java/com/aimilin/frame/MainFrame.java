package com.aimilin.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.aimilin.utils.ConfigUtils;

/**
 * 主界面类
 * @author LiuJunGuang
 * @date 2013-3-26下午8:25:32
 */
public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -4150720965518192378L;
	private static Logger log = Logger.getLogger(MainFrame.class);
	private static ConfigUtils conf = ConfigUtils.getInstance();
	private final JMenuItem exitMI, sendMailMI, sysSetMI, mailSetMI, useHelpMI, aboutMI;

	public MainFrame() {
		super(conf.getString("window.title", "邮件群发"));
		jFrameValidate();// 初始化界面
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu fileMenu = addMenu(menuBar, conf.getString("menu.file", "文件(F)"), KeyEvent.VK_F);
		sendMailMI = addMenuItem(fileMenu, conf.getString("menuItem.file.sendMail", "发送邮件"), KeyEvent.VK_E);
		fileMenu.addSeparator();// 添加分割线
		exitMI = addMenuItem(fileMenu, conf.getString("menuItem.file.exit", "退出"), KeyEvent.VK_X);// 退出菜单项的初始化

		JMenu setMenu = addMenu(menuBar, conf.getString("menu.set", "设置(T)"), KeyEvent.VK_T);
		sysSetMI = addMenuItem(setMenu, conf.getString("menuItem.set.window", "界面设置"), KeyEvent.VK_W);
		mailSetMI = addMenuItem(setMenu, conf.getString("menuItem.set.mail", "邮件设置"), KeyEvent.VK_M);

		JMenu helpMenu = addMenu(menuBar, conf.getString("menu.help", "帮助(H)"), KeyEvent.VK_T);
		useHelpMI = addMenuItem(helpMenu, conf.getString("menuItem.help.useHelp", "使用帮助"), KeyEvent.VK_H);
		aboutMI = addMenuItem(helpMenu, conf.getString("menuItem.help.about", "关于"), KeyEvent.VK_A);
		setVisible(true);
	}

	// 初始化界面配置
	private void jFrameValidate() {
		Toolkit tk = getToolkit();// 获得屏幕的宽和高
		Dimension dim = tk.getScreenSize();
		int width = conf.getInt("window.width", 800);
		int height = conf.getInt("window.height", 500);
		this.setBounds((dim.width - width) / 2, (dim.height - height) / 2, width, height);
		validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 添加菜单
	private JMenu addMenu(JMenuBar menuBar, String name, int key) {
		JMenu menu = new JMenu(name);
		menuBar.add(menu);
		menu.setMnemonic(key);// 设置快捷键
		return menu;
	}

	// 返回新建菜单项
	private JMenuItem addMenuItem(JMenu menu, String name, int key) {
		// 新建邮件菜单项的初始化
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.addActionListener(this);// 监听退出菜单项事件
		menuItem.setAccelerator(KeyStroke.getKeyStroke(key, KeyEvent.CTRL_MASK));// 设置快捷键
		menu.add(menuItem);
		return menuItem;
	}

	// 添加子窗体的方法
	public void addIFame(JPanel panel) {
		this.add(panel, BorderLayout.CENTER);
		this.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitMI) {// 退出系统
			System.exit(0);
		} else if (e.getSource() == sendMailMI) {// 发送邮件
			log.debug("发送邮件");
		} else if (e.getSource() == sysSetMI) {// 系统设置
			log.debug("系统设置");
		} else if (e.getSource() == mailSetMI) {// 邮件设置
			log.debug("邮件设置");
		} else if (e.getSource() == useHelpMI) {// 使用帮助
			log.debug("使用帮助");
		} else if (e.getSource() == aboutMI) {// 关于
			log.debug("关于");
		}

	}

}

package com.aimilin.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.aimilin.utils.ConfigUtils;
import com.aimilin.utils.FrameFactory;
import com.aimilin.utils.ImageUtils;

/**
 * 主界面类
 * @author LiuJunGuang
 * @date 2013-3-26下午8:25:32
 */
public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -4150720965518192378L;
	private static Logger log = Logger.getLogger(MainFrame.class);
	private static ConfigUtils conf = ConfigUtils.getInstance();
	private JMenuItem exitMI, sendMailMI, sysSetMI, mailSetMI, useHelpMI, aboutMI;
	private JComponent centerPanel = null;

	public MainFrame() {
		super(conf.getString("window.title", "邮件群发"));
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		initFileMenu(menuBar);
		initSetMenu(menuBar);
		jFrameValidate();// 初始化界面
	}

	// 初始化界面配置
	private void jFrameValidate() {
		Toolkit tk = getToolkit();// 获得屏幕的宽和高
		Dimension dim = tk.getScreenSize();
		int width = conf.getInt("window.width", 800);
		int height = conf.getInt("window.height", 500);
		this.setBounds((dim.width - width) / 2, (dim.height - height) / 2, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.validate();
	}

	// 初始化文件菜单
	private void initFileMenu(JMenuBar menuBar) {
		JMenu fileMenu = addMenu(menuBar, conf.getString("menu.file", "文件(F)"), KeyEvent.VK_F);
		sendMailMI = addMenuItem(fileMenu, conf.getString("menuItem.file.sendMail", "发送邮件"),
				ImageUtils.loadImage("send_email.png"), KeyEvent.VK_E);
		fileMenu.addSeparator();// 添加分割线
		exitMI = addMenuItem(fileMenu, conf.getString("menuItem.file.exit", "退出"), ImageUtils.loadImage("error.png"),
				KeyEvent.VK_X);// 退出菜单项的初始化
	}

	// 初始化设置菜单
	private void initSetMenu(JMenuBar menuBar) {
		JMenu setMenu = addMenu(menuBar, conf.getString("menu.set", "设置(T)"), KeyEvent.VK_T);
		sysSetMI = addMenuItem(setMenu, conf.getString("menuItem.set.window", "界面设置"),
				ImageUtils.loadImage("user_set.png"), KeyEvent.VK_W);
		mailSetMI = addMenuItem(setMenu, conf.getString("menuItem.set.mail", "邮件设置"), ImageUtils.loadImage("set.png"),
				KeyEvent.VK_M);

		JMenu helpMenu = addMenu(menuBar, conf.getString("menu.help", "帮助(H)"), KeyEvent.VK_T);
		useHelpMI = addMenuItem(helpMenu, conf.getString("menuItem.help.useHelp", "使用帮助"),
				ImageUtils.loadImage("help.png"), KeyEvent.VK_H);
		aboutMI = addMenuItem(helpMenu, conf.getString("menuItem.help.about", "关于"), null, KeyEvent.VK_A);
	}

	/**
	 * 新建菜单
	 * @author LiuJunGuang
	 * @param menuBar 菜单条对象
	 * @param name 菜单名称
	 * @param key 菜单快捷键，KeyEvent中定义的键
	 * @return JMenu
	 * @date 2013-3-31下午9:28:28
	 */
	private JMenu addMenu(JMenuBar menuBar, String name, int key) {
		JMenu menu = new JMenu(name);
		menuBar.add(menu);
		menu.setMnemonic(key);// 设置快捷键
		return menu;
	}

	/**
	 * 新建菜单项
	 * @author LiuJunGuang
	 * @param menu 菜单
	 * @param name 菜单项名称
	 * @param icon 图片
	 * @param key 菜单项快捷键
	 * @return JMenuItem
	 * @date 2013-3-31下午9:28:31
	 */
	private JMenuItem addMenuItem(JMenu menu, String name, ImageIcon icon, int key) {
		// 新建邮件菜单项的初始化
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.addActionListener(this);// 监听退出菜单项事件
		if (icon != null)
			menuItem.setIcon(icon);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(key, KeyEvent.CTRL_MASK));// 设置快捷键
		menu.add(menuItem);
		return menuItem;
	}

	// 添加子窗体的方法
	public void addCompoent(JComponent panel) {
		if (null != centerPanel) {
			this.remove(centerPanel);
		}
		this.add(panel, BorderLayout.CENTER);
		this.centerPanel = panel;
		this.validate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitMI) {// 退出系统
			System.exit(0);
		} else if (e.getSource() == sendMailMI) {// 发送邮件
			this.addCompoent(FrameFactory.getInstance().getSendMailUI());
		} else if (e.getSource() == sysSetMI) {// 系统设置
			log.debug("系统设置");
		} else if (e.getSource() == mailSetMI) {// 邮件设置
			this.addCompoent(FrameFactory.getInstance().getMailConfigUI());
		} else if (e.getSource() == useHelpMI) {// 使用帮助
			log.debug("使用帮助");
		} else if (e.getSource() == aboutMI) {// 关于
			log.debug("关于");
		}

	}

}

package com.aimilin.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.apache.commons.lang3.StringUtils;

import com.aimilin.utils.ConfigUtils;
import com.aimilin.utils.ImageUtils;

/**
 * 发送邮件类
 * @author LiuJunGuang
 * @date 2013-4-1下午8:56:49
 */
public class SendFrame extends JPanel implements ActionListener {
	private static final long serialVersionUID = 594927382035366078L;
	private ConfigUtils conf = ConfigUtils.getInstance();
	private JButton sendBt = null;//发送
	private JButton saveBt = null;//保存
	private JButton importBt = null;//保存
	private JButton copyToBt = null;//添加抄送人
	private JButton secretToBt = null;//添加密送人
	private JButton attachBt = null;//添加附件
	
	public SendFrame() {
		super();
		this.setLayout(new BorderLayout());
		//初始化工具条类
		JToolBar toolBar = initToolBar();
		this.add(toolBar,BorderLayout.NORTH);
		//初始化主Panel
		JPanel mainPanel = initMainPanel();
		this.add(mainPanel,BorderLayout.CENTER);
		//初始化状态栏Panel
		JPanel statePanel = initStatePanel();
		this.add(statePanel,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	/**
	 * 初始化工具条
	 * @author LiuJunGuang
	 * @date 2013-4-1下午8:59:37
	 */
	private JToolBar initToolBar(){
		JToolBar toolBar = new JToolBar();
		sendBt = createButton(conf.getString("sendFrame.button.send","发送"),"send_email.png");
		toolBar.add(sendBt);
		toolBar.addSeparator();
		saveBt = createButton(conf.getString("sendFrame.button.save","保存"),"save.png");
		toolBar.add(saveBt);
		importBt = createButton(conf.getString("sendFrame.button.import","导入"),"import.png");
		toolBar.add(importBt);
		toolBar.addSeparator();
		copyToBt = createButton(conf.getString("sendFrame.button.copyTo","添加抄送"),"user.png");
		toolBar.add(copyToBt);
		secretToBt = createButton(conf.getString("sendFrame.button.secretTo","添加密送"),"user_red.png");
		toolBar.add(secretToBt);
		attachBt = createButton(conf.getString("sendFrame.button.attach","添加附件"),"attach.png");
		toolBar.add(attachBt);
		return toolBar;
	}
	
	private JButton createButton(String name,String iconName){
		JButton bt = new JButton();
		bt.addActionListener(this);
		bt.setToolTipText(name);
		if(conf.getBoolean("window.show_text",true))
			bt.setText(name);
		if(StringUtils.isNotEmpty(iconName))
			bt.setIcon(ImageUtils.loadImage(iconName));
		return bt;
	}

	/**
	 * 初始化主panel
	 * @author LiuJunGuang
	 * @return JPanel
	 * @date 2013-4-1下午9:18:13
	 */
	private JPanel initMainPanel() {
		JPanel mainPanel = new JPanel();
		return mainPanel;
	}

	/**
	 * 初始化状态栏
	 * @author LiuJunGuang
	 * @return
	 * @date 2013-4-1下午9:19:53
	 */
	private JPanel initStatePanel() {
		JPanel statePanel = new JPanel();
		return statePanel;
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new SendFrame());
		f.setVisible(true);
		f.validate();
		f.pack();
	}
	
	

}

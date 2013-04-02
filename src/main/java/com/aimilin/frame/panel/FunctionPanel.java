package com.aimilin.frame.panel;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aimilin.utils.ConfigUtils;

/**
 * 收件人主题等功能面板
 * @author LiuJunGuang
 * @date 2013-4-2下午10:41:14
 */
public class FunctionPanel {
	private ConfigUtils conf = ConfigUtils.getInstance();

	private JTextField toTf;// 收件人
	private JTextField subjectTf;// 主题

	// 初始化联系人Panel
	public JPanel initFunctionPanel() {
		JPanel leftPanel = new JPanel();
		leftPanel.add(intiBox());
		return leftPanel;
	}

	// 初始化收件人Box
	private Box intiBox() {
		// 创建标签box
		Box boxV1 = Box.createVerticalBox();
		JLabel toLabel = new JLabel(conf.getString("sendFrame.lable.to", "收件人:"));
		boxV1.add(toLabel);
		boxV1.add(Box.createVerticalStrut(10));
		JLabel subjectLabel = new JLabel(conf.getString("sendFrame.lable.subject", "主题:"));
		boxV1.add(subjectLabel);

		// 创建文本框box
		Box boxV2 = Box.createVerticalBox();
		toTf = createTextField();
		boxV2.add(toTf);
		boxV2.add(Box.createVerticalStrut(8));
		subjectTf = createTextField();
		boxV2.add(subjectTf);

		// 创建基本box
		Box baseBox = Box.createHorizontalBox();
		baseBox.add(boxV1);
		baseBox.add(Box.createHorizontalStrut(20));
		baseBox.add(boxV2);
		boxV2.add(Box.createVerticalStrut(8));
		return baseBox;
	}

	private JTextField createTextField() {
		JTextField tf = new JTextField();
		tf.setPreferredSize(new Dimension(400, 25));
		return tf;
	}
}

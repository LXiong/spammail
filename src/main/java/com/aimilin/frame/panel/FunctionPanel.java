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
		Box boxH1 = Box.createHorizontalBox();
		JLabel toLabel = new JLabel(conf.getString("sendFrame.lable.to", "收件人:"));
		toLabel.setPreferredSize(new Dimension(50, 25));
		boxH1.add(toLabel);
		boxH1.add(Box.createHorizontalStrut(10));
		toTf = createTextField();
		boxH1.add(toTf);

		// 创建文本框box
		Box boxH2 = Box.createHorizontalBox();
		JLabel subjectLabel = new JLabel(conf.getString("sendFrame.lable.subject", "主    题:"));
		subjectLabel.setPreferredSize(new Dimension(50, 25));
		boxH2.add(subjectLabel);
		boxH2.add(Box.createHorizontalStrut(10));
		subjectTf = createTextField();
		boxH2.add(subjectTf);

		// 创建基本box
		Box baseBox = Box.createVerticalBox();
		baseBox.add(boxH1);
		baseBox.add(Box.createVerticalStrut(8));
		baseBox.add(boxH2);
		baseBox.add(Box.createVerticalStrut(8));
		return baseBox;
	}

	private JTextField createTextField() {
		JTextField tf = new JTextField();
		tf.setPreferredSize(new Dimension(400, 25));
		return tf;
	}
}

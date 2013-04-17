package com.aimilin.frame.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.BooleanUtils;

import com.aimilin.utils.ConfigUtils;

/**
 * 收件人主题等功能面板
 * @author LiuJunGuang
 * @date 2013-4-2下午10:41:14
 */
public class FunctionPanel {
	private ConfigUtils conf = ConfigUtils.getInstance();

	private JTextField toTf;// 收件人
	private JTextField ccTf;// 抄送人
	private JTextField bccTf;// 密送人
	private JTextField subjectTf;// 主题

	private Box baseBox;
	private JPanel panel;
	private Map<Integer, MyBox> boxMap = new HashMap<Integer, MyBox>();
	private Boolean addBcc = true;
	private Boolean addCc = true;
	/**
	 * @fields ADDRESS_TYPE_CC 抄送人
	 */
	public static int ADDRESS_TYPE_CC = 1;
	/**
	 * @fields ADDRESS_TYPE_BCC 密送人
	 */
	public static int ADDRESS_TYPE_BCC = 2;

	// 初始化联系人Panel
	public Component initFunctionPanel() {
		panel = new JPanel(new BorderLayout());
		panel.add(intiBox(), BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setMinimumSize(new Dimension(400, 73));
		return scrollPane;
	}

	// 初始化收件人Box
	private Box intiBox() {
		// 创建收件人
		Box boxH1 = Box.createHorizontalBox();
		JLabel toLabel = new JLabel(conf.getString("sendFrame.label.to", "收件人:"));
		toLabel.setPreferredSize(new Dimension(50, 25));
		boxH1.add(toLabel);
		boxH1.add(Box.createHorizontalStrut(10));
		toTf = createTextField();
		boxH1.add(toTf);

		// 创建主题
		Box boxH2 = Box.createHorizontalBox();
		JLabel subjectLabel = new JLabel(conf.getString("sendFrame.label.subject", "主题:"));
		subjectLabel.setPreferredSize(new Dimension(50, 25));
		boxH2.add(subjectLabel);
		boxH2.add(Box.createHorizontalStrut(10));
		subjectTf = createTextField();
		boxH2.add(subjectTf);

		// 创建基本box
		baseBox = Box.createVerticalBox();
		baseBox.add(Box.createVerticalStrut(5));
		baseBox.add(boxH1);
		baseBox.add(Box.createVerticalStrut(5));
		baseBox.add(boxH2);
		baseBox.add(Box.createVerticalStrut(5));
		return baseBox;
	}

	/**
	 * 添加或删除收件人
	 * @author LiuJunGuang
	 * @param addressType
	 * @date 2013-4-17下午9:24:41
	 */
	public void addOrRemove(int addressType) {
		if (FunctionPanel.ADDRESS_TYPE_BCC == addressType) {
			addOrRemove(addressType, addBcc);
			addBcc = BooleanUtils.negate(addBcc);
		} else if (FunctionPanel.ADDRESS_TYPE_CC == addressType) {
			addOrRemove(addressType, addCc);
			addCc = BooleanUtils.negate(addCc);
		}
	}

	/**
	 * 添加或删除联系人
	 * @author LiuJunGuang
	 * @param boxType 添加类型
	 * @param addOrRemove true为添加 ，false为移除
	 * @date 2013-4-17下午8:31:13
	 */
	private void addOrRemove(int addressType, boolean addOrRemove) {
		if (addOrRemove)
			addBox(addressType);
		else
			removeBox(addressType);
	}

	// 删除联系人Box
	private void removeBox(int addressType) {
		if (boxMap.get(addressType) == null)
			return;
		if (FunctionPanel.ADDRESS_TYPE_CC == addressType) {
			ccTf = null;
		} else if (FunctionPanel.ADDRESS_TYPE_BCC == addressType) {
			bccTf = null;
		}
		MyBox mybox = boxMap.remove(Integer.valueOf(addressType));
		baseBox.remove(mybox.box);
		baseBox.remove(mybox.component);
	}

	/**
	 * 根据收件人类型添加收件人信息
	 * @author LiuJunGuang
	 * @param addressType
	 * @date 2013-4-17下午9:02:34
	 */
	private void addBox(int addressType) {
		if (boxMap.containsKey(addressType))
			return;

		JTextField tf = createTextField();
		String labStr = "", defaultStr = "";
		if (FunctionPanel.ADDRESS_TYPE_CC == addressType) {
			labStr = "sendFrame.lable.cc";
			defaultStr = "抄  送:";
			ccTf = tf;
		} else if (FunctionPanel.ADDRESS_TYPE_BCC == addressType) {
			labStr = "sendFrame.lable.bcc";
			defaultStr = "密  送:";
			bccTf = tf;
		}
		Box ccH = Box.createHorizontalBox();
		JLabel ccLabel = new JLabel(conf.getString(labStr, defaultStr));
		ccLabel.setPreferredSize(new Dimension(50, 25));
		ccH.add(ccLabel);
		ccH.add(Box.createHorizontalStrut(10));
		ccH.add(tf);
		Component com = Box.createVerticalStrut(5);
		baseBox.add(ccH, baseBox.getComponentCount() - 2);
		baseBox.add(com, baseBox.getComponentCount() - 2);
		boxMap.put(Integer.valueOf(addressType), new MyBox(ccH, com));
	}

	/**
	 * 创建文本框
	 * @author LiuJunGuang
	 * @return JTextField
	 * @date 2013-4-17下午8:55:03
	 */
	private JTextField createTextField() {
		JTextField tf = new JTextField();
		tf.setPreferredSize(new Dimension(400, 25));
		return tf;
	}

	/**
	 * Box对象和Component对象的封装
	 * @author LiuJunGuang
	 * @date 2013-4-17下午9:00:27
	 */
	private class MyBox {
		public Box box = null;
		public Component component;

		public MyBox(Box box, Component component) {
			super();
			this.box = box;
			this.component = component;
		}
	}

	/**
	 * 获取收件人文本框对象
	 * @return toTf
	 */
	public JTextField getToTf() {
		return toTf;
	}

	/**
	 * @return ccTf 获取抄送人文本框
	 */
	public JTextField getCcTf() {
		return ccTf;
	}

	/**
	 * @return bccTf 获取密送人文本框
	 */
	public JTextField getBccTf() {
		return bccTf;
	}

	/**
	 * @return subjectTf 获取主题文本框
	 */
	public JTextField getSubjectTf() {
		return subjectTf;
	}

	/**
	 * @return panel 获取主面板
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @return addBcc 是否添加密送人，true添加，false 删除
	 */
	public Boolean getAddBcc() {
		return addBcc;
	}

	/**
	 * @return addCc 是否添加抄送人，true添加，false 删除
	 */
	public Boolean getAddCc() {
		return addCc;
	}
}

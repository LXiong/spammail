package com.aimilin.frame.panel;

import javax.swing.JButton;

/**
 * 可构建按钮接口
 * @author LiuJunGuang
 * @date 2013-4-6下午7:12:22
 */
public interface Createable {

	/**
	 * 可构建按钮
	 * @author LiuJunGuang
	 * @param buttonName 按钮名称
	 * @param iconName 图标名称
	 * @return JButton
	 * @date 2013-4-6下午7:13:41
	 */
	public JButton createButton(String buttonName, String iconName);
}

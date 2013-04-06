package com.aimilin.frame.panel;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.aimilin.utils.ConfigUtils;

/**
 * 基础Panel
 * @author LiuJunGuang
 * @date 2013-4-6下午7:11:16
 */
public abstract class BasePanel implements Createable {
	protected ConfigUtils conf = ConfigUtils.getInstance();
	private Logger log = Logger.getLogger(BasePanel.class);
	protected final String SEPARATOR = "separator";

	/**
	 * 初始化工具条按钮,同时将按钮对象设置到bean中，buttonArray数组为二维数组定义如下:
	 * 
	 * <pre>
	 * 0 -- bean属性名称
	 * 1 -- 配置文件中定义的属性名称
	 * 2 -- 如果配置文件加载失败使用的默认值
	 * 4 -- icon图标
	 * separator -- 菜单分隔符
	 * 
	 * String[][] buttonArray = {
	 * 		{ "boldBt", "content.toolbar.bold", "加粗", "text_bold.png" },
	 * 		{ "separator" }
	 * 		 };
	 * </pre>
	 * @author LiuJunGuang
	 * @param buttonArray
	 * @date 2013-4-6下午1:49:08
	 */
	public void initButton(Object bean, JToolBar toolBar, String[][] buttonArray) {
		for (String[] strs : buttonArray) {
			try {
				if (this.SEPARATOR.equals(strs[0])) {
					toolBar.addSeparator();
					continue;
				}
				JButton button = createButton(conf.getString(strs[1], strs[2]), strs[3]);
				toolBar.add(button);
				if (bean != null)
					BeanUtils.setProperty(bean, strs[0], button);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	@Override
	public abstract JButton createButton(String buttonName, String iconName);

}

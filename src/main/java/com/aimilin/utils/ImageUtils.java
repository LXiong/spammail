package com.aimilin.utils;

import java.net.URL;

import javax.swing.ImageIcon;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 图片加载器
 * @author LiuJunGuang
 * @date 2013-3-31下午8:23:33
 */
public class ImageUtils {
	private static Logger log = Logger.getLogger(ImageUtils.class);

	/**
	 * 根据图像名称加载图像信息，
	 * 如果指定的参数imageName为null或empty则返回null，
	 * 如果图像加载失败则返回null
	 * @author LiuJunGuang
	 * @param imageName
	 * @return
	 * @date 2013-3-31下午8:26:07
	 */
	public static ImageIcon loadImage(String imageName) {
		if (StringUtils.isEmpty(imageName))
			return null;
		try {
			if (imageName.indexOf(":") == -1) {
				URL iconUrl = ImageUtils.class.getClassLoader().getResource("images/" + imageName);
				return new ImageIcon(iconUrl);
			}
			return new ImageIcon(imageName);
		} catch (Exception e) {
			log.error(String.format("图像\"%s\"加载失败！", imageName));
		}
		return null;
	}
}

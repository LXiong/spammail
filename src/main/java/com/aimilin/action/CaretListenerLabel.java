package com.aimilin.action;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;

import com.aimilin.utils.ConfigUtils;

/**
 * 用于侦听文本组件插入符的位置更改的侦听器。
 * @author LiuJunGuang
 * @date 2013-4-6下午9:21:55
 */
public class CaretListenerLabel extends JLabel implements CaretListener {
	private static final long serialVersionUID = -3227686324843612191L;
	protected ConfigUtils conf = ConfigUtils.getInstance();

	public CaretListenerLabel() {
		super();
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		final JTextPane textPane = (JTextPane) e.getSource();
		final int dot = e.getDot();
		final int mark = e.getMark();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (dot == mark) { // no selection
					try {
						setText(String.format(conf.getString("status.insert_message", "行号 ：%d  , 列号 ：%d"),
								getRow(dot, textPane), getColumn(dot, textPane)));
					} catch (Exception ble) {
						setText(String.format(conf.getString("status.insert_caret", "插入符位置 : %d"), dot));
					}
				} else if (dot < mark) {
					setText(String.format(conf.getString("status.select_message", "已选择 , 开始 : %d , 结束 : %d"), dot, mark));
				} else {
					setText(String.format(conf.getString("status.select_message", "已选择 , 开始 : %d , 结束 : %d"), mark, dot));
				}
			}
		});
	}

	/**
	 * 获取光标所在的行
	 * @author LiuJunGuang
	 * @param pos
	 * @param editor
	 * @return
	 * @date 2013-4-6下午10:40:13
	 */
	private int getRow(int pos, JTextComponent editor) {
		int rn = (pos == 0) ? 1 : 0;
		try {
			int offs = pos;
			while (offs > 0) {
				offs = Utilities.getRowStart(editor, offs) - 1;
				rn++;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return rn;
	}

	/**
	 * 获取光标所在的列
	 * @author LiuJunGuang
	 * @param pos
	 * @param editor
	 * @return
	 * @date 2013-4-6下午10:40:14
	 */
	private int getColumn(int pos, JTextComponent editor) {
		try {
			return pos - Utilities.getRowStart(editor, pos) + 1;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
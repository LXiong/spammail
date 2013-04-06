package com.aimilin.action;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * 文档监听器
 * @author LiuJunGuang
 * @date 2013-4-6下午8:50:01
 */
public class MyDocumentListener implements DocumentListener {

	public MyDocumentListener() {
		super();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		displayEditInfo(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		displayEditInfo(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		displayEditInfo(e);
	}

	private void displayEditInfo(DocumentEvent e) {
		Document document = e.getDocument();
		int changeLength = e.getLength();
		System.out.println(e.getType().toString() + ": " + changeLength + " character"
				+ ((changeLength == 1) ? ". " : "s. ") + " Text length = " + document.getLength());
	}

}

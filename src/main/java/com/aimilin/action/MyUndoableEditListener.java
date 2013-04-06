package com.aimilin.action;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * 撤销恢复监听器
 * @author LiuJunGuang
 * @date 2013-4-6下午8:37:50
 */
public class MyUndoableEditListener implements UndoableEditListener {
	private UndoManager undo = new UndoManager();
	// undo helpers
	private UndoAction undoAction;
	private RedoAction redoAction;

	public MyUndoableEditListener() {
		super();
		undoAction = new UndoAction(undo);
		redoAction = new RedoAction(undo);
		undoAction.setRedoAction(redoAction);
		redoAction.setUndoAction(undoAction);
	}

	@Override
	public void undoableEditHappened(UndoableEditEvent e) {
		// Remember the edit and update the menus.
		undo.addEdit(e.getEdit());
		undoAction.updateUndoState();
		redoAction.updateRedoState();
	}

	public UndoAction getUndoAction() {
		return undoAction;
	}

	public void setUndoAction(UndoAction undoAction) {
		this.undoAction = undoAction;
	}

	public RedoAction getRedoAction() {
		return redoAction;
	}

	public void setRedoAction(RedoAction redoAction) {
		this.redoAction = redoAction;
	}
}
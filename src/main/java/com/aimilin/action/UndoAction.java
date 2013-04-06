package com.aimilin.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 * 撤销动作
 * @author LiuJunGuang
 * @date 2013-4-6下午8:45:10
 */
public class UndoAction extends AbstractAction {
	private static final long serialVersionUID = -1801934999852003179L;
	private RedoAction redoAction;
	private UndoManager undo;

	public UndoAction(UndoManager undo) {
		super("Undo");
		setEnabled(false);
		this.undo = undo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			undo.undo();
		} catch (CannotUndoException ex) {
			System.out.println("Unable to undo: " + ex);
			ex.printStackTrace();
		}
		updateUndoState();
		redoAction.updateRedoState();
	}

	protected void updateUndoState() {
		if (undo.canUndo()) {
			setEnabled(true);
			putValue(Action.NAME, undo.getUndoPresentationName());
		} else {
			setEnabled(false);
			putValue(Action.NAME, "Undo");
		}
	}

	public RedoAction getRedoAction() {
		return redoAction;
	}

	public void setRedoAction(RedoAction redoAction) {
		this.redoAction = redoAction;
	}

}
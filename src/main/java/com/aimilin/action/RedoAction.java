package com.aimilin.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

/**
 * 恢复动作
 * @author LiuJunGuang
 * @date 2013-4-6下午8:45:22
 */
public class RedoAction extends AbstractAction {
	private static final long serialVersionUID = 6929644275383183097L;
	private UndoAction undoAction;
	private UndoManager undo;

	public RedoAction(UndoManager undo) {
		super("Redo");
		setEnabled(false);
		this.undo = undo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			undo.redo();
		} catch (CannotRedoException ex) {
			System.out.println("Unable to redo: " + ex);
			ex.printStackTrace();
		}
		updateRedoState();
		undoAction.updateUndoState();
	}

	protected void updateRedoState() {
		if (undo.canRedo()) {
			setEnabled(true);
			putValue(Action.NAME, undo.getRedoPresentationName());
		} else {
			setEnabled(false);
			putValue(Action.NAME, "Redo");
		}
	}

	public UndoAction getUndoAction() {
		return undoAction;
	}

	public void setUndoAction(UndoAction undoAction) {
		this.undoAction = undoAction;
	}

}
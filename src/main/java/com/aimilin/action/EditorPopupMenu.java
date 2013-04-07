package com.aimilin.action;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import com.aimilin.utils.ImageUtils;

/**
 * 右键菜单
 * @author LiuJunGuang
 * @date 2013-4-7下午8:14:24
 */
public class EditorPopupMenu extends MouseAdapter implements UndoableEditListener {

	/** 添加剪切侦听器 */
	private Action cutAction = new DefaultEditorKit.CutAction();
	/** 添加复制侦听器 */
	private Action copyAction = new DefaultEditorKit.CopyAction();
	/** 添加粘贴侦听器 */
	private Action pasteAction = new DefaultEditorKit.PasteAction();
	/** 添加撤消管理器 */
	protected UndoManager undoMenager = new UndoManager();
	/** 添加撤消侦听器 */
	private UndoAction undoAction = new UndoAction();
	/** 添加恢复侦听器 */
	private RedoAction redoAction = new RedoAction();

	private JPopupMenu popup;

	public EditorPopupMenu() {
		popup = new JPopupMenu();
		JMenuItem copy = new JMenuItem(copyAction);
		copy.setText("复制");
		copy.setIcon(ImageUtils.loadImage("copy.png"));
		JMenuItem paste = new JMenuItem(pasteAction);
		paste.setText("粘贴");
		paste.setIcon(ImageUtils.loadImage("paste.png"));
		JMenuItem cut = new JMenuItem(cutAction);
		cut.setText("剪切");
		cut.setIcon(ImageUtils.loadImage("cut.png"));
		JMenuItem undo = new JMenuItem(undoAction);
		undo.setText("撤销");
		undo.setIcon(ImageUtils.loadImage("undo.png"));
		JMenuItem redo = new JMenuItem(redoAction);
		redo.setText("重做");
		redo.setIcon(ImageUtils.loadImage("redo.png"));
		popup.add(undo);
		popup.add(redo);
		popup.addSeparator();// 插入分隔符
		popup.add(copy);
		popup.add(paste);
		popup.add(cut);
	}

	// 撤销
	class UndoAction extends AbstractAction {
		private static final long serialVersionUID = 3432594228980334715L;

		public UndoAction() {
			super("Undo");
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				undoMenager.undo();
			} catch (CannotUndoException ex) {
				System.out.println("Unable to undo: " + ex);
				ex.printStackTrace();
			}
			update();
			redoAction.update();
		}

		protected void update() {
			if (undoMenager.canUndo()) {
				setEnabled(true);
				putValue(Action.NAME, undoMenager.getUndoPresentationName());
			} else {
				setEnabled(false);
				putValue(Action.NAME, "撤销");
			}
		}
	}

	// 重做
	class RedoAction extends AbstractAction {
		private static final long serialVersionUID = 5953634866272225781L;

		public RedoAction() {
			super("Redo");
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				undoMenager.redo();
			} catch (CannotRedoException ex) {
				System.err.println("Unable to redo: " + ex);
				ex.printStackTrace();
			}
			update();
			undoAction.update();
		}

		protected void update() {
			if (undoMenager.canRedo()) {
				setEnabled(true);
				putValue(Action.NAME, undoMenager.getRedoPresentationName());
			} else {
				setEnabled(false);
				putValue(Action.NAME, "重做");
			}
		}
	}

	@Override
	public void undoableEditHappened(UndoableEditEvent e) {
		undoMenager.addEdit(e.getEdit());
		undoAction.update();
		redoAction.update();
	}

	// 编辑区鼠标右键的响应
	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			// 处理鼠标右键单击
			popup.show(e.getComponent(), e.getX(), e.getY());// 显示弹出菜单
		}
	}
}

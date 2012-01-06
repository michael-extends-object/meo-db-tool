package com.github.meo.db.tool.ui.dialog;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessagePane extends JOptionPane {

	private static final long serialVersionUID = -6314709192065933154L;
	private static final String DIALOG_TITLE_INFO = "Information";
	private static final String DIALOG_TITLE_ERROR = "Error";
	private static final String TEXT_AREA_TITLE = "Message";

	private static JOptionPane getOptionPane(int messageType,
			Object... messages) {
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(400, 200));

		for (Object message : messages) {
			textArea.append(message.toString());
		}

		Object[] message = { TEXT_AREA_TITLE, scrollPane };

		return new JOptionPane(message, messageType, CLOSED_OPTION);
	}

	public static void showInfoDialog(Component parent, Object... messages) {
		JOptionPane pane = getOptionPane(INFORMATION_MESSAGE, messages);
		pane.createDialog(parent, DIALOG_TITLE_INFO).setVisible(true);
	}

	public static void showErrorDialog(Component parent, Object... messages) {
		JOptionPane pane = getOptionPane(ERROR_MESSAGE, messages);
		pane.createDialog(parent, DIALOG_TITLE_ERROR).setVisible(true);
	}

}

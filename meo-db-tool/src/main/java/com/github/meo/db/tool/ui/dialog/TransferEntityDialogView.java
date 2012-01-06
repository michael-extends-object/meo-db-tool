package com.github.meo.db.tool.ui.dialog;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTree;

import javax.swing.JSplitPane;

public class TransferEntityDialogView extends JDialog {

	private static final long serialVersionUID = -2364087053404335592L;
	private JTable entityTable;
	private JLabel sourceDatabaseLabel;
	private JLabel targetDatabaseLabel;
	private JComboBox sourceDatabaseComboBox;
	private JComboBox targetDatabaseComboBox;
	private JButton cancelButton;
	private JButton transferEntityButton;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTree tree;

	public TransferEntityDialogView() {

		setTitle("Transfer Entity");
		setSize(new Dimension(800, 600));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		sourceDatabaseLabel = new JLabel("Source Database");
		sourceDatabaseLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_sourceDatabaseLabel = new GridBagConstraints();
		gbc_sourceDatabaseLabel.gridwidth = 2;
		gbc_sourceDatabaseLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_sourceDatabaseLabel.insets = new Insets(0, 0, 5, 0);
		gbc_sourceDatabaseLabel.gridx = 0;
		gbc_sourceDatabaseLabel.gridy = 0;
		getContentPane().add(sourceDatabaseLabel, gbc_sourceDatabaseLabel);
		sourceDatabaseLabel.setLabelFor(sourceDatabaseComboBox);

		sourceDatabaseComboBox = new JComboBox();
		GridBagConstraints gbc_sourceDatabaseComboBox = new GridBagConstraints();
		gbc_sourceDatabaseComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_sourceDatabaseComboBox.gridwidth = 2;
		gbc_sourceDatabaseComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_sourceDatabaseComboBox.gridx = 0;
		gbc_sourceDatabaseComboBox.gridy = 1;
		getContentPane()
				.add(sourceDatabaseComboBox, gbc_sourceDatabaseComboBox);

		targetDatabaseLabel = new JLabel("Target Database");
		targetDatabaseLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_targetDatabaseLabel = new GridBagConstraints();
		gbc_targetDatabaseLabel.gridwidth = 2;
		gbc_targetDatabaseLabel.insets = new Insets(0, 0, 5, 0);
		gbc_targetDatabaseLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_targetDatabaseLabel.gridx = 0;
		gbc_targetDatabaseLabel.gridy = 2;
		getContentPane().add(targetDatabaseLabel, gbc_targetDatabaseLabel);
		targetDatabaseLabel.setLabelFor(targetDatabaseComboBox);

		targetDatabaseComboBox = new JComboBox();
		GridBagConstraints gbc_targetDatabaseComboBox = new GridBagConstraints();
		gbc_targetDatabaseComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_targetDatabaseComboBox.gridwidth = 2;
		gbc_targetDatabaseComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_targetDatabaseComboBox.gridx = 0;
		gbc_targetDatabaseComboBox.gridy = 3;
		getContentPane()
				.add(targetDatabaseComboBox, gbc_targetDatabaseComboBox);

		entityTable = new JTable();

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		getContentPane().add(new JScrollPane(entityTable), gbc_scrollPane);

		splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 2;
		gbc_splitPane.insets = new Insets(0, 0, 5, 5);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 5;
		getContentPane().add(splitPane, gbc_splitPane);

		scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		tree = new JTree();
		scrollPane.setViewportView(tree);

		scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);

		table = new JTable();
		scrollPane_1.setRowHeaderView(table);

		cancelButton = new JButton("Cancel");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 0;
		gbc_cancelButton.gridy = 6;
		getContentPane().add(cancelButton, gbc_cancelButton);

		transferEntityButton = new JButton("Transfer");
		GridBagConstraints gbc_transferEntityButton = new GridBagConstraints();
		gbc_transferEntityButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_transferEntityButton.gridx = 1;
		gbc_transferEntityButton.gridy = 6;
		getContentPane().add(transferEntityButton, gbc_transferEntityButton);

		getSourceDatabaseComboBox().setEnabled(false);
	}

	public JTable getEntityTable() {
		return entityTable;
	}

	public JComboBox getSourceDatabaseComboBox() {
		return sourceDatabaseComboBox;
	}

	public JComboBox getTargetDatabaseComboBox() {
		return targetDatabaseComboBox;
	}

	public void setEntityTable(JTable entityTable) {
		this.entityTable = entityTable;
	}

	public void setSourceDatabaseComboBox(JComboBox sourceDatabaseComboBox) {
		this.sourceDatabaseComboBox = sourceDatabaseComboBox;
	}

	public void setTargetDatabaseComboBox(JComboBox targetDatabaseComboBox) {
		this.targetDatabaseComboBox = targetDatabaseComboBox;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getTransferEntityButton() {
		return transferEntityButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public void setTransferEntityButton(JButton transferEntityButton) {
		this.transferEntityButton = transferEntityButton;
	}

	// public JTree getErmTree() {
	// return ermTree;
	// }
	//
	// public JTable getRefEntityTable() {
	// return refEntityTable;
	// }

}

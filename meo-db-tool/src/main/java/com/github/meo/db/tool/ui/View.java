package com.github.meo.db.tool.ui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import java.awt.Dimension;

public class View extends JFrame {

	private static final long serialVersionUID = 8098001422371795718L;
	private JTable entityTable;
	private JComboBox databaseComboBox;
	private JTree ermTree;

	public View() {

		setTitle("MEO DB Tool");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JMenuBar menuBar = new JMenuBar();
		// setJMenuBar(menuBar);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 125, 300, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(4);
		GridBagConstraints gbc_splitPane_1 = new GridBagConstraints();
		gbc_splitPane_1.gridwidth = 2;
		gbc_splitPane_1.fill = GridBagConstraints.BOTH;
		gbc_splitPane_1.gridx = 0;
		gbc_splitPane_1.gridy = 0;
		getContentPane().add(splitPane_1, gbc_splitPane_1);

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setMinimumSize(new Dimension(200, 100));
		splitPane_2.setDividerSize(0);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setLeftComponent(splitPane_2);

		databaseComboBox = new JComboBox();
		splitPane_2.setLeftComponent(databaseComboBox);

		JScrollPane scrollPane = new JScrollPane();
		splitPane_2.setRightComponent(scrollPane);

		ermTree = new ErmTree();
		scrollPane.setViewportView(ermTree);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane_1);

		entityTable = new JTable();
		scrollPane_1.setViewportView(entityTable);

	}

	public JTable getEntityTable() {
		return entityTable;
	}

	public JComboBox getDatabaseComboBox() {
		return databaseComboBox;
	}

	public void setEntityTable(JTable table) {
		this.entityTable = table;
	}

	public void setDatabaseComboBox(JComboBox databaseComboBox) {
		this.databaseComboBox = databaseComboBox;
	}

	public JTree getErmTree() {
		return ermTree;
	}

	public void setErmTree(JTree ermTree) {
		this.ermTree = ermTree;
	}

	public JPopupMenu getEntityTableContextMenu() {
		JPopupMenu popupMenu = new JPopupMenu();
		return popupMenu;
	}
}
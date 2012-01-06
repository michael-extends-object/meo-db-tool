package com.github.meo.db.tool.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.util.Assert;

import com.github.meo.db.tool.dao.Database;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityRelationshipModel;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.ui.dialog.MessagePane;
import com.github.meo.db.tool.ui.dialog.TransferEntityDialog;

public class Controller {

	private Model model;
	private View view;

	public Controller() {
		init();
	}

	private void init() {
		model = new Model();
		view = new View();
		setModels();
		setListeners();
	}

	private void setModels() {
		view.getDatabaseComboBox().setModel(model.getDatabaseComboBoxModel());
		view.getErmTree().setModel(
				model.getErmTreeModel(ModelTestObjects
						.getDatabaseAccountManagementSource()));
		view.getEntityTable().setModel(new EntityTableModel());
	}

	private void setListeners() {

		view.getDatabaseComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				changeDatabase((Database) comboBox.getSelectedItem());
			}
		});

		view.getErmTree().getSelectionModel()
				.addTreeSelectionListener(new TreeSelectionListener() {

					public void valueChanged(final TreeSelectionEvent e) {
						// TreePath path = e.getNewLeadSelectionPath();
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) view
								.getErmTree().getLastSelectedPathComponent();

						if (node == null) {
							return;
						}

						switch (e.getPath().getPathCount()) {
						case 1:
							break;
						case 2:
							changeEntityRelationshipModel((IEntityRelationshipModel) node
									.getUserObject());
							break;
						case 3:
							changeEntityType((IEntityType) node.getUserObject());
							break;
						case 4:
							// changeEntityType((IEntityType)
							// (((DefaultMutableTreeNode) node
							// .getParent()).getUserObject()));
							changeAttributeType((IAttributeType) node
									.getUserObject());
							break;
						default:
							break;
						}
					}
				});

		view.getEntityTable().addMouseListener(new MouseAdapter() {
			public void mousePressed(final MouseEvent e) {
				if (e.isPopupTrigger()) {
					showEntityTableContextMenu(e);
				}
			}

			public void mouseReleased(final MouseEvent e) {
				if (e.isPopupTrigger()) {
					showEntityTableContextMenu(e);
				}
			}
		});
	}

	private void showTransferEntityDialog() {
		TransferEntityDialog transferEntityDialog;
		transferEntityDialog = new TransferEntityDialog(getModel()
				.getDatabases(), getSelectedEntities());
		transferEntityDialog.setSourceDatabases(getDatabase());
		JDialog dialog = transferEntityDialog.getView();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private void showEntityTableContextMenu(MouseEvent e) {
		JPopupMenu popupMenu = view.getEntityTableContextMenu();
		popupMenu.add(getTransferEntitiesAction());
		popupMenu.add(getDeleteEntitiesAction());
		popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}

	public Action getTransferEntitiesAction() {
		@SuppressWarnings("serial")
		Action action = new AbstractAction("Transfer Entities") {
			public void actionPerformed(ActionEvent e) {
				showTransferEntityDialog();
			}
		};
		return action;
	}

	public Action getDeleteEntitiesAction() {
		@SuppressWarnings("serial")
		Action action = new AbstractAction("Delete Entity") {
			public void actionPerformed(ActionEvent e) {
				model.getEntityDao().deleteEntities(getSelectedEntities());
				updateEntityTable();
			}
		};
		return action;
	}

	private List<IEntity> getSelectedEntities() {

		List<IEntity> entities = new ArrayList<IEntity>();

		EntityTableModel tableModel = (EntityTableModel) view.getEntityTable()
				.getModel();

		for (int rowIndex : view.getEntityTable().getSelectedRows()) {
			entities.add(tableModel.getEntity(rowIndex));
		}

		return entities;
	}

	private void changeEntityRelationshipModel(IEntityRelationshipModel erm) {
		Assert.notNull(erm);
		getDatabase().setCurrentEntityRelationshipModel(erm);
	}

	private void changeDatabase(Database database) {
		Assert.notNull(database);
		updateErmTree();
	}

	private void changeEntityType(IEntityType entityType) {
		Assert.notNull(entityType);
		setEntityType(entityType);
		updateEntityTable();
	}

	private void changeAttributeType(IAttributeType attributeType) {
		Assert.notNull(attributeType);
		setAttributeType(attributeType);
	}

	private void updateErmTree() {
		view.getErmTree().setModel(model.getErmTreeModel(getDatabase()));
		view.getEntityTable().setModel(new EntityTableModel());
	}

	public void updateEntityTable() {
		getModel().setEntityDao(getDatabase());
		try {
			List<IEntity> entities = getModel().getEntityDao().selectEntities(
					getEntityType());
			setEntityTableModel(new EntityTableModel(entities));
		} catch (BadSqlGrammarException e) {
			MessagePane.showErrorDialog(getView(), e.getMessage());
		}
	}

	public Database getDatabase() {
		Database database = (Database) view.getDatabaseComboBox()
				.getSelectedItem();
		database.setCurrentEntityRelationshipModel(ModelTestObjects.getErm());
		return database;
	}

	public void setEntityTableModel(EntityTableModel tableModel) {
		view.getEntityTable().setModel(tableModel);
	}

	public Model getModel() {
		return model;
	}

	public View getView() {
		return view;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}

	private void setEntityType(IEntityType entityType) {
		getModel().setEntityType(entityType);
	}

	private IEntityType getEntityType() {
		return getModel().getEntityType();
	}

	private void setAttributeType(IAttributeType attributeType) {
		getModel().setAttributeType(attributeType);
	}
}
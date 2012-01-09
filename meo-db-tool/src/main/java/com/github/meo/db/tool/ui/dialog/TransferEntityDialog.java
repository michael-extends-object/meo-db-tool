package com.github.meo.db.tool.ui.dialog;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.Assert;

import com.github.meo.db.tool.dao.Database;
import com.github.meo.db.tool.dao.EntityDao;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.ui.EntityTableModel;

public class TransferEntityDialog {

	private TransferEntityDialogModel model;
	private TransferEntityDialogView view;

	public TransferEntityDialog(List<Database> databases, List<IEntity> entities) {
		model = new TransferEntityDialogModel();
		setDatabases(databases);
		setEntities(entities);
		view = new TransferEntityDialogView();
		view.setModal(true);
		setModels();
		setListeners();
	}

	private void setListeners() {
		view.getCancelButton().addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (view.isVisible()) {
					view.setVisible(false);
				}
			}
		});

		getView().getTransferEntityButton().addActionListener(
				new ActionListener() {
					public void actionPerformed(final ActionEvent event) {
						// Database sourceDatabase = getSourceDatabase();
						Database targetDatabase = getTargetDatabase();
						targetDatabase
								.setCurrentErm(targetDatabase
										.getErms().get(0));
						// EntityDao entityDaoSource = new EntityDao(
						// sourceDatabase);
						EntityDao entityDaoTarget = new EntityDao(
								targetDatabase);

						List<Object> errors = new ArrayList<Object>();

						try {
							entityDaoTarget.insertEntities(getEntities());
						} catch (DuplicateKeyException e) {
							// unique constraint violation
							errors.add(e);
						} catch (DataAccessException e) {
							errors.add(e);
						}

						if (!errors.isEmpty()) {
							MessagePane.showErrorDialog(getView(),
									errors.toArray());
							return;
						}

						String message = String.format(
								"%s entities have been inserted succesfully!",
								getEntities().size());
						MessagePane.showInfoDialog(getView(), message);
						// TODO close transfer entity dialog?
					}
				});

		getView().getSourceDatabaseComboBox().addActionListener(
				new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						changeSourceDatabase();
					}
				});

		getView().getTargetDatabaseComboBox().addActionListener(
				new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						changeTargetDatabase();
					}
				});
	}

	private void setModels() {
		getView().getSourceDatabaseComboBox().setModel(
				getModel().getComboBoxModelDatabases());
		getView().getTargetDatabaseComboBox().setModel(
				getModel().getComboBoxModelDatabases());
		getView().getTargetDatabaseComboBox().removeItem(getSourceDatabase());
		getView().getEntityTable().setModel(
				new EntityTableModel(getModel().getEntities()));
		// getView().getRefEntityTable().setModel(new EntityTableModel());
	}

	public Database getSourceDatabase() {
		return (Database) view.getSourceDatabaseComboBox().getSelectedItem();
	}

	public Database getTargetDatabase() {
		return (Database) view.getTargetDatabaseComboBox().getSelectedItem();
	}

	public void setSourceDatabases(List<Database> databases) {
		Assert.notNull(databases);
		view.getSourceDatabaseComboBox().setModel(
				new DefaultComboBoxModel(databases.toArray()));
	}

	public void setTargetDatabases(List<Database> databases) {
		Assert.notNull(databases);
		view.getTargetDatabaseComboBox().setModel(
				new DefaultComboBoxModel(databases.toArray()));
	}

	public TransferEntityDialogModel getModel() {
		return model;
	}

	public TransferEntityDialogView getView() {
		return view;
	}

	private void changeSourceDatabase() {
		refreshTargetDatabases();
		// // Database database = getSourceDatabase();
		// // view.getSourceDatabaseComboBox().addItem(getTargetDatabase());
		// // view.getTargetDatabaseComboBox().removeItem(database);
	}

	private void refreshTargetDatabases() {
		view.getTargetDatabaseComboBox().setModel(
				getModel().getComboBoxModelDatabases());
		view.getTargetDatabaseComboBox().removeItem(getSourceDatabase());
	}

	private void changeSourceDatabase(Database database) {
		view.getSourceDatabaseComboBox().setSelectedItem(database);
		refreshTargetDatabases();
	}

	private void changeTargetDatabase() {
		// // Database database = getTargetDatabase();
		// // view.getTargetDatabaseComboBox().addItem(getSourceDatabase());
		// // view.getSourceDatabaseComboBox().removeItem(database);
	}

	public List<Database> getDatabase() {
		return getModel().getDatabases();
	}

	public List<IEntity> getEntities() {
		return getModel().getEntities();
	}

	public void setModel(TransferEntityDialogModel model) {
		this.model = model;
	}

	public void setView(TransferEntityDialogView view) {
		this.view = view;
	}

	public void setDatabases(List<Database> databases) {
		Assert.notNull(databases);
		getModel().setDatabases(databases);
	}

	public void setEntities(List<IEntity> entities) {
		Assert.notNull(entities);
		getModel().setEntities(entities);
	}

	public void setSourceDatabases(Database database) {
		changeSourceDatabase(database);
	}

}
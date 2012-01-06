package com.github.meo.db.tool.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import com.github.meo.db.tool.dao.Database;
import com.github.meo.db.tool.domain.IEntity;

public class TransferEntityDialogModel {

	List<IEntity> entities;
	List<Database> databases;

	TransferEntityDialogModel() {
		init();
	}

	private void init() {
		setEntities(new ArrayList<IEntity>());
		setDatabases(new ArrayList<Database>());
	}

	public List<IEntity> getEntities() {
		return entities;
	}

	public List<Database> getDatabases() {
		return databases;
	}

	public void setEntities(List<IEntity> entities) {
		this.entities = entities;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}

	public ComboBoxModel getComboBoxModelDatabases() {
		return new DefaultComboBoxModel(getDatabases().toArray());
	}
}

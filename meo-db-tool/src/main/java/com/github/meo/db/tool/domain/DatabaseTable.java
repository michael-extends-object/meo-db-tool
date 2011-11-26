package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTable {

	private String name;
	private List<DatabaseTableColumn> databaseTableColumns;
	
	public DatabaseTable() {
		init();
	}

	public DatabaseTable(String name) {
		init();
		setName(name);
	}

	private void init() {
		setDatabaseTableColumns(new ArrayList<DatabaseTableColumn>());
	}

	public List<DatabaseTableColumn> getDatabaseTableColumns() {
		return databaseTableColumns;
	}

	public void setDatabaseTableColumns(
			List<DatabaseTableColumn> databaseTableColumns) {
		this.databaseTableColumns = databaseTableColumns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public boolean equals(Object object) {
		
		// null reference?
		if (object == null) {
			return false;
		}

		/*
		 * Are the references pointing to the same object?
		 */
		if (this == object) {
			return true;
		}

		/*
		 * Same class?
		 */
		if (!getClass().equals(object.getClass())) {
			return false;
		}

		DatabaseTable databaseTable = (DatabaseTable) object;

		/*
		 * Do the objects have the same name?
		 */
		if (getName() == null) {
			if (databaseTable.getName() != null) {
				return false;
			}
		} else {

			if (databaseTable.getName() == null) {
				return false;
			}

			if (!getName().equals(databaseTable.getName())) {
				return false;
			}
		}

		return true;
	}
}

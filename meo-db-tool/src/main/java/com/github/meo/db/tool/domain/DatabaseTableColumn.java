package com.github.meo.db.tool.domain;

public class DatabaseTableColumn {

	private String name;

	public DatabaseTableColumn() {
	}

	public DatabaseTableColumn(String name) {
		setName(name);
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

		DatabaseTableColumn databaseTableColumn = (DatabaseTableColumn) object;
		
		/*
		 * Do the objects have the same name?
		 */
		if (getName() == null) {
			if (databaseTableColumn.getName() != null) {
				return false;
			}
		} else {
			if (databaseTableColumn.getName() == null) {
				return false;
			}

			if (!getName().equals(databaseTableColumn.getName())) {
				return false;
			}
		}

		return true;
	}

}

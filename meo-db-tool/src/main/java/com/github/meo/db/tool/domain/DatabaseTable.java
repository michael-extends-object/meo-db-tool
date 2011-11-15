package com.github.meo.db.tool.domain;

public class DatabaseTable {

	private String name;

	public DatabaseTable() {

	}

	public DatabaseTable(String name) {
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

package com.github.meo.db.tool.domain.db;

public class Column {

	private String name;

	public Column() {
	}

	public Column(String name) {
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

		Column column = (Column) object;

		/*
		 * Do the objects have the same name?
		 */
		if (getName() == null) {
			if (column.getName() != null) {
				return false;
			}
		} else {
			if (column.getName() == null) {
				return false;
			}

			if (!getName().equals(column.getName())) {
				return false;
			}
		}

		return true;
	}

}
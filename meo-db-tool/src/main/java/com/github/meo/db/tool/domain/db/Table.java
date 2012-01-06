package com.github.meo.db.tool.domain.db;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String name;
	private List<Column> columns;

	public Table() {
		init();
	}

	public Table(String name) {
		init();
		setName(name);
	}

	private void init() {
		setColumns(new ArrayList<Column>());
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
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

		Table table = (Table) object;

		/*
		 * Do the objects have the same name?
		 */
		if (getName() == null) {
			if (table.getName() != null) {
				return false;
			}
		} else {

			if (table.getName() == null) {
				return false;
			}

			if (!getName().equals(table.getName())) {
				return false;
			}
		}

		return true;
	}
}
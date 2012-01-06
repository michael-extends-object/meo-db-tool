package com.github.meo.db.tool.sql;

public interface Sequence {

	/**
	 * Returns the current sequence value.
	 * @return Current sequence value
	 */
	public String getCurrentValueSql();

	/**
	 * Returns an SQL statement to get the next value of the sequence. The query
	 * will automatically increment the sequence.
	 * 
	 * @return Next sequence value
	 */
	public String getNextValueSql();

}

/**
 * 
 */
package com.github.meo.db.tool.testsuite;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author michael.follmann
 * 
 */
public class ObjectIllegalAccessException implements Driver {

	private ObjectIllegalAccessException() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#acceptsURL(java.lang.String)
	 */
	public boolean acceptsURL(String arg0) throws SQLException {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#connect(java.lang.String, java.util.Properties)
	 */
	public Connection connect(String arg0, Properties arg1) throws SQLException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#getMajorVersion()
	 */
	public int getMajorVersion() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#getMinorVersion()
	 */
	public int getMinorVersion() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#getPropertyInfo(java.lang.String,
	 * java.util.Properties)
	 */
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1)
			throws SQLException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Driver#jdbcCompliant()
	 */
	public boolean jdbcCompliant() {
		return false;
	}

}

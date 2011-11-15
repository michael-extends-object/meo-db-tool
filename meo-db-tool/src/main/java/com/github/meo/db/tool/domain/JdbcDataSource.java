package com.github.meo.db.tool.domain;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JdbcDataSource implements DataSource {

	private final static Logger logger = Logger.getLogger(JdbcDataSource.class);

	private Driver driver;
	private String url;
	private String username;
	private String password;

	public JdbcDataSource() {

	}

	public JdbcDataSource(Driver driver) {
		setDriver(driver);
	}

	public JdbcDataSource(String driverClassName) {
		setDriver(getDriver(driverClassName));
	}

	public JdbcDataSource(Driver driver, String url) {
		setDriver(driver);
		setUrl(url);
	}

	public JdbcDataSource(String driverClassName, String url) {
		setDriver(getDriver(driverClassName));
		setUrl(url);
	}

	public JdbcDataSource(Driver driver, String url, String username) {
		setDriver(driver);
		setUrl(url);
		setUsername(username);
	}

	public JdbcDataSource(String driverClassName, String url, String username) {
		setDriver(getDriver(driverClassName));
		setUrl(url);
		setUsername(username);
	}

	public JdbcDataSource(Driver driver, String url, String username,
			String password) {
		setDriver(driver);
		setUrl(url);
		setUsername(username);
		setPassword(password);
	}

	public JdbcDataSource(String driverClassName, String url, String username,
			String password) {
		setDriver(getDriver(driverClassName));
		setUrl(url);
		setUsername(username);
		setPassword(password);
	}

	public void setDriver(String driverClassName) {
		setDriver(getDriver(driverClassName));
	}

	public String getDriverClassName() {
		return driver.getClass().getName();
	}

	public static Driver getDriver(String driverClassName) {

		Driver driver = null;

		try {
			driver = (Driver) Class.forName(driverClassName).newInstance();
		} catch (Exception e) {
			logger.error(String.format("Could not instantiate class '%s'", driverClassName));
		}

		return driver;
	}

	@Override
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

		JdbcDataSource jdbcDataSource = (JdbcDataSource) object;

		/*
		 * Do the objects have the same driver?
		 */
		if (getDriver() == null) {
			if (jdbcDataSource.getDriver() != null) {
				return false;
			}
		} else {

			if (jdbcDataSource.getDriver() == null) {
				return false;
			}

			if (!getDriver().getClass().getName().equals(jdbcDataSource.getDriver().getClass().getName())) {
				return false;
			}
		}



		/*
		 * Do the objects have the same url?
		 */
		if (getUrl() == null) {
			if (jdbcDataSource.getUrl() != null) {
				return false;
			}
		} else {

			if (jdbcDataSource.getUrl() == null) {
				return false;
			}

			if (!getUrl().equals(jdbcDataSource.getUrl())) {
				return false;
			}
		}
		

		/*
		 * Do the objects have the same user name?
		 */
		if (getUsername() == null) {
			if (jdbcDataSource.getUsername() != null) {
				return false;
			}
		} else {

			if (jdbcDataSource.getUsername() == null) {
				return false;
			}

			if (!getUsername().equals(jdbcDataSource.getUsername())) {
				return false;
			}
		}
		
		

		/*
		 * Do the objects have the same password?
		 */
		if (getPassword() == null) {
			if (jdbcDataSource.getPassword() != null) {
				return false;
			}
		} else {

			if (jdbcDataSource.getPassword() == null) {
				return false;
			}

			if (!getPassword().equals(jdbcDataSource.getPassword())) {
				return false;
			}
		}
		
		return true;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
	}

	public void setLoginTimeout(int seconds) throws SQLException {
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public Connection getConnection() throws SQLException {
		return null;
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

}

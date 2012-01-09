package com.github.meo.db.tool.xml;

import java.sql.Driver;

public class MappableDataSource {

	private Driver driver;
	private String url;
	private String username;
	private String password;

	public MappableDataSource() {

	}

	public Driver getDriver() {
		return driver;
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

	public void setDriver(Driver driver) {
		this.driver = driver;
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

	@Override
	public String toString() {

		if (driver == null) {
			return "UNKNOWN_" + url + "_" + username;
		}

		return driver.getClass().getSimpleName() + "_" + url + "_" + username;
	}

	@Override
	public boolean equals(Object object) {

		// null reference?
		if (object == null) {
			return false;
		}

		// Same object?
		if (this == object) {
			return true;
		}

		// Same class?
		if (!getClass().equals(object.getClass())) {
			return false;
		}

		MappableDataSource dataSource = (MappableDataSource) object;

		// Same driver?
		if (!getDriver().getClass().getName()
				.equals(dataSource.getDriver().getClass().getName())) {
			return false;
		}

		// Same URL?
		if (!getUrl().equals(dataSource.getUrl())) {
			return false;
		}

		// Same username?
		if (!getUsername().equals(dataSource.getUsername())) {
			return false;
		}

		// Same password?
		if (!getPassword().equals(dataSource.getPassword())) {
			return false;
		}

		return true;
	}
}
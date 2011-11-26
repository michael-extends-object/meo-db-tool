package com.github.meo.db.tool.xml;

import java.sql.Driver;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;

public class DriverXmlAdapter extends XmlAdapter<String, Driver> {

	private final static Logger logger = Logger.getLogger(DriverXmlAdapter.class);

	public DriverXmlAdapter() {
	}

	@Override
	public String marshal(Driver driver) {
		return driver.getClass().getName();
	}

	@Override
	public Driver unmarshal(String driverClassName) {

		Driver driver = null;

		try {
			driver = (Driver) Class.forName(driverClassName).newInstance();
		} catch (Exception e) {
			logger.error(String.format("Could not instantiate class '%s'",
					driverClassName));
		}

		return driver;
	}

}
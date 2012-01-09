package com.github.meo.db.tool.xml;

import java.sql.Driver;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DriverXmlAdapter extends XmlAdapter<String, Driver> {

	public DriverXmlAdapter() {
	}

	@Override
	public String marshal(Driver driver) {
		return driver.getClass().getName();
	}

	@Override
	public Driver unmarshal(String driverClassName) throws Exception {
		Driver driver = (Driver) Class.forName(driverClassName).newInstance();
		return driver;
	}
}
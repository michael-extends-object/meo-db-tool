package com.github.meo.db.tool.xml;

import java.sql.Driver;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.meo.db.tool.domain.JdbcDataSource;

public class DriverXmlAdapter extends XmlAdapter<String, Driver> {

	public DriverXmlAdapter() {
	}

	@Override
	public String marshal(Driver driver) {
		return driver.getClass().getName();
	}

	@Override
	public Driver unmarshal(String driverClassName) {
		return JdbcDataSource.getDriver(driverClassName);
	}

}
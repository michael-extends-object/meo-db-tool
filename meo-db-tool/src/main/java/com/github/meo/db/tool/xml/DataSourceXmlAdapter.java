package com.github.meo.db.tool.xml;

import java.sql.Driver;

import javax.sql.DataSource;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DataSourceXmlAdapter extends
		XmlAdapter<MappableDataSource, DataSource> {

	@Override
	public DataSource unmarshal(MappableDataSource mappableDataSource)
			throws Exception {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();
		ds.setDriver(mappableDataSource.getDriver());
		ds.setUrl(mappableDataSource.getUrl());
		ds.setUsername(mappableDataSource.getUsername());
		ds.setPassword(mappableDataSource.getPassword());
		return ds;
	}

	@Override
	public MappableDataSource marshal(DataSource dataSource) throws Exception {

		MappableDataSource mappableDataSource = new MappableDataSource();

		if (dataSource instanceof BasicDataSource) {
			BasicDataSource ds = (BasicDataSource) dataSource;
			Driver driver = (Driver) Class.forName(ds.getDriverClassName())
					.newInstance();
			mappableDataSource.setDriver(driver);
			mappableDataSource.setUrl(ds.getUrl());
			mappableDataSource.setUsername(ds.getUsername());
			mappableDataSource.setPassword(ds.getPassword());
		}

		if (dataSource instanceof SimpleDriverDataSource) {
			SimpleDriverDataSource ds = (SimpleDriverDataSource) dataSource;
			mappableDataSource.setDriver(ds.getDriver());
			mappableDataSource.setUrl(ds.getUrl());
			mappableDataSource.setUsername(ds.getUsername());
			mappableDataSource.setPassword(ds.getPassword());
		}

		return mappableDataSource;
	}
}
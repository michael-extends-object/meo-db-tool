package com.github.meo.db.tool.xml;

import static org.junit.Assert.assertEquals;

import java.sql.Driver;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class DriverXmlAdapterTests {
	DriverXmlAdapter driverXmlAdapter;

	@Before
	public void setUp() throws Exception {
		driverXmlAdapter = new DriverXmlAdapter();
	}

	@Test
	public void marshalUnmarshal() throws Exception {
		Driver driver = TestObjects.getDriver();
		String marshalledDriver = driverXmlAdapter.marshal(driver);
		Driver unmarshalledDriver = driverXmlAdapter
				.unmarshal(marshalledDriver);
		assertEquals("org.postgresql.Driver", unmarshalledDriver.getClass()
				.getName());
	}

	@Test(expected = ClassNotFoundException.class)
	public void unmarshalException() throws Exception {
		try {
			driverXmlAdapter.unmarshal(TestObjects.INVALID_DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			assertEquals("invalid.Driver", e.getMessage());
			throw e;
		}
	}
}

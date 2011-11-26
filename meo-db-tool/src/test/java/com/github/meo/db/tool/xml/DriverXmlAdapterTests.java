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
		
		assertEquals(driver.getClass(), driverXmlAdapter.unmarshal(marshalledDriver).getClass());
	}
	
	@Test
	public void unmarshalException() throws Exception {
		driverXmlAdapter.unmarshal(TestObjects.INVALID_DRIVER_CLASS_NAME);
	}
}

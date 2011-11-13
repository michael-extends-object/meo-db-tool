package com.github.meo.db.tool.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.meo.db.tool.domain.AttributeImplTests;
import com.github.meo.db.tool.domain.EntityImplTests;
import com.github.meo.db.tool.domain.JdbcDataSourceTests;
import com.github.meo.db.tool.xml.DriverXmlAdapterTests;
import com.github.meo.db.tool.xml.EncryptedStringXmlAdapterTests;
import com.github.meo.db.tool.xml.CustomJaxb2MarshallerTests;

@RunWith(Suite.class)
@SuiteClasses({
	AttributeImplTests.class, 
	DriverXmlAdapterTests.class, 
	EncryptedStringXmlAdapterTests.class, 
	EntityImplTests.class, 
	JdbcDataSourceTests.class, 
	CustomJaxb2MarshallerTests.class
})

public class AllTests {
}
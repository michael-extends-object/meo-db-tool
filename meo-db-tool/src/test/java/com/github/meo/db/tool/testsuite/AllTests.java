package com.github.meo.db.tool.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.meo.db.tool.dao.EntityDaoImplTests;
import com.github.meo.db.tool.dao.EntityRowMapperTests;
import com.github.meo.db.tool.domain.AttributeImplTests;
import com.github.meo.db.tool.domain.AttributeMappingTests;
import com.github.meo.db.tool.domain.CardinalityTests;
import com.github.meo.db.tool.domain.DatabaseTableTests;
import com.github.meo.db.tool.domain.DatabaseTableColumnTests;
import com.github.meo.db.tool.domain.DatabaseTests;
import com.github.meo.db.tool.domain.EntityImplTests;
import com.github.meo.db.tool.domain.EntityMappingTests;
import com.github.meo.db.tool.domain.RelationshipImplTests;
import com.github.meo.db.tool.sql.SqlColumnListTests;
import com.github.meo.db.tool.sql.SqlUtilsTests;
import com.github.meo.db.tool.xml.DriverXmlAdapterTests;
import com.github.meo.db.tool.xml.EncryptedStringXmlAdapterTests;
import com.github.meo.db.tool.xml.CustomJaxb2MarshallerTests;

@RunWith(Suite.class)
@SuiteClasses({
	AttributeImplTests.class, 
	AttributeMappingTests.class, 
	CardinalityTests.class, 
	CustomJaxb2MarshallerTests.class, 
	DatabaseTests.class, 
	DatabaseTableTests.class, 
	DatabaseTableColumnTests.class, 
	DriverXmlAdapterTests.class, 
	EncryptedStringXmlAdapterTests.class, 
	EntityDaoImplTests.class, 
	EntityImplTests.class, 
	EntityMappingTests.class, 
	EntityRowMapperTests.class, 
	RelationshipImplTests.class, 
	SqlColumnListTests.class, 
	SqlUtilsTests.class
})

public class AllTests {
}
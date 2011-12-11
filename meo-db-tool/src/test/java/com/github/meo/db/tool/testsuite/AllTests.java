package com.github.meo.db.tool.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.meo.db.tool.dao.EntityRowMapperTests;
import com.github.meo.db.tool.dao.EntityDaoTests;
import com.github.meo.db.tool.domain.AttributeTests;
import com.github.meo.db.tool.domain.AttributeTypeMappingTests;
import com.github.meo.db.tool.domain.AttributeTypeTests;
import com.github.meo.db.tool.domain.CardinalityTests;
import com.github.meo.db.tool.domain.DatabaseTableTests;
import com.github.meo.db.tool.domain.DatabaseTableColumnTests;
import com.github.meo.db.tool.domain.DatabaseTests;
import com.github.meo.db.tool.domain.EntityTests;
import com.github.meo.db.tool.domain.EntityRelationshipModelTests;
import com.github.meo.db.tool.domain.EntityTypeTests;
import com.github.meo.db.tool.domain.EntityTypeMappingTests;
import com.github.meo.db.tool.domain.RelationshipTests;
import com.github.meo.db.tool.exception.AttributeNotFoundExceptionTests;
import com.github.meo.db.tool.sql.SqlColumnListTests;
import com.github.meo.db.tool.sql.SqlUtilsTests;
import com.github.meo.db.tool.xml.DriverXmlAdapterTests;
import com.github.meo.db.tool.xml.EncryptedStringXmlAdapterTests;
import com.github.meo.db.tool.xml.CustomJaxb2MarshallerTests;

@RunWith(Suite.class)
@SuiteClasses({
	AttributeTests.class, 
	AttributeNotFoundExceptionTests.class, 
	AttributeTypeTests.class,	
	AttributeTypeMappingTests.class, 
	CardinalityTests.class, 
	CustomJaxb2MarshallerTests.class, 
	DatabaseTests.class, 
	DatabaseTableTests.class, 
	DatabaseTableColumnTests.class, 
	DriverXmlAdapterTests.class, 
	EncryptedStringXmlAdapterTests.class, 
	EntityTypeTests.class, 
	EntityDaoTests.class, 
	EntityTests.class, 
	EntityTypeMappingTests.class, 
	EntityRowMapperTests.class, 
	EntityRelationshipModelTests.class, 
	RelationshipTests.class, 
	SqlColumnListTests.class, 
	SqlUtilsTests.class
})

public class AllTests {
}
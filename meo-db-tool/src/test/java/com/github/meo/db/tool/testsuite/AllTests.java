package com.github.meo.db.tool.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.meo.db.tool.dao.DatabaseTests;
import com.github.meo.db.tool.dao.EntityRowMapperTests;
import com.github.meo.db.tool.dao.EntityDaoTests;
import com.github.meo.db.tool.domain.AttributeTests;
import com.github.meo.db.tool.domain.AttributeTypeTests;
import com.github.meo.db.tool.domain.CardinalityTests;
import com.github.meo.db.tool.domain.TableTests;
import com.github.meo.db.tool.domain.ColumnTests;
import com.github.meo.db.tool.domain.EntityTests;
import com.github.meo.db.tool.domain.EntityRelationshipModelTests;
import com.github.meo.db.tool.domain.EntityTypeTests;
import com.github.meo.db.tool.domain.RelationshipTests;
import com.github.meo.db.tool.domain.RelationshipTypeTests;
import com.github.meo.db.tool.domain.mapping.AttributeTypeMappingTests;
import com.github.meo.db.tool.domain.mapping.EntityTypeMappingTests;
import com.github.meo.db.tool.exception.AttributeNotFoundExceptionTests;
import com.github.meo.db.tool.sql.SqlColumnListTests;
import com.github.meo.db.tool.sql.SqlStatementUtilsTests;
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
	TableTests.class, 
	ColumnTests.class, 
	DriverXmlAdapterTests.class, 
	EncryptedStringXmlAdapterTests.class, 
	EntityTypeTests.class, 
	EntityDaoTests.class, 
	EntityTests.class, 
	EntityTypeMappingTests.class, 
	EntityRowMapperTests.class, 
	EntityRelationshipModelTests.class, 
	RelationshipTests.class, 
	RelationshipTypeTests.class, 
	SqlColumnListTests.class, 
	SqlStatementUtilsTests.class
})

public class AllTests {
}
package com.github.meo.db.tool.testsuite;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.AttributeImpl;
import com.github.meo.db.tool.domain.Entity;
import com.github.meo.db.tool.domain.EntityImpl;
import com.github.meo.db.tool.domain.JdbcDataSource;

public class TestObjects {

	private static final String ATTRIBUTE_NAME_A = "Attribute A";
	private static final String ATTRIBUTE_NAME_B = "Attribute B";
	private static final String ATTRIBUTE_NAME_C = "Attribute C";

	private static Attribute attributeA;
	private static Attribute attributeB;
	private static Attribute attributeC;

	private static final String ENTITY_NAME_A = "Entity A";
	private static final String ENTITY_NAME_B = "Entity B";
	private static final String ENTITY_NAME_C = "Entity C";

//	private static final String JDBC_DRIVER_HSQL = "org.hsqldb.jdbcDriver";
//	private static final String JDBC_DRIVER_PSQL = "org.postgresql.Driver";
//	private static final String DB_URL_HSQL_SOURCE = "jdbc:hsqldb:mem:SourceDatabase";
//	private static final String DB_URL_HSQL_TARGET = "jdbc:hsqldb:mem:TargetDatabase";
	private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	private static final String INVALID_DRIVER_CLASS_NAME = "invalid.Driver";
	private static final String URL = "jdbc:postgresql://localhost/dbem";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	public static Attribute getAttributeA() {
		attributeA = new AttributeImpl(ATTRIBUTE_NAME_A);
		attributeA.setValue("value");
		return attributeA;
	}

	public static Attribute getAttributeB() {
		attributeB = new AttributeImpl(ATTRIBUTE_NAME_B);
		attributeB.setValue(Boolean.TRUE);
		return attributeB;
	}

	public static Attribute getAttributeC() {
		attributeC = new AttributeImpl(ATTRIBUTE_NAME_C);
		attributeC.setValue(8.8);
		return attributeC;
	}

	public static List<Attribute> getAttributes() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(getAttributeA());
		attributes.add(getAttributeB());
		attributes.add(getAttributeC());
		return attributes;
	}

	public static Entity getEntityA() {
		Entity entity = new EntityImpl(ENTITY_NAME_A);
		entity.setAttributes(getAttributes());
		return entity;
	}

	public static Entity getEntityB() {
		Entity entity = new EntityImpl(ENTITY_NAME_B);
		entity.setAttributes(getAttributes());
		return entity;
	}

	public static Entity getEntityC() {
		Entity entity = new EntityImpl(ENTITY_NAME_C);
		entity.setAttributes(getAttributes());
		return entity;
	}

	public static List<Entity> getEntities() {
		List<Entity> entities = new ArrayList<Entity>();
		entities.add(getEntityA());
		entities.add(getEntityB());
		entities.add(getEntityC());
		return entities;
	}
	
	public static Driver getDriver() {
		return JdbcDataSource.getDriver(DRIVER_CLASS_NAME);
	}

	public static String getDriverClassName() {
		return DRIVER_CLASS_NAME;
	}

	public static String getInvalidDriverClassName() {
		return INVALID_DRIVER_CLASS_NAME;
	}
	
	public static String getUrl() {
		return URL;
	}
	
	public static String getUsername() {
		return USERNAME;
	}
	
	public static String getPassword() {
		return PASSWORD;
	}
	
	
	public static JdbcDataSource getJdbcDataSourceA() {
		JdbcDataSource jdbcDataSource = new JdbcDataSource();

		jdbcDataSource.setDriver(JdbcDataSource.getDriver(getDriverClassName()));
		jdbcDataSource.setUrl(URL);
		jdbcDataSource.setUsername(USERNAME);
		jdbcDataSource.setPassword(PASSWORD);

		return jdbcDataSource;
	}
	
	public static List<JdbcDataSource> getJdbcDataSources() {
		List<JdbcDataSource> jdbcDataSources = new ArrayList<JdbcDataSource>();
		jdbcDataSources.add(getJdbcDataSourceA());

		return jdbcDataSources;
	}

}

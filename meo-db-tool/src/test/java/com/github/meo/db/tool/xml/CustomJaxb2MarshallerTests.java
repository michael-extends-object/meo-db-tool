package com.github.meo.db.tool.xml;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.XmlMappingException;

import com.github.meo.db.tool.dao.Database;
import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IErm;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.IRelationshipType;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.mapping.AttributeTypeMapping;
import com.github.meo.db.tool.domain.mapping.EntityTypeMapping;
import com.github.meo.db.tool.domain.mapping.ErmMapping;
import com.github.meo.db.tool.domain.mapping.RelationshipTypeMapping;
import com.github.meo.db.tool.testsuite.TestObjects;

public class CustomJaxb2MarshallerTests {

	private final String basePath = "src/test/resources/tmp/";
	private static ApplicationContext appContext;
	private static CustomJaxb2Marshaller jaxbMarshaller;

	@BeforeClass
	public static void setUpClass() {
		appContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/app-context.xml");
		jaxbMarshaller = (CustomJaxb2Marshaller) appContext
				.getBean("jaxbMarshaller");
	}

	@Test
	public void marshalUnmarshalAttribute() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (IAttribute attribute : TestObjects.getAttributes()) {
			String path = getPath(attribute, attribute.getAttributeType()
					.getName());
			jaxbMarshaller.marshal(attribute, path);
			assertEquals(attribute, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalUnmarshalMappbleAttribute() throws XmlMappingException,
			FileNotFoundException, IOException {
		IAttributeType attributeType = new AttributeType("Attribute Name");
		MappableAttribute attribute = new MappableAttribute();
		attribute.setAttributeType(attributeType);
		attribute.setValue("Value");
		String path = getPath(attribute, attribute.getAttributeType().getName());
		jaxbMarshaller.marshal(attribute, path);
		assertEquals(attribute, jaxbMarshaller.unmarshal(path));
	}

	@Test
	public void marshalUnmarshalAttributeType() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {
			String path = getPath(attributeType, attributeType.getName());
			jaxbMarshaller.marshal(attributeType, path);
			assertEquals(attributeType, jaxbMarshaller.unmarshal(path));
		}

	}

	@Test
	public void marshalColumn() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (Column object : TestObjects.getColumns()) {
			String path = getPath(object, object.getName());
			jaxbMarshaller.marshal(object, path);
			assertEquals(object, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalUnmarshalEntity() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (IEntity entity : TestObjects.getEntities()) {
			String path = getPath(entity, entity.getEntityType().getName());
			jaxbMarshaller.marshal(entity, path);
			assertEquals(entity, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalUnmarshalEntityRelationshipModel()
			throws XmlMappingException, FileNotFoundException, IOException {
		for (IErm erm : TestObjects.getErms()) {
			String path = getPath(erm, erm.getName());
			jaxbMarshaller.marshal(erm, path);
			assertEquals(erm, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalUnmarshalEntityType() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (IEntityType entityType : TestObjects.getEntityTypes()) {
			String path = getPath(entityType, entityType.getName());
			jaxbMarshaller.marshal(entityType, path);
			assertEquals(entityType, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalRelationshipType() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (IRelationshipType relationshipType : TestObjects
				.getRelationshipTypes()) {
			String path = getPath(relationshipType, relationshipType.getName());
			jaxbMarshaller.marshal(relationshipType, path);
			assertEquals(relationshipType, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalTable() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (Table object : TestObjects.getTables()) {
			String path = getPath(object, object.getName());
			jaxbMarshaller.marshal(object, path);
			assertEquals(object, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalAttributeTypeMapping() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (AttributeTypeMapping object : TestObjects
				.getAttributeTypeMappings()) {
			String path = getPath(object, object.toString());
			jaxbMarshaller.marshal(object, path);
			assertEquals(object, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalEntityTypeMapping() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (EntityTypeMapping object : TestObjects.getEntityTypeMappings()) {
			String path = getPath(object, object.toString());
			jaxbMarshaller.marshal(object, path);
			assertEquals(object, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalRelationshipTypeMapping() throws XmlMappingException,
			FileNotFoundException, IOException {
		RelationshipTypeMapping object = new RelationshipTypeMapping();
		object.setRelationshipType(TestObjects.getRelationshipTypeA());
		object.setAttributeTypes(TestObjects.getAttributeTypes());
		object.setReferencedAttributeTypes(TestObjects.getAttributeTypes());
		object.setColumns(TestObjects.getColumns());
		object.setReferencedColumns(TestObjects.getColumns());
		String path = getPath(object, object.toString());
		jaxbMarshaller.marshal(object, path);
		assertEquals(object, jaxbMarshaller.unmarshal(path));
	}

	@Test
	public void marshalErmMapping() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (ErmMapping object : TestObjects.getErmMappings()) {
			String path = getPath(object, object.toString());
			jaxbMarshaller.marshal(object, path);
			assertEquals(object, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalDatabase() throws XmlMappingException,
			FileNotFoundException, IOException {
		for (Database object : TestObjects.getDatabases()) {
			String path = getPath(object, object.toString());
			jaxbMarshaller.marshal(object, path);
			assertEquals(object, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalMappableDataSource() throws XmlMappingException,
			FileNotFoundException, IOException {
		MappableDataSource object = new MappableDataSource();
		object.setDriver(TestObjects.getDriver());
		object.setUrl("url");
		object.setUsername("Username");
		object.setPassword("Password");
		String path = getPath(object, object.toString());
		jaxbMarshaller.marshal(object, path);
		assertEquals(object, jaxbMarshaller.unmarshal(path));
	}

	private String getPath(Object object, String... args) {
		String path = basePath + object.getClass().getSimpleName();
		for (String string : args) {
			path += "_";
			path += string;
		}
		return path + ".xml";
	}

}
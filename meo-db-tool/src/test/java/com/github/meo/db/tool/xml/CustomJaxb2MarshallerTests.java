package com.github.meo.db.tool.xml;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.XmlMappingException;

import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityRelationshipModel;
import com.github.meo.db.tool.domain.IEntityType;
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
		for (IEntityRelationshipModel erm : TestObjects.getErms()) {
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

	private String getPath(Object object, String... args) {
		String path = basePath + object.getClass().getSimpleName();
		for (String string : args) {
			path += "_";
			path += string;
		}
		return path + ".xml";
	}
}
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

	@Ignore
	@Test
	public void marshalUnmarshalAttribute() throws XmlMappingException,
			FileNotFoundException, IOException {

		for (IAttribute attribute : TestObjects.getAttributes()) {

			String path = basePath + attribute.getClass().getSimpleName() + "_"
					+ attribute.getAttributeType().getName() + ".xml";

			jaxbMarshaller.marshal(attribute, path);

			assertEquals(attribute, jaxbMarshaller.unmarshal(path));
		}

	}

	@Test
	public void marshalUnmarshalAttributeType() throws XmlMappingException,
			FileNotFoundException, IOException {

		for (IAttributeType attributeType : TestObjects.getAttributeTypes()) {

			String path = basePath + attributeType.getClass().getSimpleName()
					+ "_" + attributeType.getName() + ".xml";

			jaxbMarshaller.marshal(attributeType, path);

			assertEquals(attributeType, jaxbMarshaller.unmarshal(path));
		}

	}

	@Ignore
	@Test
	public void marshalUnmarshalEntity() throws XmlMappingException,
			FileNotFoundException, IOException {

		for (IEntity entity : TestObjects.getEntities()) {

			String path = basePath + entity.getClass().getSimpleName() + "_"
					+ entity.getEntityType().getName() + ".xml";

			jaxbMarshaller.marshal(entity, path);

			assertEquals(entity, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalUnmarshalEntityRelationshipModel()
			throws XmlMappingException, FileNotFoundException, IOException {

		for (IEntityRelationshipModel erm : TestObjects.getErms()) {

			String path = basePath + erm.getClass().getSimpleName() + "_"
					+ erm.getName() + ".xml";

			jaxbMarshaller.marshal(erm, path);

			// assertEquals(erm, jaxbMarshaller.unmarshal(path));
		}
	}

	@Test
	public void marshalUnmarshalEntityType() throws XmlMappingException,
			FileNotFoundException, IOException {

		for (IEntityType entityType : TestObjects.getEntityTypes()) {

			String path = basePath + entityType.getClass().getSimpleName()
					+ "_" + entityType.getName() + ".xml";

			jaxbMarshaller.marshal(entityType, path);

			// assertEquals(entityType, jaxbMarshaller.unmarshal(path));
		}
	}

	@Ignore
	@Test
	public void marshalUnmarshalRelationship() throws XmlMappingException,
			FileNotFoundException, IOException {

		for (IEntityType entityType : TestObjects.getEntityTypes()) {

			String path = basePath + entityType.getClass().getSimpleName()
					+ "_" + entityType.getName() + ".xml";

			jaxbMarshaller.marshal(entityType, path);

			assertEquals(entityType, jaxbMarshaller.unmarshal(path));
		}
	}

}
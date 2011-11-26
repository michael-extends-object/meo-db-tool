package com.github.meo.db.tool.xml;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.XmlMappingException;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.Entity;
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

		for (Attribute attribute : TestObjects.getAttributes()) {

			String path = basePath + attribute.getClass().getSimpleName() + "_"
					+ attribute.getName() + ".xml";

			jaxbMarshaller.marshal(attribute, path);

			assertEquals(attribute, jaxbMarshaller.unmarshal(path));
		}

	}

	@Test
	public void marshalUnmarshalEntity() throws XmlMappingException,
			FileNotFoundException, IOException {

		for (Entity entity : TestObjects.getEntities()) {

			String path = basePath + entity.getClass().getSimpleName() + "_"
					+ entity.getName() + ".xml";

			jaxbMarshaller.marshal(entity, path);

			assertEquals(entity, jaxbMarshaller.unmarshal(path));
		}
	}
}
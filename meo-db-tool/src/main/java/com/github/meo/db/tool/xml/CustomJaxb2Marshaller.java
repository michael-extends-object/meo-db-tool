package com.github.meo.db.tool.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class CustomJaxb2Marshaller extends Jaxb2Marshaller {

	Map<String, Object> jaxbContextProperties;
	
	/**
	 * Marshal a given Object to a file (specified by a given path)
	 */
	public void marshal(Object obj, String path) throws XmlMappingException,
			IOException {
		marshal(obj, new StreamResult(new FileOutputStream(path)));
	}

	/**
	 * Unmarshal a given file with a given path
	 */
	public Object unmarshal(String path) throws XmlMappingException,
			FileNotFoundException, IOException {
		return unmarshal(new StreamSource(new FileInputStream(path)));
	}
	
	/**
	 * Setter method for EclipseLink OXM binding files
	 * 
	 * @param metadataFiles
	 * @throws IOException
	 */
	public void setBindingFiles(List<File> metadataFiles) throws IOException {
		jaxbContextProperties = new HashMap<String, Object>();
		jaxbContextProperties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY,
				metadataFiles);
		setJaxbContextProperties(jaxbContextProperties);
	}

}
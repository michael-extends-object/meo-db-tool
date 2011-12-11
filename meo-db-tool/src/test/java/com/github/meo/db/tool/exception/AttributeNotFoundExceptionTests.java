package com.github.meo.db.tool.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.AttributeType;

public class AttributeNotFoundExceptionTests {

	String expectedString;
	String actualString;

	@Test
	public void testAttributeNotFoundExceptionString() {
		Exception e = new AttributeTypeNotFoundException("Attribute");
		expectedString = "Couldn't find the attribute 'Attribute'";
		actualString = e.getMessage();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testAttributeNotFoundExceptionAttribute() {
		IAttributeType attributeType = new AttributeType("Attribute");
		IAttribute attribute = new Attribute(attributeType);
		Exception e = new AttributeTypeNotFoundException(attribute);
		expectedString = "Couldn't find the attribute 'Attribute'";
		actualString = e.getMessage();
		assertEquals(expectedString, actualString);
	}

}
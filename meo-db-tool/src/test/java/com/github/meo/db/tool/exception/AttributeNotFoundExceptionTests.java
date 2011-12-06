package com.github.meo.db.tool.exception;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.AttributeImpl;
import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.AttributeTypeImpl;

public class AttributeNotFoundExceptionTests {

	String expectedString;
	String actualString;

	@Test
	public void testAttributeNotFoundExceptionString() {
		Exception e = new AttributeNotFoundException("Attribute");
		expectedString = "Couldn't find the attribute 'Attribute'";
		actualString = e.getMessage();
		assertEquals(expectedString, actualString);
	}

	@Test
	public void testAttributeNotFoundExceptionAttribute() {
		AttributeType attributeType = new AttributeTypeImpl("Attribute");
		Attribute attribute = new AttributeImpl(attributeType);
		Exception e = new AttributeNotFoundException(attribute);
		expectedString = "Couldn't find the attribute 'Attribute'";
		actualString = e.getMessage();
		assertEquals(expectedString, actualString);
	}

}
package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.Attribute;

public class AttributeNotFoundException extends Exception {

	private static final long serialVersionUID = 7401453191028072094L;

	public AttributeNotFoundException(final String attributeName) {
		super(String.format("Couldn't find the attribute '%s'", attributeName));
	}

	public AttributeNotFoundException(final Attribute attribute) {
		super(String.format("Couldn't find the attribute '%s'", attribute));
	}
}
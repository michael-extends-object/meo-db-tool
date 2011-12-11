package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IAttribute;

public class AttributeTypeNotFoundException extends Exception {

	private static final long serialVersionUID = 7401453191028072094L;

	public AttributeTypeNotFoundException(final String attributeName) {
		super(String.format("Couldn't find the attribute '%s'", attributeName));
	}

	public AttributeTypeNotFoundException(final IAttribute attribute) {
		super(String.format("Couldn't find the attribute '%s'", attribute));
	}
}
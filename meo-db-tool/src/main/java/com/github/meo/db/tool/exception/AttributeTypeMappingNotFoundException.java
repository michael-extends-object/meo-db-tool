package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IAttributeType;

public class AttributeTypeMappingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3332008252956886841L;

	public AttributeTypeMappingNotFoundException(final String message) {
		super(message);
	}

	public AttributeTypeMappingNotFoundException(
			final IAttributeType attributeType) {
		super(ExceptionUtils.bla(ExceptionMessage.AttributeTypeMappingNotFound,
				attributeType));
	}

}
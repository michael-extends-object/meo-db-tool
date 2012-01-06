package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IAttribute;

public class AttributeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7839462811526763238L;

	public AttributeNotFoundException(final String attributeName) {
		super(ExceptionUtils.bla(ExceptionMessage.AttributeNotFound,
				attributeName));
	}

	public AttributeNotFoundException(final IAttribute attribute) {
		super(ExceptionUtils.bla(ExceptionMessage.AttributeNotFound, attribute));
	}

}

package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntityType;

public class ColumnNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6140727615557343904L;

	public ColumnNotFoundException(final String columnName) {
		super(ExceptionUtils.bla(ExceptionMessage.ColumnNotFound, columnName));
	}

	public ColumnNotFoundException(IEntityType entityType,
			IAttributeType attributeType) {
		super(ExceptionUtils.bla(ExceptionMessage.ColumnNotFound, entityType,
				attributeType));
	}

}

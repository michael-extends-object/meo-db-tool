package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IEntityType;

public class EntityTypeMappingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9064590607641095609L;

	public EntityTypeMappingNotFoundException(final IEntityType entityType) {
		super(ExceptionUtils.bla(ExceptionMessage.EntityTypeMappingNotFound,
				entityType));
	}

}
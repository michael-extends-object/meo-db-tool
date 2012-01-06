package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IEntityRelationshipModel;

public class EntityRelationshipModelMappingNotFoundException extends
		RuntimeException {

	private static final long serialVersionUID = 3442145616341954936L;

	public EntityRelationshipModelMappingNotFoundException(final String message) {
		super(message);
	}

	public EntityRelationshipModelMappingNotFoundException(
			final IEntityRelationshipModel erm) {
		super(ExceptionUtils.bla(
				ExceptionMessage.EntityRelationshipModelMappingNotFound, erm));
	}

}
package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IRelationshipType;

public class RelationshipTypeMappingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7200881976253428628L;

	public RelationshipTypeMappingNotFoundException(
			final IRelationshipType relationshipType) {
		super(ExceptionUtils.bla(
				ExceptionMessage.RelationshipTypeMappingNotFound,
				relationshipType));
	}

}
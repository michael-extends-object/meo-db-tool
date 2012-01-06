package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IEntityType;

public class RelationshipTypeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -125695842288598499L;

	public RelationshipTypeNotFoundException(IEntityType entityType) {
		super(String.format(
				"Couldn't a relationship type for the entity type '%s'",
				entityType));
	}

	public RelationshipTypeNotFoundException(IEntityType entityType,
			IEntityType referencedEntityType) {
		super(
				String.format(
						"Couldn't a relationship type for the entity types '%s' and '%s'",
						entityType, referencedEntityType));
	}

}

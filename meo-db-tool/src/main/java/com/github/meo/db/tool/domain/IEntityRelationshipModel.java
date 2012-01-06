package com.github.meo.db.tool.domain;

import java.util.List;

public interface IEntityRelationshipModel {

	public boolean addEntityType(IEntityType entityType);

	public boolean addRelationshipType(IRelationshipType relationshipType);

	public String getName();

	public List<IEntityType> getEntityTypes();

	public List<IRelationshipType> getRelationshipTypes();

	public List<IRelationshipType> getRelationshipTypes(IEntityType entityType);

	public IRelationshipType getRelationshipType(IEntityType entityType,
			IEntityType referencedEntityType);

	public void setName(String name);

	public void setEntityTypes(List<IEntityType> entityTypes);

	public void setRelationshipTypes(List<IRelationshipType> relationshipTypes);

	public String toString();

	public IEntityRelationshipModel clone();

	public boolean equals(Object object);

}
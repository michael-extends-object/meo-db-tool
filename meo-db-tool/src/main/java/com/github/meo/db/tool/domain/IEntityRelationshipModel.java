package com.github.meo.db.tool.domain;

import java.util.List;

public interface IEntityRelationshipModel {

	public boolean addEntityType(IEntityType entityType);

	public boolean addRelationship(IRelationship relationship);

	public String getName();

	public List<IEntityType> getEntityTypes();

	public List<IRelationship> getRelationships();

	public void setName(String name);

	public void setEntityTypes(List<IEntityType> entityTypes);

	public void setRelationships(List<IRelationship> relationships);

	public String toString();
	
	public IEntityRelationshipModel clone();
	
	public boolean equals(Object object);
}

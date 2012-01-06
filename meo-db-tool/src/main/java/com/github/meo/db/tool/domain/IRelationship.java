package com.github.meo.db.tool.domain;

import java.util.List;


public interface IRelationship {

	public boolean addReferencedEntity(IEntity entity);

	public void addReferencedEntities(List<IEntity> referencedEntities);

	public IRelationshipType getRelationshipType();
	
	public IEntity getEntity();

	public List<IEntity> getReferencedEntities();

	public void setEntity(IEntity entity);

	public void setReferencedEntities(List<IEntity> referencedEntities);

	public String toString();

	public IRelationship clone();

	public boolean equals(Object object);

}
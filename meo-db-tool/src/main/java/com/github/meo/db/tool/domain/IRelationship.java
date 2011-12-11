package com.github.meo.db.tool.domain;

import java.util.List;

public interface IRelationship {

	public boolean addReferencedEntity(Object entity);
	
	public boolean addReferencedEntities(List<Object> referencedEntities);

	public String getName();

	public Object getEntity();

	public Object getReferencedEntity();

	public List<Object> getReferencedEntities();

	public Cardinality getCardinality();

	public void setName(String name);

	public void setEntity(Object entity);

	public void setReferencedEntity(Object referencedEntity);

	public void setReferencedEntities(List<Object> referencedEntities);

	public void setCardinality(Cardinality cardinality);

	public boolean equals(Object object);

	public IRelationship clone();

	public String toString();

}
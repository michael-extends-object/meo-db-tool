package com.github.meo.db.tool.domain;

public interface Relationship {

	public Cardinality getCardinality();

	public String getName();

	public Entity getReferencedEntity();

	public void setCardinality(Cardinality cardinality);

	public void setName(String name);

	public void setReferencedEntity(Entity referencedEntity);
	
	public String toString();

	public Relationship clone();
}
package com.github.meo.db.tool.domain;

import java.util.List;

public interface Relationship {

	public boolean equals(Object object);

	public Relationship clone();

	public String toString();

	public boolean addReferencedEntity(Entity referencedEntity);

	public String getName();

	public Entity getReferencedEntity();

	public List<Entity> getReferencedEntities();

	public Cardinality getCardinality();

	public void setName(String name);

	public void setReferencedEntity(Entity referencedEntity);

	public void setReferencedEntities(List<Entity> referencedEntities);

	public void setCardinality(Cardinality cardinality);

}
package com.github.meo.db.tool.domain;

import java.util.List;


public interface IEntityType {

	public boolean addAttributeType(IAttributeType attributeType);

	public String getName();

//	public IEntityRelationshipModel getEntityRelationshipModel();

	public List<IAttributeType> getAttributeTypes();

	public List<IAttributeType> getAttributeTypesPrimaryKey();

	public void setName(String name);

//	public void setEntityRelationshipModel(IEntityRelationshipModel erm);

	public void setAttributeTypes(List<IAttributeType> attributeTypes);

	public IEntity getEntity();

	public IAttributeType getAttributeType(String name);

	public String toString();

	public IEntityType clone();

	public boolean equals(Object object);
}

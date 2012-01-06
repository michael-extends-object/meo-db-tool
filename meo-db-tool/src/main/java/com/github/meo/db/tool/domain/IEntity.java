package com.github.meo.db.tool.domain;

import java.util.List;

import com.github.meo.db.tool.exception.AttributeNotFoundException;

public interface IEntity {

	public IEntityType getEntityType();

	public IAttribute getAttribute(String name)
			throws AttributeNotFoundException;

	public Object getAttributeValue(String attributeName)
			throws AttributeNotFoundException;

	public Object getAttributeValue(IAttribute attribute)
			throws AttributeNotFoundException;

	public List<IAttribute> getAttributes();

	public List<IAttribute> getAttributesNotNull();

	public List<IAttribute> getAttributesPrimaryKey();

	public List<IAttribute> getAttributesPrimaryKeyNotNull();

	public void setAttributeValue(IAttribute attribute, Object value)
			throws AttributeNotFoundException;

	public void setAttributeValue(String attributeName, Object value)
			throws AttributeNotFoundException;

	public IEntity clone();

	public String toString();

	public boolean equals(Object object);

}
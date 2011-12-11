package com.github.meo.db.tool.domain;

import java.util.List;

import com.github.meo.db.tool.exception.AttributeTypeNotFoundException;

public interface IEntity {

	public IEntityType getEntityType();

	public Object getAttributeValue(String attributeName)
			throws AttributeTypeNotFoundException;

	public Object getAttributeValue(IAttribute attribute)
			throws AttributeTypeNotFoundException;

	public List<IAttribute> getAttributes();

	public List<IAttribute> getAttributesPrimaryKey();

	public void setAttributeValue(IAttribute attribute, Object value)
			throws AttributeTypeNotFoundException;

	public void setAttributeValue(String attributeName, Object value)
			throws AttributeTypeNotFoundException;

	public void setAttributes(List<IAttribute> attributes);

	public boolean addAttribute(IAttribute attribute);

	public IAttribute getAttribute(String name)
			throws AttributeTypeNotFoundException;

	/**
	 * Setter method for property entity type.
	 * 
	 * Will automatically create attributes according to the entity type.
	 * 
	 * @param entityType
	 */
	public void setEntityType(IEntityType entityType);

	public IEntity clone();

	public String toString();

	public List<IAttribute> getAttributesPrimaryKeyNotNull();

	public List<IAttribute> getAttributesNotNull();
}
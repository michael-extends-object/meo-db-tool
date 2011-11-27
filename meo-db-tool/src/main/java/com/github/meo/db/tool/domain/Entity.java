package com.github.meo.db.tool.domain;

import java.util.List;

public interface Entity {

	public String getName();

	public Object getAttributeValue(String attributeName);

	public Object getAttributeValue(Attribute attribute);

	public List<Attribute> getAttributes();

	public List<Attribute> getAttributesPrimaryKey();

	public boolean setAttributeValue(Attribute attribute, Object value);

	public boolean setAttributeValue(String attributeName, Object value);

	public void setAttributes(List<Attribute> attributes);

	public boolean addAttribute(Attribute attribute);

	public Attribute getAttribute(String name);

	public void setName(String name);

	public Entity clone();

	public String toString();

	public List<Relationship> getRelationships();

	public void setRelationships(List<Relationship> realtionships);

	public boolean addRelationship(Relationship realtionship);

	public List<Attribute> getAttributesPrimaryKeyNotNull();

	public List<Attribute> getAttributesNotNull();
}
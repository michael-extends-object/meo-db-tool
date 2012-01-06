package com.github.meo.db.tool.domain;


public interface IAttribute {

	public IAttributeType getAttributeType();

	public String getName();

	public boolean isPrimaryKey();

	public Object getValue();

	public void setValue(Object value);

	public IAttribute clone();

	public boolean equals(Object object);
}

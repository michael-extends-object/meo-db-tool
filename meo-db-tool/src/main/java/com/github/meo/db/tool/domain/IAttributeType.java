package com.github.meo.db.tool.domain;


public interface IAttributeType {

	public boolean equals(Object object);

	public String getName();

	public boolean isPrimaryKey();

	public void setName(String name);

	public void setPrimaryKey(boolean isPrimaryKey);

	public IAttributeType clone();

	public IAttribute getAttribute();

}

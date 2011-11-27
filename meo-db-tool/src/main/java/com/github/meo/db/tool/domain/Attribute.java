package com.github.meo.db.tool.domain;

public interface Attribute {

	public boolean equals(Object object);

	public String getName();

	public Object getValue();

	public boolean isPrimaryKey();

	public void setName(String name);

	public void setValue(Object value);

	public void setPrimaryKey(boolean isPrimaryKey);
	
	public Attribute clone();

}

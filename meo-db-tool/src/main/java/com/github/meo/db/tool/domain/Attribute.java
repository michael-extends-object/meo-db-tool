package com.github.meo.db.tool.domain;

public interface Attribute {

	public Object clone();

	public boolean equals(Object object);

	public String getName();

	public Object getValue();

	public void setName(String name);

	public void setValue(Object value);

}

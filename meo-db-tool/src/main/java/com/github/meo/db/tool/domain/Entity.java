package com.github.meo.db.tool.domain;

import java.util.List;

public interface Entity extends Attribute {

	public void addAttribute(Attribute attribute);

	public Attribute getAttribute(String name);

	public List<Attribute> getAttributes();

	public void setAttributes(List<Attribute> attributes);
}

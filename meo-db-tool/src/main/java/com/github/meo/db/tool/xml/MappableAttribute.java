package com.github.meo.db.tool.xml;

import javax.xml.bind.annotation.XmlElement;

import com.github.meo.db.tool.domain.IAttributeType;

public class MappableAttribute {

	@XmlElement
	public IAttributeType attributeType;

	@XmlElement
	public Object value;

}
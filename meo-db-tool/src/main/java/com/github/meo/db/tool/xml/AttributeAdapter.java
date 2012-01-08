package com.github.meo.db.tool.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.IAttribute;


public class AttributeAdapter extends XmlAdapter<MappableAttribute, Attribute> {

	@Override
	public Attribute unmarshal(MappableAttribute mappableAttribute)
			throws Exception {
		IAttribute attribute = mappableAttribute.getAttributeType()
				.createAttribute();
		attribute.setValue(mappableAttribute.getValue());
		return (Attribute) attribute;
	}

	@Override
	public MappableAttribute marshal(Attribute attribute) throws Exception {
		MappableAttribute mappableAttribute = new MappableAttribute();
		mappableAttribute.setAttributeType(attribute.getAttributeType());
		mappableAttribute.setValue(attribute.getValue());
		return mappableAttribute;
	}

}
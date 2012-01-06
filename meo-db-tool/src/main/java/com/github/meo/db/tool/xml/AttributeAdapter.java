package com.github.meo.db.tool.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.IAttributeType;

public class AttributeAdapter extends XmlAdapter<MappableAttribute, Attribute> {

	@Override
	public MappableAttribute marshal(Attribute attribute) throws Exception {
		MappableAttribute mappableAttribute = new MappableAttribute();
		mappableAttribute.attributeType = attribute.getAttributeType();
		mappableAttribute.value = attribute.getValue();
		return mappableAttribute;
	}

	@Override
	public Attribute unmarshal(MappableAttribute mappableAttribute)
			throws Exception {
		IAttributeType attributeType = mappableAttribute.attributeType;
		Attribute attribute = (Attribute) attributeType.getAttribute();
		attribute.setValue(mappableAttribute.value);
		return attribute;
	}
}

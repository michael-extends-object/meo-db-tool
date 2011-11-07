package com.github.meo.db.tool.testsuite;

import java.util.ArrayList;
import java.util.List;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.AttributeImpl;
import com.github.meo.db.tool.domain.Entity;
import com.github.meo.db.tool.domain.EntityImpl;

public class TestObjects {

	private static final String ATTRIBUTE_NAME_A = "Attribute A";
	private static final String ATTRIBUTE_NAME_B = "Attribute B";
	private static final String ATTRIBUTE_NAME_C = "Attribute C";

	private static Attribute attributeA;
	private static Attribute attributeB;
	private static Attribute attributeC;
	
	private static final String ENTITY_NAME_A = "Entity A";
	private static final String ENTITY_NAME_B = "Entity B";
	private static final String ENTITY_NAME_C = "Entity C";
	
	public static Attribute getAttributeA() {
		attributeA = new AttributeImpl(ATTRIBUTE_NAME_A);
		return attributeA;
	}

	public static Attribute getAttributeB() {
		attributeB = new AttributeImpl(ATTRIBUTE_NAME_B);
		return attributeB;
	}

	public static Attribute getAttributeC() {
		attributeC = new AttributeImpl(ATTRIBUTE_NAME_C);
		return attributeC;
	}
	
	public static List<Attribute> getAttributes() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(getAttributeA());
		attributes.add(getAttributeB());
		attributes.add(getAttributeC());
		return attributes;
	}
	
	public static Entity getEntityA() {
		Entity entity = new EntityImpl(ENTITY_NAME_A);
		entity.setAttributes(getAttributes());
		return entity;
	}
	
	public static Entity getEntityB() {
		Entity entity = new EntityImpl(ENTITY_NAME_B);
		entity.setAttributes(getAttributes());
		return entity;
	}
	
	public static Entity getEntityC() {
		Entity entity = new EntityImpl(ENTITY_NAME_C);
		entity.setAttributes(getAttributes());
		return entity;
	}
	
	public static List<Entity> getEntities() {
		List<Entity> entities = new ArrayList<Entity>();
		entities.add(getEntityA());
		entities.add(getEntityB());
		entities.add(getEntityC());
		return entities;
	}
	
}

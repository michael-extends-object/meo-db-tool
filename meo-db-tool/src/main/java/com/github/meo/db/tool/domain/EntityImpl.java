package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, Cloneable {

	private String name;
	private Object value;
	private List<Attribute> attributes;

	public EntityImpl() {
		init();
	}

	public EntityImpl(String name) {
		init();
		setName(name);
	}

	private void init() {
		setAttributes(new ArrayList<Attribute>());
	}

	@Override
	public Object clone() {
		Entity entity = new EntityImpl();

		entity.setName(getName());

		if (getAttributes() == null) {
			entity.setAttributes(null);
		} else {
			for (Attribute attribute : getAttributes()) {
				entity.addAttribute((Attribute) attribute.clone());
			}
		}

		return entity;
	}

	@Override
	public boolean equals(Object object) {

		// null reference?
		if (object == null) {
			return false;
		}
		
		/*
		 * Are the references pointing to the same object?
		 */
		if (this == object) {
			return true;
		}

		/*
		 * Same class?
		 */
		if (!getClass().equals(object.getClass())) {
			return false;
		}

		Entity entity = (Entity) object;
		
		/*
		 * Do the objects have the same name?
		 */
		if (getName() == null) {
			if (entity.getName() != null) {
				return false;
			}
		} else {

			if (entity.getName() == null) {
				return false;
			}

			if (!getName().equals(entity.getName())) {
				return false;
			}
		}

		/*
		 * Do the objects have the same attributes?
		 */
		if (getAttributes() == null) {
			if (entity.getAttributes() != null) {
				return false;
			}
		} else {
			if (entity.getAttributes() == null) {
				return false;
			} else {
				if (getAttributes().size() != entity.getAttributes().size()) {
					return false;
				}

				for (int i = 0; i < getAttributes().size(); i++) {
					if (!getAttributes().get(i).equals(
							entity.getAttributes().get(i))) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}

	/**
	 * 
	 * @param name
	 * @return Returns the argument with the given name. In case there was no
	 *         argument with the given name, the method will return null.
	 */
	public Attribute getAttribute(String name) {

		if (name == null) {
			throw new IllegalArgumentException(
					"The given attribute name is null!");
		}

		Attribute resultAttribute = null;

		for (Attribute attribute : getAttributes()) {
			if (name.equals(attribute.getName())) {
				resultAttribute = attribute;
			}
		}

		return resultAttribute;
	}

	public String getName() {
		return name;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}

package com.github.meo.db.tool.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;

public class EntityTypeMapping {

	private IEntityType entityType;
	private Table table;
	private List<AttributeTypeMapping> attributeTypeMappings;

	public EntityTypeMapping() {
		init();
	}

	public void init() {
		setAttributeTypeMappings(new ArrayList<AttributeTypeMapping>());
	}

	public Column getColumn(IAttribute attribute) {

		Assert.notNull(attribute);
		Column column = null;

		for (AttributeTypeMapping attributeTypeMapping : getAttributeTypeMappings()) {
			if (attribute.getAttributeType().getName()
					.equals(attributeTypeMapping.getAttributeType().getName())) {
				column = attributeTypeMapping.getColumn();
			}
		}

		return column;
	}

	public boolean addAttributeTypeMapping(
			AttributeTypeMapping attributeTypeMapping) {
		return attributeTypeMappings.add(attributeTypeMapping);
	}

	public IEntityType getEntityType() {
		return entityType;
	}

	public Table getTable() {
		return table;
	}

	public List<AttributeTypeMapping> getAttributeTypeMappings() {
		return attributeTypeMappings;
	}

	public void setEntityType(IEntityType entityType) {
		this.entityType = entityType;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setAttributeTypeMappings(
			List<AttributeTypeMapping> attributeTypeMappings) {
		this.attributeTypeMappings = attributeTypeMappings;
	}

	@Override
	public String toString() {
		return String.format("EntityTypeMapping(%s, %s)", entityType, table);
	}

	@Override
	public EntityTypeMapping clone() {
		EntityTypeMapping entityTypeMapping = new EntityTypeMapping();
		entityTypeMapping.setEntityType(getEntityType());
		entityTypeMapping.setTable(getTable());
		entityTypeMapping.setAttributeTypeMappings(getAttributeTypeMappings());
		return entityTypeMapping;
	}

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

		EntityTypeMapping entityTypeMapping = (EntityTypeMapping) object;

		/*
		 * Do the objects have the same entity type?
		 */
		if (!getEntityType().equals(entityTypeMapping.getEntityType())) {
			return false;
		}

		/*
		 * Do the objects have the same table?
		 */
		if (!getTable().equals(entityTypeMapping.getTable())) {
			return false;
		}

		/*
		 * Do the objects have equal attribute type mappings?
		 */
		if (getAttributeTypeMappings().size() != entityTypeMapping
				.getAttributeTypeMappings().size()) {
			return false;
		}

		for (int i = 0; i < getAttributeTypeMappings().size(); i++) {
			if (!getAttributeTypeMappings().get(i).equals(
					entityTypeMapping.getAttributeTypeMappings().get(i))) {
				return false;
			}
		}

		return true;
	}

}
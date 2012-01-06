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
				column = attributeTypeMapping
						.getColumn();
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

}
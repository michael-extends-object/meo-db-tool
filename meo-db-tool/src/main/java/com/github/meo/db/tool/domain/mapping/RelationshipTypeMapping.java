package com.github.meo.db.tool.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IRelationshipType;
import com.github.meo.db.tool.domain.RelationshipType;
import com.github.meo.db.tool.domain.db.Column;

public class RelationshipTypeMapping implements Cloneable {

	private IRelationshipType relationshipType;
	List<Column> columns;
	List<Column> referencedColumns;
	List<IAttributeType> attributeTypes;
	List<IAttributeType> referencedAttributeTypes;

	public RelationshipTypeMapping() {
		init();
	}

	public RelationshipTypeMapping(IRelationshipType relationshipType) {
		init();
		setRelationshipType(relationshipType);
		setColumns(new ArrayList<Column>());
		setReferencedColumns(new ArrayList<Column>());
		setAttributeTypes(new ArrayList<IAttributeType>());
		setReferencedAttributeTypes(new ArrayList<IAttributeType>());
	}

	private void init() {
		setRelationshipType(new RelationshipType());
	}

	public void addColumn(Column column) {
		Assert.notNull(column);
		getColumns().add(column);
	}

	public void addReferencedColumn(Column referencedColumn) {
		Assert.notNull(referencedColumn);
		getReferencedColumns().add(referencedColumn);
	}

	public void addAttributeType(IAttributeType attributeType) {
		Assert.notNull(attributeType);
		getAttributeTypes().add(attributeType);
	}

	public void addReferencedAttributeType(IAttributeType refAttributeType) {
		Assert.notNull(refAttributeType);
		getReferencedAttributeTypes().add(refAttributeType);
	}

	public IRelationshipType getRelationshipType() {
		return relationshipType;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public List<Column> getReferencedColumns() {
		return referencedColumns;
	}

	public List<IAttributeType> getAttributeTypes() {
		return attributeTypes;
	}

	public List<IAttributeType> getReferencedAttributeTypes() {
		return referencedAttributeTypes;
	}

	public void setRelationshipType(IRelationshipType relationshipType) {
		Assert.notNull(relationshipType);
		this.relationshipType = relationshipType;
	}

	public void setColumns(List<Column> columns) {
		Assert.notNull(columns);
		this.columns = columns;
	}

	public void setReferencedColumns(List<Column> referencedColumns) {
		Assert.notNull(referencedColumns);
		this.referencedColumns = referencedColumns;
	}

	public void setAttributeTypes(List<IAttributeType> attributeTypes) {
		Assert.notNull(attributeTypes);
		this.attributeTypes = attributeTypes;
	}

	public void setReferencedAttributeTypes(
			List<IAttributeType> referencedAttributeTypes) {
		Assert.notNull(referencedAttributeTypes);
		this.referencedAttributeTypes = referencedAttributeTypes;
	}

	@Override
	public String toString() {
		return String.format("RelationshipTypeMapping(%s)",
				getRelationshipType());
	}

	@Override
	public RelationshipTypeMapping clone() {
		RelationshipTypeMapping relationshipTypeMapping;
		relationshipTypeMapping = new RelationshipTypeMapping(
				getRelationshipType());
		relationshipTypeMapping.setAttributeTypes(getAttributeTypes());
		relationshipTypeMapping
				.setReferencedAttributeTypes(getReferencedAttributeTypes());
		relationshipTypeMapping.setColumns(getColumns());
		relationshipTypeMapping.setReferencedColumns(getReferencedColumns());
		return relationshipTypeMapping;
	}

	public boolean equals(Object object) {

		// null reference?
		if (object == null) {
			return false;
		}

		// Same object?
		if (this == object) {
			return true;
		}

		// Same class?
		if (!getClass().equals(object.getClass())) {
			return false;
		}

		RelationshipTypeMapping relationshipTypeMapping = (RelationshipTypeMapping) object;

		// Same relationship type?
		if (!getRelationshipType().equals(relationshipTypeMapping.getRelationshipType())) {
			return false;
		}

		// Same attribute types?
		if (getAttributeTypes().size() != relationshipTypeMapping
				.getAttributeTypes().size()) {
			return false;
		}
		for (int i = 0; i < getAttributeTypes().size(); i++) {
			if (!getAttributeTypes().get(i).equals(
					relationshipTypeMapping.getAttributeTypes().get(i))) {
				return false;
			}
		}
		
		// Same referenced attribute types?
		if (getReferencedAttributeTypes().size() != relationshipTypeMapping
				.getReferencedAttributeTypes().size()) {
			return false;
		}
		for (int i = 0; i < getReferencedAttributeTypes().size(); i++) {
			if (!getReferencedAttributeTypes().get(i).equals(
					relationshipTypeMapping.getReferencedAttributeTypes().get(i))) {
				return false;
			}
		}
		
		// Same columns?
		if (getColumns().size() != relationshipTypeMapping
				.getColumns().size()) {
			return false;
		}
		for (int i = 0; i < getColumns().size(); i++) {
			if (!getColumns().get(i).equals(
					relationshipTypeMapping.getColumns().get(i))) {
				return false;
			}
		}
		
		// Same referenced attribute types?
		if (getReferencedColumns().size() != relationshipTypeMapping
				.getReferencedColumns().size()) {
			return false;
		}
		for (int i = 0; i < getReferencedColumns().size(); i++) {
			if (!getReferencedColumns().get(i).equals(
					relationshipTypeMapping.getReferencedColumns().get(i))) {
				return false;
			}
		}

		return true;
	}

}
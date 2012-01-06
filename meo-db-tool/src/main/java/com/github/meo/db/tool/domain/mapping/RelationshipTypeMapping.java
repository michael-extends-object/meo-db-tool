package com.github.meo.db.tool.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IRelationshipType;
import com.github.meo.db.tool.domain.RelationshipType;
import com.github.meo.db.tool.domain.db.Column;

public class RelationshipTypeMapping {

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

}
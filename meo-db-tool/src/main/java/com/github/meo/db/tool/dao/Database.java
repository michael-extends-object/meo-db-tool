package com.github.meo.db.tool.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.EntityRelationshipModel;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntityRelationshipModel;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.IRelationshipType;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.domain.mapping.AttributeTypeMapping;
import com.github.meo.db.tool.domain.mapping.EntityTypeMapping;
import com.github.meo.db.tool.domain.mapping.IEntityRelationshipModelMapping;
import com.github.meo.db.tool.domain.mapping.RelationshipTypeMapping;
import com.github.meo.db.tool.exception.ColumnNotFoundException;
import com.github.meo.db.tool.exception.EntityRelationshipModelMappingNotFoundException;
import com.github.meo.db.tool.exception.RelationshipTypeMappingNotFoundException;

public class Database {

	private String name;
	private DataSource dataSource;
	private List<IEntityRelationshipModel> entityRelationshipModels;
	private List<IEntityRelationshipModelMapping> entityRelationshipModelMappings;
	private IEntityRelationshipModel currentEntityRelationshipModel;

	public Database() {
		init();
	}

	public Database(String name) {
		init();
		setName(name);
	}

	private void init() {
		entityRelationshipModels = new ArrayList<IEntityRelationshipModel>();
		entityRelationshipModelMappings = new ArrayList<IEntityRelationshipModelMapping>();
		setCurrentEntityRelationshipModel(new EntityRelationshipModel());
	}

	public boolean addEntityRelationshipModel(IEntityRelationshipModel erm) {
		return getEntityRelationshipModels().add(erm);
	}

	public boolean addEntityRelationshipModelMapping(
			IEntityRelationshipModelMapping ermMapping) {
		return getEntityRelationshipModelMappings().add(ermMapping);
	}

	public Table getTable(IEntityType entityType) {

		Assert.notNull(entityType);

		return getEntityTypeMapping(entityType).getTable();
	}

	public EntityTypeMapping getEntityTypeMapping(IEntityType entityType) {

		Assert.notNull(entityType);

		IEntityRelationshipModelMapping ermMapping = getEntityRelationshipModelMapping(getCurrentEntityRelationshipModel());
		for (EntityTypeMapping entityTypeMapping : ermMapping
				.getEntityTypeMappings()) {
			// TODO consider a different comparison
			if (entityType.getName().equals(
					entityTypeMapping.getEntityType().getName())) {
				return entityTypeMapping;
			}
		}

		return null;
	}

	public IEntityRelationshipModelMapping getEntityRelationshipModelMapping(
			IEntityRelationshipModel erm) {

		Assert.notNull(erm);

		for (IEntityRelationshipModelMapping entityRelationshipModelMapping : getEntityRelationshipModelMappings()) {
			if (erm.equals(entityRelationshipModelMapping
					.getEntityRelationshipModel())) {
				return entityRelationshipModelMapping;
			}
		}

		throw new EntityRelationshipModelMappingNotFoundException(erm);
	}

	public List<EntityTypeMapping> getEntityTypeMappings() {

		IEntityRelationshipModelMapping ermMapping = getEntityRelationshipModelMapping(getCurrentEntityRelationshipModel());

		return ermMapping.getEntityTypeMappings();
	}

	public List<AttributeTypeMapping> getAttributeTypeMappings(
			IEntityType entityType) {

		Assert.notNull(entityType);

		EntityTypeMapping entityTypeMapping = getEntityTypeMapping(entityType);

		return entityTypeMapping.getAttributeTypeMappings();
	}

	public List<Column> getColumns(IEntityType entityType) {

		List<Column> columns = new ArrayList<Column>();

		for (AttributeTypeMapping attributeTypeMapping : getAttributeTypeMappings(entityType)) {
			columns.add(attributeTypeMapping.getColumn());
		}

		return columns;
	}

	public int getColumnCount(IEntityType entityType) {
		return getColumns(entityType).size();
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Column getColumn(IEntityType entityType, IAttributeType attributeType) {

		Assert.notNull(attributeType);

		return getColumn(entityType, attributeType.getName());
	}

	public Column getColumn(IEntityType entityType, String attributeName) {

		Assert.notNull(entityType);
		Assert.notNull(attributeName);

		for (AttributeTypeMapping attributeTypeMapping : getAttributeTypeMappings(entityType)) {
			if (attributeName.equals(attributeTypeMapping.getAttributeType()
					.getName())) {
				return attributeTypeMapping.getColumn();
			}
		}

		throw new ColumnNotFoundException(entityType,
				new AttributeType(attributeName));
	}

	public List<IEntityType> getReferencedEntityTypes(IEntityType entityType) {

		List<IEntityType> entityTypes = new ArrayList<IEntityType>();

		for (IRelationshipType relationshipType : getRelationshipTypes()) {
			if (relationshipType.getEntityType().equals(entityType)) {
				entityTypes.add(relationshipType.getReferencedEntityType());
			}
		}

		return entityTypes;
	}

	public List<IRelationshipType> getRelationshipTypes() {
		return getCurrentEntityRelationshipModel().getRelationshipTypes();
	}

	public List<IEntityRelationshipModel> getEntityRelationshipModels() {
		return entityRelationshipModels;
	}

	public List<IEntityRelationshipModelMapping> getEntityRelationshipModelMappings() {
		return entityRelationshipModelMappings;
	}

	public IEntityRelationshipModel getCurrentEntityRelationshipModel() {
		return currentEntityRelationshipModel;
	}

	public void setEntityRelationshipModels(
			List<IEntityRelationshipModel> entityRelationshipModels) {

		Assert.notNull(entityRelationshipModels);

		this.entityRelationshipModels = entityRelationshipModels;
	}

	public void setEntityRelationshipModelMappings(
			List<IEntityRelationshipModelMapping> entityRelationshipModelMappings) {

		Assert.notNull(entityRelationshipModelMappings);

		this.entityRelationshipModelMappings = entityRelationshipModelMappings;
	}

	public void setCurrentEntityRelationshipModel(
			IEntityRelationshipModel currentEntityRelationshipModel) {

		Assert.notNull(currentEntityRelationshipModel);

		this.currentEntityRelationshipModel = currentEntityRelationshipModel;
	}

	public RelationshipTypeMapping getRelationshipTypeMapping(
			IRelationshipType relationshipType) {

		Assert.notNull(relationshipType);

		IEntityRelationshipModelMapping ermMapping = getEntityRelationshipModelMapping(getCurrentEntityRelationshipModel());

		for (RelationshipTypeMapping relationshipTypeMapping : ermMapping
				.getRelationshipTypeMappings()) {
			if (relationshipType.equals(relationshipTypeMapping)) {
				return relationshipTypeMapping;
			}
		}

		throw new RelationshipTypeMappingNotFoundException(relationshipType);
	}

	public IRelationshipType getRelationshipType(IEntityType entityType,
			IEntityType referencedEntityType) {
		Assert.notNull(entityType);
		Assert.notNull(referencedEntityType);
		return getCurrentEntityRelationshipModel().getRelationshipType(
				entityType, referencedEntityType);
	}

	public RelationshipTypeMapping getRelationshipTypeMapping(
			IEntityType entityType, IEntityType referencedEntityType) {
		IRelationshipType relationshipType = getRelationshipType(entityType,
				referencedEntityType);
		return getRelationshipTypeMapping(relationshipType);
	}
}
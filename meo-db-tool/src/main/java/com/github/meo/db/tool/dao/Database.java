package com.github.meo.db.tool.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.Erm;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IErm;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.IRelationshipType;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.domain.mapping.AttributeTypeMapping;
import com.github.meo.db.tool.domain.mapping.EntityTypeMapping;
import com.github.meo.db.tool.domain.mapping.ErmMapping;
import com.github.meo.db.tool.domain.mapping.RelationshipTypeMapping;
import com.github.meo.db.tool.exception.ColumnNotFoundException;
import com.github.meo.db.tool.exception.ErmMappingNotFoundException;
import com.github.meo.db.tool.exception.RelationshipTypeMappingNotFoundException;

public class Database {

	private String name;
	private DataSource dataSource;
	private List<IErm> erms;
	private List<ErmMapping> ermMappings;
	private IErm currentErm;

	public Database() {
		init();
	}

	public Database(String name) {
		init();
		setName(name);
	}

	private void init() {
		erms = new ArrayList<IErm>();
		ermMappings = new ArrayList<ErmMapping>();
		setCurrentErm(new Erm());
	}

	public boolean addEntityRelationshipModel(IErm erm) {
		return getErms().add(erm);
	}

	public boolean addErmMapping(ErmMapping ermMapping) {
		return getErmMappings().add(ermMapping);
	}

	public Table getTable(IEntityType entityType) {

		Assert.notNull(entityType);

		return getEntityTypeMapping(entityType).getTable();
	}

	public EntityTypeMapping getEntityTypeMapping(IEntityType entityType) {

		Assert.notNull(entityType);

		ErmMapping ermMapping = getErmMapping(getCurrentErm());
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

	public ErmMapping getErmMapping(IErm erm) {

		Assert.notNull(erm);

		for (ErmMapping ErmMapping : getErmMappings()) {
			if (erm.equals(ErmMapping.getErm())) {
				return ErmMapping;
			}
		}

		throw new ErmMappingNotFoundException(erm);
	}

	public List<EntityTypeMapping> getEntityTypeMappings() {

		ErmMapping ermMapping = getErmMapping(getCurrentErm());

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

		throw new ColumnNotFoundException(entityType, new AttributeType(
				attributeName));
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
		return getCurrentErm().getRelationshipTypes();
	}

	public List<IErm> getErms() {
		return erms;
	}

	public List<ErmMapping> getErmMappings() {
		return ermMappings;
	}

	public IErm getCurrentErm() {
		return currentErm;
	}

	public void setErms(List<IErm> entityRelationshipModels) {

		Assert.notNull(entityRelationshipModels);

		this.erms = entityRelationshipModels;
	}

	public void setErmMappings(List<ErmMapping> ErmMappings) {

		Assert.notNull(ErmMappings);

		this.ermMappings = ErmMappings;
	}

	public void setCurrentErm(IErm currentEntityRelationshipModel) {

		Assert.notNull(currentEntityRelationshipModel);

		this.currentErm = currentEntityRelationshipModel;
	}

	public RelationshipTypeMapping getRelationshipTypeMapping(
			IRelationshipType relationshipType) {

		Assert.notNull(relationshipType);

		ErmMapping ermMapping = getErmMapping(getCurrentErm());

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
		return getCurrentErm().getRelationshipType(entityType,
				referencedEntityType);
	}

	public RelationshipTypeMapping getRelationshipTypeMapping(
			IEntityType entityType, IEntityType referencedEntityType) {
		IRelationshipType relationshipType = getRelationshipType(entityType,
				referencedEntityType);
		return getRelationshipTypeMapping(relationshipType);
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

		Database database = (Database) object;

		// Same name?
		if (!getName().equals(database.getName())) {
			return false;
		}

		// Same data source?
		if (!getDataSource().getClass().getName()
				.equals(database.getDataSource().getClass().getName())) {
			return false;
		}

		// Same ERMs?
		if (getErms().size() != database.getErms().size()) {
			return false;
		}
		for (int i = 0; i < getErms().size(); i++) {
			if (!getErms().get(i).equals(database.getErms().get(i))) {
				return false;
			}
		}

		// Same ERM-mappings?
		if (getErmMappings().size() != database.getErmMappings().size()) {
			return false;
		}
		for (int i = 0; i < getErmMappings().size(); i++) {
			if (!getErmMappings().get(i).equals(
					database.getErmMappings().get(i))) {
				return false;
			}
		}

		return true;
	}
}
package com.github.meo.db.tool.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.IEntityRelationshipModel;

public class EntityRelationshipModelMapping implements
		IEntityRelationshipModelMapping {

	private IEntityRelationshipModel entityRelationshipModel;
	private List<EntityTypeMapping> entityTypeMappings;
	private List<RelationshipTypeMapping> relationshipTypeMappings;

	public EntityRelationshipModelMapping() {
		init();
	}

	public EntityRelationshipModelMapping(
			IEntityRelationshipModel entityRelationshipModel) {
		setEntityRelationshipModel(entityRelationshipModel);
	}

	private void init() {
		entityTypeMappings = new ArrayList<EntityTypeMapping>();
		relationshipTypeMappings = new ArrayList<RelationshipTypeMapping>();
	}

	public boolean addEntityTypeMapping(EntityTypeMapping entityTypeMapping) {

		Assert.notNull(entityTypeMapping);

		return entityTypeMappings.add(entityTypeMapping);
	}

	public boolean addRelationshipTypeMapping(
			RelationshipTypeMapping relationshipTypeMapping) {

		Assert.notNull(relationshipTypeMapping);

		return relationshipTypeMappings.add(relationshipTypeMapping);
	}

	public IEntityRelationshipModel getEntityRelationshipModel() {
		return entityRelationshipModel;
	}

	public List<EntityTypeMapping> getEntityTypeMappings() {
		return entityTypeMappings;
	}

	public List<RelationshipTypeMapping> getRelationshipTypeMappings() {
		return relationshipTypeMappings;
	}

	public void setEntityRelationshipModel(
			IEntityRelationshipModel entityRelationshipModel) {

		Assert.notNull(entityRelationshipModel);

		this.entityRelationshipModel = entityRelationshipModel;
	}

	public void setEntityTypeMappings(List<EntityTypeMapping> entityTypeMappings) {

		Assert.notNull(entityTypeMappings);

		this.entityTypeMappings = entityTypeMappings;
	}

	public void setRelationshipTypeMappings(
			List<RelationshipTypeMapping> relationshipTypeMappings) {

		Assert.notNull(relationshipTypeMappings);

		this.relationshipTypeMappings = relationshipTypeMappings;
	}

}
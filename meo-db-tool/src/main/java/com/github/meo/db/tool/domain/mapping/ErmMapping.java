package com.github.meo.db.tool.domain.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.IErm;

public class ErmMapping {

	private IErm erm;
	private List<EntityTypeMapping> entityTypeMappings;
	private List<RelationshipTypeMapping> relationshipTypeMappings;

	public ErmMapping() {
		init();
	}

	public ErmMapping(IErm entityRelationshipModel) {
		setErm(entityRelationshipModel);
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

	public IErm getErm() {
		return erm;
	}

	public List<EntityTypeMapping> getEntityTypeMappings() {
		return entityTypeMappings;
	}

	public List<RelationshipTypeMapping> getRelationshipTypeMappings() {
		return relationshipTypeMappings;
	}

	public void setErm(IErm erm) {
		Assert.notNull(erm);
		this.erm = erm;
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

	@Override
	public String toString() {
		return String.format("EntityRelationshipModelMapping(%s)", getErm());
	}

	public ErmMapping clone() {
		ErmMapping ermMapping;
		ermMapping = new ErmMapping(getErm());
		ermMapping.setEntityTypeMappings(getEntityTypeMappings());
		ermMapping.setRelationshipTypeMappings(getRelationshipTypeMappings());
		return ermMapping;
	}

	@Override
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

		ErmMapping ermMapping = (ErmMapping) object;

		// Same ERM?
		if (!getErm().equals(ermMapping.getErm())) {
			return false;
		}

		// Same entity type mappings?
		if (getEntityTypeMappings().size() != ermMapping
				.getEntityTypeMappings().size()) {
			return false;
		}
		for (int i = 0; i < getEntityTypeMappings().size(); i++) {
			if (!getEntityTypeMappings().get(i).equals(
					ermMapping.getEntityTypeMappings().get(i))) {
				return false;
			}
		}

		// Same relationship type mappings?
		if (getRelationshipTypeMappings().size() != ermMapping
				.getRelationshipTypeMappings().size()) {
			return false;
		}
		for (int i = 0; i < getRelationshipTypeMappings().size(); i++) {
			if (!getRelationshipTypeMappings().get(i).equals(
					ermMapping.getRelationshipTypeMappings().get(i))) {
				return false;
			}
		}

		return true;
	}

}
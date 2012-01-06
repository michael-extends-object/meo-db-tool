package com.github.meo.db.tool.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.github.meo.db.tool.exception.RelationshipTypeNotFoundException;

public class EntityRelationshipModel implements IEntityRelationshipModel,
		Cloneable {

	String name;
	List<IEntityType> entityTypes;
	List<IRelationshipType> relationshipTypes;

	public EntityRelationshipModel() {
		init();
	}

	public EntityRelationshipModel(String name) {
		init();
		setName(name);
	}

	private void init() {
		setName("");
		entityTypes = new ArrayList<IEntityType>();
		setRelationshipTypes(new ArrayList<IRelationshipType>());
	}

	public boolean addEntityType(IEntityType entityType) {

		Assert.notNull(entityType);

		return getEntityTypes().add(entityType);
	}

	public boolean addRelationshipType(IRelationshipType relationshipType) {

		Assert.notNull(relationshipType);

		return relationshipTypes.add(relationshipType);
	}

	public String getName() {
		return name;
	}

	public List<IEntityType> getEntityTypes() {
		return entityTypes;
	}

	public List<IRelationshipType> getRelationshipTypes() {
		return relationshipTypes;
	}

	public List<IRelationshipType> getRelationshipTypes(IEntityType entityType) {

		Assert.notNull(entityType);

		List<IRelationshipType> relationshipTypes = new ArrayList<IRelationshipType>();

		for (IRelationshipType rType : getRelationshipTypes()) {
			if (entityType.equals(rType.getEntityType())) {
				relationshipTypes.add(rType);
			}
		}

		return relationshipTypes;
	}

	public IRelationshipType getRelationshipType(IEntityType entityType,
			IEntityType referencedEntityType) {

		Assert.notNull(entityType);
		Assert.notNull(referencedEntityType);

		for (IRelationshipType rType : getRelationshipTypes(entityType)) {
			if (referencedEntityType.equals(rType.getReferencedEntityType())) {
				return rType;
			}
		}

		throw new RelationshipTypeNotFoundException(entityType,
				referencedEntityType);
	}

	public void setName(String name) {

		Assert.notNull(name);

		this.name = name;
	}

	public void setEntityTypes(List<IEntityType> entityTypes) {

		Assert.notNull(entityTypes);

		for (IEntityType entityType : entityTypes) {
			addEntityType(entityType);
		}
	}

	public void setRelationshipTypes(List<IRelationshipType> relationships) {

		Assert.notNull(relationships);

		this.relationshipTypes = relationships;
	}

	@Override
	public String toString() {
		return name;
	}

	public IEntityRelationshipModel clone() {

		IEntityRelationshipModel erm = new EntityRelationshipModel(getName());

		for (IEntityType entityType : getEntityTypes()) {
			erm.addEntityType(entityType.clone());
		}

		for (IRelationshipType relationshipType : getRelationshipTypes()) {
			erm.addRelationshipType(relationshipType.clone());
		}

		return erm;
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

		IEntityRelationshipModel erm = (IEntityRelationshipModel) object;

		/*
		 * Do the objects have the same name?
		 */
		if (!getName().equals(erm.getName())) {
			return false;
		}

		/*
		 * Do the objects have the same entity types?
		 */
		if (getEntityTypes().size() != erm.getEntityTypes().size()) {
			return false;
		}

		for (int i = 0; i < getEntityTypes().size(); i++) {
			if (!getEntityTypes().get(i).equals(erm.getEntityTypes().get(i))) {
				return false;
			}
		}

		/*
		 * Do the objects have the same relationships?
		 */
		if (getRelationshipTypes().size() != erm.getRelationshipTypes().size()) {
			return false;
		}

		for (int i = 0; i < getRelationshipTypes().size(); i++) {
			if (!getRelationshipTypes().get(i).equals(
					erm.getRelationshipTypes().get(i))) {
				return false;
			}
		}

		return true;
	}

}
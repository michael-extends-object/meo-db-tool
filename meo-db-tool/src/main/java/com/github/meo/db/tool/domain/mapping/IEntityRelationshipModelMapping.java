package com.github.meo.db.tool.domain.mapping;

import java.util.List;

import com.github.meo.db.tool.domain.IEntityRelationshipModel;

public interface IEntityRelationshipModelMapping {

	public boolean addEntityTypeMapping(EntityTypeMapping entityTypeMapping);

	public boolean addRelationshipTypeMapping(
			RelationshipTypeMapping relationshipTypeMapping);

	public IEntityRelationshipModel getEntityRelationshipModel();
	
	public List<EntityTypeMapping> getEntityTypeMappings();

	public List<RelationshipTypeMapping> getRelationshipTypeMappings();

	public void setEntityRelationshipModel(IEntityRelationshipModel erm);
	
	public void setEntityTypeMappings(List<EntityTypeMapping> entityTypeMappings);

	public void setRelationshipTypeMappings(
			List<RelationshipTypeMapping> relationshipTypeMappings);

}

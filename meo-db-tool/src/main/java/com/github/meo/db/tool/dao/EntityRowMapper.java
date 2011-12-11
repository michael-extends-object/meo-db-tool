package com.github.meo.db.tool.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.meo.db.tool.domain.AttributeTypeMapping;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.exception.AttributeTypeNotFoundException;

public class EntityRowMapper implements RowMapper<IEntity> {

	IEntityType entityType;
	Database database;

	EntityRowMapper() {
	}

	EntityRowMapper(IEntityType entityType) {
		setEntityType(entityType);
	}

	public EntityRowMapper(Database database, IEntityType entityType) {
		setEntityType(entityType);
		setDatabase(database);
	}

	public IEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		IEntity entity = entityType.getEntity();

		for (AttributeTypeMapping attributeTypeMapping : database
				.getAttributeTypeMappings(entityType)) {
			String attributeName = attributeTypeMapping.getAttributeType()
					.getName();
			String columnName = attributeTypeMapping.getDatabaseTableColumn()
					.toString();
			try {
				entity.setAttributeValue(attributeName,
						rs.getObject(columnName));
			} catch (AttributeTypeNotFoundException e) {
				e.printStackTrace();
			}
		}

		return entity;
	}

	public IEntityType getEntityType() {
		return entityType;
	}

	public Database getDatabase() {
		return database;
	}

	public void setEntityType(IEntityType entityType) {
		this.entityType = entityType;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

}
package com.github.meo.db.tool.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.AttributeMapping;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.Entity;

public class EntityRowMapper implements RowMapper<Entity> {

	Entity entity;
	Database database;

	EntityRowMapper() {
	}

	EntityRowMapper(Entity entity) {
		setEntity(entity);
	}

	public EntityRowMapper(Database database, Entity entity) {
		setEntity(entity);
		setDatabase(database);
	}

	public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Entity entity = (Entity) this.entity.clone();

		for (AttributeMapping attributeMapping : database
				.getAttributeMappings(entity)) {
			String attributeName = attributeMapping.getAttribute().getName();
			String columnName = attributeMapping.getDatabaseTableColumn()
					.toString();
			Attribute attribute = entity.getAttribute(attributeName);
			attribute.setValue(rs.getObject(columnName));
		}

		return entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public Database getDatabase() {
		return database;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

}
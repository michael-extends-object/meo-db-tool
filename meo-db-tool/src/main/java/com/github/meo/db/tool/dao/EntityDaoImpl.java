package com.github.meo.db.tool.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.domain.Entity;
import com.github.meo.db.tool.domain.Relationship;
import com.github.meo.db.tool.sql.SqlUtils;

public class EntityDaoImpl implements EntityDao {

	Logger logger = Logger.getLogger(EntityDaoImpl.class);

	Database database;
	SimpleJdbcTemplate jdbcTemplate;

	public EntityDaoImpl() {

	}

	public EntityDaoImpl(Database database) {
		setDatabase(database);
		jdbcTemplate = new SimpleJdbcTemplate(getDatabase().getDataSource());
	}

	public void insertEntity(Entity entity) {
		insertEntity(getDatabase(), entity);
	}

	public void insertEntity(Database database, Entity entity) {

		for (Relationship relationship : entity.getRealtionships()) {
			insertEntity(database, relationship.getReferencedEntity());
		}

		List<DatabaseTableColumn> databaseTableColmuns = new ArrayList<DatabaseTableColumn>();

		for (Attribute attribute : entity.getAttributesNotNull()) {
			databaseTableColmuns.add(database.getDatabaseTableColumn(entity,
					attribute));
		}

		if (databaseTableColmuns.isEmpty()) {
			// nothing to insert
			return;
		}

		String sqlString = SqlUtils.getInsertStatement(
				database.getDatabaseTable(entity), databaseTableColmuns);

		List<Object> values = new ArrayList<Object>();
		for (Attribute attribute : entity.getAttributesNotNull()) {
			values.add(attribute.getValue());
		}

		jdbcTemplate.update(sqlString, values.toArray());
	}

	public SimpleJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void deleteEntity(Entity entity) {

		List<DatabaseTableColumn> databaseTableColumns = new ArrayList<DatabaseTableColumn>();

		for (Attribute attribute : entity.getAttributesPrimaryKeyNotNull()) {
			databaseTableColumns.add(database.getDatabaseTableColumn(entity,
					attribute));
		}

		String sqlStatement = SqlUtils.getDeleteStatement(
				database.getDatabaseTable(entity), databaseTableColumns);

		List<Object> values = new ArrayList<Object>();

		for (Attribute attribute : entity.getAttributesPrimaryKeyNotNull()) {
			values.add(attribute.getValue());
		}

		jdbcTemplate.update(sqlStatement, values.toArray());
	}

	public List<Entity> selectEntities(Entity entity) {

		List<Entity> entities;

		entities = jdbcTemplate.query(SqlUtils.getSelectStatement(
				database.getDatabaseTable(entity),
				database.getDatabaseTableColumns(entity)), new EntityRowMapper(
				getDatabase(), entity));

		return entities;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
		jdbcTemplate = new SimpleJdbcTemplate(database.getDataSource());
	}

}

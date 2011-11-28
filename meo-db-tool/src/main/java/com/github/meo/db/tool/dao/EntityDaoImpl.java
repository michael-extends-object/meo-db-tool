package com.github.meo.db.tool.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.github.meo.db.tool.domain.Attribute;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.domain.Entity;
import com.github.meo.db.tool.domain.Relationship;
import com.github.meo.db.tool.sql.SqlUtils;

public class EntityDaoImpl implements EntityDao {

	Database database;
	SimpleJdbcTemplate jdbcTemplate;

	public EntityDaoImpl() {
	}

	public EntityDaoImpl(Database database) {
		setDatabase(database);
	}

	public void insertEntity(Entity entity) {

		for (Relationship relationship : entity.getRelationships()) {
			for (Entity referencedEntity : relationship.getReferencedEntities()) {
				insertEntity(referencedEntity);
			}
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

	public List<Entity> selectEntities(Entity entity) {

		for (Relationship relationship : entity.getRelationships()) {

			List<Entity> newReferencedEntities = new ArrayList<Entity>();

			for (Entity referencedEntity : relationship.getReferencedEntities()) {
				newReferencedEntities.addAll(selectEntities(referencedEntity));
			}

			relationship.setReferencedEntities(newReferencedEntities);
		}

		List<Entity> entities;

		entities = jdbcTemplate.query(SqlUtils.getSelectStatement(
				database.getDatabaseTable(entity),
				database.getDatabaseTableColumns(entity)), new EntityRowMapper(
				getDatabase(), entity));

		return entities;
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

	public Database getDatabase() {
		return database;
	}

	public SimpleJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setDatabase(Database database) {

		if (database == null) {
			return;
		}

		this.database = database;

		DataSource ds = getDatabase().getDataSource();

		if (ds != null) {
			setJdbcTemplate(new SimpleJdbcTemplate(ds));
		}
	}

	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {

		if (jdbcTemplate == null) {
			return;
		}

		this.jdbcTemplate = jdbcTemplate;
	}

}

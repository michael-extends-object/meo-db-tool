package com.github.meo.db.tool.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.DatabaseTable;
import com.github.meo.db.tool.domain.DatabaseTableColumn;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.sql.SqlUtils;

public class EntityDao implements IEntityDao {

	Database database;
	SimpleJdbcTemplate jdbcTemplate;

	public EntityDao() {
		init();
	}

	public EntityDao(Database database) {
		init();
		setDatabase(database);
	}
	
	private void init() {
		setDatabase(new Database());
	}

	public void insertEntity(IEntity entity) {

		List<DatabaseTableColumn> databaseTableColmuns = new ArrayList<DatabaseTableColumn>();
		List<Object> values = new ArrayList<Object>();

		for (IAttribute attribute : entity.getAttributesNotNull()) {
			databaseTableColmuns.add(database.getDatabaseTableColumn(
					entity.getEntityType(), attribute.getAttributeType()));
			values.add(attribute.getValue());
		}
		
		if (databaseTableColmuns.isEmpty()) {
			// nothing to insert
			return;
		}

		String sqlString = SqlUtils.getInsertStatement(
				database.getDatabaseTable(entity.getEntityType()),
				databaseTableColmuns);

		jdbcTemplate.update(sqlString, values.toArray());
	}

	public List<IEntity> selectEntities(IEntityType entityType) {

		DatabaseTable databaseTable = database.getDatabaseTable(entityType);
		List<DatabaseTableColumn> databaseTableColumns = getDatabase()
				.getDatabaseTableColumns(entityType);

		String sqlSelectStatement = SqlUtils.getSelectStatement(databaseTable,
				databaseTableColumns);

		List<IEntity> entities = jdbcTemplate.query(sqlSelectStatement,
				new EntityRowMapper(getDatabase(), entityType));
		
		return entities;
	}

	public void deleteEntities(IEntityType entityType) {
		String sqlStatement = SqlUtils.getDeleteStatement(database
				.getDatabaseTable(entityType));
		jdbcTemplate.update(sqlStatement);
	}

	public void deleteEntity(IEntity entity) {

		List<DatabaseTableColumn> databaseTableColumns = new ArrayList<DatabaseTableColumn>();
		List<Object> values = new ArrayList<Object>();

		for (IAttribute attribute : entity.getAttributesPrimaryKeyNotNull()) {
			databaseTableColumns.add(database.getDatabaseTableColumn(
					entity.getEntityType(), attribute.getAttributeType()));
			values.add(attribute.getValue());
		}

		String sqlStatement = SqlUtils.getDeleteStatement(
				database.getDatabaseTable(entity.getEntityType()),
				databaseTableColumns);

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

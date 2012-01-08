package com.github.meo.db.tool.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.IAttribute;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.domain.db.Table;
import com.github.meo.db.tool.domain.db.Column;
import com.github.meo.db.tool.domain.mapping.RelationshipTypeMapping;
import com.github.meo.db.tool.sql.SqlStatementUtils;

public class EntityDao implements IEntityDao {

	private final static Logger log = Logger.getLogger(EntityDao.class);

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

	public void insertEntity(IEntity entity) throws DataAccessException {

		List<Column> colmuns = new ArrayList<Column>();
		List<Object> values = new ArrayList<Object>();

		for (IAttribute attribute : entity.getAttributesNotNull()) {
			colmuns.add(database.getColumn(entity.getEntityType(),
					attribute.getAttributeType()));
			values.add(attribute.getValue());
		}

		if (colmuns.isEmpty()) {
			// nothing to insert
			return;
		}

		String sqlString = SqlStatementUtils.getInsertStatement(
				database.getTable(entity.getEntityType()), colmuns);

		try {
			jdbcTemplate.update(sqlString, values.toArray());
		} catch (DuplicateKeyException e) {
			log.error(
					String.format(
							"Could not insert entity '%s' due to a unique key violation",
							entity), e);
			throw e;
		}

	}

	/*
	 * TODO use batch insert instead
	 */
	public void insertEntities(List<IEntity> entities)
			throws DataAccessException {

		Assert.notNull(entities);

		for (IEntity entity : entities) {
			insertEntity(entity);
		}
	}

	public List<IEntity> selectEntities(IEntityType entityType) {

		Table table = database.getTable(entityType);
		List<Column> columns = getDatabase().getColumns(entityType);

		String sqlSelectStatement = SqlStatementUtils.getSelectStatement(table,
				columns);

		List<IEntity> entities = jdbcTemplate.query(sqlSelectStatement,
				new EntityRowMapper(getDatabase(), entityType));

		return entities;
	}

	public List<IEntity> selectEntities(IEntityType entityType,
			List<IAttribute> attributes) {

		Table table = database.getTable(entityType);
		List<Column> columnsSelect = getDatabase().getColumns(entityType);

		List<Column> columnsWhere = new ArrayList<Column>();
		List<Object> values = new ArrayList<Object>();

		for (IAttribute attribute : attributes) {
			columnsWhere.add(database.getColumn(entityType,
					attribute.getAttributeType()));
			values.add(attribute.getValue());
		}

		String sqlSelectWhereStatement = SqlStatementUtils
				.getSelectWhereStatement(table, columnsSelect, columnsWhere);

		List<IEntity> entities = jdbcTemplate.query(sqlSelectWhereStatement,
				new EntityRowMapper(getDatabase(), entityType),
				values.toArray());

		return entities;
	}

	public List<IEntity> selectReferencedEntities(IEntity entity,
			IEntityType refEntityType) {

		RelationshipTypeMapping relMapping = getDatabase()
				.getRelationshipTypeMapping(entity.getEntityType(),
						refEntityType);

		List<IAttributeType> attributeTypes = relMapping.getAttributeTypes();
		List<IAttributeType> refAttributeTypes = relMapping
				.getReferencedAttributeTypes();
		List<IAttribute> attributes = new ArrayList<IAttribute>();

		for (int i = 0; i < refAttributeTypes.size(); i++) {
			IAttribute attribute = refAttributeTypes.get(i).createAttribute();
			attribute.setValue(entity.getAttributeValue(attributeTypes.get(i)
					.getName()));
			attributes.add(attribute);
		}

		return selectEntities(refEntityType, attributes);
	}

	public void deleteEntity(IEntity entity) {

		List<Column> columns = new ArrayList<Column>();
		List<Object> values = new ArrayList<Object>();

		for (IAttribute attribute : entity.getAttributesPrimaryKeyNotNull()) {
			columns.add(database.getColumn(entity.getEntityType(),
					attribute.getAttributeType()));
			values.add(attribute.getValue());
		}

		String sqlStatement = SqlStatementUtils.getDeleteStatement(
				database.getTable(entity.getEntityType()), columns);

		jdbcTemplate.update(sqlStatement, values.toArray());
	}

	public void deleteEntities(List<IEntity> entities) {
		Assert.notNull(entities);
		for (IEntity entity : entities) {
			deleteEntity(entity);
		}
	}

	public void deleteEntities(IEntityType entityType) {
		String sqlStatement = SqlStatementUtils.getDeleteStatement(database
				.getTable(entityType));
		jdbcTemplate.update(sqlStatement);
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
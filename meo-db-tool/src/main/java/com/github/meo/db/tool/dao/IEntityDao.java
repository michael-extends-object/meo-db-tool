package com.github.meo.db.tool.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityType;

public interface IEntityDao {

	public void insertEntity(IEntity entity);

	public List<IEntity> selectEntities(IEntityType entityType);

	public void deleteEntities(IEntityType entityType);

	public void deleteEntity(IEntity entity);

	public Database getDatabase();

	public SimpleJdbcTemplate getJdbcTemplate();

	public void setDatabase(Database database);

	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate);
}
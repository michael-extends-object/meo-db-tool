package com.github.meo.db.tool.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.github.meo.db.tool.domain.Database;
import com.github.meo.db.tool.domain.Entity;

public interface EntityDao {

	public void insertEntity(Entity entity);

	public List<Entity> selectEntities(Entity entity);

	public void deleteEntity(Entity entity);

	public Database getDatabase();

	public SimpleJdbcTemplate getJdbcTemplate();

	public void setDatabase(Database database);

	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate);
}
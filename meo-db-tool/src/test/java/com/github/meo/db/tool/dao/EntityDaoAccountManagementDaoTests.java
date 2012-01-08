package com.github.meo.db.tool.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityType;
import com.github.meo.db.tool.testsuite.TestObjectsAccountManagement;

public class EntityDaoAccountManagementDaoTests {

	@Test
	public void testBla() throws SQLException {

		Database databaseSource = TestObjectsAccountManagement
				.getDatabaseAccountManagementSource();
		Database databaseTarget = TestObjectsAccountManagement
				.getDatabaseAccountManagementTarget();

		IEntityDao entityDaoSource = new EntityDao(databaseSource);

		@SuppressWarnings("unused")
		IEntityDao entityDaoTarget = new EntityDao(databaseTarget);

		IEntityType entityType = TestObjectsAccountManagement.getTypeGroup();

		@SuppressWarnings("unused")
		List<IEntity> entities = entityDaoSource.selectEntities(entityType);

	}
}

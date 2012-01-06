package com.github.meo.db.tool.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;

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
		IEntityDao entityDaoTarget = new EntityDao(databaseTarget);

		IEntityType entityType = TestObjectsAccountManagement.getTypeGroup();

		List<IEntity> entities = entityDaoSource.selectEntities(entityType);

//		Connection connection = databaseSource.getDataSource().getConnection();

//		DatabaseMetaData dbmd = connection.getMetaData();
//		System.out.println(dbmd.supportsGetGeneratedKeys());
		
//		DataFieldMaxValueIncrementer bla = new PostgreSQLSequenceMaxValueIncrementer(databaseSource.getDataSource(), "seq_user_group_id");
//		System.out.println(bla.nextLongValue());
		
		// entityDaoTarget.insertEntities(entities);

		// for (IEntity entity : entities) {
		// System.out.println(entity);
		// for (IAttribute attribute : entity.getAttributes()) {
		// System.out.println(attribute.getName() + ": "
		// + attribute.getValue());
		// }
		// }

	}
}

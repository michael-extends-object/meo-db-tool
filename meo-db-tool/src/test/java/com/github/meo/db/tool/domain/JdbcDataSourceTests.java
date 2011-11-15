package com.github.meo.db.tool.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Driver;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.github.meo.db.tool.testsuite.TestObjects;

public class JdbcDataSourceTests {

	JdbcDataSource jdbcDataSource;
	JdbcDataSource jdbcDataSourceA;
	JdbcDataSource jdbcDataSourceB;
	Driver driver;

	@Before
	public void setUp() {
		jdbcDataSource = new JdbcDataSource();
		jdbcDataSourceA = new JdbcDataSource();
		jdbcDataSourceB = new JdbcDataSource();

	}

	@Test
	public void createInstanceA() {
		Driver driver = TestObjects.getDriver();
		jdbcDataSource = new JdbcDataSource(driver);
		assertEquals(driver, jdbcDataSource.getDriver());
	}

	@Test
	public void createInstanceB() {
		String driverClassName = TestObjects.getDriverClassName();
		jdbcDataSource = new JdbcDataSource(driverClassName);
		assertEquals(driverClassName, jdbcDataSource.getDriverClassName());
	}

	@Test
	public void createInstanceC() {
		Driver driver = TestObjects.getDriver();
		String url = TestObjects.getUrl();
		jdbcDataSource = new JdbcDataSource(driver, url);
		assertEquals(driver, jdbcDataSource.getDriver());
		assertEquals(url, jdbcDataSource.getUrl());
	}

	@Test
	public void createInstanceD() {
		String driverClassName = TestObjects.getDriverClassName();
		String url = TestObjects.getUrl();
		jdbcDataSource = new JdbcDataSource(driverClassName, url);
		assertEquals(driverClassName, jdbcDataSource.getDriverClassName());
		assertEquals(url, jdbcDataSource.getUrl());
	}

	@Test
	public void createInstanceE() {
		Driver driver = TestObjects.getDriver();
		String url = TestObjects.getUrl();
		String username = TestObjects.getUrl();
		jdbcDataSource = new JdbcDataSource(driver, url, username);
		assertEquals(driver, jdbcDataSource.getDriver());
		assertEquals(url, jdbcDataSource.getUrl());
		assertEquals(username, jdbcDataSource.getUsername());
	}

	@Test
	public void createInstanceF() {
		String driverClassName = TestObjects.getDriverClassName();
		String url = TestObjects.getUrl();
		String username = TestObjects.getUrl();
		jdbcDataSource = new JdbcDataSource(driverClassName, url, username);
		assertEquals(driverClassName, jdbcDataSource.getDriverClassName());
		assertEquals(url, jdbcDataSource.getUrl());
		assertEquals(username, jdbcDataSource.getUsername());
	}

	@Test
	public void createInstanceG() {
		Driver driver = TestObjects.getDriver();
		String url = TestObjects.getUrl();
		String username = TestObjects.getUrl();
		String password = TestObjects.getPassword();
		jdbcDataSource = new JdbcDataSource(driver, url, username, password);
		assertEquals(driver, jdbcDataSource.getDriver());
		assertEquals(url, jdbcDataSource.getUrl());
		assertEquals(username, jdbcDataSource.getUsername());
		assertEquals(password, jdbcDataSource.getPassword());
	}

	@Test
	public void createInstanceH() {
		String driverClassName = TestObjects.getDriverClassName();
		String url = TestObjects.getUrl();
		String username = TestObjects.getUrl();
		String password = TestObjects.getPassword();
		jdbcDataSource = new JdbcDataSource(driverClassName, url, username,
				password);
		assertEquals(driverClassName, jdbcDataSource.getDriverClassName());
		assertEquals(url, jdbcDataSource.getUrl());
		assertEquals(username, jdbcDataSource.getUsername());
		assertEquals(password, jdbcDataSource.getPassword());
	}

	@Test
	public void getDriverInvalidClassName() {
		JdbcDataSource.getDriver(TestObjects.getInvalidDriverClassName());
	}

	@Test
	public void equalsNull() {
		for (JdbcDataSource jdbcDataSource : TestObjects.getJdbcDataSources()) {
			assertFalse(jdbcDataSource.equals(null));
		}
	}

	@Test
	public void equalsSameObject() {
		assertTrue(jdbcDataSource.equals(jdbcDataSource));
	}

	@Test
	public void equalsDifferentClass() {
		assertFalse(jdbcDataSourceA.equals(new Object()));
	}

	@Test
	public void equalsDriver() {
		jdbcDataSourceA.setDriver(TestObjects.getDriver());
		jdbcDataSourceB.setDriver(TestObjects.getDriver());
		assertTrue(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsNullDriverA() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceA.setDriver((Driver) null);
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsNullDriverB() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB.setDriver((Driver) null);
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsDifferentDriver() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB.setDriver(JdbcDataSource
				.getDriver("org.hsqldb.jdbcDriver"));
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsUrl() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		assertTrue(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsNullUrlA() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceA.setUrl(null);
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsNullUrlB() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB.setUrl(null);
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsDifferentUrl() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB.setUrl("Different URL");
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsUsername() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		assertTrue(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsNullUsernameA() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceA.setUsername(null);
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsNullUsernameB() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB.setUsername(null);
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsDifferentUsername() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB.setUsername("Different user name");
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsPassword() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		assertTrue(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsNullPasswordA() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceA.setPassword(null);
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsNullPasswordB() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB.setPassword(null);
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void equalsDifferentPassword() {
		jdbcDataSourceA = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB = TestObjects.getJdbcDataSourceA();
		jdbcDataSourceB.setPassword("Different user name");
		assertFalse(jdbcDataSourceA.equals(jdbcDataSourceB));
	}

	@Test
	public void setGetDriver() {
		Driver driver = TestObjects.getDriver();
		jdbcDataSource.setDriver(driver);
		assertEquals(driver, jdbcDataSource.getDriver());
	}

	@Test
	public void getSetDriverClassName() {
		String driverClassName = TestObjects.getDriverClassName();
		jdbcDataSource.setDriver(driverClassName);
		assertEquals(driverClassName, jdbcDataSource.getDriverClassName());
	}

	@Test
	public void setGetUrl() {
		String url = TestObjects.getUrl();
		jdbcDataSource.setUrl(url);
		assertEquals(url, jdbcDataSource.getUrl());
	}

	@Test
	public void setGetUsername() {
		String username = TestObjects.getUsername();
		jdbcDataSource.setUsername(username);
		assertEquals(username, jdbcDataSource.getUsername());
	}

	@Test
	public void setGetPassword() {
		String password = TestObjects.getPassword();
		jdbcDataSource.setPassword(password);
		assertEquals(password, jdbcDataSource.getPassword());
	}

	@Test
	public void setGetPrintWriter() throws SQLException {
		jdbcDataSource.setLogWriter(null);
		jdbcDataSource.getLogWriter();
	}

	@Test
	public void setGetLoginTimeout() throws SQLException {
		jdbcDataSource.setLoginTimeout(0);
		jdbcDataSource.getLoginTimeout();
	}

	@Test
	public void isWrapperFor() throws SQLException {
		jdbcDataSource.isWrapperFor(null);
		jdbcDataSource.unwrap(null);
	}

	@Test
	public void getConnection() throws SQLException {
		jdbcDataSource.getConnection(null, null);
		jdbcDataSource.getConnection();
	}

	@Test
	public void getDriverInstantiationException() {
		JdbcDataSource.getDriver("java.sql.Driver");
	}

	@Test
	public void getDriverIllegalAccessException() {
		JdbcDataSource
				.getDriver("com.github.meo.db.tool.testsuite.ObjectIllegalAccessException");
	}

}
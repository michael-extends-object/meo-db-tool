package com.github.meo.db.tool.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.meo.db.tool.domain.AttributeTests;
import com.github.meo.db.tool.domain.EntityTests;

@RunWith(Suite.class)
@SuiteClasses({
	AttributeTests.class,
	EntityTests.class
})

public class AllTests {

}

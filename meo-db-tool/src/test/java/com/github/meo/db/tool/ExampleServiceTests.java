package com.github.meo.db.tool;

import com.github.meo.db.tool.ExampleService;
import junit.framework.TestCase;

public class ExampleServiceTests extends TestCase {

	private ExampleService service = new ExampleService();
	
	public void testReadOnce() throws Exception {
		assertEquals("Hello world!", service.getMessage());
	}

}

package com.github.meo.db.tool.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class CardinalityTests {

//	private Cardinality cardinality;
	
	private Cardinality oneToOne;
	private Cardinality oneToMany;
	private Cardinality manyToOne;
	private Cardinality manyToMany;

	private String oneToOneString;
	private String oneToManyString;
	private String manyToOneString;
	private String manyToManyString;

	@Before
	public void setUp() {
		oneToOne = Cardinality.OneToOne;
		oneToMany = Cardinality.OneToMany;
		manyToOne = Cardinality.ManyToOne;
		manyToMany = Cardinality.ManyToMany;

		oneToOneString = "1:1";
		oneToManyString = "1:N";
		manyToOneString = "N:1";
		manyToManyString = "N:M";
	}
	
	@Test
	public void testValueOf() {
		assertEquals(Cardinality.OneToOne, Cardinality.valueOf("OneToOne"));
		assertEquals(Cardinality.OneToMany, Cardinality.valueOf("OneToMany"));
		assertEquals(Cardinality.ManyToOne, Cardinality.valueOf("ManyToOne"));
		assertEquals(Cardinality.ManyToMany, Cardinality.valueOf("ManyToMany"));
	}
	
	@Test
	public void testValues() {
		List<Cardinality> actualList = new ArrayList<Cardinality>();
		List<Cardinality> expectedList = new ArrayList<Cardinality>();

		for (Cardinality cardinality : Cardinality.values()) {
			actualList.add(cardinality);
		}
		
		expectedList.add(oneToOne);
		expectedList.add(oneToMany);
		expectedList.add(manyToOne);
		expectedList.add(manyToMany);
		
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void testOneToOneToString() {
		assertEquals(oneToOne.toString(), oneToOneString);
	}

	@Test
	public void testOneToManyToString() {
		assertEquals(oneToMany.toString(), oneToManyString);
	}

	@Test
	public void testManyToOneToString() {
		assertEquals(manyToOne.toString(), manyToOneString);
	}

	@Test
	public void testManyToManyToString() {
		assertEquals(manyToMany.toString(), manyToManyString);
	}
}

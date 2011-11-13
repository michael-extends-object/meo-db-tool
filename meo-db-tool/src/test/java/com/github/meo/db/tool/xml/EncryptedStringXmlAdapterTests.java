package com.github.meo.db.tool.xml;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EncryptedStringXmlAdapterTests {

	EncryptedStringXmlAdapter encryptedStringXmlAdapter;
	String plaintext = "password";
	String encryptedString;
	String secondEncryptedString;

	@Before
	public void setUp() throws Exception {
		encryptedStringXmlAdapter = new EncryptedStringXmlAdapter();
		encryptedString = encryptedStringXmlAdapter.marshal(plaintext);
		secondEncryptedString = encryptedStringXmlAdapter.marshal(plaintext);
		
	}

	@Test
	public void marshalA() throws Exception {
		assertTrue(encryptedString.startsWith("ENC("));
		assertTrue(secondEncryptedString.startsWith("ENC("));
	}
	
	@Test
	public void marshalB() throws Exception {
		assertFalse(plaintext.equals(encryptedString));
		assertFalse(plaintext.equals(secondEncryptedString));
	}
	
	@Test
	public void marshalC() throws Exception {
		assertFalse(encryptedString.equals(secondEncryptedString));
	}

	@Test
	public void unmarshalA() throws Exception {
		assertEquals(plaintext,
				encryptedStringXmlAdapter.unmarshal(encryptedString));
	}
	
	@Test
	public void unmarshalB() throws Exception {
		assertEquals(plaintext,
				encryptedStringXmlAdapter.unmarshal(plaintext));
	}

}

package com.github.meo.db.tool.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;

public class EncryptedStringXmlAdapter extends XmlAdapter<String, String> {

	private final PBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	private final String JASYPT_KEY = "SD65jlE9jg85N6MDzfyuDwn4bdItwUxgZnx91D50UYUdyobOff3kba2Vee7LNUA";

	/**
	 * Constructor.
	 */
	public EncryptedStringXmlAdapter() {
		encryptor.setPassword(JASYPT_KEY);
	}

	/**
	 * Encrypts the value to be placed back in XML.
	 */
	@Override
	public String marshal(String plaintext) throws Exception {
		// This encrypts and adds the ENC(...)
		return PropertyValueEncryptionUtils.encrypt(plaintext, encryptor);
	}

	/**
	 * Decrypts the string value.
	 */
	@Override
	public String unmarshal(String cyphertext) throws Exception {

		// Perform decryption operations as needed and store the new values
		if (PropertyValueEncryptionUtils.isEncryptedValue(cyphertext))
			return PropertyValueEncryptionUtils.decrypt(cyphertext, encryptor);

		return cyphertext;
	}

}
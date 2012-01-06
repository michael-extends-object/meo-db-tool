package com.github.meo.db.tool.exception;

public class ExceptionUtils {

	public static String bla(ExceptionMessage message, Object... args) {
		return String.format(message.toString(), args);
	}

}

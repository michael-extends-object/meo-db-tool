package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IErm;

public class ErmMappingNotFoundException extends
		RuntimeException {

	private static final long serialVersionUID = 3442145616341954936L;

	public ErmMappingNotFoundException(final String message) {
		super(message);
	}

	public ErmMappingNotFoundException(
			final IErm erm) {
		super(ExceptionUtils.bla(
				ExceptionMessage.EntityRelationshipModelMappingNotFound, erm));
	}

}
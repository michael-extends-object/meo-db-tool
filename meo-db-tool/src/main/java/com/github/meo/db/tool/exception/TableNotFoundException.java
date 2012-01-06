package com.github.meo.db.tool.exception;

import com.github.meo.db.tool.domain.IEntityRelationshipModel;
import com.github.meo.db.tool.domain.IEntityType;

public class TableNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6451776343888363944L;

	public TableNotFoundException(IEntityRelationshipModel erm,
			IEntityType entityType) {
		super(ExceptionUtils.bla(ExceptionMessage.TableNotFound, erm,
				entityType));
	}

}

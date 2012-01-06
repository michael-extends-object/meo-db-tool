package com.github.meo.db.tool.exception;

public enum ExceptionMessage {

	AttributeNotFound {
		public String toString() {
			return "Couldn't find the attribute '%s'";
		}
	},
	TableNotFound {
		public String toString() {
			return "Couldn't find the database table with given parameters: '%s', '%s'";
		}
	},
	ColumnNotFound {
		public String toString() {
			return "Couldn't find the database table column with given parameters: '%s', '%s'";
		}
	},
	AttributeTypeMappingNotFound {
		public String toString() {
			return "Couldn't find the mapping for attribute '%s'";
		}
	},
	EntityTypeMappingNotFound {
		public String toString() {
			return "Couldn't find the mapping for entity type '%s'";
		}
	},
	EntityRelationshipModelMappingNotFound {
		public String toString() {
			return "Couldn't find the mapping for entity relationship model '%s'";
		}
	},
	RelationshipTypeMappingNotFound {
		public String toString() {
			return "Couldn't find the mapping for relationship type '%s'";
		}
	}
}

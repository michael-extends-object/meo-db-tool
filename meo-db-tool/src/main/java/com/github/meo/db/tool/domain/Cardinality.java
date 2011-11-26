package com.github.meo.db.tool.domain;

public enum Cardinality {
	OneToOne {
		/**
		 * @return Returns 1:1
		 */
		public String toString() {
			return "1:1";
		}
	},
	/**
	 */
	OneToMany {
		/**
		 * @return Returns 1:N
		 */
		@Override
		public String toString() {
			return "1:N";
		}
	},
	/**
	 */
	ManyToOne {
		/**
		 * @return Returns N:1
		 */
		@Override
		public String toString() {
			return "N:1";
		}
	},
	/**
	 */
	ManyToMany {
		/**
		 * @return Returns N:M
		 */
		@Override
		public String toString() {
			return "N:M";
		}
	}
}

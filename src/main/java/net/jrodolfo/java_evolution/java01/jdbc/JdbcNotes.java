package net.jrodolfo.java_evolution.java01.jdbc;

/**
 * Explains JDBC, introduced in Java 1.1.
 */
public class JdbcNotes {

	public String problemSolved() {
		return "Java applications needed a portable database API instead of vendor-specific database calls";
	}

	public String coreTypes() {
		return "Connection, Statement, PreparedStatement, ResultSet, and JDBC drivers form the core API model";
	}

	public String repositoryDecision() {
		return "a faithful JDBC demo needs a driver, database, schema, credentials, and cleanup, so this repository keeps it explanatory";
	}
}

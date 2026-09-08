package net.jrodolfo.java_evolution.java01.jdbc;

import java.sql.SQLException;

/**
 * Demonstrates the JDBC 1.1 driver-dispatch model introduced in Java 1.1.
 *
 * <p>Modern JDKs have expanded the original JDBC interfaces with many later
 * methods. Rather than introduce later APIs or a dynamic-proxy compatibility
 * layer into this Java 1.1 example, this class uses small local types that
 * model the original roles: a driver manager selects a driver by URL, and the
 * selected driver creates a connection.</p>
 */
public class JdbcExamples {

	private final LearningDriverManager driverManager = new LearningDriverManager();

	/** Registers a driver that accepts URLs beginning with the supplied prefix. */
	public LearningDriver registerLearningDriver(String urlPrefix) {
		LearningDriver driver = new LearningDriver(urlPrefix);
		driverManager.registerDriver(driver);
		return driver;
	}

	/** Removes a previously registered learning driver. */
	public void deregisterDriver(LearningDriver driver) {
		driverManager.deregisterDriver(driver);
	}

	/**
	 * Opens a connection through the small driver-manager model.
	 *
	 * @param jdbcUrl URL requested by the application
	 * @return connection created by the matching driver
	 * @throws SQLException when no registered driver accepts the URL
	 */
	public LearningConnection openConnection(String jdbcUrl) throws SQLException {
		return driverManager.getConnection(jdbcUrl);
	}

	/** @return number of registered learning drivers */
	public int registeredLearningDriverCount() {
		return driverManager.countDrivers();
	}

	/** Small model of the JDBC driver-selection role. */
	public static final class LearningDriverManager {
		private LearningDriver driver;

		public void registerDriver(LearningDriver driver) {
			this.driver = driver;
		}

		public void deregisterDriver(LearningDriver driver) {
			if (this.driver == driver) {
				this.driver = null;
			}
		}

		public LearningConnection getConnection(String url) throws SQLException {
			if (driver != null && driver.acceptsURL(url)) {
				return driver.connect(url);
			}
			throw new SQLException("No suitable driver for " + url);
		}

		public int countDrivers() {
			return driver == null ? 0 : 1;
		}
	}

	/** Minimal driver contract used by the learning model. */
	public static final class LearningDriver {
		private final String urlPrefix;

		private LearningDriver(String urlPrefix) {
			this.urlPrefix = urlPrefix;
		}

		public boolean acceptsURL(String url) {
			return url != null && url.startsWith(urlPrefix);
		}

		public LearningConnection connect(String url) throws SQLException {
			if (!acceptsURL(url)) {
				return null;
			}
			return new LearningConnection(url);
		}
	}

	/** Minimal connection that exposes the selected URL and metadata. */
	public static final class LearningConnection {
		private final String url;
		private boolean closed;

		private LearningConnection(String url) {
			this.url = url;
		}

		public LearningDatabaseMetaData getMetaData() {
			return new LearningDatabaseMetaData(url);
		}

		public void close() {
			closed = true;
		}

		public boolean isClosed() {
			return closed;
		}
	}

	/** Minimal metadata returned by the learning connection. */
	public static final class LearningDatabaseMetaData {
		private final String url;

		private LearningDatabaseMetaData(String url) {
			this.url = url;
		}

		public String getURL() {
			return url;
		}

		public String getDriverName() {
			return "java-evolution learning jdbc driver";
		}

		public String getDatabaseProductName() {
			return "learning-database";
		}
	}
}

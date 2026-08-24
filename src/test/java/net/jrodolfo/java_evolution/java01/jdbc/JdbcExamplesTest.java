package net.jrodolfo.java_evolution.java01.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class JdbcExamplesTest {

	private static final String URL_PREFIX = "jdbc:learning:";
	private static final String VALID_URL = "jdbc:learning:demo";
	private static final String UNSUPPORTED_URL = "jdbc:not-learning:demo";

	private final JdbcExamples examples = new JdbcExamples();

	@Test
	void driverManagerRoutesMatchingJdbcUrlToRegisteredDriver() throws Exception {
		Driver driver = examples.registerLearningDriver(URL_PREFIX);
		try {
			Connection connection = examples.openConnection(VALID_URL);

			assertThat(connection)
					.as("DriverManager should return a connection from the registered driver that accepts the URL")
					.isNotNull();
			assertThat(connection.isClosed())
					.as("A newly opened JDBC connection should start open")
					.isFalse();
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void driverAcceptsOnlyItsJdbcUrlPrefix() throws Exception {
		Driver driver = examples.registerLearningDriver(URL_PREFIX);
		try {
			assertThat(driver.acceptsURL(VALID_URL))
					.as("JDBC drivers declare which URL shapes they can handle")
					.isTrue();
			assertThat(driver.acceptsURL(UNSUPPORTED_URL))
					.as("DriverManager should skip drivers that reject a URL")
					.isFalse();
			assertThat(driver.connect(UNSUPPORTED_URL, null))
					.as("A JDBC driver returns null from connect when it does not understand the URL")
					.isNull();
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void unsupportedUrlFailsWhenNoRegisteredDriverAcceptsIt() throws Exception {
		Driver driver = examples.registerLearningDriver(URL_PREFIX);
		try {
			assertThatThrownBy(() -> examples.openConnection(UNSUPPORTED_URL))
					.as("DriverManager should fail when no registered JDBC driver accepts the URL")
					.isInstanceOf(SQLException.class)
					.hasMessageContaining("No suitable driver");
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void connectionExposesDatabaseMetadataThroughStandardJdbcTypes() throws Exception {
		Driver driver = examples.registerLearningDriver(URL_PREFIX);
		try {
			Connection connection = examples.openConnection(VALID_URL);
			DatabaseMetaData metadata = connection.getMetaData();

			assertThat(metadata.getURL())
					.as("DatabaseMetaData should expose the JDBC URL selected by DriverManager")
					.isEqualTo(VALID_URL);
			assertThat(metadata.getDriverName())
					.as("DatabaseMetaData should describe the driver behind the connection")
					.contains("learning jdbc driver");
			assertThat(metadata.getDatabaseProductName())
					.as("JDBC separates the standard API from the database product behind it")
					.isEqualTo("learning-database");
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void closingConnectionChangesConnectionState() throws Exception {
		Driver driver = examples.registerLearningDriver(URL_PREFIX);
		try {
			Connection connection = examples.openConnection(VALID_URL);

			connection.close();

			assertThat(connection.isClosed())
					.as("Connection.close should mark the JDBC resource as closed")
					.isTrue();
			assertThat(connection.isValid(0))
					.as("A closed connection should no longer be valid")
					.isFalse();
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void deregisteringDriverRemovesItFromDriverManager() throws Exception {
		int before = examples.registeredLearningDriverCount();
		Driver driver = examples.registerLearningDriver(URL_PREFIX);
		try {
			assertThat(examples.registeredLearningDriverCount())
					.as("DriverManager keeps registered drivers in a JVM-wide registry")
					.isEqualTo(before + 1);
		}
		finally {
			examples.deregisterDriver(driver);
		}

		assertThat(examples.registeredLearningDriverCount())
				.as("The learning driver should be removed from DriverManager after deregistration")
				.isEqualTo(before);
	}
}

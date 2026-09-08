package net.jrodolfo.java_evolution.java01.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class JdbcExamplesTest {
	private static final String PREFIX = "jdbc:learning:";
	private static final String VALID_URL = "jdbc:learning:demo";
	private static final String UNSUPPORTED_URL = "jdbc:not-learning:demo";
	private final JdbcExamples examples = new JdbcExamples();

	@Test
	void driverManagerRoutesMatchingUrlToRegisteredDriver() throws Exception {
		JdbcExamples.LearningDriver driver = examples.registerLearningDriver(PREFIX);
		try {
			JdbcExamples.LearningConnection connection = examples.openConnection(VALID_URL);
			assertThat(connection).isNotNull();
			assertThat(connection.isClosed()).isFalse();
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void driverAcceptsOnlyItsUrlPrefix() throws Exception {
		JdbcExamples.LearningDriver driver = examples.registerLearningDriver(PREFIX);
		try {
			assertThat(driver.acceptsURL(VALID_URL)).isTrue();
			assertThat(driver.acceptsURL(UNSUPPORTED_URL)).isFalse();
			assertThat(driver.connect(UNSUPPORTED_URL)).isNull();
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void unsupportedUrlFailsWhenNoDriverAcceptsIt() throws Exception {
		JdbcExamples.LearningDriver driver = examples.registerLearningDriver(PREFIX);
		try {
			assertThatThrownBy(() -> examples.openConnection(UNSUPPORTED_URL))
					.isInstanceOf(SQLException.class)
					.hasMessageContaining("No suitable driver");
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void connectionExposesSelectedUrlAndMetadata() throws Exception {
		JdbcExamples.LearningDriver driver = examples.registerLearningDriver(PREFIX);
		try {
			JdbcExamples.LearningDatabaseMetaData metadata = examples.openConnection(VALID_URL).getMetaData();
			assertThat(metadata.getURL()).isEqualTo(VALID_URL);
			assertThat(metadata.getDriverName()).contains("learning jdbc driver");
			assertThat(metadata.getDatabaseProductName()).isEqualTo("learning-database");
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void closingConnectionChangesConnectionState() throws Exception {
		JdbcExamples.LearningDriver driver = examples.registerLearningDriver(PREFIX);
		try {
			JdbcExamples.LearningConnection connection = examples.openConnection(VALID_URL);
			connection.close();
			assertThat(connection.isClosed()).isTrue();
		}
		finally {
			examples.deregisterDriver(driver);
		}
	}

	@Test
	void deregisteringDriverRemovesItFromDriverManager() {
		JdbcExamples.LearningDriver driver = examples.registerLearningDriver(PREFIX);
		assertThat(examples.registeredLearningDriverCount()).isEqualTo(1);
		examples.deregisterDriver(driver);
		assertThat(examples.registeredLearningDriverCount()).isZero();
	}
}

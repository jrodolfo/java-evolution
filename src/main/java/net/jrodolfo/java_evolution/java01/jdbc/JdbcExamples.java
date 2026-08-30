package net.jrodolfo.java_evolution.java01.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Demonstrates the JDBC driver boundary introduced in Java 1.1.
 *
 * <p>
 * JDBC is normally used with a real database driver and a real database. This
 * example deliberately stays at the service-provider boundary: it registers a
 * tiny learning driver, lets {@link DriverManager} route a JDBC URL to that
 * driver, and exposes connection metadata. It is not a SQL engine.
 * </p>
 * <p>
 * The small provider uses {@link Proxy} as an intentional modern-JDK
 * compatibility adapter. Dynamic proxies were added after JDBC 1.1, but they
 * avoid filling this historical driver example with dozens of boilerplate
 * methods required by today's expanded {@link Connection} and
 * {@link DatabaseMetaData} interfaces.
 * </p>
 */
public class JdbcExamples {

	private static final String DRIVER_NAME = "java-evolution learning jdbc driver";
	private static final String DRIVER_VERSION = "1.1-learning";
	private static final String DATABASE_NAME = "learning-database";
	private static final String DATABASE_VERSION = "in-memory metadata only";

	/**
	 * Registers a driver that accepts URLs beginning with the supplied prefix.
	 *
	 * @param urlPrefix accepted JDBC URL prefix, such as {@code jdbc:learning:}
	 * @return the registered driver, which should be deregistered after use
	 * @throws SQLException when driver registration fails
	 */
	public Driver registerLearningDriver(String urlPrefix) throws SQLException {
		Driver driver = new LearningDriver(urlPrefix);
		DriverManager.registerDriver(driver);
		return driver;
	}

	/**
	 * Removes a previously registered driver from {@link DriverManager}.
	 *
	 * @param driver driver to deregister
	 * @throws SQLException when deregistration fails
	 */
	public void deregisterDriver(Driver driver) throws SQLException {
		DriverManager.deregisterDriver(driver);
	}

	/**
	 * Opens a connection through {@link DriverManager}.
	 *
	 * @param jdbcUrl JDBC URL
	 * @return connection selected by a matching registered driver
	 * @throws SQLException when no matching driver can open the URL
	 */
	public Connection openConnection(String jdbcUrl) throws SQLException {
		return DriverManager.getConnection(jdbcUrl);
	}

	/**
	 * Counts currently registered learning drivers.
	 *
	 * @return number of registered drivers created by this example
	 */
	public int registeredLearningDriverCount() {
		int count = 0;
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			if (drivers.nextElement() instanceof LearningDriver) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Minimal JDBC driver used to demonstrate the driver registration model.
	 */
	public static final class LearningDriver implements Driver {

		private final String urlPrefix;

		private LearningDriver(String urlPrefix) {
			this.urlPrefix = urlPrefix;
		}

		@Override
		public Connection connect(String url, Properties info) throws SQLException {
			if (!acceptsURL(url)) {
				return null;
			}
			return learningConnection(url);
		}

		@Override
		public boolean acceptsURL(String url) {
			return url != null && url.startsWith(urlPrefix);
		}

		@Override
		public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
			return new DriverPropertyInfo[0];
		}

		@Override
		public int getMajorVersion() {
			return 1;
		}

		@Override
		public int getMinorVersion() {
			return 1;
		}

		@Override
		public boolean jdbcCompliant() {
			return false;
		}

		@Override
		public Logger getParentLogger() throws SQLFeatureNotSupportedException {
			throw new SQLFeatureNotSupportedException("this learning driver does not use java.util.logging");
		}
	}

	private static Connection learningConnection(String url) {
		ConnectionHandler handler = new ConnectionHandler(url);
		return (Connection) Proxy.newProxyInstance(
				JdbcExamples.class.getClassLoader(),
				new Class<?>[] { Connection.class },
				handler);
	}

	private static DatabaseMetaData learningMetadata(String url) {
		MetadataHandler handler = new MetadataHandler(url);
		return (DatabaseMetaData) Proxy.newProxyInstance(
				JdbcExamples.class.getClassLoader(),
				new Class<?>[] { DatabaseMetaData.class },
				handler);
	}

	private static final class ConnectionHandler implements InvocationHandler {

		private final String url;
		private boolean closed;

		private ConnectionHandler(String url) {
			this.url = url;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			String name = method.getName();
			if ("close".equals(name)) {
				closed = true;
				return null;
			}
			if ("isClosed".equals(name)) {
				return Boolean.valueOf(closed);
			}
			if ("isValid".equals(name)) {
				return Boolean.valueOf(!closed);
			}
			if ("getMetaData".equals(name)) {
				return learningMetadata(url);
			}
			return handleObjectOrWrapperMethod(proxy, method, args);
		}
	}

	private static final class MetadataHandler implements InvocationHandler {

		private final String url;

		private MetadataHandler(String url) {
			this.url = url;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			String name = method.getName();
			if ("getURL".equals(name)) {
				return url;
			}
			if ("getDriverName".equals(name)) {
				return DRIVER_NAME;
			}
			if ("getDriverVersion".equals(name)) {
				return DRIVER_VERSION;
			}
			if ("getDriverMajorVersion".equals(name)) {
				return Integer.valueOf(1);
			}
			if ("getDriverMinorVersion".equals(name)) {
				return Integer.valueOf(1);
			}
			if ("getDatabaseProductName".equals(name)) {
				return DATABASE_NAME;
			}
			if ("getDatabaseProductVersion".equals(name)) {
				return DATABASE_VERSION;
			}
			return handleObjectOrWrapperMethod(proxy, method, args);
		}
	}

	private static Object handleObjectOrWrapperMethod(Object proxy, Method method, Object[] args)
			throws SQLException {
		String name = method.getName();
		if ("toString".equals(name)) {
			return "JdbcExamples proxy for " + proxy.getClass().getInterfaces()[0].getSimpleName();
		}
		if ("hashCode".equals(name)) {
			return Integer.valueOf(System.identityHashCode(proxy));
		}
		if ("equals".equals(name)) {
			return Boolean.valueOf(proxy == args[0]);
		}
		if ("unwrap".equals(name)) {
			throw new SQLFeatureNotSupportedException("unwrap is not supported by this learning proxy");
		}
		if ("isWrapperFor".equals(name)) {
			return Boolean.FALSE;
		}
		throw new SQLFeatureNotSupportedException(method.getName() + " is outside this JDBC learning example");
	}
}

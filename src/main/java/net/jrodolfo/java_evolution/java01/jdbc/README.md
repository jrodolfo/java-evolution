# JDBC

Java 1.1 introduced JDBC as a standard database-access API.

This is an executable model of the JDBC service-provider example. It demonstrates
driver registration, URL matching, driver-manager dispatch, connection metadata,
and cleanup without requiring a database server. Small local types represent the
Java 1.1 roles, avoiding modern additions to the JDBC interfaces in this lesson.

## 1. What Problem Does This Feature Solve?

Java applications needed a portable way to connect to relational databases without coding directly against each database vendor's native API.

## 2. What Did Java Introduce?

JDBC standardized concepts such as `Connection`, `Statement`, `PreparedStatement`, `ResultSet`, and driver-based connectivity.

## 3. What Does The Example Show?

The example registers a tiny learning driver with a small local driver manager.
The driver accepts a learning URL such as `jdbc:learning:demo`, returns a
learning connection, and exposes basic database metadata. This models the
JDBC 1.1 dispatch roles; it is not a SQL engine or a replacement for the
modern `java.sql.DriverManager` interfaces.

The connection is intentionally metadata-only. It is not a SQL engine and does
not pretend to execute queries. That keeps the example faithful to the JDBC
architecture while avoiding database fixtures, credentials, schemas, and
environment-specific cleanup.

The portability mental model is:

```text
application -> JDBC interfaces -> DriverManager -> vendor driver -> database
```

Application code uses the standard JDBC interfaces. `DriverManager` chooses a
registered driver that understands the requested JDBC URL, and that
vendor-specific driver translates the standard calls into the database's
protocol. The application can therefore change databases by changing the
driver and connection details instead of rewriting every database operation
against a vendor API.

## 4. Remember This

JDBC is the low-level standard contract many higher-level persistence tools
build on. A production JDBC application still needs a real driver, real
database, schema management, transactions, and resource cleanup.

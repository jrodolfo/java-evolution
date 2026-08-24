# JDBC

Java 1.1 introduced JDBC as a standard database-access API.

This is an executable JDBC service-provider example. It demonstrates driver
registration, URL matching, `DriverManager` dispatch, connection metadata, and
cleanup without requiring a database server.

## 1. What Problem Does This Feature Solve?

Java applications needed a portable way to connect to relational databases without coding directly against each database vendor's native API.

## 2. What Did Java Introduce?

JDBC standardized concepts such as `Connection`, `Statement`, `PreparedStatement`, `ResultSet`, and driver-based connectivity.

## 3. What Does The Example Show?

The example registers a tiny local `Driver` with `DriverManager`. The driver
accepts a learning URL such as `jdbc:learning:demo`, returns a standard
`Connection`, and exposes basic `DatabaseMetaData`.

The connection is intentionally metadata-only. It is not a SQL engine and does
not pretend to execute queries. That keeps the example faithful to the JDBC
architecture while avoiding database fixtures, credentials, schemas, and
environment-specific cleanup.

## 4. Remember This

JDBC is the low-level standard contract many higher-level persistence tools
build on. A production JDBC application still needs a real driver, real
database, schema management, transactions, and resource cleanup.

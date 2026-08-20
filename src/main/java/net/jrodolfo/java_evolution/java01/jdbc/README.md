# JDBC

Java 1.1 introduced JDBC as a standard database-access API.

## 1. What Problem Does This Feature Solve?

Java applications needed a portable way to connect to relational databases without coding directly against each database vendor's native API.

## 2. What Did Java Introduce?

JDBC standardized concepts such as `Connection`, `Statement`, `PreparedStatement`, `ResultSet`, and driver-based connectivity.

## 3. Why This Repository Uses Notes

A faithful JDBC example needs a driver, database, schema, credentials, and cleanup. A fake in-memory string example would not teach JDBC.

## 4. Remember This

JDBC is the low-level standard contract many higher-level persistence tools build on.

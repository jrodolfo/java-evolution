# JNDI

J2SE 1.3 included Java Naming and Directory Interface (JNDI).

## 1. What Problem Does This Feature Solve?

Applications often need to look up named resources such as directory entries, environment objects, or enterprise services.

## 2. What Did Java Introduce?

JNDI standardized naming and directory concepts around contexts, names, lookups, bindings, and providers.

## 3. What Does The Example Show?

The example configures an `InitialContext` with a small in-memory provider through the standard `Context.INITIAL_CONTEXT_FACTORY` property.

It demonstrates binding names to objects, looking objects up by name, rebinding an existing name, handling missing names, listing names, and listing full bindings.

Real deployments often used LDAP, DNS, RMI registries, application-server resources, or directory services. This repository uses an in-memory provider so the JNDI provider model can be exercised without external infrastructure.

## 4. Remember This

JNDI matters in older enterprise Java because applications often looked up resources rather than constructing them directly.

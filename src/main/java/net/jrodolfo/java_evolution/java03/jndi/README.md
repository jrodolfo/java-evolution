# JNDI

J2SE 1.3 included Java Naming and Directory Interface (JNDI).

## 1. What Problem Does This Feature Solve?

Applications often need to look up named resources such as directory entries, environment objects, or enterprise services.

## 2. What Did Java Introduce?

JNDI standardized naming and directory concepts around contexts, names, lookups, bindings, and providers.

## 3. Why This Repository Uses Notes

A meaningful JNDI example needs a naming or directory provider. Without that provider, a unit test mostly exercises setup ceremony.

## 4. Remember This

JNDI matters in older enterprise Java because applications often looked up resources rather than constructing them directly.

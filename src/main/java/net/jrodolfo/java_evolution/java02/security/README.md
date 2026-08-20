# Security Policy

Java 2 introduced a more flexible security architecture.

## 1. What Problem Does This Feature Solve?

Early Java security was strongly associated with applets and sandboxing. Java 2 generalized the model around permissions and policy files.

## 2. What Did Java Introduce?

The Java 2 security model used code sources, protection domains, permissions, policy files, and access checks.

## 3. Why This Repository Uses Notes

Security-policy behavior depends on launcher options, policy files, and runtime configuration. The Security Manager was later deprecated and disabled, so a tiny executable demo would age poorly.

## 4. Remember This

Java 2 security policy is essential migration history, especially when reading older code that assumes a Security Manager or policy file.

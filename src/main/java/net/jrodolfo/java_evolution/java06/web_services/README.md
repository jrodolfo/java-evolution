# Web-Service Support

Java 6 expanded Java SE web-service and XML support.

This is an explanatory learning module because several APIs associated with the Java 6 era, such as JAXB and JAX-WS support, were later removed from the JDK and are now normally supplied as explicit dependencies.

## 1. What Problem Does This Feature Solve?

Before Java 6, many XML and SOAP web-service APIs lived mostly in Java EE stacks or separate web-service packs.

That made simple service clients and XML binding tasks less uniform across standard Java installations.

## 2. What Did Java 6 Add?

Java 6 brought more web-service support into Java SE, including APIs around:

- web-service metadata
- XML binding
- XML web services
- streaming XML processing
- XML digital signatures

## 3. Why This Repository Uses Notes

Modern JDKs removed several Java EE and CORBA-related modules that older code once received from the JDK.

An executable Java 6 web-service example in this JDK 26 project would require explicit dependencies and would mostly teach migration packaging rather than the original Java 6 platform change.

## 4. Remember This

Java 6 made web-service support feel more built in. Modern Java moved that model back toward explicit dependencies.

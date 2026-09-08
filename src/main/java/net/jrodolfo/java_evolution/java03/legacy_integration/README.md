# Legacy Integration

J2SE 1.3 belongs to an era where Java was being positioned as both a desktop
and distributed enterprise platform. Applets and browser plugins brought Java
into client environments, while RMI/IIOP and CORBA addressed communication and
interoperability between remote object systems. Enterprise integration APIs
were significant because applications were increasingly expected to connect
business systems rather than run in isolation.

## 1. What Problem Does This Feature Area Solve?

Java was expanding from applets and standalone applications into enterprise and distributed systems.

## 2. What Should A Learner Remember?

Some APIs from this era were later removed from the JDK or moved to explicit dependencies. They are important migration context, not daily-use modern Java.

## 3. Why This Repository Uses Notes

Recreating CORBA, plugin, or multi-process enterprise setups would distract from the learning goal and would not be portable on JDK 26.

## 4. Remember This

When maintaining older Java systems, watch for assumptions about bundled enterprise APIs that modern JDKs no longer provide.

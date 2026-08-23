# Preferences API

J2SE 1.4 added `java.util.prefs`.

## 1. What Problem Does This Feature Solve?

Applications sometimes need to store small user or system configuration values without choosing a full file format or database.

## 2. What Did Java Introduce?

The Preferences API provides hierarchical nodes and key/value storage for user and system preferences.

## 3. Why This Repository Uses Notes

Preferences are executable in normal applications, but they use platform-specific backing stores such as files, registries, or operating-system preference services. Even user preferences can fail to synchronize in sandboxed, CI, or restricted desktop environments.

That behavior is the important lesson: the API looks like a simple key/value store, but reads and writes depend on a backing store outside the Java object itself.

This repository keeps Preferences as notes so the Maven build does not write to user or system preference storage.

## 4. Remember This

Preferences are for small configuration values, not application data, secrets, or large documents.

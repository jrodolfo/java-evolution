# Preferences API

J2SE 1.4 added `java.util.prefs`.

## 1. What Problem Does This Feature Solve?

Applications sometimes need to store small user or system configuration values without choosing a full file format or database.

## 2. What Did Java Introduce?

The Preferences API provides hierarchical nodes and key/value storage for user and system preferences.

## 3. Why This Repository Uses Notes

Preferences can write to platform-specific backing stores such as files, registries, or system locations. That makes tests environment-sensitive.

## 4. Remember This

Preferences are for small configuration values, not application data, secrets, or large documents.

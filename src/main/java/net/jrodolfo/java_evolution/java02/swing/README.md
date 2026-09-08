# Swing

Java 2 made Swing part of the standard platform.

## 1. What Problem Does This Feature Solve?

AWT provided GUI components, but many AWT components used a **native peer**: a
platform-specific operating-system widget created to represent the Java
component. That could make behavior and appearance depend on the host system.
Swing offered a richer set of **lightweight components**, which draw themselves
in Java instead of requiring a separate native widget for each component. This
improves portability and gives Swing's look-and-feel system more control over
appearance.

## 2. What Did Java Introduce?

Swing provided components such as `JFrame`, `JPanel`, `JButton`, `JTable`, and the pluggable look-and-feel model.

Swing also made model/action/event concepts central to desktop UI code. UI
updates and event handling are coordinated through the **Event Dispatch Thread
(EDT)**, the thread responsible for processing Swing events and changing Swing
components. Keeping that work on one event thread avoids competing updates to
the same UI state and makes event ordering predictable.

The main concepts are:

- models hold component data
- actions describe reusable user commands
- the Event Dispatch Thread (EDT) coordinates UI work

## 3. What Does The Example Show?

`SwingExamples` is a headless executable example. It demonstrates:

- `DefaultListModel` for ordered list data
- `DefaultTableModel` for table data
- `Action` and `JButton#doClick()` for event-driven commands
- `SwingUtilities.invokeAndWait(...)` for running code on the EDT

The example does not show visible windows, layout managers, painting, platform Look and Feel, or real mouse/keyboard interaction. Those parts of Swing are visual and environment-dependent.

## 4. Remember This

Swing matters historically as Java's standard desktop UI toolkit, even when server-side Java developers rarely touch it today.

# Swing

Java 2 made Swing part of the standard platform.

## 1. What Problem Does This Feature Solve?

AWT provided GUI components, but they were closely tied to native peers. Swing offered a richer, more portable set of lightweight components.

## 2. What Did Java Introduce?

Swing provided components such as `JFrame`, `JPanel`, `JButton`, `JTable`, and the pluggable look-and-feel model.

Swing also made model/action/event concepts central to desktop UI code:

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

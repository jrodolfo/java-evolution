# Java 2D

Java 2 added Java 2D graphics APIs.

## 1. What Problem Does This Feature Solve?

Applications needed richer drawing, text, image, shape, color, and rendering control than the original AWT graphics model provided.

## 2. What Did Java Introduce?

Java 2D added APIs around `Graphics2D`, shapes, strokes, paints, transforms, images, and rendering hints.

## 3. What Does The Example Show?

`Java2DExamples` renders into `BufferedImage` instances instead of opening GUI windows. This keeps the example portable in Maven and CI while still showing real rendering behavior:

- creating an in-memory image
- drawing a filled rectangle with `Graphics2D`
- drawing a stroked `Shape`
- reading pixels to verify that rendering changed the image

The example avoids platform-dependent font layout and visible windows. Those topics matter in desktop applications, but they would make this refresher less deterministic.

## 4. Remember This

Java 2D is part of Java's desktop and graphics history, not an everyday backend feature.

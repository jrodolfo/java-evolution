package net.jrodolfo.java_evolution.java02.java2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * Demonstrates Java 2D rendering to an in-memory image.
 */
public class Java2DExamples {

	static {
		System.setProperty("java.awt.headless", "true");
	}

	/**
	 * Creates a blank image that can be drawn without opening a GUI window.
	 *
	 * @param width image width
	 * @param height image height
	 * @return blank image
	 */
	public BufferedImage blankImage(int width, int height) {
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	/**
	 * Draws a filled rectangle into an in-memory image.
	 *
	 * @return image with a red rectangle
	 */
	public BufferedImage drawFilledRectangle() {
		BufferedImage image = blankImage(16, 16);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.RED);
		graphics.fillRect(4, 4, 8, 8);
		graphics.dispose();
		return image;
	}

	/**
	 * Draws an outlined ellipse using Java 2D shape and stroke APIs.
	 *
	 * @return image with a blue ellipse outline
	 */
	public BufferedImage drawOutlinedEllipse() {
		BufferedImage image = blankImage(20, 20);
		Graphics2D graphics = image.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		graphics.setColor(Color.BLUE);
		graphics.setStroke(new BasicStroke(2.0f));
		graphics.draw(new Ellipse2D.Double(4.0, 4.0, 12.0, 12.0));
		graphics.dispose();
		return image;
	}

	/**
	 * Reads the color written to a pixel.
	 *
	 * @param image image to inspect
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return pixel color including alpha
	 */
	public Color pixelColor(BufferedImage image, int x, int y) {
		return new Color(image.getRGB(x, y), true);
	}
}

package net.jrodolfo.java_evolution.java02.java2d;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

class Java2DExamplesTest {

	private final Java2DExamples examples = new Java2DExamples();

	@Test
	void bufferedImageCanBeCreatedWithoutGuiWindow() {
		BufferedImage image = examples.blankImage(12, 8);

		assertThat(image.getWidth())
				.as("Java 2D can render into an in-memory image in headless tests")
				.isEqualTo(12);
		assertThat(image.getHeight()).isEqualTo(8);
	}

	@Test
	void graphics2DCanFillShapesWithColor() {
		BufferedImage image = examples.drawFilledRectangle();

		assertThat(examples.pixelColor(image, 6, 6))
				.as("A filled Java 2D shape should write the selected color into covered pixels")
				.isEqualTo(new Color(255, 0, 0, 255));
		assertThat(examples.pixelColor(image, 1, 1))
				.as("Pixels outside the filled shape should remain transparent")
				.isEqualTo(new Color(0, 0, 0, 0));
	}

	@Test
	void graphics2DCanDrawStrokedShapes() {
		BufferedImage image = examples.drawOutlinedEllipse();

		assertThat(examples.pixelColor(image, 10, 4))
				.as("Drawing an outlined shape should color pixels along the stroke")
				.isEqualTo(new Color(0, 0, 255, 255));
		assertThat(examples.pixelColor(image, 10, 10))
				.as("The center of an outlined shape should remain unfilled")
				.isEqualTo(new Color(0, 0, 0, 0));
	}
}

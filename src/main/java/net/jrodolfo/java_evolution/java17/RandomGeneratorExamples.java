package net.jrodolfo.java_evolution.java17;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Demonstrates the enhanced pseudo-random number generator API introduced in
 * Java 17.
 *
 * <p>
 * Before Java 17, random-number APIs were less unified and algorithm selection
 * was not as discoverable through one common abstraction.
 * </p>
 *
 * <p>
 * Java 17 added the {@link RandomGenerator} interface and
 * {@link RandomGeneratorFactory} for selecting named algorithms. This gives
 * code a standard way to choose and discover pseudo-random number generators.
 * </p>
 */
public class RandomGeneratorExamples {

	/**
	 * Creates a generator by algorithm name and produces a bounded value.
	 *
	 * @param algorithm the random generator algorithm name
	 * @param bound exclusive upper bound
	 * @return a generated value between zero inclusive and bound exclusive
	 */
	public int boundedRandomValue(String algorithm, int bound) {
		RandomGenerator generator = RandomGeneratorFactory.of(algorithm).create(42L);
		return generator.nextInt(bound);
	}

	/**
	 * Checks whether a named random generator algorithm exists.
	 *
	 * @param algorithm the algorithm name
	 * @return whether the algorithm is available in this JDK
	 */
	public boolean algorithmExists(String algorithm) {
		return RandomGeneratorFactory.all()
				.anyMatch(factory -> factory.name().equals(algorithm));
	}
}

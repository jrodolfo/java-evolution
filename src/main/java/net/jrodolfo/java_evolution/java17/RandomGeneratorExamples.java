package net.jrodolfo.java_evolution.java17;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Demonstrates the enhanced pseudo-random number generator API introduced in
 * Java 17.
 *
 * <p>
 * Java 17 added the {@link RandomGenerator} interface and factories for
 * selecting named algorithms.
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

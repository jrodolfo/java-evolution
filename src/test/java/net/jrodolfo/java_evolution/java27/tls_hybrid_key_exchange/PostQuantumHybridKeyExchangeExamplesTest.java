package net.jrodolfo.java_evolution.java27.tls_hybrid_key_exchange;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;

class PostQuantumHybridKeyExchangeExamplesTest {

	private final PostQuantumHybridKeyExchangeExamples examples =
			new PostQuantumHybridKeyExchangeExamples();

	@Test
	void defaultTlsProviderExposesHybridGroup() throws NoSuchAlgorithmException {
		assertThat(examples.supportsHybridGroup()).isTrue();
		assertThat(examples.defaultNamedGroups()).contains(PostQuantumHybridKeyExchangeExamples.HYBRID_GROUP);
	}

	@Test
	void applicationCanSelectHybridGroupWithoutConnecting() throws NoSuchAlgorithmException {
		assertThat(examples.configureHybridGroup())
				.containsExactly(PostQuantumHybridKeyExchangeExamples.HYBRID_GROUP);
		assertThat(examples.boundary()).contains("without requiring a live network");
	}
}

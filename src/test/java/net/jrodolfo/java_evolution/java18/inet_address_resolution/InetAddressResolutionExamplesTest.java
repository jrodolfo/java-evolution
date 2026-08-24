package net.jrodolfo.java_evolution.java18.inet_address_resolution;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class InetAddressResolutionExamplesTest {

	private final InetAddressResolutionExamples examples = new InetAddressResolutionExamples();

	@Test
	void childJvmDiscoversProviderAndResolvesFakeHost(@TempDir Path workspace) throws Exception {
		InetAddressResolutionExamples.CommandResult result = examples.runResolverProbe(workspace);

		assertThat(result.exitCode())
				.as("the generated provider and probe should compile and run in an isolated child JVM")
				.isZero();
		assertThat(result.output())
				.as("the custom resolver should answer a fake host without real DNS")
				.contains("host=demo.internal")
				.contains("address=10.0.0.42")
				.contains("canonical=demo.internal");
	}

	@Test
	void childJvmRejectsUnknownNamesDeterministically(@TempDir Path workspace) throws Exception {
		InetAddressResolutionExamples.CommandResult result = examples.runResolverProbe(workspace);

		assertThat(result.output())
				.as("unknown names should fail through the provider rather than external DNS")
				.contains("unknown=provider rejected outside.internal");
	}

	@Test
	void sourceShowsTheRealServiceProviderInterfaceAndServiceFile() {
		assertThat(examples.providerSource())
				.as("the provider source should implement the Java 18 SPI")
				.contains("extends InetAddressResolverProvider")
				.contains("InetAddressResolver")
				.contains("lookupByName")
				.contains("lookupByAddress");
		assertThat(examples.serviceProviderFileName())
				.as("service discovery should use the standard META-INF/services path")
				.isEqualTo("META-INF/services/java.net.spi.InetAddressResolverProvider");
	}

	@Test
	void exampleExplainsTheUseCaseAndIsolationCaveat() {
		assertThat(examples.extensionPoint())
				.as("the example should name the extension point")
				.contains("InetAddressResolverProvider")
				.contains("service-provider");
		assertThat(examples.useCase())
				.as("the feature is for specialized networking environments")
				.contains("custom DNS")
				.contains("advanced networking");
		assertThat(examples.processWideCaveat())
				.as("the repository should explain why the provider runs in a child JVM")
				.contains("whole process")
				.contains("child JVM");
	}
}

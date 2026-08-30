package net.jrodolfo.java_evolution.java16.unix_domain_socket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.net.SocketException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class UnixDomainSocketChannelExamplesTest {

	private final UnixDomainSocketChannelExamples examples = new UnixDomainSocketChannelExamples();

	@Test
	void protocolFamilyIsUnix() {
		assertThat(examples.protocolFamilyName())
				.as("Java 16 exposes Unix-domain socket channels through StandardProtocolFamily.UNIX")
				.isEqualTo("UNIX");
	}

	@Test
	void socketAddressUsesFileSystemPath() throws IOException {
		Path directory = Files.createTempDirectory("java-evolution-uds-address-");
		Path socketPath = directory.resolve("app.sock");
		try {
			assertThat(examples.socketAddress(socketPath).getPath())
					.as("UnixDomainSocketAddress should represent a local file-system path")
					.isEqualTo(socketPath);
		}
		finally {
			Files.deleteIfExists(socketPath);
			Files.deleteIfExists(directory);
		}
	}

	@Test
	void clientAndServerExchangeMessageThroughLocalSocket() throws Exception {
		Path directory = createSocketDirectory();
		Path socketPath = directory.resolve("app.sock");
		try {
			assertThat(examples.exchangeMessage(socketPath, "ping"))
					.as("A Unix-domain socket channel should support same-machine message exchange by path")
					.isEqualTo("ping");
		}
		catch (UnsupportedOperationException | UnsupportedAddressTypeException | AccessDeniedException exception) {
			assumeTrue(false, "Unix-domain socket channels are not available in this environment: " + exception);
		}
		catch (SocketException exception) {
			assumeTrue(false, "Unix-domain socket binding is blocked in this environment: " + exception.getMessage());
		}
		finally {
			Files.deleteIfExists(socketPath);
			Files.deleteIfExists(directory);
		}
	}

	private Path createSocketDirectory() throws IOException {
		Path shortTemporaryRoot = Path.of("/tmp");
		if (isMacOs() && Files.isDirectory(shortTemporaryRoot) && Files.isWritable(shortTemporaryRoot)) {
			return Files.createTempDirectory(shortTemporaryRoot, "uds-");
		}
		return Files.createTempDirectory("java-evolution-uds-exchange-");
	}

	private boolean isMacOs() {
		return System.getProperty("os.name", "").toLowerCase(Locale.ROOT).contains("mac");
	}
}

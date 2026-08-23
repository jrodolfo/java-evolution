package net.jrodolfo.java_evolution.java16.unix_domain_socket;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Demonstrates Unix-domain socket channel support, introduced in Java 16.
 */
public class UnixDomainSocketChannelExamples {

	/**
	 * Names the protocol family used for Unix-domain socket channels.
	 *
	 * @return protocol family name
	 */
	public String protocolFamilyName() {
		return StandardProtocolFamily.UNIX.name();
	}

	/**
	 * Creates a Unix-domain socket address from a local file-system path.
	 *
	 * @param path local socket path
	 * @return Unix-domain socket address
	 */
	public UnixDomainSocketAddress socketAddress(Path path) {
		return UnixDomainSocketAddress.of(path);
	}

	/**
	 * Exchanges one short message through a Unix-domain socket.
	 *
	 * @param socketPath local socket path
	 * @param message message sent by the client
	 * @return message received by the server
	 * @throws IOException when the socket cannot be opened, bound, connected, read, or written
	 * @throws InterruptedException when waiting for the client thread is interrupted
	 */
	public String exchangeMessage(Path socketPath, String message) throws IOException, InterruptedException {
		UnixDomainSocketAddress address = socketAddress(socketPath);
		String[] received = new String[1];

		try (ServerSocketChannel server = ServerSocketChannel.open(StandardProtocolFamily.UNIX)) {
			server.bind(address);

			Thread client = new Thread(() -> sendMessage(address, message));
			client.start();

			try (SocketChannel accepted = server.accept()) {
				ByteBuffer buffer = ByteBuffer.allocate(128);
				accepted.read(buffer);
				buffer.flip();
				received[0] = StandardCharsets.UTF_8.decode(buffer).toString();
			}

			client.join();
		}

		return received[0];
	}

	private void sendMessage(UnixDomainSocketAddress address, String message) {
		try (SocketChannel channel = SocketChannel.open(StandardProtocolFamily.UNIX)) {
			channel.connect(address);
			channel.write(StandardCharsets.UTF_8.encode(message));
		}
		catch (IOException exception) {
			throw new UnixDomainSocketExchangeException(exception);
		}
	}

	static class UnixDomainSocketExchangeException extends RuntimeException {
		UnixDomainSocketExchangeException(IOException cause) {
			super(cause);
		}
	}
}

package net.jrodolfo.java_evolution.java01.rmi;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Demonstrates Remote Method Invocation, introduced in Java 1.1.
 *
 * <p>
 * Early RMI examples commonly used generated stubs. Modern JDKs use dynamic
 * stubs, so this example keeps the historically important API shape without
 * requiring obsolete {@code rmic} generated source.
 * </p>
 */
public class RmiExamples {

	private static final String SERVICE_NAME = "greeting";

	/**
	 * Starts a local RMI registry, constructs an exported remote object, and
	 * binds the service.
	 *
	 * @return local RMI endpoint that must be closed after use
	 * @throws IOException when local socket binding or object export fails
	 */
	public RmiGreetingEndpoint startGreetingService() throws IOException {
		int port = freeLoopbackPort();
		Registry registry = LocateRegistry.createRegistry(port);
		GreetingRemoteObject remoteObject = new GreetingRemoteObject();
		registry.rebind(SERVICE_NAME, remoteObject);
		return new RmiGreetingEndpoint(port, registry, remoteObject);
	}

	private int freeLoopbackPort() throws IOException {
		ServerSocket socket = new ServerSocket(0, 1, InetAddress.getByName("127.0.0.1"));
		try {
			return socket.getLocalPort();
		}
		finally {
			socket.close();
		}
	}

	/**
	 * Remote contract. RMI methods declare {@link RemoteException} because network
	 * calls can fail even when the target Java method is simple.
	 */
	public interface GreetingService extends Remote {

		/**
		 * Greets the supplied request.
		 *
		 * @param request serializable request copied through the RMI call
		 * @return greeting message
		 * @throws RemoteException when the remote invocation fails
		 */
		String greet(GreetingRequest request) throws RemoteException;
	}

	/**
	 * Serializable argument passed to the remote service.
	 */
	public static final class GreetingRequest implements Serializable {

		private static final long serialVersionUID = 1L;

		private final String name;

		public GreetingRequest(String name) {
			this.name = name;
		}

		/**
		 * @return requested name
		 */
		public String name() {
			return name;
		}
	}

	/**
	 * Local owner for the registry and exported remote object.
	 */
	public static final class RmiGreetingEndpoint {

		private final int port;
		private final Registry registry;
		private final GreetingRemoteObject remoteObject;

		private RmiGreetingEndpoint(int port, Registry registry, GreetingRemoteObject remoteObject) {
			this.port = port;
			this.registry = registry;
			this.remoteObject = remoteObject;
		}

		/**
		 * Looks up the remote service through the registry.
		 *
		 * @return client-facing RMI stub
		 * @throws RemoteException when registry communication fails
		 * @throws NotBoundException when the service name is not bound
		 */
		public GreetingService lookupGreetingService() throws RemoteException, NotBoundException {
			Registry clientRegistry = LocateRegistry.getRegistry("127.0.0.1", port);
			return (GreetingService) clientRegistry.lookup(SERVICE_NAME);
		}

		/**
		 * Returns the last request received by the exported object.
		 *
		 * @return last request received on the server side
		 */
		public GreetingRequest lastReceivedRequest() {
			return remoteObject.lastReceivedRequest();
		}

		Registry registryForTestCleanup() {
			return registry;
		}

		Remote remoteObjectForTestCleanup() {
			return remoteObject;
		}

		/**
		 * Unbinds the RMI service name. The historical example has no modern
		 * unexport operation; tests may release the exported resources separately.
		 */
		public void close() throws RemoteException, NotBoundException {
			registry.unbind(SERVICE_NAME);
		}
	}

	private static final class GreetingRemoteObject extends UnicastRemoteObject implements GreetingService {

		private GreetingRemoteObject() throws RemoteException {
			super();
		}

		private GreetingRequest lastReceivedRequest;

		public String greet(GreetingRequest request) throws RemoteException {
			lastReceivedRequest = request;
			return "hello, " + request.name();
		}

		private GreetingRequest lastReceivedRequest() {
			return lastReceivedRequest;
		}
	}
}

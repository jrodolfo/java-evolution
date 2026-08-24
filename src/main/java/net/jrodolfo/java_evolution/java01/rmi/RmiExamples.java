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
	 * Starts a local RMI registry, exports a remote object, and binds the service.
	 *
	 * @return local RMI endpoint that must be closed after use
	 * @throws IOException when local socket binding or object export fails
	 */
	public RmiGreetingEndpoint startGreetingService() throws IOException {
		int port = freeLoopbackPort();
		Registry registry = LocateRegistry.createRegistry(port);
		GreetingRemoteObject remoteObject = new GreetingRemoteObject();
		try {
			GreetingService stub = (GreetingService) UnicastRemoteObject.exportObject(remoteObject, 0);
			registry.rebind(SERVICE_NAME, stub);
			return new RmiGreetingEndpoint(port, registry, remoteObject);
		}
		catch (RemoteException exception) {
			try {
				UnicastRemoteObject.unexportObject(remoteObject, true);
			}
			catch (RemoteException ignored) {
				// The object may not have been exported if exportObject failed.
			}
			UnicastRemoteObject.unexportObject(registry, true);
			throw exception;
		}
	}

	private int freeLoopbackPort() throws IOException {
		ServerSocket socket = new ServerSocket(0, 1, InetAddress.getLoopbackAddress());
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
	public static final class RmiGreetingEndpoint implements AutoCloseable {

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
			Registry clientRegistry = LocateRegistry.getRegistry(InetAddress.getLoopbackAddress().getHostAddress(), port);
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

		/**
		 * Unbinds and unexports the RMI resources.
		 */
		public void close() throws RemoteException, NotBoundException {
			try {
				registry.unbind(SERVICE_NAME);
			}
			finally {
				UnicastRemoteObject.unexportObject(remoteObject, true);
				UnicastRemoteObject.unexportObject(registry, true);
			}
		}
	}

	private static final class GreetingRemoteObject implements GreetingService {

		private GreetingRequest lastReceivedRequest;

		@Override
		public String greet(GreetingRequest request) throws RemoteException {
			lastReceivedRequest = request;
			return "hello, " + request.name();
		}

		private GreetingRequest lastReceivedRequest() {
			return lastReceivedRequest;
		}
	}
}

package net.jrodolfo.java_evolution.java02.security;

import java.net.MalformedURLException;
import java.net.SocketPermission;
import java.net.URL;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.ProtectionDomain;

/**
 * Demonstrates the Java 2 security-policy model with ordinary permission
 * objects.
 *
 * <p>
 * Java 2 generalized security around code sources, protection domains,
 * permissions, and policy grants. This example models those concepts directly
 * without installing a {@code SecurityManager} or loading a policy file, because
 * Security Manager based enforcement is deprecated and disabled in modern Java.
 * </p>
 */
@SuppressWarnings("removal")
public class SecurityPolicyExamples {

	private static final String CODE_SOURCE_LOCATION = "file:/trusted/app/";
	private static final String SERVICE_ENDPOINT = "api.example.test:443";

	/**
	 * Creates a synthetic code source representing where code came from.
	 *
	 * @return a code source for a trusted application location
	 * @throws MalformedURLException if the synthetic URI cannot be converted to a URL
	 */
	@SuppressWarnings("deprecation")
	public CodeSource trustedCodeSource() throws MalformedURLException {
		return new CodeSource(new URL(CODE_SOURCE_LOCATION), (java.security.cert.Certificate[]) null);
	}

	/**
	 * Creates a protection domain with one explicit permission grant.
	 *
	 * @return a protection domain that can connect to one service endpoint
	 * @throws MalformedURLException if the synthetic code source cannot be created
	 */
	public ProtectionDomain domainWithConnectPermission() throws MalformedURLException {
		Permissions permissions = new Permissions();
		permissions.add(connectPermission());
		permissions.setReadOnly();
		return new ProtectionDomain(trustedCodeSource(), permissions);
	}

	/**
	 * Checks whether the domain implies the granted connect permission.
	 *
	 * @return {@code true} because the permission collection grants connect access
	 * @throws MalformedURLException if the synthetic code source cannot be created
	 */
	public boolean canConnectToApi() throws MalformedURLException {
		return domainWithConnectPermission().implies(new SocketPermission(SERVICE_ENDPOINT, "connect"));
	}

	/**
	 * Checks whether the same domain implies a different, ungranted action.
	 *
	 * @return {@code false} because listen access was not granted
	 * @throws MalformedURLException if the synthetic code source cannot be created
	 */
	public boolean cannotListenOnApiPort() throws MalformedURLException {
		return domainWithConnectPermission().implies(new SocketPermission(SERVICE_ENDPOINT, "listen"));
	}

	/**
	 * Exposes the static permission collection used by the protection domain.
	 *
	 * @return the static permission collection
	 * @throws MalformedURLException if the synthetic code source cannot be created
	 */
	public PermissionCollection grantedPermissions() throws MalformedURLException {
		return domainWithConnectPermission().getPermissions();
	}

	/**
	 * Explains the core Java 2 security model in one sentence.
	 *
	 * @return a short model summary
	 */
	public String modelSummary() {
		return "Java 2 security policy connected code sources, protection domains, permissions, and access checks";
	}

	/**
	 * Explains the modern boundary of this example.
	 *
	 * @return a short boundary explanation
	 */
	public String modernBoundary() {
		return "this example demonstrates permission reasoning without installing the deprecated Security Manager";
	}

	private SocketPermission connectPermission() {
		return new SocketPermission(SERVICE_ENDPOINT, "connect");
	}
}

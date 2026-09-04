package net.jrodolfo.java_evolution.java27.tls_hybrid_key_exchange;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

/** Demonstrates Java 27's post-quantum hybrid TLS named-group configuration. */
public class PostQuantumHybridKeyExchangeExamples {

	/** The first hybrid group exposed by the Java 27 default TLS provider. */
	public static final String HYBRID_GROUP = "X25519MLKEM768";

	/** Returns the named groups advertised by the default TLS context. */
	public String[] defaultNamedGroups() throws NoSuchAlgorithmException {
		return SSLContext.getDefault().getDefaultSSLParameters().getNamedGroups();
	}

	/** Configures a TLS parameter object for the hybrid group. */
	public String[] configureHybridGroup() throws NoSuchAlgorithmException {
		SSLParameters parameters = SSLContext.getDefault().getDefaultSSLParameters();
		parameters.setNamedGroups(new String[] { HYBRID_GROUP });
		return parameters.getNamedGroups();
	}

	/** Explains the deliberately local scope of this example. */
	public String boundary() {
		return "the example verifies provider capability and TLS configuration without requiring a live network handshake";
	}

	/** Returns whether the default provider exposes the named hybrid group. */
	public boolean supportsHybridGroup() throws NoSuchAlgorithmException {
		return Arrays.asList(defaultNamedGroups()).contains(HYBRID_GROUP);
	}
}

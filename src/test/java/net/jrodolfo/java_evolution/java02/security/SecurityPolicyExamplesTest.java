package net.jrodolfo.java_evolution.java02.security;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.SocketPermission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;

import org.junit.jupiter.api.Test;

@SuppressWarnings("removal")
class SecurityPolicyExamplesTest {

	private final SecurityPolicyExamples examples = new SecurityPolicyExamples();

	@Test
	void codeSourceIdentifiesWhereCodeCameFrom() throws Exception {
		assertThat(examples.trustedCodeSource().getLocation().toString())
				.as("A CodeSource models the origin used by Java 2 security-policy decisions")
				.isEqualTo("file:/trusted/app/");
	}

	@Test
	void protectionDomainCombinesCodeSourceAndPermissions() throws Exception {
		ProtectionDomain domain = examples.domainWithConnectPermission();

		assertThat(domain.getCodeSource().getLocation().toString())
				.as("A ProtectionDomain keeps the code origin associated with its permissions")
				.isEqualTo("file:/trusted/app/");
		assertThat(domain.staticPermissionsOnly())
				.as("This example uses an explicit in-memory permission collection instead of dynamic policy lookup")
				.isTrue();
	}

	@Test
	void permissionChecksAreActionSpecific() throws Exception {
		assertThat(examples.canConnectToApi())
				.as("The protection domain should imply the explicitly granted connect action")
				.isTrue();
		assertThat(examples.cannotListenOnApiPort())
				.as("A connect grant should not imply an unrelated listen action")
				.isFalse();
	}

	@Test
	void grantedPermissionsAreReadOnlyAfterDomainConstruction() throws Exception {
		PermissionCollection permissions = examples.grantedPermissions();

		assertThat(permissions.isReadOnly())
				.as("The example freezes the permission collection after declaring its grant")
				.isTrue();
		assertThat(permissions.implies(new SocketPermission("api.example.test:443", "connect")))
				.as("The permission collection itself should answer implication questions")
				.isTrue();
	}

	@Test
	void exampleExplainsHistoricalModelAndModernBoundary() {
		assertThat(examples.modelSummary())
				.as("The example should name the Java 2 security-policy concepts learners need")
				.contains("code sources")
				.contains("protection domains")
				.contains("permissions")
				.contains("access checks");
		assertThat(examples.modernBoundary())
				.as("The example should not teach Security Manager installation as modern practice")
				.contains("permission reasoning")
				.contains("deprecated Security Manager");
	}
}

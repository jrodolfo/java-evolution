package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Java24NotesTest {

	@Test
	void classFileApiNotesIdentifyStandardBytecodeTooling() {
		assertThat(new ClassFileApiNotes().purpose())
				.as("The final Class-File API should be framed as standard class-file tooling")
				.contains("class files");
	}

	@Test
	void securityManagerDisabledNotesPointToDeploymentLevelIsolation() {
		assertThat(new SecurityManagerDisabledNotes().impact())
				.as("The test should document that Security Manager is no longer an application sandbox")
				.contains("sandbox");
	}

	@Test
	void virtualThreadSynchronizationNotesExplainExistingCodeScalability() {
		assertThat(new VirtualThreadSynchronizationNotes().benefit())
				.as("The feature matters because synchronized legacy code should scale better on virtual threads")
				.contains("scalability");
	}

	@Test
	void cryptoAndKdfNotesSeparateFinalSupportFromPreviewSupport() {
		assertThat(new QuantumResistantCryptoNotes().algorithms())
				.as("Java 24 added post-quantum algorithm support")
				.contains("ML-KEM")
				.contains("ML-DSA");
		assertThat(new KeyDerivationFunctionPreviewNotes().status())
				.as("KDF should remain documented as preview in Java 24 and final in Java 25")
				.contains("final in Java 25");
	}

	@Test
	void aotNotesExplainStartupOrientedRuntimeWork() {
		assertThat(new AotClassLoadingNotes().purpose())
				.as("AOT class loading is a startup/runtime topic, not a language syntax feature")
				.contains("startup");
	}

	@Test
	void languagePreviewsLinkJava24WorkToLaterStatus() {
		assertThat(new PrimitivePatternsSecondPreviewNotes().status())
				.as("Primitive patterns should still be preview after Java 24")
				.contains("third preview");
		assertThat(new FlexibleConstructorBodiesThirdPreviewNotes().status())
				.as("Flexible constructor bodies should point to Java 25 finalization")
				.contains("final in Java 25");
		assertThat(new ModuleImportDeclarationsSecondPreviewNotes().status())
				.as("Module imports should point to Java 25 finalization")
				.contains("final in Java 25");
	}

	@Test
	void concurrencyPreviewsDocumentWhichIdeasAreStillEvolving() {
		assertThat(new ScopedValuesFourthPreviewNotes().status())
				.as("Scoped values should point to Java 25 finalization")
				.contains("final in Java 25");
		assertThat(new StructuredConcurrencyFourthPreviewNotes().status())
				.as("Structured concurrency should stay marked as preview in Java 25")
				.contains("fifth preview");
	}
}

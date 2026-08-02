package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Java24NotesTest {

	@Test
	void notesDescribeJava24Features() {
		assertThat(new ClassFileApiNotes().purpose()).contains("class files");
		assertThat(new SecurityManagerDisabledNotes().impact()).contains("sandbox");
		assertThat(new VirtualThreadSynchronizationNotes().benefit()).contains("scalability");
		assertThat(new QuantumResistantCryptoNotes().algorithms()).contains("ML-KEM").contains("ML-DSA");
		assertThat(new AotClassLoadingNotes().purpose()).contains("startup");
		assertThat(new KeyDerivationFunctionPreviewNotes().status()).contains("final in Java 25");
		assertThat(new PrimitivePatternsSecondPreviewNotes().status()).contains("third preview");
		assertThat(new FlexibleConstructorBodiesThirdPreviewNotes().status()).contains("final in Java 25");
		assertThat(new ModuleImportDeclarationsSecondPreviewNotes().status()).contains("final in Java 25");
		assertThat(new ScopedValuesFourthPreviewNotes().status()).contains("final in Java 25");
		assertThat(new StructuredConcurrencyFourthPreviewNotes().status()).contains("fifth preview");
	}
}

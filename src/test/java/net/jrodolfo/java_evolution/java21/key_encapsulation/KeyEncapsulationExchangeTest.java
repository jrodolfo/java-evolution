package net.jrodolfo.java_evolution.java21.key_encapsulation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

import javax.crypto.DecapsulateException;
import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

class KeyEncapsulationExchangeTest {

	@Test
	void exchangeShowsSenderAndReceiverReachTheSameSecret() throws Exception {
		var exchange = new KeyEncapsulationExchange();

		var result = exchange.exchange();

		assertThat(result.secretsMatch())
				.as("the receiver should recover the same shared secret the sender created")
				.isTrue();
		assertThat(result.senderSecretBytes())
				.as("the sender-side secret is real key material")
				.isNotEmpty();
		assertThat(result.receiverSecretBytes())
				.as("the receiver-side secret is real key material")
				.isNotEmpty();
		assertThat(result.encapsulation())
				.as("the encapsulation message is what travels between parties")
				.isNotEmpty();
	}

	@Test
	void receiverPublishesOnlyThePublicKeyForEncapsulation() throws Exception {
		var receiver = KemReceiver.create();

		var sender = KemSender.forReceiver(receiver.publicKey());
		var encapsulated = sender.encapsulate();
		var receiverSecret = receiver.decapsulate(encapsulated.encapsulation());

		assertThat(KemReceiver.KEY_PAIR_ALGORITHM)
				.as("the example asks the key pair generator for X25519")
				.isEqualTo("X25519");
		assertThat(receiver.publicKey().getAlgorithm())
				.as("the generated public key is represented by the broader XDH key family")
				.isEqualTo("XDH");
		assertThat(sender.providerName())
				.as("DHKEM is implemented by a Java security provider")
				.isNotBlank();
		assertThat(Arrays.equals(encapsulated.secretBytes(), receiverSecret.getEncoded()))
				.as("the private key lets the receiver recover the sender's shared secret")
				.isTrue();
	}

	@Test
	void malformedEncapsulationCannotBeDecapsulated() throws Exception {
		var receiver = KemReceiver.create();

		assertThatThrownBy(() -> receiver.decapsulate(new byte[] { 1, 2, 3 }))
				.as("DHKEM should reject encapsulation messages with an invalid size")
				.isInstanceOf(DecapsulateException.class);
	}

	@Test
	void secretBytesRejectsKeysWithoutAnEncodedRepresentation() {
		SecretKey unencodableKey = new SecretKey() {
			@Override
			public String getAlgorithm() {
				return "test";
			}

			@Override
			public String getFormat() {
				return null;
			}

			@Override
			public byte[] getEncoded() {
				return null;
			}
		};

		var encapsulatedSecret = new EncapsulatedSecret(unencodableKey, new byte[] { 1 });

		assertThatThrownBy(encapsulatedSecret::secretBytes)
				.as("The example needs encoded bytes when exposing key material for comparison")
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("the shared secret does not support encoding");
	}
}

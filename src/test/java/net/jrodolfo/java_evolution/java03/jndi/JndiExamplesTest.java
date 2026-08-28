package net.jrodolfo.java_evolution.java03.jndi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;

import org.junit.jupiter.api.Test;

class JndiExamplesTest {

	private final JndiExamples examples = new JndiExamples();

	@Test
	void initialContextLoadsConfiguredProvider() throws Exception {
		Context context = examples.createContext();
		try {
			assertThat(context.getEnvironment().get(Context.INITIAL_CONTEXT_FACTORY))
					.as("InitialContext should be configured through the standard provider property")
					.isEqualTo(JndiExamples.MemoryInitialContextFactory.class.getName());
		}
		finally {
			context.close();
		}
	}

	@Test
	void bindAndLookupResolveNameToObject() throws Exception {
		Object result = examples.bindAndLookup("service/greeting", "hello from jndi");

		assertThat(result)
				.as("JNDI lookup should resolve a name to the object bound in the context")
				.isEqualTo("hello from jndi");
	}

	@Test
	void duplicateBindFailsUntilNameIsRebound() throws Exception {
		Context context = examples.createContext();
		try {
			context.bind("service/greeting", "first");

			assertThatThrownBy(() -> context.bind("service/greeting", "second"))
					.as("bind should fail when a name is already present")
					.isInstanceOf(NameAlreadyBoundException.class);

			context.rebind("service/greeting", "second");

			assertThat(context.lookup("service/greeting"))
					.as("rebind should replace the object associated with the name")
					.isEqualTo("second");
		}
		finally {
			context.close();
		}
	}

	@Test
	void missingLookupFailsWithNameNotFound() throws Exception {
		Context context = examples.createContext();
		try {
			assertThatThrownBy(() -> context.lookup("missing"))
					.as("lookup should report unresolved names explicitly")
					.isInstanceOf(NameNotFoundException.class);
		}
		finally {
			context.close();
		}
	}

	@Test
	void listShowsNamesAndClassNames() throws Exception {
		Context context = examples.createContext();
		try {
			context.bind("service/greeting", "hello");
			context.bind("service/retries", new Integer(3));

			List names = new ArrayList();
			NamingEnumeration enumeration = context.list("");
			while (enumeration.hasMore()) {
				NameClassPair pair = (NameClassPair) enumeration.next();
				names.add(pair.getName() + ":" + pair.getClassName());
			}

			assertThat(names)
					.as("list should enumerate names and the class names of their bound objects")
					.containsExactlyInAnyOrder("service/greeting:java.lang.String", "service/retries:java.lang.Integer");
		}
		finally {
			context.close();
		}
	}

	@Test
	void listBindingsShowsNamesAndObjects() throws Exception {
		Context context = examples.createContext();
		try {
			context.bind("service/greeting", "hello");

			NamingEnumeration enumeration = context.listBindings("");
			Binding binding = (Binding) enumeration.next();

			assertThat(binding.getName())
					.as("listBindings should include the bound name")
					.isEqualTo("service/greeting");
			assertThat(binding.getObject())
					.as("listBindings should include the object, not only its class name")
					.isEqualTo("hello");
		}
		finally {
			context.close();
		}
	}
}

package net.jrodolfo.java_evolution.java03.jndi;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.InitialContext;

/**
 * Demonstrates Java Naming and Directory Interface (JNDI), included in J2SE
 * 1.3.
 *
 * <p>
 * Production JNDI providers often point to LDAP, DNS, RMI registries, or
 * application-server resources. This example uses a tiny in-memory provider so
 * the provider model, names, contexts, bindings, and lookups can be exercised
 * without external infrastructure.
 * </p>
 */
public class JndiExamples {

	/**
	 * Creates an {@link InitialContext} configured with the in-memory provider used
	 * by this example.
	 *
	 * @return configured initial context
	 * @throws NamingException when the context cannot be created
	 */
	public Context createContext() throws NamingException {
		Hashtable environment = new Hashtable();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, MemoryInitialContextFactory.class.getName());
		return new InitialContext(environment);
	}

	/**
	 * Binds a name and looks it up again through the JNDI API.
	 *
	 * @param name object name
	 * @param value object to bind
	 * @return object returned by lookup
	 * @throws NamingException when binding or lookup fails
	 */
	public Object bindAndLookup(String name, Object value) throws NamingException {
		Context context = createContext();
		try {
			context.bind(name, value);
			return context.lookup(name);
		}
		finally {
			context.close();
		}
	}

	/**
	 * JNDI provider factory selected through {@link Context#INITIAL_CONTEXT_FACTORY}.
	 */
	public static final class MemoryInitialContextFactory implements InitialContextFactory {

		public Context getInitialContext(Hashtable environment) throws NamingException {
			return new MemoryContext(environment);
		}
	}

	/**
	 * Minimal in-memory context for demonstrating focused JNDI operations.
	 */
	public static final class MemoryContext implements Context {

		private final Hashtable environment;
		private final Map bindings = new HashMap();

		MemoryContext(Hashtable environment) {
			this.environment = new Hashtable();
			if (environment != null) {
				this.environment.putAll(environment);
			}
		}

		public Object lookup(String name) throws NamingException {
			if (!bindings.containsKey(name)) {
				throw new NameNotFoundException(name);
			}
			return bindings.get(name);
		}

		public Object lookup(Name name) throws NamingException {
			return lookup(name.toString());
		}

		public void bind(String name, Object object) throws NamingException {
			if (bindings.containsKey(name)) {
				throw new NameAlreadyBoundException(name);
			}
			bindings.put(name, object);
		}

		public void bind(Name name, Object object) throws NamingException {
			bind(name.toString(), object);
		}

		public void rebind(String name, Object object) throws NamingException {
			bindings.put(name, object);
		}

		public void rebind(Name name, Object object) throws NamingException {
			rebind(name.toString(), object);
		}

		public void unbind(String name) throws NamingException {
			if (!bindings.containsKey(name)) {
				throw new NameNotFoundException(name);
			}
			bindings.remove(name);
		}

		public void unbind(Name name) throws NamingException {
			unbind(name.toString());
		}

		public NamingEnumeration list(String name) throws NamingException {
			requireRootName(name);
			Iterator iterator = bindings.entrySet().iterator();
			return new IteratorNamingEnumeration(iterator, false);
		}

		public NamingEnumeration list(Name name) throws NamingException {
			return list(name.toString());
		}

		public NamingEnumeration listBindings(String name) throws NamingException {
			requireRootName(name);
			Iterator iterator = bindings.entrySet().iterator();
			return new IteratorNamingEnumeration(iterator, true);
		}

		public NamingEnumeration listBindings(Name name) throws NamingException {
			return listBindings(name.toString());
		}

		public Object lookupLink(String name) throws NamingException {
			return lookup(name);
		}

		public Object lookupLink(Name name) throws NamingException {
			return lookup(name);
		}

		public Hashtable getEnvironment() throws NamingException {
			return new Hashtable(environment);
		}

		public Object addToEnvironment(String propertyName, Object propertyValue) throws NamingException {
			return environment.put(propertyName, propertyValue);
		}

		public Object removeFromEnvironment(String propertyName) throws NamingException {
			return environment.remove(propertyName);
		}

		public void close() throws NamingException {
			bindings.clear();
		}

		public String getNameInNamespace() throws NamingException {
			return "";
		}

		public void rename(String oldName, String newName) throws NamingException {
			Object object = lookup(oldName);
			unbind(oldName);
			bind(newName, object);
		}

		public void rename(Name oldName, Name newName) throws NamingException {
			rename(oldName.toString(), newName.toString());
		}

		public void destroySubcontext(String name) throws NamingException {
			throw unsupported("subcontexts are outside this focused example");
		}

		public void destroySubcontext(Name name) throws NamingException {
			destroySubcontext(name.toString());
		}

		public Context createSubcontext(String name) throws NamingException {
			throw unsupported("subcontexts are outside this focused example");
		}

		public Context createSubcontext(Name name) throws NamingException {
			return createSubcontext(name.toString());
		}

		public NameParser getNameParser(String name) throws NamingException {
			throw unsupported("custom name parsing is outside this focused example");
		}

		public NameParser getNameParser(Name name) throws NamingException {
			return getNameParser(name.toString());
		}

		public Name composeName(Name name, Name prefix) throws NamingException {
			throw unsupported("compound names are outside this focused example");
		}

		public String composeName(String name, String prefix) throws NamingException {
			return prefix + "/" + name;
		}

		private void requireRootName(String name) throws NamingException {
			if (!"".equals(name)) {
				throw unsupported("listing is supported only for the root context");
			}
		}

		private OperationNotSupportedException unsupported(String message) {
			return new OperationNotSupportedException(message);
		}
	}

	private static final class IteratorNamingEnumeration implements NamingEnumeration {

		private final Iterator iterator;
		private final boolean includeObjects;

		private IteratorNamingEnumeration(Iterator iterator, boolean includeObjects) {
			this.iterator = iterator;
			this.includeObjects = includeObjects;
		}

		public Object next() throws NamingException {
			return nextElement();
		}

		public boolean hasMore() throws NamingException {
			return hasMoreElements();
		}

		public void close() throws NamingException {
		}

		public boolean hasMoreElements() {
			return iterator.hasNext();
		}

		public Object nextElement() {
			Map.Entry entry = (Map.Entry) iterator.next();
			String name = (String) entry.getKey();
			Object object = entry.getValue();
			if (includeObjects) {
				return new Binding(name, object);
			}
			return new NameClassPair(name, object.getClass().getName());
		}
	}
}

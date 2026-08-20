package net.jrodolfo.java_evolution.java03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Demonstrates dynamic proxies, introduced in J2SE 1.3.
 */
public class DynamicProxyExamples {

	/**
	 * Creates a proxy that decorates calls to a service interface.
	 *
	 * @return decorated service response
	 */
	public String proxyAddsTracing() {
		GreetingService target = new DefaultGreetingService();
		GreetingService proxy = (GreetingService) Proxy.newProxyInstance(
				GreetingService.class.getClassLoader(),
				new Class<?>[] { GreetingService.class },
				new TracingInvocationHandler(target));
		return proxy.greet("Java");
	}

	interface GreetingService {
		String greet(String name);
	}

	static class DefaultGreetingService implements GreetingService {
		@Override
		public String greet(String name) {
			return "Hello, " + name;
		}
	}

	static class TracingInvocationHandler implements InvocationHandler {
		private final Object target;

		TracingInvocationHandler(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = method.invoke(target, args);
			return "traced: " + result;
		}
	}
}

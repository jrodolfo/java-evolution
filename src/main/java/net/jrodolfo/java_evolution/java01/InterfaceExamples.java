package net.jrodolfo.java_evolution.java01;

/**
 * Refreshes interfaces as early Java contracts.
 */
public class InterfaceExamples {

	/**
	 * Calls two unrelated implementations through the same interface.
	 *
	 * @return rendered messages
	 */
	public String renderThroughInterface() {
		Renderer plain = new PlainRenderer();
		Renderer bracketed = new BracketedRenderer();
		return plain.render("java") + " / " + bracketed.render("java");
	}

	interface Renderer {
		String render(String value);
	}

	static class PlainRenderer implements Renderer {
		@Override
		public String render(String value) {
			return value;
		}
	}

	static class BracketedRenderer implements Renderer {
		@Override
		public String render(String value) {
			return "[" + value + "]";
		}
	}
}

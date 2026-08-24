package net.jrodolfo.java_evolution.java02.swing;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SwingExamplesTest {

	private final SwingExamples examples = new SwingExamples();

	@Test
	void listModelKeepsOrderedItems() {
		assertThat(examples.listModelSnapshot())
				.as("DefaultListModel should keep ordered data separately from visible rendering")
				.containsExactly("collections", "swing", "java2d");
	}

	@Test
	void tableModelExposesCellValues() {
		assertThat(examples.tableModelValue())
				.as("DefaultTableModel should expose table data without needing a visible JTable")
				.isEqualTo("Swing");
	}

	@Test
	void buttonCanTriggerActionWithoutShowingAWindow() {
		assertThat(examples.buttonActionClicks())
				.as("JButton#doClick should invoke the configured Action through Swing's event model")
				.isEqualTo(1);
	}

	@Test
	void codeCanRunOnEventDispatchThread() throws Exception {
		assertThat(examples.runsOnEventDispatchThread())
				.as("Swing UI work should be dispatched on the Event Dispatch Thread")
				.isTrue();
	}
}

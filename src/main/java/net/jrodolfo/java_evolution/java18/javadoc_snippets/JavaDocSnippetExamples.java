package net.jrodolfo.java_evolution.java18.javadoc_snippets;

import java.util.List;
import java.util.Locale;

/**
 * Demonstrates JavaDoc {@code @snippet}, introduced in Java 18.
 *
 * <p>
 * Before Java 18, JavaDoc examples were commonly written with {@code <pre>}
 * blocks. Those blocks displayed code, but the documentation tool could not
 * understand much about the example. The {@code @snippet} tag gives examples a
 * first-class structure that JavaDoc can render more clearly.
 * </p>
 *
 * <p>
 * A compact inline snippet can show a single idea:
 * </p>
 *
 * {@snippet :
 * var examples = new JavaDocSnippetExamples();
 * var title = examples.normalizeTitle("  java   snippets  "); // @highlight substring="normalizeTitle"
 * }
 *
 * <p>
 * A longer snippet can show multiple steps and mark a specific region:
 * </p>
 *
 * {@snippet region="limit" :
 * var examples = new JavaDocSnippetExamples();
 * var lines = List.of("install jdk", "run tests", "read javadocs");
 *
 * // @start region="limit"
 * var preview = examples.previewLines(lines, 2);
 * // @end
 * }
 */
public class JavaDocSnippetExamples {

	/**
	 * Normalizes a short title for display in documentation.
	 *
	 * <p>
	 * The snippet is not only formatted text. JavaDoc understands it as a code
	 * example and can render it with snippet-aware styling:
	 * </p>
	 *
	 * {@snippet :
	 * var examples = new JavaDocSnippetExamples();
	 * var normalized = examples.normalizeTitle("  java   evolution  ");
	 * }
	 *
	 * @param title title to normalize
	 * @return trimmed title with repeated whitespace collapsed and each word capitalized
	 */
	public String normalizeTitle(String title) {
		if (title == null || title.isBlank()) {
			return "";
		}

		var words = title.trim().split("\\s+");
		for (var index = 0; index < words.length; index++) {
			words[index] = capitalize(words[index]);
		}
		return String.join(" ", words);
	}

	/**
	 * Returns a small preview from a larger list.
	 *
	 * <p>
	 * Documentation snippets are useful when the reader needs to see a short API
	 * interaction without reading a full test class:
	 * </p>
	 *
	 * {@snippet :
	 * var examples = new JavaDocSnippetExamples();
	 * var preview = examples.previewLines(List.of("one", "two", "three"), 2);
	 * }
	 *
	 * @param lines source lines
	 * @param limit maximum number of lines to return
	 * @return the first {@code limit} lines, or all lines when the list is shorter
	 */
	public List<String> previewLines(List<String> lines, int limit) {
		if (limit <= 0) {
			return List.of();
		}
		return lines.stream()
				.limit(limit)
				.toList();
	}

	/**
	 * Formats a command shown in documentation.
	 *
	 * <p>
	 * Snippets are especially helpful for command-like examples because the source
	 * stays readable and the generated JavaDoc stays consistent:
	 * </p>
	 *
	 * {@snippet :
	 * var examples = new JavaDocSnippetExamples();
	 * var command = examples.formatCommand("jwebserver", 8000);
	 * }
	 *
	 * @param command command name
	 * @param port port number
	 * @return formatted command
	 */
	public String formatCommand(String command, int port) {
		return "%s --port %d".formatted(command, port);
	}

	private String capitalize(String word) {
		if (word.isEmpty()) {
			return word;
		}
		return word.substring(0, 1).toUpperCase(Locale.ROOT) + word.substring(1).toLowerCase(Locale.ROOT);
	}
}

package net.jrodolfo.java_evolution.java04.preferences;

/**
 * Explains the Preferences API.
 */
public class PreferencesNotes {

	public String problemSolved() {
		return "applications need a standard place for small user or system configuration values";
	}

	public String apiShape() {
		return "java.util.prefs provides hierarchical nodes and key/value storage";
	}

	public String repositoryDecision() {
		return "preferences use platform-specific backing stores that may be unavailable or fail to synchronize in tests";
	}
}

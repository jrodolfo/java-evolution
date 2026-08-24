package net.jrodolfo.java_evolution.java02.swing;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 * Demonstrates headless-safe Swing concepts from the Java 2 platform.
 */
public class SwingExamples {

	static {
		System.setProperty("java.awt.headless", "true");
	}

	/**
	 * Uses Swing's list model without creating a visible list component.
	 *
	 * @return ordered snapshot of model values
	 */
	public List<String> listModelSnapshot() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("collections");
		model.addElement("swing");
		model.addElement("java2d");

		List<String> values = new ArrayList<String>();
		for (int index = 0; index < model.getSize(); index++) {
			values.add(model.getElementAt(index));
		}
		return values;
	}

	/**
	 * Uses Swing's table model as a data model separate from visible rendering.
	 *
	 * @return value stored in the first table row
	 */
	public String tableModelValue() {
		DefaultTableModel model = new DefaultTableModel(new Object[] { "feature", "release" }, 0);
		model.addRow(new Object[] { "Swing", "Java 2" });
		return String.valueOf(model.getValueAt(0, 0));
	}

	/**
	 * Triggers a Swing action through a button model without showing a window.
	 *
	 * @return number of times the action was invoked
	 */
	public int buttonActionClicks() {
		final int[] clicks = new int[] { 0 };
		Action action = new AbstractAction("Save") {
			public void actionPerformed(ActionEvent event) {
				clicks[0]++;
			}
		};

		JButton button = new JButton(action);
		button.doClick();
		return clicks[0];
	}

	/**
	 * Runs code on Swing's Event Dispatch Thread (EDT).
	 *
	 * @return {@code true} when the callback ran on the EDT
	 * @throws InvocationTargetException when the callback fails
	 * @throws InterruptedException when interrupted while waiting for the EDT
	 */
	public boolean runsOnEventDispatchThread() throws InvocationTargetException, InterruptedException {
		final boolean[] ranOnEventDispatchThread = new boolean[] { false };
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				ranOnEventDispatchThread[0] = SwingUtilities.isEventDispatchThread();
			}
		});
		return ranOnEventDispatchThread[0];
	}
}

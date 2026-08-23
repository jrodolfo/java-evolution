package net.jrodolfo.java_evolution.java01.javabeans;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.EventListener;

/**
 * Demonstrates JavaBeans introspection, introduced in Java 1.1.
 */
public class JavaBeansExamples {

	/**
	 * Finds the JavaBeans properties declared by the sample bean.
	 *
	 * @return discovered property names
	 * @throws IntrospectionException when bean metadata cannot be read
	 */
	public String[] propertyNames() throws IntrospectionException {
		PropertyDescriptor[] descriptors = beanInfo().getPropertyDescriptors();
		String[] names = new String[descriptors.length];
		for (int index = 0; index < descriptors.length; index++) {
			names[index] = descriptors[index].getName();
		}
		return names;
	}

	/**
	 * Finds the read and write methods for a JavaBeans property.
	 *
	 * @param propertyName property to inspect
	 * @return read method name followed by write method name
	 * @throws IntrospectionException when bean metadata cannot be read
	 */
	public String accessorPair(String propertyName) throws IntrospectionException {
		PropertyDescriptor descriptor = property(propertyName);
		return descriptor.getReadMethod().getName() + "/" + descriptor.getWriteMethod().getName();
	}

	/**
	 * Finds the Java type of a JavaBeans property.
	 *
	 * @param propertyName property to inspect
	 * @return property type
	 * @throws IntrospectionException when bean metadata cannot be read
	 */
	public Class<?> propertyType(String propertyName) throws IntrospectionException {
		return property(propertyName).getPropertyType();
	}

	/**
	 * Finds the listener set exposed through add/remove listener methods.
	 *
	 * @return event set name
	 * @throws IntrospectionException when bean metadata cannot be read
	 */
	public String eventSetName() throws IntrospectionException {
		EventSetDescriptor[] descriptors = beanInfo().getEventSetDescriptors();
		return descriptors[0].getName();
	}

	private BeanInfo beanInfo() throws IntrospectionException {
		return Introspector.getBeanInfo(ProjectBean.class, Object.class);
	}

	private PropertyDescriptor property(String propertyName) throws IntrospectionException {
		PropertyDescriptor[] descriptors = beanInfo().getPropertyDescriptors();
		for (PropertyDescriptor descriptor : descriptors) {
			if (descriptor.getName().equals(propertyName)) {
				return descriptor;
			}
		}
		throw new IntrospectionException("Unknown property: " + propertyName);
	}

	/**
	 * Small bean used by the introspection examples.
	 */
	public static class ProjectBean {

		private String name;
		private boolean active;

		public ProjectBean() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		public void addProjectListener(ProjectListener listener) {
			// JavaBeans tooling discovers the listener convention; this example does not dispatch events.
		}

		public void removeProjectListener(ProjectListener listener) {
			// JavaBeans tooling discovers the listener convention; this example does not dispatch events.
		}
	}

	/**
	 * Listener type used to demonstrate JavaBeans event-set discovery.
	 */
	public interface ProjectListener extends EventListener {
		void projectChanged();
	}
}

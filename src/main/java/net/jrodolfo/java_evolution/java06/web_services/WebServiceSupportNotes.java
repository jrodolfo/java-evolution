package net.jrodolfo.java_evolution.java06.web_services;

/**
 * Explains Java 6 web-service support.
 */
public class WebServiceSupportNotes {

	/**
	 * @return the Java 6 web-service direction
	 */
	public String javaSixDirection() {
		return "Java 6 brought more XML and web-service APIs into the Java SE platform";
	}

	/**
	 * @return examples of API areas involved
	 */
	public String apiAreas() {
		return "web-service metadata, XML binding, XML web services, streaming XML, and XML digital signatures";
	}

	/**
	 * @return the modern compatibility caveat
	 */
	public String modernJdkCaveat() {
		return "modern JDKs removed several Java EE and CORBA-era modules, so applications should use explicit dependencies";
	}
}

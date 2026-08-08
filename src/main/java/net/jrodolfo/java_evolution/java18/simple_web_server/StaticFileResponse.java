package net.jrodolfo.java_evolution.java18.simple_web_server;

/**
 * Represents a response returned by the local static-file server example.
 *
 * <p>
 * The Java 18 feature is about serving static files quickly. This record keeps
 * the client-side verification small so tests can assert the HTTP status and
 * response body without exposing learners to a full web framework.
 * </p>
 *
 * @param statusCode the HTTP status code
 * @param body the response body
 */
public record StaticFileResponse(int statusCode, String body) {

	/**
	 * Checks whether the server returned a successful HTTP response.
	 *
	 * @return {@code true} for any 2xx status code
	 */
	public boolean isSuccessful() {
		return statusCode >= 200 && statusCode < 300;
	}

	/**
	 * Checks whether the requested static file was not found.
	 *
	 * @return {@code true} when the status code is 404
	 */
	public boolean isNotFound() {
		return statusCode == 404;
	}
}

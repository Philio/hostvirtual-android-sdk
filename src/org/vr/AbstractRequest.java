package org.vr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public abstract class AbstractRequest {

	// Request methods
	public enum Method {
		GET, POST, PUT, DELETE
	};

	// HTTP timeouts
	protected int TIMEOUT_CONNECT = 5000;
	protected int TIMEOUT_SOCKET = 30000;

	/**
	 * Execute request
	 * 
	 * @param method
	 * @param uri
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public abstract String execute(Method method, String uri, List<NameValuePair> params) throws IOException;

	/**
	 * Get query string for a URI
	 * 
	 * @param uri
	 * @param params
	 * @return
	 */
	protected String getQueryString(String uri, List<NameValuePair> params) {
		if (params == null || params.size() == 0) {
			return null;
		}

		String queryString = uri.contains("?") ? "" : "?";
		queryString += URLEncodedUtils.format(params, "UTF-8");

		return queryString;
	}

	/**
	 * Check the HTTP status code to ensure request was successful
	 * 
	 * @param code
	 * @throws IOException
	 */
	protected void checkStatusCode(int code) throws IOException {
		if (code != 200) {
			throw new IOException("Received HTTP code " + Integer.toString(code));
		}
	}

	/**
	 * Read from a stream and convert to string
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	protected String readStream(InputStream stream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuilder builder = new StringBuilder();

		String line;
		while ((line = reader.readLine()) != null) {
			if (builder.length() > 0) {
				builder.append('\n');
			}
			builder.append(line);
		}

		return builder.toString();
	}

}
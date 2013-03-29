package org.vr.cloud;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

public class HttpURLConnectionRequest extends AbstractRequest {

	/**
	 * Execute request
	 * 
	 * @param method
	 * @param uri
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public String execute(Method method, String uri, List<NameValuePair> params) throws IOException {
		// Append params for GET/DELETE requests
		if (method == Method.GET || method == Method.DELETE) {
			uri += getQueryString(uri, params);
		}

		// Create connection and configure
		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(TIMEOUT_CONNECT);
		conn.setReadTimeout(TIMEOUT_SOCKET);
		conn.setUseCaches(false);

		// Method specific settings
		switch (method) {
		case GET:
			conn.connect();
			break;
		case POST:
			conn.setRequestMethod("POST");
			writeStream(conn, params);
			break;
		case PUT:
			conn.setRequestMethod("PUT");
			writeStream(conn, params);
			break;
		case DELETE:
			conn.setRequestMethod("DELETE");
			conn.connect();
			break;
		}
		
		// Check response code
		checkStatusCode(conn.getResponseCode());
		
		// Get response body
		return readStream(conn.getInputStream());
	}
	
	/**
	 * Write form encoded data to output stream
	 * 
	 * @param conn
	 * @param params
	 * @throws IOException
	 */
	private void writeStream(HttpURLConnection conn, List<NameValuePair> params) throws IOException {
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoOutput(true);
		OutputStream stream = conn.getOutputStream();
		new UrlEncodedFormEntity(params).writeTo(stream);
		stream.flush();
		stream.close();
	}

}

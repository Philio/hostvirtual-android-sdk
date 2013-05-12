package org.vr;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

public class DefaultHttpClientRequest extends AbstractRequest {

	// Instance of DefaultHttpClient
	private DefaultHttpClient mClient = new DefaultHttpClient();
	
	/**
	 * Configure HTTP client
	 */
	public DefaultHttpClientRequest() {
		HttpConnectionParams.setConnectionTimeout(mClient.getParams(), TIMEOUT_CONNECT);
		HttpConnectionParams.setSoTimeout(mClient.getParams(), TIMEOUT_SOCKET);
	}

	/**
	 * Execute request
	 * 
	 * @param method
	 * @param uri
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Override
	public String execute(Method method, String uri, List<NameValuePair> params) throws IOException  {
		// Create request
		HttpUriRequest request = null;
		switch (method) {
		case GET:
			request = new HttpGet(uri + getQueryString(uri, params));
			break;
		case POST:
			request = new HttpPost(uri);
			((HttpPost) request).setEntity(new UrlEncodedFormEntity(params));
			break;
		case PUT:
			request = new HttpPut(uri);
			((HttpPut) request).setEntity(new UrlEncodedFormEntity(params));
			break;
		case DELETE:
			request = new HttpDelete(uri + getQueryString(uri, params));
			break;
		}
		
		// Execute request and get response
		HttpResponse response = mClient.execute(request);
		checkStatusCode(response.getStatusLine().getStatusCode());
		
		// Read response from stream
		return readStream(response.getEntity().getContent());
	}

}
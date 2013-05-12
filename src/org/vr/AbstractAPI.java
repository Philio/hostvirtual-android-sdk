package org.vr;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public abstract class AbstractAPI {

	// Base API path
	protected static final String BASE_URI = "https://www.vr.org/vapi/";

	// Request instance
	protected AbstractRequest mRequest;

	// API key
	protected String mApiKey;
	
	/**
	 * Constructor used for authentication requests only
	 * 
	 * @param request
	 */
	public AbstractAPI(AbstractRequest request) {
		mRequest = request;
	}
	
	/**
	 * Standard constructor that should be implemented by all subclasses except for authentication
	 * 
	 * @param request
	 * @param apiKey
	 */
	public AbstractAPI(AbstractRequest request, String apiKey) {
		mRequest = request;
		mApiKey = apiKey;
	}
	
	/**
	 * Create request params and add API key
	 * 
	 * @return
	 * @throws APIException
	 */
	protected List<NameValuePair> getInitialParams() throws APIException {
		if (mApiKey == null) {
			throw new APIException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		return params;
	}

}

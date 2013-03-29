package org.vr.cloud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vr.cloud.AbstractRequest.Method;

public class Api {

	// Base API path
	private static final String BASE_URI = "https://www.vr.org/vapi/";

	// Request instance
	private AbstractRequest mRequest;

	// API key
	private String mApiKey;

	/**
	 * Set request object
	 * 
	 * @param request
	 */
	public Api(AbstractRequest request) {
		this(request, null);
	}

	/**
	 * Set request object and API key
	 * 
	 * @param request
	 * @param apiKey
	 */
	public Api(AbstractRequest request, String apiKey) {
		mRequest = request;
		mApiKey = apiKey;
	}

	/**
	 * Set api key
	 * 
	 * @param apiKey
	 */
	public void setApiKey(String apiKey) {
		mApiKey = apiKey;
	}

	/**
	 * Perform a login to get user account info
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject login(String email, String password) throws IOException, JSONException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		return new JSONObject(mRequest.execute(Method.GET, BASE_URI + "auth/login", params));
	}

	/**
	 * Get details for all running servers on the account
	 * 
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray servers() throws ApiException, IOException, JSONException {
		if (mApiKey == null) {
			throw new ApiException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		return new JSONArray(mRequest.execute(Method.GET, BASE_URI + "cloud/servers", params));
	}
	
	/**
	 * Get available OS images
	 * 
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray osList() throws ApiException, IOException, JSONException {
		if (mApiKey == null) {
			throw new ApiException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		return new JSONArray(mRequest.execute(Method.GET, BASE_URI + "cloud/images", params));
	}
	
	/**
	 * Get available locations
	 * 
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray locations() throws ApiException, IOException, JSONException {
		if (mApiKey == null) {
			throw new ApiException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		return new JSONArray(mRequest.execute(Method.GET, BASE_URI + "cloud/locations", params));
	}

	/**
	 * Get available locations for a package
	 * 
	 * @param aPackage
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray locations(String aPackage) throws ApiException, IOException, JSONException {
		if (mApiKey == null) {
			throw new ApiException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		params.add(new BasicNameValuePair("package", aPackage));
		return new JSONArray(mRequest.execute(Method.GET, BASE_URI + "cloud/locations", params));
	}

	/**
	 * Get available plans
	 * 
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray plans() throws ApiException, IOException, JSONException {
		if (mApiKey == null) {
			throw new ApiException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		return new JSONArray(mRequest.execute(Method.GET, BASE_URI + "cloud/sizes", params));
	}
	
	/**
	 * Get available plans for a location
	 * 
	 * @param location
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray plans(String location) throws ApiException, IOException, JSONException {
		if (mApiKey == null) {
			throw new ApiException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		params.add(new BasicNameValuePair("location", location));
		return new JSONArray(mRequest.execute(Method.GET, BASE_URI + "cloud/sizes", params));
	}

	/**
	 * Get available packages
	 * 
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray packages() throws ApiException, IOException, JSONException {
		if (mApiKey == null) {
			throw new ApiException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		return new JSONArray(mRequest.execute(Method.GET, BASE_URI + "cloud/packages", params));
	}
	
	/**
	 * Get DNS zones by type
	 * 
	 * @param type
	 * @return
	 * @throws ApiException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray dnsZones(String type) throws ApiException, IOException, JSONException {
		if (mApiKey == null) {
			throw new ApiException("Method requires an API key");
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", mApiKey));
		params.add(new BasicNameValuePair("type", type));
		return new JSONArray(mRequest.execute(Method.GET, BASE_URI + "dns/zones", params));
	}

}

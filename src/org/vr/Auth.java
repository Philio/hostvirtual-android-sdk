package org.vr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.vr.AbstractRequest.Method;

public class Auth extends AbstractAPI {

	// Auth URI path
	private static final String AUTH_URI = BASE_URI + "auth/";
	
	/**
	 * Set request object
	 * 
	 * @param request
	 */
	public Auth(AbstractRequest request) {
		super(request);
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
		return new JSONObject(mRequest.execute(Method.GET, AUTH_URI + "login", params));
	}

}

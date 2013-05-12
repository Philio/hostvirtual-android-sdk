package org.vr;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.vr.AbstractRequest.Method;

public class DNS extends AbstractAPI {

	// Cloud URI path
	private static final String DNS_URI = BASE_URI + "dns/";

	/**
	 * Set request object and API key
	 * 
	 * @param request
	 * @param apiKey
	 */
	public DNS(AbstractRequest request, String apiKey) {
		super(request, apiKey);
	}

	/**
	 * Get DNS zones by type
	 * 
	 * @param type
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray zones(String type) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		params.add(new BasicNameValuePair("type", type));
		return new JSONArray(mRequest.execute(Method.GET, DNS_URI + "zones", params));
	}
	
}

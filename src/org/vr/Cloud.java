package org.vr;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.vr.AbstractRequest.Method;

public class Cloud extends AbstractAPI {

	// Cloud URI path
	private static final String CLOUD_URI = BASE_URI + "cloud/";

	/**
	 * Set request object and API key
	 * 
	 * @param request
	 * @param apiKey
	 */
	public Cloud(AbstractRequest request, String apiKey) {
		super(request, apiKey);
	}

	/**
	 * Get available locations
	 * 
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray locations() throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "locations", params));
	}

	/**
	 * Get available locations for a package
	 * 
	 * @param aPackage
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray locations(String aPackage) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		params.add(new BasicNameValuePair("package", aPackage));
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "locations", params));
	}

	/**
	 * Get available plans
	 * 
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray plans() throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "sizes", params));
	}

	/**
	 * Get available plans for a location
	 * 
	 * @param location
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray plans(String location) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		params.add(new BasicNameValuePair("location", location));
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "sizes", params));
	}

	/**
	 * Get available OS images
	 * 
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray osList() throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "images", params));
	}

	/**
	 * Get current packages (includes details of unprovisioned/purchased servers)
	 * 
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray packages() throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "packages", params));
	}

	/**
	 * Get details of a single package
	 * 
	 * @param packageId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject packages(int packageId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONObject(
				mRequest.execute(Method.GET, CLOUD_URI + "package/" + Integer.toString(packageId), params));
	}

	/**
	 * Get details for all servers on the account
	 * 
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray servers() throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "servers", params));
	}

	/**
	 * Get all server options for a specific server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject serverOptions(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONObject(mRequest.execute(Method.GET, CLOUD_URI + "server/" + Integer.toString(serverId), params));
	}

	/**
	 * Get summary information for a specific server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject serverSummary(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONObject(mRequest.execute(Method.GET, CLOUD_URI + "serversummary/" + Integer.toString(serverId),
				params));
	}

	/**
	 * Get monthly bandwidth summary for a specific server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray bandwidth(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "servermonthlybw/" + Integer.toString(serverId),
				params));
	}

	/**
	 * Get IP addresses (IPv4 + IPv6) for a specific server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray ips(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "networkips/" + Integer.toString(serverId),
				params));
	}

	/**
	 * Get IPv4 addresses for a specific server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray ipv4(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "IPv4/" + Integer.toString(serverId), params));
	}

	/**
	 * Get IPv6 addresses for a specific server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONArray ipv6(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONArray(mRequest.execute(Method.GET, CLOUD_URI + "IPv6/" + Integer.toString(serverId), params));
	}

	/**
	 * Start rescue mode for a server
	 * 
	 * @param serverId
	 * @param password
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject startRescue(int serverId, String password) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		params.add(new BasicNameValuePair("rescue_pass", password));
		return new JSONObject(mRequest.execute(Method.POST,
				CLOUD_URI + "server/start_rescue/" + Integer.toString(serverId), params));
	}

	/**
	 * Stop rescue mode for a server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject stopRescue(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONObject(mRequest.execute(Method.POST,
				CLOUD_URI + "server/stop_rescue/" + Integer.toString(serverId), params));
	}

	/**
	 * Start a server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject start(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONObject(mRequest.execute(Method.POST, CLOUD_URI + "server/start/" + Integer.toString(serverId),
				params));
	}

	/**
	 * Stop a server
	 * 
	 * @param serverId
	 * @return
	 * @throws APIException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject stop(int serverId) throws APIException, IOException, JSONException {
		List<NameValuePair> params = getInitialParams();
		return new JSONObject(mRequest.execute(Method.POST, CLOUD_URI + "server/shutdown/" + Integer.toString(serverId),
				params));
	}

}

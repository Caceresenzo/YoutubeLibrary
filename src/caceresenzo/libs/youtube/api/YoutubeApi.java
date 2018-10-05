package caceresenzo.libs.youtube.api;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import caceresenzo.libs.json.JsonObject;
import caceresenzo.libs.json.parser.JsonParser;
import caceresenzo.libs.network.Downloader;
import caceresenzo.libs.youtube.Constants;

public class YoutubeApi<R> {
	
	/* Constants */
	public static final String API_URL = "https://www.googleapis.com/youtube/v3/";
	
	/* Parameters Constants */
	public static final String PARAMETERS_KEY = "key";
	
	/* Variables */
	private final String method;
	
	/* Constructor */
	protected YoutubeApi(String method) {
		this.method = method;
	}
	
	public String forgeBaseApiUrl() {
		return API_URL + method + "?" + PARAMETERS_KEY + "=" + Constants.GOOGLE_KEY;
	}
	
	protected Map<String, Object> getParameters() {
		return new HashMap<>();
	}
	
	protected JsonObject download(Map<String, Object> parameters) throws Exception {
		String url = forgeBaseApiUrl();
		
		if (parameters != null && !parameters.isEmpty()) {
			StringBuilder builder = new StringBuilder(url);
			builder.append("&");
			
			for (Entry<String, Object> entry : parameters.entrySet()) {
				builder.append(entry.getKey());
				builder.append("=");
				builder.append(URLEncoder.encode(String.valueOf(entry.getValue()), Charset.forName("UTF-8").name()));
				builder.append("&");
			}
			
			url = builder.toString();
		}
		
		return (JsonObject) new JsonParser().parse(Downloader.webget(url, Charset.forName("UTF-8")));
	}
	
	public R execute() throws Exception {
		return null;
	}
	
}
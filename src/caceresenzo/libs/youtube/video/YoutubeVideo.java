package caceresenzo.libs.youtube.video;

import caceresenzo.libs.youtube.format.YoutubeFormat;

public class YoutubeVideo {
	
	private YoutubeFormat format;
	private String url = "";
	
	public YoutubeVideo(YoutubeFormat format, String url) {
		this.format = format;
		this.url = url;
	}
	
	/**
	 * The url to download the file.
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Format data for the specific file.
	 */
	public YoutubeFormat getFormat() {
		return format;
	}
	
	/**
	 * Format data for the specific file.
	 */
	@Deprecated
	public YoutubeFormat getMeta() {
		return format;
	}
	
	@Override
	public String toString() {
		return "YoutubeFile [format=" + format + ", url=" + url + "]";
	}
	
}
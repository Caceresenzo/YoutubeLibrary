package caceresenzo.libs.youtube.playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import caceresenzo.libs.json.JsonArray;
import caceresenzo.libs.json.JsonObject;
import caceresenzo.libs.logger.Logger;
import caceresenzo.libs.youtube.common.InvalidKindException;
import caceresenzo.libs.youtube.common.Kindable;

/**
 * Simple class to handle youtube's API result for Playlist
 * 
 * @author Enzo CACERES
 */
public class YoutubePlaylist implements Kindable {
	
	/* Constants */
	public static final Pattern PLAYLIST_ID_MATCHER = Pattern.compile("^.*?(?:v|list)=(.*?)(?:&|$)");
	
	public static final String KIND = "youtube#playlistItemListResponse";
	
	public static final int NO_RESULTS = -1;
	
	/* Json Constants */
	public static final String JSON_KEY_PREVIOUS_PAGE_TOKEN = "prevPageToken";
	public static final String JSON_KEY_NEXT_PAGE_TOKEN = "nextPageToken";
	
	public static final String JSON_KEY_PAGE_INFO = "pageInfo";
	public static final String JSON_KEY_PAGE_INFO_TOTAL_RESULTS = "totalResults";
	public static final String JSON_KEY_PAGE_INFO_RESULTS_PER_PAGE = "resultsPerPage";
	
	public static final String JSON_KEY_ITEMS = "items";
	
	/* Variables */
	private String previousPageToken, nextPageToken;
	private int totalResults, resultsPerPage;
	private List<YoutubePlaylistItem> items;
	
	/* Constructor */
	private YoutubePlaylist(String previousPageToken, String nextPageToken, int totalResults, int resultsPerPage, List<YoutubePlaylistItem> items) {
		this.previousPageToken = previousPageToken;
		this.nextPageToken = nextPageToken;
		this.totalResults = totalResults;
		this.resultsPerPage = resultsPerPage;
		this.items = items;
	}
	
	/**
	 * @return If the page got a previous page
	 */
	public boolean hasPreviousPage() {
		return previousPageToken != null;
	}
	
	/**
	 * @return If the page got a next page
	 */
	public boolean hasNextPage() {
		return nextPageToken != null;
	}
	
	/**
	 * @param actualCount
	 *            Actual item count
	 * @return If the next page has more items
	 */
	public boolean hasMoreItemOnNextPage(int actualCount) {
		return actualCount < totalResults && nextPageToken != null;
	}
	
	/**
	 * @return Previous page token
	 */
	public String getPreviousPageToken() {
		return previousPageToken;
	}
	
	/**
	 * @return Next page token to fetch
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}
	
	/**
	 * @return Get total results count on the playlist
	 */
	public int getTotalResults() {
		return totalResults;
	}
	
	/**
	 * @return How many results a page have
	 */
	public int getResultsPerPage() {
		return resultsPerPage;
	}
	
	/**
	 * @return Playlist items
	 */
	public List<YoutubePlaylistItem> getItems() {
		return items;
	}
	
	@Override
	public String getItemKind() {
		return KIND;
	}
	
	/**
	 * Create a {@link YoutubePlaylist} instance from a {@link JsonObject}
	 * 
	 * @param jsonObject
	 *            Source json
	 * @return New instance
	 * @throws InvalidKindException
	 *             If the kind is not valid
	 */
	public static YoutubePlaylist fromJson(JsonObject jsonObject) {
		String kind = jsonObject.getString(JSON_KEY_KIND);
		
		if (!KIND.equals(kind)) {
			throw new InvalidKindException(KIND, kind);
		}
		
		String previousPageToken = jsonObject.getString(JSON_KEY_PREVIOUS_PAGE_TOKEN);
		String nextPageToken = jsonObject.getString(JSON_KEY_NEXT_PAGE_TOKEN);
		int totalResults = NO_RESULTS;
		int resultsPerPage = NO_RESULTS;
		List<YoutubePlaylistItem> items = null;
		
		JsonObject pageInfoJsonObject = jsonObject.getJsonObject(JSON_KEY_PAGE_INFO);
		if (pageInfoJsonObject != null) {
			totalResults = pageInfoJsonObject.getInteger(JSON_KEY_PAGE_INFO_TOTAL_RESULTS, NO_RESULTS);
			resultsPerPage = pageInfoJsonObject.getInteger(JSON_KEY_PAGE_INFO_RESULTS_PER_PAGE, NO_RESULTS);
		}
		
		JsonArray itemsJsonArray = jsonObject.getJsonArray(JSON_KEY_ITEMS);
		if (itemsJsonArray != null) {
			items = new ArrayList<>();
			
			for (Object object : itemsJsonArray) {
				items.add(YoutubePlaylistItem.fromJson((JsonObject) object));
			}
		}
		
		return new YoutubePlaylist(previousPageToken, nextPageToken, totalResults, resultsPerPage, items);
	}
	
}
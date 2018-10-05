package caceresenzo.libs.youtube.api.implementations;

import java.util.Map;

import caceresenzo.libs.string.StringUtils;
import caceresenzo.libs.youtube.api.YoutubeApi;
import caceresenzo.libs.youtube.playlist.YoutubePlaylist;

public class YoutubePlaylistApi extends YoutubeApi<YoutubePlaylist> {
	
	/* Parameters Constants */
	public static final String PARAMETERS_MAX_RESULTS = "maxResults";
	public static final String PARAMETERS_PART = "part";
	public static final String PARAMETERS_PLAYLIST_ID = "playlistId";
	public static final String PARAMETERS_PAGE_TOKEN = "pageToken";
	
	/* Variables */
	private String playlistId, pageToken;
	
	/* Constructor */
	public YoutubePlaylistApi(String playlistId) {
		this(playlistId, null);
	}
	
	/* Constructor */
	public YoutubePlaylistApi(String playlistId, String pageToken) {
		super("playlistItems");
		
		this.playlistId = playlistId;
		this.pageToken = pageToken;
	}
	
	@Override
	protected Map<String, Object> getParameters() {
		Map<String, Object> parameters = super.getParameters();
		
		parameters.put(PARAMETERS_MAX_RESULTS, 50);
		parameters.put(PARAMETERS_PART, "snippet");
		
		parameters.put(PARAMETERS_PLAYLIST_ID, playlistId);
		
		if (StringUtils.validate(pageToken)) {
			parameters.put(PARAMETERS_PAGE_TOKEN, pageToken);
		}
		
		return parameters;
	}
	
	@Override
	public YoutubePlaylist execute() throws Exception {
		return YoutubePlaylist.fromJson(download(getParameters()));
	}
	
}
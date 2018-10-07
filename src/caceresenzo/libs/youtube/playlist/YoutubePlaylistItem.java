package caceresenzo.libs.youtube.playlist;

import caceresenzo.libs.json.JsonObject;
import caceresenzo.libs.youtube.common.InvalidKindException;
import caceresenzo.libs.youtube.common.Kindable;
import caceresenzo.libs.youtube.video.Thumbnails;
import caceresenzo.libs.youtube.video.VideoMeta;

public class YoutubePlaylistItem implements Kindable {
	
	/* Constants */
	public static final String KIND = "youtube#playlistItem";
	public static final String BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v=";
	
	public static final int NO_POSITION = -1;
	
	/* Json Constants */
	public static final String JSON_KEY_DATA = "snippet";
	public static final String JSON_KEY_DATA_TITLE = "title";
	public static final String JSON_KEY_DATA_DESCRIPTION = "description";
	public static final String JSON_KEY_DATA_CHANNEL_TITLE = "channelTitle";
	public static final String JSON_KEY_DATA_POSITION = "position";
	public static final String JSON_KEY_DATA_THUMBNAILS = "thumbnails";
	public static final String JSON_KEY_DATA_THUMBNAILS_RESOLUTION_MAXIMUM = "maxres";
	public static final String JSON_KEY_DATA_RESSOURCE_ID = "resourceId";
	public static final String JSON_KEY_DATA_RESSOURCE_ID_VIDEO_ID = "videoId";
	
	/* Variables */
	private final String videoId;
	private final VideoMeta videoMeta;
	private final int position;
	
	/* Constructor */
	private YoutubePlaylistItem(String videoId, VideoMeta videoMeta, int position) {
		this.videoId = videoId;
		this.videoMeta = videoMeta;
		this.position = position;
	}
	
	/**
	 * @return Video Id
	 */
	public String getVideoId() {
		return videoId;
	}
	
	/**
	 * @return Youtube Video Url ({@value #BASE_YOUTUBE_URL})
	 */
	public String getVideoUrl() {
		return BASE_YOUTUBE_URL + videoId;
	}
	
	/**
	 * @return Video Meta
	 */
	public VideoMeta getVideoMeta() {
		return videoMeta;
	}
	
	/**
	 * @return Item position in playlist
	 */
	public int getPosition() {
		return position;
	}
	
	@Override
	public String getItemKind() {
		return KIND;
	}
	
	public static YoutubePlaylistItem fromJson(JsonObject jsonObject) {
		String kind = jsonObject.getString(JSON_KEY_KIND);
		
		if (!KIND.equals(kind)) {
			throw new InvalidKindException(KIND, kind);
		}
		
		jsonObject = jsonObject.getJsonObject(JSON_KEY_DATA);
		
		String videoId = null;
		String title = jsonObject.getString(JSON_KEY_DATA_TITLE);
		String description = jsonObject.getString(JSON_KEY_DATA_DESCRIPTION);
		String channelTitle = jsonObject.getString(JSON_KEY_DATA_CHANNEL_TITLE);
		int position = jsonObject.getInteger(JSON_KEY_DATA_POSITION, NO_POSITION);
		Thumbnails thumbnails = null;
		
		JsonObject ressourceIdJsonObject = jsonObject.getJsonObject(JSON_KEY_DATA_RESSOURCE_ID);
		if (ressourceIdJsonObject != null) {
			videoId = ressourceIdJsonObject.getString(JSON_KEY_DATA_RESSOURCE_ID_VIDEO_ID);
		}
		
		JsonObject thumbnailsJsonObject = jsonObject.getJsonObject(JSON_KEY_DATA_THUMBNAILS);
		if (thumbnailsJsonObject != null) {
			thumbnails = new Thumbnails(videoId);
			
			if (!thumbnailsJsonObject.containsKey(JSON_KEY_DATA_THUMBNAILS_RESOLUTION_MAXIMUM)) {
				thumbnails.disableMaximumResolution();
			}
		}
		
		VideoMeta videoMeta = new VideoMeta(videoId, title, description, null, channelTitle, VideoMeta.NO_VIDEO_LENGTH, VideoMeta.NO_VIEW_COUNT, thumbnails);
		
		return new YoutubePlaylistItem(videoId, videoMeta, position);
	}
	
}
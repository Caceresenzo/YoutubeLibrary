package caceresenzo.libs.youtube.video;

/**
 * Meta data container class
 * 
 * @author Enzo CACERES
 */
public class VideoMeta {
	
	/* Constants */
	public static final int NO_VIDEO_LENGTH = -1;
	public static final int NO_VIEW_COUNT = -1;
	
	/* Variables */
	private String videoId, title, description, author, channelId;
	private long videoLength, viewCount;
	private boolean isLiveStream;
	private final Thumbnails thumbnails;
	
	/* Constructor */
	public VideoMeta(String videoId, String title, String description, String author, String channelId, long videoLength, long viewCount) {
		this(videoId, title, description, author, channelId, videoLength, viewCount, false, new Thumbnails(videoId));
	}
	
	/* Constructor */
	public VideoMeta(String videoId, String title, String description, String author, String channelId, long videoLength, long viewCount, Thumbnails thumbnails) {
		this(videoId, title, description, author, channelId, videoLength, viewCount, false, thumbnails);
	}
	
	/* Constructor */
	public VideoMeta(String videoId, String title, String description, String author, String channelId, long videoLength, long viewCount, boolean isLiveStream) {
		this(videoId, title, description, author, channelId, videoLength, viewCount, isLiveStream, new Thumbnails(videoId));
	}
	
	/* Constructor */
	public VideoMeta(String videoId, String title, String description, String author, String channelId, long videoLength, long viewCount, boolean isLiveStream, Thumbnails thumbnails) {
		this.videoId = videoId;
		this.title = title;
		this.description = description;
		this.author = author;
		this.channelId = channelId;
		this.videoLength = videoLength;
		this.viewCount = viewCount;
		this.isLiveStream = isLiveStream;
		this.thumbnails = thumbnails == null ? new Thumbnails(videoId) : thumbnails;
	}
	
	/**
	 * @return Video id
	 */
	public String getVideoId() {
		return videoId;
	}
	
	/**
	 * @return (untranslated) Video title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return Video description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return Channel name
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * @return Channel name
	 */
	@Deprecated
	public String getChannelName() {
		return author;
	}
	
	/**
	 * @return Channel id
	 */
	public String getChannelId() {
		return channelId;
	}
	
	/**
	 * @return If the video is a Live Stream
	 */
	public boolean isLiveStream() {
		return isLiveStream;
	}
	
	/**
	 * @return The video length in seconds.
	 */
	public long getVideoLength() {
		return videoLength;
	}
	
	/**
	 * @return The video views count
	 */
	public long getViewCount() {
		return viewCount;
	}
	
	/**
	 * @return Video thumbnail container
	 */
	public Thumbnails getThumbnails() {		
		return thumbnails;
	}
	
	@Override
	public String toString() {
		return "VideoMeta[videoId=" + videoId + ", title=" + title + ", author=" + author + ", channelId=" + channelId + ", videoLength=" + videoLength + ", viewCount=" + viewCount + ", isLiveStream=" + isLiveStream + "]";
	}
	
}
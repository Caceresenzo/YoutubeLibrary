package caceresenzo.libs.youtube.video;

/**
 * Meta data container class
 * 
 * @author Enzo CACERES
 */
public class VideoMeta {
	
	/* Constants */
	public static final String IMAGE_BASE_URL = "http://i.ytimg.com/vi/";
	
	/* Variables */
	private String videoId, title, author, channelId;
	private long videoLength, viewCount;
	private boolean isLiveStream;
	
	/* Constructor */
	public VideoMeta(String videoId, String title, String author, String channelId, long videoLength, long viewCount) {
		this(videoId, title, author, channelId, videoLength, viewCount, false);
	}
	
	/* Constructor */
	public VideoMeta(String videoId, String title, String author, String channelId, long videoLength, long viewCount, boolean isLiveStream) {
		this.videoId = videoId;
		this.title = title;
		this.author = author;
		this.channelId = channelId;
		this.videoLength = videoLength;
		this.viewCount = viewCount;
		this.isLiveStream = isLiveStream;
	}
	
	/**
	 * Image size: 120 x 90
	 * 
	 * @return Image thumbnail for this video
	 */
	public String getThumbUrl() {
		return IMAGE_BASE_URL + videoId + "/default.jpg";
	}
	
	/**
	 * Image size: 320 x 180
	 * 
	 * @return Image thumbnail for this video
	 */
	public String getMqImageUrl() {
		return IMAGE_BASE_URL + videoId + "/mqdefault.jpg";
	}
	
	/**
	 * Image size: 480 x 360
	 * 
	 * @return Image thumbnail for this video
	 */
	public String getHqImageUrl() {
		return IMAGE_BASE_URL + videoId + "/hqdefault.jpg";
	}
	
	/**
	 * Image size: 640 x 480
	 * 
	 * @return Image thumbnail for this video
	 */
	public String getSdImageUrl() {
		return IMAGE_BASE_URL + videoId + "/sddefault.jpg";
	}
	
	/**
	 * Image size: Max Res
	 * 
	 * @return Image thumbnail for this video
	 */
	public String getMaxResolutionImageUrl() {
		return IMAGE_BASE_URL + videoId + "/maxresdefault.jpg";
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
	 * @return Channel name
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * @return Channel name
	 */
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		VideoMeta videoMeta = (VideoMeta) o;
		
		if (videoLength != videoMeta.videoLength)
			return false;
		if (viewCount != videoMeta.viewCount)
			return false;
		if (isLiveStream != videoMeta.isLiveStream)
			return false;
		if (videoId != null ? !videoId.equals(videoMeta.videoId) : videoMeta.videoId != null)
			return false;
		if (title != null ? !title.equals(videoMeta.title) : videoMeta.title != null)
			return false;
		if (author != null ? !author.equals(videoMeta.author) : videoMeta.author != null)
			return false;
		return channelId != null ? channelId.equals(videoMeta.channelId) : videoMeta.channelId == null;
	}
	
	@Override
	public int hashCode() {
		int result = videoId != null ? videoId.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (author != null ? author.hashCode() : 0);
		result = 31 * result + (channelId != null ? channelId.hashCode() : 0);
		result = 31 * result + (int) (videoLength ^ (videoLength >>> 32));
		result = 31 * result + (int) (viewCount ^ (viewCount >>> 32));
		result = 31 * result + (isLiveStream ? 1 : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return "VideoMeta[videoId=" + videoId + ", title=" + title + ", author=" + author + ", channelId=" + channelId + ", videoLength=" + videoLength + ", viewCount=" + viewCount + ", isLiveStream=" + isLiveStream + "]";
	}
	
}
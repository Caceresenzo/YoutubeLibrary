package caceresenzo.libs.youtube.video;

/**
 * Thumbnails manager for {@link VideoMeta}
 * 
 * @author Enzo CACERES
 */
public class Thumbnails {
	
	/* Constants */
	public static final String IMAGE_BASE_URL = "http://i.ytimg.com/vi/";
	
	/* Private Constants */
	private static final int INDEX_RESOLUTION_MAXIMUM = 4;
	
	/* Variables */
	private final String videoId;
	private final boolean[] disabledResolutions;
	
	/* Constructor */
	public Thumbnails(String videoId) {
		this.videoId = videoId;
		this.disabledResolutions = new boolean[5];
	}
	
	/**
	 * Disable the use of the {@link #getMaximumResolutionThumbnailImageUrl()}, maybe the video don't support it
	 * 
	 * @return Itself
	 */
	public Thumbnails disableMaximumResolution() {
		disabledResolutions[INDEX_RESOLUTION_MAXIMUM] = true;
		
		return this;
	}
	
	/**
	 * Image size: 120 x 90
	 * 
	 * @return Default image thumbnail url
	 */
	public String getDefaultThumbnailImageUrl() {
		return IMAGE_BASE_URL + videoId + "/default.jpg";
	}
	
	/**
	 * Image size: 320 x 180
	 * 
	 * @return Medium image thumbnail url
	 */
	public String getMediumThumbnailImageUrl() {
		return IMAGE_BASE_URL + videoId + "/mqdefault.jpg";
	}
	
	/**
	 * Image size: 480 x 360
	 * 
	 * @return High image thumbnail url
	 */
	public String getHighThumbnailImageUrl() {
		return IMAGE_BASE_URL + videoId + "/hqdefault.jpg";
	}
	
	/**
	 * Image size: 640 x 480
	 * 
	 * @return Stardard image thumbnail url
	 */
	public String getStandardThumbnailImageUrl() {
		return IMAGE_BASE_URL + videoId + "/sddefault.jpg";
	}
	
	/**
	 * Image size: 1280 x 720
	 * 
	 * @return Maximum resolution image thumbnail url, null if disabled
	 */
	public String getMaximumResolutionThumbnailImageUrl() {
		if (disabledResolutions[INDEX_RESOLUTION_MAXIMUM]) {
			return null;
		}
		
		return IMAGE_BASE_URL + videoId + "/maxresdefault.jpg";
	}
	
	/**
	 * @return Best image that can possibly be used
	 */
	public String getBestThumbnailImageUrl() {
		if (!disabledResolutions[INDEX_RESOLUTION_MAXIMUM]) {
			return getMaximumResolutionThumbnailImageUrl();
		}
		
		return getStandardThumbnailImageUrl();
	}
	
}
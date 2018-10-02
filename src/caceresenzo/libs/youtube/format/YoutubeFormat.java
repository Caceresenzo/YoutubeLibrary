package caceresenzo.libs.youtube.format;

import caceresenzo.libs.array.SparseArray;

public class YoutubeFormat {
	
	public static final int UNSPECIFIED_VIDEO_HEIGHT = -1;
	public static final int UNSPECIFIED_AUDIO_BITRATE = -1;
	
	public static final SparseArray<YoutubeFormat> FORMAT_MAP = new SparseArray<>();
	
	static {
		// http://en.wikipedia.org/wiki/YouTube#Quality_and_formats
		
		/* Video and Audio */
		FORMAT_MAP.put(17, new YoutubeFormat(17, "3gp", 144, VideoCodec.MPEG4, AudioCodec.AAC, 24, false));
		FORMAT_MAP.put(36, new YoutubeFormat(36, "3gp", 240, VideoCodec.MPEG4, AudioCodec.AAC, 32, false));
		FORMAT_MAP.put(5, new YoutubeFormat(5, "flv", 240, VideoCodec.H263, AudioCodec.MP3, 64, false));
		FORMAT_MAP.put(43, new YoutubeFormat(43, "webm", 360, VideoCodec.VP8, AudioCodec.VORBIS, 128, false));
		FORMAT_MAP.put(18, new YoutubeFormat(18, "mp4", 360, VideoCodec.H264, AudioCodec.AAC, 96, false));
		FORMAT_MAP.put(22, new YoutubeFormat(22, "mp4", 720, VideoCodec.H264, AudioCodec.AAC, 192, false));
		
		/* Dash Video */
		FORMAT_MAP.put(160, new YoutubeFormat(160, "mp4", 144, VideoCodec.H264, AudioCodec.NONE, true));
		FORMAT_MAP.put(133, new YoutubeFormat(133, "mp4", 240, VideoCodec.H264, AudioCodec.NONE, true));
		FORMAT_MAP.put(134, new YoutubeFormat(134, "mp4", 360, VideoCodec.H264, AudioCodec.NONE, true));
		FORMAT_MAP.put(135, new YoutubeFormat(135, "mp4", 480, VideoCodec.H264, AudioCodec.NONE, true));
		FORMAT_MAP.put(136, new YoutubeFormat(136, "mp4", 720, VideoCodec.H264, AudioCodec.NONE, true));
		FORMAT_MAP.put(137, new YoutubeFormat(137, "mp4", 1080, VideoCodec.H264, AudioCodec.NONE, true));
		FORMAT_MAP.put(264, new YoutubeFormat(264, "mp4", 1440, VideoCodec.H264, AudioCodec.NONE, true));
		FORMAT_MAP.put(266, new YoutubeFormat(266, "mp4", 2160, VideoCodec.H264, AudioCodec.NONE, true));
		
		FORMAT_MAP.put(298, new YoutubeFormat(298, "mp4", 720, VideoCodec.H264, 60, AudioCodec.NONE, true));
		FORMAT_MAP.put(299, new YoutubeFormat(299, "mp4", 1080, VideoCodec.H264, 60, AudioCodec.NONE, true));
		
		/* Dash Audio */
		FORMAT_MAP.put(140, new YoutubeFormat(140, "m4a", VideoCodec.NONE, AudioCodec.AAC, 128, true));
		FORMAT_MAP.put(141, new YoutubeFormat(141, "m4a", VideoCodec.NONE, AudioCodec.AAC, 256, true));
		
		/* WEBM Dash Video */
		FORMAT_MAP.put(278, new YoutubeFormat(278, "webm", 144, VideoCodec.VP9, AudioCodec.NONE, true));
		FORMAT_MAP.put(242, new YoutubeFormat(242, "webm", 240, VideoCodec.VP9, AudioCodec.NONE, true));
		FORMAT_MAP.put(243, new YoutubeFormat(243, "webm", 360, VideoCodec.VP9, AudioCodec.NONE, true));
		FORMAT_MAP.put(244, new YoutubeFormat(244, "webm", 480, VideoCodec.VP9, AudioCodec.NONE, true));
		FORMAT_MAP.put(247, new YoutubeFormat(247, "webm", 720, VideoCodec.VP9, AudioCodec.NONE, true));
		FORMAT_MAP.put(248, new YoutubeFormat(248, "webm", 1080, VideoCodec.VP9, AudioCodec.NONE, true));
		FORMAT_MAP.put(271, new YoutubeFormat(271, "webm", 1440, VideoCodec.VP9, AudioCodec.NONE, true));
		FORMAT_MAP.put(313, new YoutubeFormat(313, "webm", 2160, VideoCodec.VP9, AudioCodec.NONE, true));
		
		FORMAT_MAP.put(302, new YoutubeFormat(302, "webm", 720, VideoCodec.VP9, 60, AudioCodec.NONE, true));
		FORMAT_MAP.put(308, new YoutubeFormat(308, "webm", 1440, VideoCodec.VP9, 60, AudioCodec.NONE, true));
		FORMAT_MAP.put(303, new YoutubeFormat(303, "webm", 1080, VideoCodec.VP9, 60, AudioCodec.NONE, true));
		FORMAT_MAP.put(315, new YoutubeFormat(315, "webm", 2160, VideoCodec.VP9, 60, AudioCodec.NONE, true));
		
		/* WEBM Dash Audio */
		FORMAT_MAP.put(171, new YoutubeFormat(171, "webm", VideoCodec.NONE, AudioCodec.VORBIS, 128, true));
		
		FORMAT_MAP.put(249, new YoutubeFormat(249, "webm", VideoCodec.NONE, AudioCodec.OPUS, 48, true));
		FORMAT_MAP.put(250, new YoutubeFormat(250, "webm", VideoCodec.NONE, AudioCodec.OPUS, 64, true));
		FORMAT_MAP.put(251, new YoutubeFormat(251, "webm", VideoCodec.NONE, AudioCodec.OPUS, 160, true));
		
		/* HLS Live Stream */
		FORMAT_MAP.put(91, new YoutubeFormat(91, "mp4", 144, VideoCodec.H264, AudioCodec.AAC, 48, false, true));
		FORMAT_MAP.put(92, new YoutubeFormat(92, "mp4", 240, VideoCodec.H264, AudioCodec.AAC, 48, false, true));
		FORMAT_MAP.put(93, new YoutubeFormat(93, "mp4", 360, VideoCodec.H264, AudioCodec.AAC, 128, false, true));
		FORMAT_MAP.put(94, new YoutubeFormat(94, "mp4", 480, VideoCodec.H264, AudioCodec.AAC, 128, false, true));
		FORMAT_MAP.put(95, new YoutubeFormat(95, "mp4", 720, VideoCodec.H264, AudioCodec.AAC, 256, false, true));
		FORMAT_MAP.put(96, new YoutubeFormat(96, "mp4", 1080, VideoCodec.H264, AudioCodec.AAC, 256, false, true));
	}
	
	private String extension;
	private int itag, height, fps, audioBitrate;
	private VideoCodec videoCodec;
	private AudioCodec audioCodec;
	private boolean isDashContainer, isHlsContent;
	
	public YoutubeFormat(int itag, String extension, int height, VideoCodec videoCodec, AudioCodec audioCodec, boolean isDashContainer) {
		this(itag, extension, height, 30, videoCodec, audioCodec, UNSPECIFIED_AUDIO_BITRATE, isDashContainer, false);
	}
	
	public YoutubeFormat(int itag, String extension, VideoCodec videoCodec, AudioCodec audioCodec, int audioBitrate, boolean isDashContainer) {
		this(itag, extension, UNSPECIFIED_VIDEO_HEIGHT, 30, videoCodec, audioCodec, audioBitrate, isDashContainer, false);
	}
	
	public YoutubeFormat(int itag, String extension, int height, VideoCodec videoCodec, AudioCodec audioCodec, int audioBitrate, boolean isDashContainer) {
		this(itag, extension, height, 30, videoCodec, audioCodec, audioBitrate, isDashContainer, false);
	}
	
	public YoutubeFormat(int itag, String extension, int height, VideoCodec videoCodec, int fps, AudioCodec audioCodec, boolean isDashContainer) {
		this(itag, extension, height, fps, videoCodec, audioCodec, UNSPECIFIED_AUDIO_BITRATE, isDashContainer, false);
	}
	
	public YoutubeFormat(int itag, String extension, int height, VideoCodec videoCodec, AudioCodec audioCodec, int audioBitrate, boolean isDashContainer, boolean isHlsContent) {
		this(itag, extension, height, 30, videoCodec, audioCodec, audioBitrate, isDashContainer, isHlsContent);
	}
	
	public YoutubeFormat(int itag, String extension, int height, int fps, VideoCodec videoCodec, AudioCodec audioCodec, int audioBitrate, boolean isDashContainer, boolean isHlsContent) {
		this.itag = itag;
		this.extension = extension;
		this.height = height;
		this.fps = fps;
		this.videoCodec = videoCodec;
		this.audioCodec = audioCodec;
		this.audioBitrate = audioBitrate;
		this.isDashContainer = isDashContainer;
		this.isHlsContent = isHlsContent;
	}
	
	/**
	 * Get the frames per second
	 */
	public int getFps() {
		return fps;
	}
	
	/**
	 * Audio bitrate in kbit/s or -1 if there is no audio track.
	 */
	public int getAudioBitrate() {
		return audioBitrate;
	}
	
	/**
	 * An identifier used by youtube for different formats.
	 */
	public int getItag() {
		return itag;
	}
	
	/**
	 * The file extension and conainer format like "mp4"
	 */
	public String getExtension() {
		return extension;
	}
	
	public boolean isDashContainer() {
		return isDashContainer;
	}
	
	public AudioCodec getAudioCodec() {
		return audioCodec;
	}
	
	public VideoCodec getVideoCodec() {
		return videoCodec;
	}
	
	public boolean isHlsContent() {
		return isHlsContent;
	}
	
	/**
	 * The pixel height of the video stream or -1 for audio files.
	 */
	public int getHeight() {
		return height;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		
		YoutubeFormat format = (YoutubeFormat) other;
		
		if (itag != format.itag) {
			return false;
		}
		if (height != format.height) {
			return false;
		}
		if (fps != format.fps) {
			return false;
		}
		if (audioBitrate != format.audioBitrate) {
			return false;
		}
		if (isDashContainer != format.isDashContainer) {
			return false;
		}
		if (isHlsContent != format.isHlsContent) {
			return false;
		}
		if (extension != null ? !extension.equals(format.extension) : format.extension != null) {
			return false;
		}
		if (videoCodec != format.videoCodec) {
			return false;
		}
		
		return audioCodec == format.audioCodec;
	}
	
	@Override
	public int hashCode() {
		int result = itag;
		result = 31 * result + (extension != null ? extension.hashCode() : 0);
		result = 31 * result + height;
		result = 31 * result + fps;
		result = 31 * result + (videoCodec != null ? videoCodec.hashCode() : 0);
		result = 31 * result + (audioCodec != null ? audioCodec.hashCode() : 0);
		result = 31 * result + audioBitrate;
		result = 31 * result + (isDashContainer ? 1 : 0);
		result = 31 * result + (isHlsContent ? 1 : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return "YoutubeFormat[extension=" + extension + ", itag=" + itag + ", height=" + height + ", fps=" + fps + ", audioBitrate=" + audioBitrate + ", videoCodec=" + videoCodec + ", audioCodec=" + audioCodec + ", isDashContainer=" + isDashContainer + ", isHlsContent=" + isHlsContent + "]";
	}
	
}